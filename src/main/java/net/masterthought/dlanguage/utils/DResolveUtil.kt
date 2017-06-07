package net.masterthought.dlanguage.utils

import com.intellij.openapi.project.Project
import com.intellij.psi.PsiDirectory
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiNamedElement
import com.intellij.psi.impl.source.PsiFileImpl
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.psi.stubs.Stub
import com.intellij.psi.stubs.StubIndex
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.psi.util.PsiTreeUtil.findChildrenOfType
import net.masterthought.dlanguage.index.DModuleIndex
import net.masterthought.dlanguage.psi.*
import net.masterthought.dlanguage.psi.impl.DLangIdentifierImpl
import net.masterthought.dlanguage.psi.interfaces.DNamedElement
import net.masterthought.dlanguage.psi.interfaces.Declaration
import net.masterthought.dlanguage.psi.references.DReference
import net.masterthought.dlanguage.stubs.DLangImportDeclStub
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
        stubTreeCheck(location)
        var stub: Stub = (location as DLangIdentifierImpl).greenStub!!
        val imports: MutableSet<Import> = mutableSetOf()
        while (true) {
            for (child in stub.childrenStubs) {
                if (child is DLangImportDeclStub) {
                    imports.add(Import(child.psi))
                }
            }
            if (stub.parentStub == null) {
                return imports
            }
            stub = stub.parentStub
        }
    }

    private fun stubTreeCheck(location: DLangIdentifier) {
        if ((location as DLangIdentifierImpl).greenStub == null) {
            (location.containingFile as PsiFileImpl).calcStubTree()
            assert((location.containingFile as DLangFile).greenStub != null)
        }
    }

    fun resolveThroughLocalScope(identifier: DLangIdentifier): DNamedElement? {
        val scopeDelimiter = listOf(DLangFuncDeclaration::class.java, DLangForeachStatement::class.java, DLangWhileStatement::class.java, DLangForStatement::class.java, DLangDoStatement::class.java, DLangIfStatement::class.java, DLangBlockStatement::class.java, DLangSwitchStatement::class.java, DLangFinalSwitchStatement::class.java, DLangWithStatement::class.java, DLangSynchronizedStatement::class.java, DLangTryStatement::class.java, DLangForeachRangeStatement::class.java, DLangConditionalStatement::class.java, DLangFunctionBody::class.java, DLangClassDeclaration::class.java, DLangTemplateDeclaration::class.java, DLangStructDeclaration::class.java, DLangTemplateMixinDeclaration::class.java)


        val orderMatters = listOf(DLangFuncDeclaration::class.java, DLangUnitTesting::class.java, DLangForeachStatement::class.java, DLangWhileStatement::class.java, DLangForStatement::class.java, DLangDoStatement::class.java, DLangIfStatement::class.java, DLangBlockStatement::class.java, DLangSwitchStatement::class.java, DLangFinalSwitchStatement::class.java, DLangWithStatement::class.java, DLangSynchronizedStatement::class.java, DLangTryStatement::class.java, DLangForeachRangeStatement::class.java, DLangConditionalStatement::class.java, DLangFunctionBody::class.java)
        val orderDoesNotMatter = listOf(DLangClassDeclaration::class.java, DLangTemplateDeclaration::class.java, DLangStructDeclaration::class.java, DLangTemplateMixinDeclaration::class.java)
        fun declarationOrderMatters(element: PsiElement): Boolean {
            for (clazz in orderMatters) {
                if (clazz.isInstance(element)) {
                    return true
                }
            }
            return false
        }
        fun scopeElement(element: PsiElement): Boolean {
            for (clazz in orderMatters) {
                if (clazz.isInstance(element)) {
                    return true
                }
            }
            for (clazz in orderDoesNotMatter) {
                if (clazz.isInstance(element)) {
                    return true
                }
            }
            return false
        }
        fun getParents(element: PsiElement): List<PsiElement> {
            val parents = mutableListOf<PsiElement>()
            var currentElement = element
            while (true) {
                if (currentElement.parent == null || currentElement is PsiDirectory) {
                    break
                }
                currentElement = currentElement.parent
                parents.add(currentElement)
            }
            return parents

        }
        stubTreeCheck(identifier)
        var current: PsiElement = identifier
        val parents = getParents(current)
        while (true) {
            if (scopeElement(current)) {
                fun getChildrenDeclarations(element: PsiElement) {
                    val declarations: MutableList<Declaration> = mutableListOf()
                    for (declaration in findChildrenOfType(element, Declaration::class.java)) {
                        declarations.add(declaration)
                    }
                }
            }
            if (current is DLangFile || current.parent == null) {//todo simplify
                return null
            }
            current = current.parent
        }
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
}
