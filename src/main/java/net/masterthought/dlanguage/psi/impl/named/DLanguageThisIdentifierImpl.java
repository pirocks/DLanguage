package net.masterthought.dlanguage.psi.impl.named;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiPolyVariantReference;
import com.intellij.psi.PsiReference;
import com.intellij.psi.PsiReferenceBase;
import com.intellij.psi.stubs.IStubElementType;
import net.masterthought.dlanguage.psi.DLanguageIdentifier;
import net.masterthought.dlanguage.psi.DLanguageThisIdentifier;
import net.masterthought.dlanguage.psi.impl.DNamedStubbedPsiElementBase;
import net.masterthought.dlanguage.psi.interfaces.DNamedElement;
import net.masterthought.dlanguage.stubs.DLanguageThisIdentifierStub;
import net.masterthought.dlanguage.types.DType;
import net.masterthought.dlanguage.utils.DUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Created by francis on 8/14/2017.
 */
public class DLanguageThisIdentifierImpl extends DNamedStubbedPsiElementBase<DLanguageThisIdentifierStub> implements DLanguageThisIdentifier {
    private final int thisLength = 4;

    public DLanguageThisIdentifierImpl(@NotNull final DLanguageThisIdentifierStub stub, final IStubElementType nodeType) {
        super(stub, nodeType);
    }

    public DLanguageThisIdentifierImpl(final ASTNode node) {
        super(node);
    }

    @Nullable
    @Override
    public DLanguageIdentifier getNameIdentifier() {
        final DLanguageThisIdentifierStub greenStub = getGreenStub();
        if (greenStub != null)
            return ((DNamedElement) DUtil.getParentClassOrStructOrTemplateOrInterfaceOrUnionStub(greenStub).getPsi()).getNameIdentifier();
        if (DUtil.getParentClassOrStructOrTemplateOrInterfaceOrUnion(this) == null)
            return null;
        return DUtil.getParentClassOrStructOrTemplateOrInterfaceOrUnion(this).getNameIdentifier();//this is a freeky function name todo
    }

    @NotNull
    @Override
    public PsiReference getReference() {
        return new DThisReference(this, TextRange.from(0, thisLength));
    }

    /**
     * todo correctly handle resolving constructors
     */
    private class DThisReference extends PsiReferenceBase<DLanguageThisIdentifier> implements PsiReference {
        /**
         * @param element        PSI element
         * @param rangeInElement range relatively to the element's start offset
         */
        public DThisReference(final DLanguageThisIdentifier element, final TextRange rangeInElement) {
            super(element, rangeInElement);
        }

        /**
         * Returns the element which is the target of the reference.
         *
         * @return the target element, or null if it was not possible to resolve the reference to a valid target.
         * @see PsiPolyVariantReference#multiResolve(boolean)
         */
        @Nullable
        @Override
        public PsiElement resolve() {
            return getElement().getNameIdentifier();
        }

        /**
         * Returns the array of String, {@link PsiElement} and/or {@link LookupElement}
         * instances representing all identifiers that are visible at the location of the reference. The contents
         * of the returned array is used to build the lookup list for basic code completion. (The list
         * of visible identifiers may not be filtered by the completion prefix string - the
         * filtering is performed later by IDEA core.)
         *
         * @return the array of available identifiers.
         */
        @NotNull
        @Override
        public Object[] getVariants() {
            return new Object[0];
        }
    }

    DType getType() {
        if (getGreenStub() != null) {
            return getGreenStub().getDType();
        }
        //todo resolve etc. Safe to assume psi loaded?
        return null;
    }
}
