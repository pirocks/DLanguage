package net.masterthought.dlanguage.stubs.types;

import com.intellij.lang.ASTNode;
import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.stubs.StubInputStream;
import com.intellij.psi.stubs.StubOutputStream;
import net.masterthought.dlanguage.psi.DLangUnionDeclaration;
import net.masterthought.dlanguage.psi.impl.DLangUnionDeclarationImpl;
import net.masterthought.dlanguage.stubs.DLangUnionDeclStub;
import net.masterthought.dlanguage.utils.DUtil;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class DLangUnionDeclStubElementType extends DNamedStubElementType<DLangUnionDeclStub, DLangUnionDeclaration> {
    public DLangUnionDeclStubElementType(String debugName) {
        super(debugName);
    }

    @Override
    public DLangUnionDeclaration createPsi(@NotNull DLangUnionDeclStub stub) {
        return new DLangUnionDeclarationImpl(stub, this);
    }

    @Override
    public boolean shouldCreateStub(ASTNode node) {
        return DUtil.definitionNode(node);
    }

    @Override
    public DLangUnionDeclStub createStub(@NotNull DLangUnionDeclaration psi, StubElement parentStub) {
        return new DLangUnionDeclStub(parentStub, this, psi.getName());
    }

    @Override
    public void serialize(@NotNull DLangUnionDeclStub stub, @NotNull StubOutputStream dataStream) throws IOException {
        dataStream.writeName(stub.getName());
    }

    @NotNull
    @Override
    public DLangUnionDeclStub deserialize(@NotNull StubInputStream dataStream, StubElement parentStub) throws IOException {
        return new DLangUnionDeclStub(parentStub, this, dataStream.readName());
    }
}
