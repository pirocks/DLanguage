package net.masterthought.dlanguage.stubs.types;

import com.intellij.lang.ASTNode;
import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.stubs.StubInputStream;
import com.intellij.psi.stubs.StubOutputStream;
import net.masterthought.dlanguage.psi.DLangClassDeclaration;
import net.masterthought.dlanguage.psi.impl.DLangClassDeclarationImpl;
import net.masterthought.dlanguage.stubs.DLangClassDeclStub;
import net.masterthought.dlanguage.utils.DUtil;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class DLangClassDeclStubElementType extends DNamedStubElementType<DLangClassDeclStub, DLangClassDeclaration> {
    public DLangClassDeclStubElementType(String debugName) {
        super(debugName);
    }

    @Override
    public DLangClassDeclaration createPsi(@NotNull DLangClassDeclStub stub) {
        return new DLangClassDeclarationImpl(stub, this);
    }

    @Override
    public boolean shouldCreateStub(ASTNode node) {
        return DUtil.definitionNode(node);
    }

    @Override
    public DLangClassDeclStub createStub(@NotNull DLangClassDeclaration psi, StubElement parentStub) {
        return new DLangClassDeclStub(parentStub, this, psi.getName());
    }

    @Override
    public void serialize(@NotNull DLangClassDeclStub stub, @NotNull StubOutputStream dataStream) throws IOException {
        dataStream.writeName(stub.getName());
    }

    @NotNull
    @Override
    public DLangClassDeclStub deserialize(@NotNull StubInputStream dataStream, StubElement parentStub) throws IOException {
        return new DLangClassDeclStub(parentStub, this, dataStream.readName());
    }
}
