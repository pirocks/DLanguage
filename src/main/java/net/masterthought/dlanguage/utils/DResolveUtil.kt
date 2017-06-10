package net.masterthought.dlanguage.utils

import com.intellij.openapi.project.Project
import com.intellij.openapi.util.Key
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiNamedElement
import com.intellij.psi.ResolveState
import com.intellij.psi.impl.source.PsiFileImpl
import com.intellij.psi.scope.PsiScopeProcessor
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.psi.stubs.StubIndex
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.psi.util.PsiTreeUtil.findChildrenOfType
import net.masterthought.dlanguage.index.DModuleIndex
import net.masterthought.dlanguage.psi.*
import net.masterthought.dlanguage.psi.impl.DLangIdentifierImpl
import net.masterthought.dlanguage.psi.interfaces.DNamedElement
import net.masterthought.dlanguage.psi.interfaces.Declaration
import net.masterthought.dlanguage.psi.interfaces.VariableDeclaration
import net.masterthought.dlanguage.psi.references.DReference
import net.masterthought.dlanguage.stubs.index.DTopLevelDeclarationIndex

/**
 * Created by francis on 5/12/17.
 */
object DResolveUtil {

    class Import(val import: DLangImport) {

        fun isScoped(): Boolean {
            val parent: DLangImportList = import.parent as DLangImportList
            return parent.importBindList != null
        }

        fun getScopedSymbolNames(): MutableSet<String> {
            val names: MutableSet<String> = mutableSetOf<String>()
            val dLangImportList = import.parent as DLangImportList
            val importBinds = findChildrenOfType(dLangImportList.importBindList, DLangImportBind::class.java)
            for (importBind in importBinds) {
                names.add(importBind.identifierList[0].name)
            }
            return names
        }

        /**
         * in theory a module could be from more than one file
         */
        fun getFiles(): MutableSet<DLangFile> {
            val files: MutableSet<DLangFile> = mutableSetOf()
            val reference: DReference = DUtil.getEndOfIdentifierList(import.moduleFullyQualifiedName).reference as DReference
            for (resolveResult in reference.multiResolve(false)) {
                files.add(resolveResult.element?.containingFile as DLangFile)
            }
            return files
        }

    }

    fun getImportedFromLocation(location: DLangIdentifier): MutableSet<Import> {
        class ImportScopeProcessor(val start: DLangIdentifier) : PsiScopeProcessor {
            val result: MutableSet<Import> = mutableSetOf()

            override fun handleEvent(event: PsiScopeProcessor.Event, associated: Any?) {
                return
            }

            override fun <T : Any?> getHint(hintKey: Key<T>): T? {
                return null
            }

            override fun execute(element: PsiElement, state: ResolveState): Boolean {
                if (element is DLangImport) {
                    result.add(Import(element))
                }
                if (element is DLangImportDeclaration) {
                    for (dLangImport in findChildrenOfType(element, DLangImport::class.java)) {
                        result.add(Import(dLangImport))
                    }
                }
                return true
            }
        }

        val importScopeProcessor = ImportScopeProcessor(location)
        PsiTreeUtil.treeWalkUp(importScopeProcessor, location, location.containingFile.containingDirectory, ResolveState.initial())
        return importScopeProcessor.result
    }

    private fun stubTreeCheck(location: DLangIdentifier) {
        if ((location as DLangIdentifierImpl).greenStub == null) {
            (location.containingFile as PsiFileImpl).calcStubTree()
            assert((location.containingFile as DLangFile).greenStub != null)
        }
    }

    fun resolveThroughLocalScope(identifier: DLangIdentifier): DNamedElement? {
        class LocalScopeResolveProcessor(val start: DLangIdentifier) : PsiScopeProcessor {
            var result: DNamedElement? = null

            override fun handleEvent(event: PsiScopeProcessor.Event, associated: Any?) {
                return
            }

            override fun <T : Any?> getHint(hintKey: Key<T>): T? {
                return null
            }

            override fun execute(element: PsiElement, state: ResolveState): Boolean {
                if (element is DNamedElement) {
                    if (element is VariableDeclaration) {
                        if (!element.actuallyIsDeclaration()) {
                            return true
                        }
                    }
                    if (element.name.equals(start.name)) {
                        result = element
                        return false
                    }
                }
                return true
            }
        }

        val resolveProcessor = LocalScopeResolveProcessor(identifier)
        PsiTreeUtil.treeWalkUp(resolveProcessor, identifier, identifier.containingFile.containingDirectory, ResolveState.initial())
        return resolveProcessor.result
    }


    fun inModuleName(start: DLangIdentifier): Boolean {
        val moduleFullyQualifiedName = PsiTreeUtil.getTopmostParentOfType(start, DLangModuleFullyQualifiedName::class.java)
        return moduleFullyQualifiedName != null
    }

    /**
     * Finds name definition across all Haskell files in the project. All
     * definitions are found when name is null.
     */
    fun findDefinitionNode(project: Project, e: DLangIdentifier): List<PsiNamedElement> {
        val inModuleName: Boolean = inModuleName(e)
        if (inModuleName) {
            val name = PsiTreeUtil.getTopmostParentOfType(e, DLangModuleFullyQualifiedName::class.java)!!.text
            val files = DModuleIndex.getFilesByModuleName(project, name, GlobalSearchScope.allScope(project))
            return files.toList()
        }

        val resolveThroughLocalScope = resolveThroughLocalScope(e)
        if (resolveThroughLocalScope != null)
            return listOf(resolveThroughLocalScope)
        val result: MutableList<Declaration> = mutableListOf()
        for (import in getImportedFromLocation(e)) {
            for (file in import.getFiles()) {
                result.addAll(StubIndex.getInstance().get(DTopLevelDeclarationIndex.KEY, e.name, e.project, GlobalSearchScope.fileScope(file)))
            }
        }
        return result


    }

//        // Guess where the name could be defined by lookup up potential modules.
//        val potentialModules = if (e == null)
//            Collections.emptySet<String>();
//        else
//            DPsiUtil.parseImports(e.containingFile)
//
//        val result = ContainerUtil.newArrayList<PsiNamedElement>()
//        val psiFile = if (e == null) null else e.containingFile.originalFile
//        // find definition in current file
//        if (psiFile is DLangFile) {
//            findDefinitionNode(psiFile as DLangFile?, name, e, result)
//        }
//        // find definition in imported files
//        for (potentialModule in potentialModules) {
//            val files = DModuleIndex.getFilesByModuleName(project, potentialModule, GlobalSearchScope.allScope(project))
//            for (f in files) {
//                val returnAllReferences = name == null
//                val inLocalModule = f != null && f == psiFile
//                val inImportedModule = f != null && potentialModules.contains(f.moduleName)
//                if (returnAllReferences || inLocalModule || inImportedModule) {
//                    findDefinitionNode(f, name, e, result)
//                }
//            }
//        }
//        return result
}


/**
 * finds definition(s) of functions/class/template
 * @param file the file to search for definitions in
 * *
 * @param name the name of the function/class/template to resolve
 * *
 * @param e the usage of the defined function/class/template etc.
 * *
 * @param result the results are added to the is arraylist
 */
//    fun findDefinitionNode(file: DLangFile?, name: String?, e: PsiNamedElement?, result: MutableList<PsiNamedElement>) {
//        if (file == null) return
//        // start with empty list of potential named elements
//        val declarationElements = ArrayList<DNamedElement>()
//
//        if (e is DLangIdentifier) {
//
//            val declarations = ArrayList<Declaration>()
//            //            final Collection<DNamedElement> elements = StubIndex.getElements(DAllNameIndex.KEY, e.getName(), e.getProject(), GlobalSearchScope.fileScope(file), DNamedElement.class);
//            val elements = StubIndex.getInstance().get(DAllNameIndex.KEY, e.name, e.project, GlobalSearchScope.fileScope(file))
//            for (element in elements) {
//                if (element is Declaration) {
//                    declarations.add(element)
//                }
//            }
//
//            for (candidateDeclaration in declarations) {
//                if (candidateDeclaration is DLangAutoDeclarationY) {
//                    if (candidateDeclaration.actuallyIsDeclaration()) {
//                        declarationElements.add(candidateDeclaration)
//                    }
//                    continue
//                }
//                declarationElements.add(candidateDeclaration)
//            }
//        }
//
//        var resolvingConstructor = false
//
//        var parent: PsiElement? = e!!.parent
//        while (true) {
//            if (parent == null)
//                break
//            if (parent is DLangNewExpression || parent is DLangNewExpressionWithArgs)
//                resolvingConstructor = true
//            parent = parent.parent
//        }
//
//        // check the list of potential named elements for a match on name
//        for (namedElement in declarationElements) {
//            //non void initializer
//            if (resolvingConstructor) {
//                if (namedElement is DLangConstructor) {
//                    result.add(namedElement)
//                }
//            } else if (name == null || name == namedElement.name && e != namedElement && namedElement !is DLangConstructor) {
//                result.add(namedElement)
//            }
//        }
//    }

/**
 * Finds a name definition inside a D file. All definitions are found when name
 * is null.
 */
//    fun findDefinitionNodes(dLangFile: DLangFile?, name: String?): MutableList<PsiNamedElement> {
//        val ret = ContainerUtil.newArrayList<PsiNamedElement>()
//        findDefinitionNode(dLangFile, name, null, ret)
//        return ret
//    }

/**
 * Finds name definition across all D files in the project. All
 * definitions are found when name is null.
 */
//    fun findDefinitionNodes(project: Project): List<PsiNamedElement> {
//        return findDefinitionNode(project, null)
//    }

/**
 * Finds name definitions that are within the scope of a file, including imports (to some degree).
 */
//    fun findDefinitionNodes(psiFile: DLangFile): List<PsiNamedElement> {
//        val result = findDefinitionNodes(psiFile, null)
//        result.addAll(findDefinitionNode(psiFile.project, null))
//        return result
//    }

