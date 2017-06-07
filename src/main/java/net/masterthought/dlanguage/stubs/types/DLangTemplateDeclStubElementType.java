package net.masterthought.dlanguage.stubs.types;

import com.intellij.lang.ASTNode;
import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.stubs.StubInputStream;
import com.intellij.psi.stubs.StubOutputStream;
import net.masterthought.dlanguage.psi.DLangTemplateDeclaration;
import net.masterthought.dlanguage.psi.impl.DLangTemplateDeclarationImpl;
import net.masterthought.dlanguage.stubs.DLangTemplateDeclStub;
import net.masterthought.dlanguage.utils.DUtil;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class DLangTemplateDeclStubElementType extends DNamedStubElementType<DLangTemplateDeclStub, DLangTemplateDeclaration> {
    public DLangTemplateDeclStubElementType(String debugName) {
        super(debugName);
    }

    @Override
    public DLangTemplateDeclaration createPsi(@NotNull DLangTemplateDeclStub stub) {
        return new DLangTemplateDeclarationImpl(stub, this);
    }

    @Override
    public boolean shouldCreateStub(ASTNode node) {
        return DUtil.definitionNode(node);
    }

    @Override
    public DLangTemplateDeclStub createStub(@NotNull DLangTemplateDeclaration psi, StubElement parentStub) {
        return new DLangTemplateDeclStub(parentStub, this, psi.getName());
    }

    @Override
    public void serialize(@NotNull DLangTemplateDeclStub stub, @NotNull StubOutputStream dataStream) throws IOException {
        dataStream.writeName(stub.getName());
    }

    @NotNull
    @Override
    public DLangTemplateDeclStub deserialize(@NotNull StubInputStream dataStream, StubElement parentStub) throws IOException {
        return new DLangTemplateDeclStub(parentStub, this, dataStream.readName());
    }
}
