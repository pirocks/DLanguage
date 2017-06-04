package net.masterthought.dlanguage.stubs.types;

import com.intellij.lang.ASTNode;
import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.stubs.StubInputStream;
import com.intellij.psi.stubs.StubOutputStream;
import net.masterthought.dlanguage.psi.DLangStructDeclaration;
import net.masterthought.dlanguage.psi.impl.DLangStructDeclarationImpl;
import net.masterthought.dlanguage.stubs.DLangStructDeclarationStub;
import net.masterthought.dlanguage.utils.DUtil;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class DLangStructDeclarationStubElementType extends DNamedStubElementType<DLangStructDeclarationStub, DLangStructDeclaration> {
    public DLangStructDeclarationStubElementType(String debugName) {
        super(debugName);
    }

    @Override
    public DLangStructDeclaration createPsi(@NotNull DLangStructDeclarationStub stub) {
        return new DLangStructDeclarationImpl(stub, this);
    }

    @Override
    public boolean shouldCreateStub(ASTNode node) {
        return DUtil.definitionNode(node);
    }

    @Override
    public DLangStructDeclarationStub createStub(@NotNull DLangStructDeclaration psi, StubElement parentStub) {
        return new DLangStructDeclarationStub(parentStub, this, psi.getName());
    }

    @Override
    public void serialize(@NotNull DLangStructDeclarationStub stub, @NotNull StubOutputStream dataStream) throws IOException {
        dataStream.writeName(stub.getName());
    }

    @NotNull
    @Override
    public DLangStructDeclarationStub deserialize(@NotNull StubInputStream dataStream, StubElement parentStub) throws IOException {
        return new DLangStructDeclarationStub(parentStub, this, dataStream.readName());
    }
}
