package net.masterthought.dlanguage.stubs.types;

import com.intellij.lang.ASTNode;
import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.stubs.StubInputStream;
import com.intellij.psi.stubs.StubOutputStream;
import net.masterthought.dlanguage.psi.DLangAliasDeclaration;
import net.masterthought.dlanguage.psi.impl.DLangAliasDeclarationImpl;
import net.masterthought.dlanguage.stubs.DLangAliasDeclarationStub;
import net.masterthought.dlanguage.utils.DUtil;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class DLangAliasDeclarationStubElementType extends DNamedStubElementType<DLangAliasDeclarationStub, DLangAliasDeclaration> {
    public DLangAliasDeclarationStubElementType(String debugName) {
        super(debugName);
    }

    @Override
    public DLangAliasDeclaration createPsi(@NotNull DLangAliasDeclarationStub stub) {
        return new DLangAliasDeclarationImpl(stub, this);
    }

    @Override
    public boolean shouldCreateStub(ASTNode node) {
        return DUtil.definitionNode(node);
    }

    @Override
    public DLangAliasDeclarationStub createStub(@NotNull DLangAliasDeclaration psi, StubElement parentStub) {
        return new DLangAliasDeclarationStub(parentStub, this, psi.getName());
    }

    @Override
    public void serialize(@NotNull DLangAliasDeclarationStub stub, @NotNull StubOutputStream dataStream) throws IOException {
        dataStream.writeName(stub.getName());
    }

    @NotNull
    @Override
    public DLangAliasDeclarationStub deserialize(@NotNull StubInputStream dataStream, StubElement parentStub) throws IOException {
        return new DLangAliasDeclarationStub(parentStub, this, dataStream.readName());
    }
}
