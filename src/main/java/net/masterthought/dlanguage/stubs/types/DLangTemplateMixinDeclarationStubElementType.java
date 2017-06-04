package net.masterthought.dlanguage.stubs.types;

import com.intellij.lang.ASTNode;
import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.stubs.StubInputStream;
import com.intellij.psi.stubs.StubOutputStream;
import net.masterthought.dlanguage.psi.DLangTemplateMixinDeclaration;
import net.masterthought.dlanguage.psi.impl.DLangTemplateMixinDeclarationImpl;
import net.masterthought.dlanguage.stubs.DLangTemplateMixinDeclarationStub;
import net.masterthought.dlanguage.utils.DUtil;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class DLangTemplateMixinDeclarationStubElementType extends DNamedStubElementType<DLangTemplateMixinDeclarationStub, DLangTemplateMixinDeclaration> {
    public DLangTemplateMixinDeclarationStubElementType(String debugName) {
        super(debugName);
    }

    @Override
    public DLangTemplateMixinDeclaration createPsi(@NotNull DLangTemplateMixinDeclarationStub stub) {
        return new DLangTemplateMixinDeclarationImpl(stub, this);
    }

    @Override
    public boolean shouldCreateStub(ASTNode node) {
        return DUtil.definitionNode(node);
    }

    @Override
    public DLangTemplateMixinDeclarationStub createStub(@NotNull DLangTemplateMixinDeclaration psi, StubElement parentStub) {
        return new DLangTemplateMixinDeclarationStub(parentStub, this, psi.getName());
    }

    @Override
    public void serialize(@NotNull DLangTemplateMixinDeclarationStub stub, @NotNull StubOutputStream dataStream) throws IOException {
        dataStream.writeName(stub.getName());
    }

    @NotNull
    @Override
    public DLangTemplateMixinDeclarationStub deserialize(@NotNull StubInputStream dataStream, StubElement parentStub) throws IOException {
        return new DLangTemplateMixinDeclarationStub(parentStub, this, dataStream.readName());
    }
}
