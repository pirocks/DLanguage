package net.masterthought.dlanguage.stubs.types;

import com.intellij.lang.ASTNode;
import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.stubs.StubInputStream;
import com.intellij.psi.stubs.StubOutputStream;
import net.masterthought.dlanguage.psi.DLangTemplateDeclaration;
import net.masterthought.dlanguage.psi.impl.DLangTemplateDeclarationImpl;
import net.masterthought.dlanguage.stubs.DLangTemplateDeclarationStub;
import net.masterthought.dlanguage.utils.DUtil;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class DLangTemplateDeclarationStubElementType extends DNamedStubElementType<DLangTemplateDeclarationStub, DLangTemplateDeclaration> {
    public DLangTemplateDeclarationStubElementType(String debugName) {
        super(debugName);
    }

    @Override
    public DLangTemplateDeclaration createPsi(@NotNull DLangTemplateDeclarationStub stub) {
        return new DLangTemplateDeclarationImpl(stub, this);
    }

    @Override
    public boolean shouldCreateStub(ASTNode node) {
        return DUtil.definitionNode(node);
    }

    @Override
    public DLangTemplateDeclarationStub createStub(@NotNull DLangTemplateDeclaration psi, StubElement parentStub) {
        return new DLangTemplateDeclarationStub(parentStub, this, psi.getName());
    }

    @Override
    public void serialize(@NotNull DLangTemplateDeclarationStub stub, @NotNull StubOutputStream dataStream) throws IOException {
        dataStream.writeName(stub.getName());
    }

    @NotNull
    @Override
    public DLangTemplateDeclarationStub deserialize(@NotNull StubInputStream dataStream, StubElement parentStub) throws IOException {
        return new DLangTemplateDeclarationStub(parentStub, this, dataStream.readName());
    }
}
