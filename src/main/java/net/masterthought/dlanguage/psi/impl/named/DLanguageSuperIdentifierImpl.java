package net.masterthought.dlanguage.psi.impl.named;

import com.intellij.lang.ASTNode;
import com.intellij.psi.stubs.IStubElementType;
import net.masterthought.dlanguage.psi.DLanguageIdentifier;
import net.masterthought.dlanguage.psi.DLanguageSuperIdentifier;
import net.masterthought.dlanguage.psi.impl.DNamedStubbedPsiElementBase;
import net.masterthought.dlanguage.psi.interfaces.DNamedElement;
import net.masterthought.dlanguage.stubs.DLanguageSuperIdentifierStub;
import net.masterthought.dlanguage.utils.DUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Created by francis on 8/14/2017.
 */
public class DLanguageSuperIdentifierImpl extends DNamedStubbedPsiElementBase<DLanguageSuperIdentifierStub> implements DLanguageSuperIdentifier {
    public DLanguageSuperIdentifierImpl(@NotNull final DLanguageSuperIdentifierStub stub, final IStubElementType nodeType) {
        super(stub, nodeType);
    }

    public DLanguageSuperIdentifierImpl(final ASTNode node) {
        super(node);
    }

    @Nullable
    @Override
    public DLanguageIdentifier getNameIdentifier() {
        final DLanguageSuperIdentifierStub greenStub = getGreenStub();
        if (greenStub != null)
            return ((DNamedElement) DUtil.getParentClassOrStructOrTemplateOrInterfaceOrUnionStub(greenStub).getPsi()).getNameIdentifier();
        if (DUtil.getParentClassOrStructOrTemplateOrInterfaceOrUnion(this) == null)
            return null;
        return DUtil.getParentClassOrStructOrTemplateOrInterfaceOrUnion(this).getNameIdentifier();//this is a freeky function name todo
    }
}
