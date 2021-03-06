package net.masterthought.dlanguage.utils;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.search.GlobalSearchScope;
import net.masterthought.dlanguage.psi.*;
import net.masterthought.dlanguage.psi.interfaces.DNamedElement;
import net.masterthought.dlanguage.psi.interfaces.containers.ConstructorContainer;
import net.masterthought.dlanguage.psi.interfaces.containers.Container;
import net.masterthought.dlanguage.psi.interfaces.containers.ContainerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.intellij.psi.util.PsiTreeUtil.findChildOfType;
import static com.intellij.psi.util.PsiTreeUtil.getParentOfType;
import static java.util.Collections.EMPTY_SET;
import static net.masterthought.dlanguage.index.DModuleIndex.getFilesByModuleName;
import static net.masterthought.dlanguage.psi.interfaces.containers.ContainerUtil.getContainedDeclarationsWithPlaceHolders;
import static net.masterthought.dlanguage.utils.PlaceHolderUtils.fillPlaceHolders;

/**
 * so instead of doing some recursive complicated garbage this should work as follows:
 * getDeclarationsVisibleFromElement, collects all declarations at the element in question, and whenever it hits upon
 * inheritance or a mixin, it puts a placeholder in its place and continues. When done, it loops over and fills in the placeholders.
 * for mixins, the mixinable should already be in the collection of declarations, so simple take the contents of the mixinable
 * and add its declarations. For inherited classes the inherited symbols are first represented by a placeholder. The placeholder
 *  is filled by first checking if a matching declaration has already been found, and if not resolving one.
 *
 *
 * todo when constructor resolution fails, the class/struct should be resolved instead
 * todo match arguments to resolve overloaded functions
 * todo improve performance somehow -- stub index/file index for declarations?
 * todo create much more expansive unittests
 * todo when returning a result resolve the identifier not the entire declaration, however this may mess with some internal code in this file and in the ContainerUtil file
 * todo make induvidual symbol imports work
 * Created by franc on 1/18/2017.
 */
public class DResolveUtil {
    static Logger log = Logger.getInstance(DResolveUtil.class);

    public static
    @NotNull
    Set<? extends DNamedElement> findDefinitionNodes(@NotNull DNamedElement element) {
        if (!(element instanceof DLanguageIdentifier))
            return EMPTY_SET;//prevent resolving definitions
        return findDefinitionNodes((DLanguageFile) element.getContainingFile(), (DLanguageIdentifier) element);

    }

    private static
    @NotNull
    Set<? extends DNamedElement> findDefinitionNodes(DLanguageFile dLanguageFile, DLanguageIdentifier element) {
        //seven major possibilities:
        //1. identifier is part of import statement, and should resolve to a file
        //2. identifier is a normal function/class name/etc..
        //  2.a.overloaded functions, specifically resolving the correct overloaded functions
        //3. identifier is a constructor/destructor usage
        //  3.a.overloaded constructors, specifically resolving the correct overloaded functions
        //4. resolving something referred to with its full name eg. foo.bar.something();
        //5. resolving a member function/var of a class/struct/template etc...
        //  ex: Class a =new Class(); a.foo(); //resolving foo
        final PsiElement parent = element.getParent();
        //identifiers within templates break this approach
        if (!dLanguageFile.getText().contains(element.getText()) &&
            getParentOfType(parent, DLanguageImportDeclaration.class) == null)
            return Collections.emptySet();//optimization to prevent unnecessary searching
        if (getParentOfType(parent, DLanguageImportDeclaration.class) != null) {
            //#1 is true
            return findModuleDefinitionNodes(getParentOfType(element, DLanguageModuleFullyQualifiedName.class));
        }
        if (parent.getText().contains(".") && !parent.getText().contains("{") | parent instanceof DLanguageModuleFullyQualifiedName
//            parent instanceof DLanguageUnaryExpression ||
//            parent instanceof DLanguageIdentifierList ||
//            parent instanceof DLanguagePostfixExpression ||
//            parent instanceof DLanguageModuleFullyQualifiedName ||
//            parent instanceof DLanguagePrimaryExpression ||
//            parent instanceof DLanguageDotIdentifier ||
//            parent instanceof DLanguageSymbol ||
//            parent instanceof DLanguageSymbolTail ||
//            parent instanceof DLanguageMixinTemplateName ||
//            parent instanceof DLanguageQualifiedIdentifierList
            ) {
            //#4 or #5 are true
            //to distinguish between #4 and #5 resolve the rightmost identifier. eg. in foo.bar.gh(), find foo and resolve it
            return findDefinitionNodesStandard(element);
            //the identifier is within one of the following:
            // IdentifierList
            // UnaryExpression
            // PostfixExpression
            // PrimaryExpression
            // DotIdentifier
            // Symbol
            // SymbolTail
            // MixinTemplateName
            // QualifiedIdentifierList

        } else {
            //#2 or 3
            PsiElement current = parent;
            while (true) {
                if (current instanceof DLanguageNewExpression || current instanceof DLanguageNewExpressionWithArgs) {
                    //#3 is true
                    return findConstructorDefinitionNodes(element, current);
                }
                if (current instanceof DLanguageDeleteExpression) {
                    break;//todo resolving destructors?
                }
                if (current instanceof DLanguageCastExpression) {
                    //#2 is true
                    break;
                    // specifically a class,alias or struct
                }
                if (current instanceof DLanguageUnaryExpression) {
                    //#2 is true
                    break;
                }
                if (current instanceof DLanguageTypeidExpression) {
                    //#2 is true
                    break;
                }
                if (current instanceof DLanguageIsExpression) {
                    //#2 is true
                    break;
                }
                if (current instanceof DLanguageTraitsArgument) {
                    //#2 is true
                    break;
                }
                if (current == null)
                    break;
                current = current.getParent();
            }
            //default to #2:
            return findDefinitionNodesStandard(element);
        }
    }

    private static
    @NotNull
    Set<DNamedElement> findConstructorDefinitionNodes(@NotNull DLanguageIdentifier element, @NotNull PsiElement newExpression) {
        Set<DNamedElement> res = new HashSet<>();
        if (newExpression instanceof DLanguageNewExpression || newExpression instanceof DLanguageNewExpressionWithArgs) {
            for (DNamedElement dNamedElement : findDefinitionNodesStandard(element)) {
                if (dNamedElement instanceof ConstructorContainer) {
                    final Set<DNamedElement> memberDeclarations = fillPlaceHolders(ContainerUtil.getContainedDeclarationsWithPlaceHolders((Container) dNamedElement));
                    for (DNamedElement memberDeclaration : memberDeclarations) {
                        if (memberDeclaration instanceof DLanguageConstructor)
                            res.add(memberDeclaration);
                    }

                }
            }
        } else {
            throw new IllegalArgumentException("new expression must contain a new expression");
        }
        return res;
    }

    /**
     * for resolving anything that isn't a constructor and doesn't have a longform name like foo.bar.gh()
     *
     * @param element
     * @return
     */
    private static
    @NotNull
    Set<DNamedElement> findDefinitionNodesStandard(DLanguageIdentifier element) {
        Set<DNamedElement> res = new HashSet<>();
        if (element.getParent() instanceof DLanguageModuleFullyQualifiedName) {
            return EMPTY_SET;
        }
        for (DNamedElement dNamedElement : getDeclarationsVisibleFromElement(element, element.getParentContainer())) {
            if (dNamedElement.getName() != null && longNamesAreReferringToSameThing(dNamedElement.getFullName(), element.getName()) && !(dNamedElement instanceof DLanguageModuleDeclaration) && dNamedElement.getName().equals(element.getName())) {
                res.add(dNamedElement);
            }
        }
        return res;
    }

    /**
     * this method does most of the work for resolve and for completion.
     * it finds all declarations visible from an element
     * @param element         element being resolved if relevant. The element from which declarations are visible
     * @return declarations visible from element
     */
    @NotNull
    public static Set<DNamedElement> getDeclarationsVisibleFromElement(@NotNull DLanguageIdentifier element) {
        return getDeclarationsVisibleFromElement(element, element.getParentContainer());
    }

    @NotNull
    private static Set<DNamedElement> getDeclarationsVisibleFromElement(@Nullable DLanguageIdentifier element, Container parentContainer) {
        final Set<DNamedElement> withPlaceHolders = getVisibleElementsWithPlaceHolders(element, parentContainer);
        return fillPlaceHolders(withPlaceHolders);
    }

    @NotNull
    static Set<DNamedElement> getVisibleElementsWithPlaceHolders(DLanguageIdentifier element, Container parentContainer) {
        Set<DNamedElement> declarations = new HashSet<>();

        if (parentContainer instanceof DLanguageFile) {
            declarations.addAll(getContainedDeclarationsWithPlaceHolders(parentContainer));
            return declarations;//don't keep searching up after leaving the file
        }
        //add the declarations from here
        declarations.addAll(getContainedDeclarationsWithPlaceHolders(parentContainer));
        declarations.addAll(getVisibleElementsWithPlaceHolders(element, parentContainer.getParentContainer()));
        return declarations;
    }

    public static Set<DNamedElement> findModuleDefinitionNodes(DLanguageModuleFullyQualifiedName module) {
        Set<DNamedElement> res = new HashSet<>();
        for (DLanguageFile dLanguageFile : fromModulesToFiles(module.getProject(), Collections.singleton(module.getText()))) {
            if (findChildOfType(dLanguageFile, DLanguageModuleDeclaration.class) != null) {
                res.add(findChildOfType(dLanguageFile, DLanguageModuleDeclaration.class));
            } else {
                res.add(dLanguageFile);
            }
        }
        return res;
    }

    public static Set<DLanguageFile> fromModulesToFiles(Project project, Set<String> modules) {
        Set<DLanguageFile> filesFound = new HashSet<>();
        for (String module : modules) {
            List<DLanguageFile> files = getFilesByModuleName(project, module, GlobalSearchScope.allScope(project));
            filesFound.addAll(files);
        }
        return filesFound;
    }

    public static boolean longNamesAreReferringToSameThing(String longName, String shorterName) {
        final int i = longName.lastIndexOf(shorterName);
        if (i < 0)
            return false;
        return i == longName.length() - shorterName.length();
    }
}
