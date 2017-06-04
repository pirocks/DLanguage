package net.masterthought.dlanguage.stubs.types;

import com.intellij.lang.ASTNode;
import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.stubs.StubInputStream;
import com.intellij.psi.stubs.StubOutputStream;
import net.masterthought.dlanguage.psi.DLangUnionDeclaration;
import net.masterthought.dlanguage.psi.impl.DLangUnionDeclarationImpl;
import net.masterthought.dlanguage.stubs.DLangUnionDeclarationStub;
import net.masterthought.dlanguage.utils.DUtil;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class DLangUnionDeclarationStubElementType extends DNamedStubElementType<DLangUnionDeclarationStub, DLangUnionDeclaration> {
    public DLangUnionDeclarationStubElementType(String debugName) {
        super(debugName);
    }

    @Override
    public DLangUnionDeclaration createPsi(@NotNull DLangUnionDeclarationStub stub) {
        return new DLangUnionDeclarationImpl(stub, this);
    }

    @Override
    public boolean shouldCreateStub(ASTNode node) {
        return DUtil.definitionNode(node);
    }

    @Override
    public DLangUnionDeclarationStub createStub(@NotNull DLangUnionDeclaration psi, StubElement parentStub) {
        return new DLangUnionDeclarationStub(parentStub, this, psi.getName());
    }

    @Override
    public void serialize(@NotNull DLangUnionDeclarationStub stub, @NotNull StubOutputStream dataStream) throws IOException {
        dataStream.writeName(stub.getName());
    }

    @NotNull
    @Override
    public DLangUnionDeclarationStub deserialize(@NotNull StubInputStream dataStream, StubElement parentStub) throws IOException {
        return new DLangUnionDeclarationStub(parentStub, this, dataStream.readName());
    }
}
