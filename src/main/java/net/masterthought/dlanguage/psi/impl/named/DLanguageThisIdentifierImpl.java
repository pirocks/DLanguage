package net.masterthought.dlanguage.psi.impl.named;

import com.intellij.lang.ASTNode;
import com.intellij.psi.stubs.IStubElementType;
import net.masterthought.dlanguage.psi.DLanguageIdentifier;
import net.masterthought.dlanguage.psi.DLanguageThisIdentifier;
import net.masterthought.dlanguage.psi.impl.DNamedStubbedPsiElementBase;
import net.masterthought.dlanguage.psi.interfaces.DNamedElement;
import net.masterthought.dlanguage.stubs.DLanguageThisIdentifierStub;
import net.masterthought.dlanguage.utils.DUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Created by francis on 8/14/2017.
 */
public class DLanguageThisIdentifierImpl extends DNamedStubbedPsiElementBase<DLanguageThisIdentifierStub> implements DLanguageThisIdentifier {
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
}
