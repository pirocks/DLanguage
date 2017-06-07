package net.masterthought.dlanguage.stubs.types;

import com.intellij.lang.ASTNode;
import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.stubs.StubInputStream;
import com.intellij.psi.stubs.StubOutputStream;
import net.masterthought.dlanguage.psi.DLangTemplateMixinDeclaration;
import net.masterthought.dlanguage.psi.impl.DLangTemplateMixinDeclarationImpl;
import net.masterthought.dlanguage.stubs.DLangTemplateMixinDeclStub;
import net.masterthought.dlanguage.utils.DUtil;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class DLangTemplateMixinDeclStubElementType extends DNamedStubElementType<DLangTemplateMixinDeclStub, DLangTemplateMixinDeclaration> {
    public DLangTemplateMixinDeclStubElementType(String debugName) {
        super(debugName);
    }

    @Override
    public DLangTemplateMixinDeclaration createPsi(@NotNull DLangTemplateMixinDeclStub stub) {
        return new DLangTemplateMixinDeclarationImpl(stub, this);
    }

    @Override
    public boolean shouldCreateStub(ASTNode node) {
        return DUtil.definitionNode(node);
    }

    @Override
    public DLangTemplateMixinDeclStub createStub(@NotNull DLangTemplateMixinDeclaration psi, StubElement parentStub) {
        return new DLangTemplateMixinDeclStub(parentStub, this, psi.getName());
    }

    @Override
    public void serialize(@NotNull DLangTemplateMixinDeclStub stub, @NotNull StubOutputStream dataStream) throws IOException {
        dataStream.writeName(stub.getName());
    }

    @NotNull
    @Override
    public DLangTemplateMixinDeclStub deserialize(@NotNull StubInputStream dataStream, StubElement parentStub) throws IOException {
        return new DLangTemplateMixinDeclStub(parentStub, this, dataStream.readName());
    }
}
