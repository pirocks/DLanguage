package net.masterthought.dlanguage.psi.references;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.IncorrectOperationException;
import net.masterthought.dlanguage.psi.DLanguageConstructor;
import net.masterthought.dlanguage.psi.DLanguageFile;
import net.masterthought.dlanguage.psi.DLanguageFuncDeclaration;
import net.masterthought.dlanguage.psi.DLanguageIdentifier;
import net.masterthought.dlanguage.psi.impl.DPsiImplUtil;
import net.masterthought.dlanguage.psi.interfaces.DNamedElement;
import net.masterthought.dlanguage.utils.DResolveUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Resolves references to elements.
 */
public class DReference extends PsiReferenceBase<PsiNamedElement> implements PsiPolyVariantReference {
    public static final ResolveResult[] EMPTY_RESOLVE_RESULT = new ResolveResult[0];
    private String name;

    public DReference(@NotNull PsiNamedElement element, TextRange textRange) {
        super(element, textRange);
        name = element.getName();
    }

    /**
     * Resolves references to a set of results.
     */
    @NotNull
    @Override
    public ResolveResult[] multiResolve(boolean incompleteCode) {
//        // We should only be resolving ddfunction_definition.
//        if (!(myElement instanceof DDefinitionFunction)) {
//            return EMPTY_RESOLVE_RESULT;
//        }
//        // Make sure that we only complete the last conid in a qualified expression.
//        if (myElement instanceof HaskellConid) {
//            // Don't resolve a module import to a constructor.
//            if (PsiTreeUtil.getParentOfType(myElement, HaskellImpdecl.class) != null) { return EMPTY_RESOLVE_RESULT; }
//            HaskellQconid qconid = PsiTreeUtil.getParentOfType(myElement, HaskellQconid.class);
//            if (qconid == null) { return EMPTY_RESOLVE_RESULT; }
//            if (!myElement.equals(Iterables.getLast(qconid.getConidList()))) { return EMPTY_RESOLVE_RESULT; }

        if (!(myElement instanceof DLanguageIdentifier))
            return new ResolveResult[0];
        final Set<? extends DNamedElement> namedElements = DResolveUtil.findDefinitionNodes((DNamedElement) myElement);
        Set<PsiNamedElement> identifiers = new HashSet<>();
        for (DNamedElement namedElement : namedElements) {
            if (namedElement instanceof DLanguageConstructor) {
                identifiers.add(namedElement);
            } else if (namedElement instanceof DLanguageFuncDeclaration) {
                identifiers.add(((DLanguageFuncDeclaration) namedElement).getIdentifier());
            } else {
                identifiers.add(PsiTreeUtil.findChildOfType(namedElement, DLanguageIdentifier.class));
            }
        }

        // Guess 20 variants tops most of the time in any real code base.
        List<ResolveResult> results = new ArrayList<ResolveResult>(20);
        for (PsiNamedElement property : identifiers) {
            //noinspection ObjectAllocationInLoop
            results.add(new PsiElementResolveResult(property));
        }
        return results.toArray(new ResolveResult[results.size()]);
    }

    /**
     * Resolves references to a single result, or fails.
     */
    @Nullable
    @Override
    public PsiElement resolve() {
        ResolveResult[] resolveResults = multiResolve(false);
        return resolveResults.length == 1 ? resolveResults[0].getElement() : null;
    }

    /**
     * Controls what names that get added to the autocompletion popup available
     * on ctrl-space.
     */
    @NotNull
    @Override
    public Object[] getVariants() {
//        // If we are not in an expression, don't provide reference completion.
//        if (PsiTreeUtil.getParentOfType(myElement, HaskellExp.class) == null) {
//            return new Object[]{};
//        }
//        // If we are in a qualified name, don't provide reference completion.
//        final PsiElement qId = PsiTreeUtil.getParentOfType(myElement, HaskellQconid.class, HaskellQvarid.class);
//        if (qId != null && qId.textContains('.')) {
//            return new Object[]{};
//        }

//        final PsiFile containingFile = myElement.getContainingFile();
//               if (!(containingFile instanceof DLanguageFile)) {
//                   return new Object[]{};
//               }
//        int offset = myElement.getTextOffset();
//
//        DCDCompletion dcdCompletion = new DCDCompletion();
//        return dcdCompletion.autoComplete(offset,containingFile).toArray();

        final PsiFile containingFile = myElement.getContainingFile();
        if (!(containingFile instanceof DLanguageFile)) {
            return new Object[0];
        }
        Set<DNamedElement> namedNodes = new HashSet<>();
        if (myElement instanceof DLanguageIdentifier)
            namedNodes = DResolveUtil.getDeclarationsVisibleFromElement((DLanguageIdentifier) myElement);
        List<String> variants = new ArrayList<String>(20);
        for (final PsiNamedElement namedElement : namedNodes) {
            variants.add(namedElement.getName());
        }
        return variants.toArray();
    }

    @Override
    public TextRange getRangeInElement() {
        return new TextRange(0, this.getElement().getNode().getTextLength());
    }

    /**
     * Called when renaming refactoring tries to rename the Psi tree.
     */
    @Override
    public PsiElement handleElementRename(final String newName)  throws IncorrectOperationException {
        PsiElement element;
        if (myElement instanceof DLanguageFuncDeclaration) {
            element = DPsiImplUtil.setName((DLanguageIdentifier) myElement, newName);
            if (element != null) return element;
            throw new IncorrectOperationException("Cannot rename " + name + " to " + newName);
        }
        return super.handleElementRename(newName);
    }
}

