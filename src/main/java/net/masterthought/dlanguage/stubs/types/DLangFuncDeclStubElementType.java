package net.masterthought.dlanguage.stubs.types;

import com.intellij.lang.ASTNode;
import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.stubs.StubInputStream;
import com.intellij.psi.stubs.StubOutputStream;
import net.masterthought.dlanguage.psi.DLangFuncDeclaration;
import net.masterthought.dlanguage.psi.impl.DLangFuncDeclarationImpl;
import net.masterthought.dlanguage.stubs.DLangFuncDeclStub;
import net.masterthought.dlanguage.utils.DUtil;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class DLangFuncDeclStubElementType extends DNamedStubElementType<DLangFuncDeclStub, DLangFuncDeclaration> {
    public DLangFuncDeclStubElementType(String debugName) {
        super(debugName);
    }

    @Override
    public DLangFuncDeclaration createPsi(@NotNull DLangFuncDeclStub stub) {
        return new DLangFuncDeclarationImpl(stub, this);
    }

    @Override
    public boolean shouldCreateStub(ASTNode node) {
        return DUtil.definitionNode(node);
    }

    @Override
    public DLangFuncDeclStub createStub(@NotNull DLangFuncDeclaration psi, StubElement parentStub) {
        return new DLangFuncDeclStub(parentStub, this, psi.getName());
    }

    @Override
    public void serialize(@NotNull DLangFuncDeclStub stub, @NotNull StubOutputStream dataStream) throws IOException {
        dataStream.writeName(stub.getName());
    }

    @NotNull
    @Override
    public DLangFuncDeclStub deserialize(@NotNull StubInputStream dataStream, StubElement parentStub) throws IOException {
        return new DLangFuncDeclStub(parentStub, this, dataStream.readName());
    }
}
