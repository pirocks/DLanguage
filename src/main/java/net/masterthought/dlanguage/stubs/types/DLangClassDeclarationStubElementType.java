package net.masterthought.dlanguage.stubs.types;

import com.intellij.lang.ASTNode;
import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.stubs.StubInputStream;
import com.intellij.psi.stubs.StubOutputStream;
import net.masterthought.dlanguage.psi.DLangClassDeclaration;
import net.masterthought.dlanguage.psi.impl.DLangClassDeclarationImpl;
import net.masterthought.dlanguage.stubs.DLangClassDeclarationStub;
import net.masterthought.dlanguage.utils.DUtil;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class DLangClassDeclarationStubElementType extends DNamedStubElementType<DLangClassDeclarationStub, DLangClassDeclaration> {
    public DLangClassDeclarationStubElementType(String debugName) {
        super(debugName);
    }

    @Override
    public DLangClassDeclaration createPsi(@NotNull DLangClassDeclarationStub stub) {
        return new DLangClassDeclarationImpl(stub, this);
    }

    @Override
    public boolean shouldCreateStub(ASTNode node) {
        return DUtil.definitionNode(node);
    }

    @Override
    public DLangClassDeclarationStub createStub(@NotNull DLangClassDeclaration psi, StubElement parentStub) {
        return new DLangClassDeclarationStub(parentStub, this, psi.getName());
    }

    @Override
    public void serialize(@NotNull DLangClassDeclarationStub stub, @NotNull StubOutputStream dataStream) throws IOException {
        dataStream.writeName(stub.getName());
    }

    @NotNull
    @Override
    public DLangClassDeclarationStub deserialize(@NotNull StubInputStream dataStream, StubElement parentStub) throws IOException {
        return new DLangClassDeclarationStub(parentStub, this, dataStream.readName());
    }
}
