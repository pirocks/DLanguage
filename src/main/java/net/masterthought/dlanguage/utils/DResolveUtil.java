package net.masterthought.dlanguage.utils;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiNamedElement;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.stubs.Stub;
import com.intellij.psi.stubs.StubIndex;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.containers.ContainerUtil;
import net.masterthought.dlanguage.index.DModuleIndex;
import net.masterthought.dlanguage.psi.*;
import net.masterthought.dlanguage.psi.interfaces.DNamedElement;
import net.masterthought.dlanguage.psi.interfaces.Declaration;
import net.masterthought.dlanguage.stubs.*;
import net.masterthought.dlanguage.stubs.index.DTopLevelDeclarationIndex;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

/**
 * Created by francis on 5/12/17.
 */
public class DResolveUtil {

//    public static class DReferenceProcessor implements PsiScopeProcessor{
//        public Set<Declaration> declarations = new HashSet<>();
//        @Override
//        public boolean execute(@NotNull PsiElement element, @NotNull ResolveState state) {
//            return true;
//        }
//
//        @Nullable
//        @Override
//        public <T> T getHint(@NotNull Key<T> hintKey) {
//            return null;
//        }
//
//        @Override
//        public void handleEvent(@NotNull Event event, @Nullable Object associated) {
//
//        }
//    }

    /**
     * Finds name definition across all Haskell files in the project. All
     * definitions are found when name is null.
     */
    @NotNull
    public static List<PsiNamedElement> findDefinitionNode(@NotNull Project project, @Nullable String name, @Nullable PsiNamedElement e) {
        // Guess where the name could be defined by lookup up potential modules.
        final Set<String> potentialModules =
            e == null ? Collections.EMPTY_SET
                : DPsiUtil.parseImports(e.getContainingFile());

        List<PsiNamedElement> result = ContainerUtil.newArrayList();
        final PsiFile psiFile = e == null ? null : e.getContainingFile().getOriginalFile();
        // find definition in current file
        if (psiFile instanceof DLanguageFile) {
            findDefinitionNode((DLanguageFile) psiFile, name, e, result);
        }
        // find definition in imported files
        for (String potentialModule : potentialModules) {
            List<DLanguageFile> files = DModuleIndex.getFilesByModuleName(project, potentialModule, GlobalSearchScope.allScope(project));
            for (DLanguageFile f : files) {
                final boolean returnAllReferences = name == null;
                final boolean inLocalModule = f != null && f.equals(psiFile);
                final boolean inImportedModule = f != null && potentialModules.contains(f.getModuleName());
                if (returnAllReferences || inLocalModule || inImportedModule) {
                    findDefinitionNode(f, name, e, result);
                }
            }
        }
        return result;
    }

    public static boolean fileTreeWalkUp(String name, DLanguageIdentifier start, Set<Declaration> declarations, Stack<DLanguageImportDeclarationStub> imports) {
        return fileTreeWalkUpImpl(start.getName(), start, declarations, imports);
    }

    private static boolean fileTreeWalkUpImpl(String name, DLanguageIdentifier start, Set<Declaration> declarations, Stack<DLanguageImportDeclarationStub> imports) {
        Stub currentBranch = start.getStub();
        while (processStubTreeBranch(currentBranch, declarations, imports)) {
            currentBranch.getParentStub();
        }
        return false;
    }

    private static boolean processStubTreeBranch(Stub stub, Set<Declaration> declarations, Stack<DLanguageImportDeclarationStub> imports) {
        //check params
        //do for struct,class,template, constructor
        //check for *.*
        //for performance args should be stored with stubs
        if (stub instanceof DLanguageFuncDeclarationStub) {
            final List<DLanguageStatement> statementList = ((DLanguageFuncDeclarationStub) stub).getPsi().getFunctionBody().getBlockStatement().getStatementList().getStatementList();//check local vars
            final List<DLanguageParameter> arguments = ((DLanguageFuncDeclarationStub) stub).getPsi().getArguments();
            final List<DLanguageTemplateParameter> templateArguments = ((DLanguageFuncDeclarationStub) stub).getPsi().getTemplateArguments();//?
        }
        if (stub instanceof DLanguageConstructorStub) {
            final List<DLanguageParameter> arguments = ((DLanguageConstructorStub) stub).getPsi().getArguments();
            final List<DLanguageTemplateParameter> templateArguments = ((DLanguageConstructorStub) stub).getPsi().getTemplateParametersList();//?
        }
        if (stub instanceof DLanguageTemplateDeclarationStub) {
            final List<DLanguageDeclDef> declDefList = ((DLanguageTemplateDeclarationStub) stub).getPsi().getDeclDefs().getDeclDefList();//check local vars
            final List<DLanguageTemplateParameter> templateArguments = ((DLanguageTemplateDeclarationStub) stub).getPsi().getTemplateParametersList();//?
        }
        if (stub instanceof DLanguageClassDeclarationStub) {
            final List<DLanguageDeclDef> declDefList = ((DLanguageClassDeclarationStub) stub).getPsi().getAggregateBody().getDeclDefs().getDeclDefList();//check local vars
            final List<DLanguageTemplateParameter> templateArguments = ((DLanguageClassDeclarationStub) stub).getPsi().getTemplateParametersList();//?
        }
        if (stub instanceof DLanguageStructDeclarationStub) {
            final List<DLanguageDeclDef> declDefList = ((DLanguageStructDeclarationStub) stub).getPsi().getAggregateBody().getDeclDefs().getDeclDefList();//check local vars
            final List<DLanguageTemplateParameter> templateArguments = ((DLanguageStructDeclarationStub) stub).getPsi().getTemplateParametersList();//?
        }
        for (Stub child : stub.getChildrenStubs()) {
            if (child instanceof DLanguageImportDeclarationStub) {
                imports.add((DLanguageImportDeclarationStub) child);//add to imports
            }
        }
        return true;
    }

    public static int numArgs(DLanguageIdentifier functionCall) {
        //up into assign expression, down into parameter list
        final DLanguageAssignExpression parentExpression = PsiTreeUtil.getParentOfType(functionCall, DLanguageAssignExpression.class);
//        parentExpression;
        final Collection<DLanguageParameters> childrenOfType = PsiTreeUtil.findChildrenOfType(parentExpression, DLanguageParameters.class);

        List<DLanguageParameter> parameterList;
        //this loop is subtle
        for (DLanguageParameters dLanguageParameters : childrenOfType) {
            parameterList = dLanguageParameters.getParameterList().getParameterList();
//            if(dLanguageParameters.getParent() instanceof
        }

//        final List<DLanguagePostfixExpression> argExpressions = parentExpression.getPostfixExpressionList();//could be multiple postfix expressions following
//        final DLanguagePostfixExpression regularArgs = argExpressions.get(argExpressions.size() - 1);//not template args etc
//        final List<DLanguageParameter> parameterList = regularArgs.getPrimaryExpression().getFunctionLiteral().getParameterMemberAttributes().getParameters().getParameterList().getParameterList();
//        return parameterList.size();
        return 0;
    }


    public static Set<Declaration> fromFile(DLanguageFile file) {
        Set<Declaration> res = new HashSet<>();
        for (String key : StubIndex.getInstance().getAllKeys(DTopLevelDeclarationIndex.KEY, file.getProject())) {
            res.addAll(StubIndex.getElements(DTopLevelDeclarationIndex.KEY, key, file.getProject(), GlobalSearchScope.fileScope(file), Declaration.class));
        }
        return res;
    }

    public static Set<Declaration> fromFile(String name, DLanguageFile file) {

        return new HashSet<Declaration>() {{
            addAll(StubIndex.getElements(DTopLevelDeclarationIndex.KEY, name, file.getProject(), GlobalSearchScope.fileScope(file), Declaration.class));
        }};
    }

    /**
     * finds definition(s) of functions/class/template
     * todo this method could be made more efficient and effective. Use a stub tree?
     *
     * @param file   the file to search for definitions in
     * @param name   the name of the function/class/template to resolve
     * @param e      the usage of the defined function/class/template etc.
     * @param result the results are added to the is arraylist
     */
    public static void findDefinitionNode(@Nullable DLanguageFile file, @Nullable String name, @Nullable PsiNamedElement e, @NotNull List<PsiNamedElement> result) {
        if (file == null) return;
        // start with empty list of potential named elements
        Collection<DNamedElement> declarationElements = new ArrayList<>();

        if (e instanceof DLanguageIdentifier) {

            List<Declaration> declarations = new ArrayList<>();
            final Collection<Declaration> elements = StubIndex.getElements(DTopLevelDeclarationIndex.KEY, e.getName(), e.getProject(), GlobalSearchScope.fileScope(file), Declaration.class);
            for (Declaration element : elements) {
                if (element != null) {
                    declarations.add(element);
                }
            }

            for (DNamedElement candidateDeclaration : declarations) {
                if (candidateDeclaration instanceof DLanguageAutoDeclarationY) {
                    if (((DLanguageAutoDeclarationY) candidateDeclaration).actuallyIsDeclaration()) {
                        declarationElements.add(candidateDeclaration);
                    }
                    continue;
                }
                declarationElements.add(candidateDeclaration);
            }
        }

        boolean resolvingConstructor = false;

        PsiElement parent = e.getParent();
        while (true) {
            if (parent == null)
                break;
            if (parent instanceof DLanguageNewExpression || parent instanceof DLanguageNewExpressionWithArgs)
                resolvingConstructor = true;
            parent = parent.getParent();
        }

        // check the list of potential named elements for a match on name
        for (DNamedElement namedElement : declarationElements) {
            //non void initializer
            if (resolvingConstructor) {
                if (namedElement instanceof DLanguageConstructor) {
                    DLanguageConstructor constructor = (DLanguageConstructor) namedElement;
                    result.add(constructor);
                }
            } else if (name == null || (name.equals(namedElement.getName()) && !(e.equals(namedElement)) && !(namedElement instanceof DLanguageConstructor))) {
                result.add(namedElement);
            }
        }
    }

    /**
     * Finds a name definition inside a D file. All definitions are found when name
     * is null.
     */
    @NotNull
    public static List<PsiNamedElement> findDefinitionNodes(@Nullable DLanguageFile dLanguageFile, @Nullable String name) {
        List<PsiNamedElement> ret = ContainerUtil.newArrayList();
        findDefinitionNode(dLanguageFile, name, null, ret);
        return ret;
    }

    /**
     * Finds name definition across all D files in the project. All
     * definitions are found when name is null.
     */
    @NotNull
    public static List<PsiNamedElement> findDefinitionNodes(@NotNull Project project) {
        return findDefinitionNode(project, null, null);
    }

    /**
     * Finds name definitions that are within the scope of a file, including imports (to some degree).
     */
    @NotNull
    public static List<PsiNamedElement> findDefinitionNodes(@NotNull DLanguageFile psiFile) {
        List<PsiNamedElement> result = findDefinitionNodes(psiFile, null);
        result.addAll(findDefinitionNode(psiFile.getProject(), null, null));
        return result;
    }
}
