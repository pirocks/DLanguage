package net.masterthought.dlanguage.stubs.types;

import com.intellij.lang.ASTNode;
import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.stubs.StubInputStream;
import com.intellij.psi.stubs.StubOutputStream;
import net.masterthought.dlanguage.psi.DLangAliasDeclaration;
import net.masterthought.dlanguage.psi.impl.DLangAliasDeclarationImpl;
import net.masterthought.dlanguage.stubs.DLangAliasDeclStub;
import net.masterthought.dlanguage.utils.DUtil;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class DLangAliasStubElementType extends DNamedStubElementType<DLangAliasDeclStub, DLangAliasDeclaration> {
    public DLangAliasStubElementType(String debugName) {
        super(debugName);
    }

    @Override
    public DLangAliasDeclaration createPsi(@NotNull DLangAliasDeclStub stub) {
        return new DLangAliasDeclarationImpl(stub, this);
    }

    @Override
    public boolean shouldCreateStub(ASTNode node) {
        return DUtil.definitionNode(node);
    }

    @Override
    public DLangAliasDeclStub createStub(@NotNull DLangAliasDeclaration psi, StubElement parentStub) {
        return new DLangAliasDeclStub(parentStub, this, psi.getName());
    }

    @Override
    public void serialize(@NotNull DLangAliasDeclStub stub, @NotNull StubOutputStream dataStream) throws IOException {
        dataStream.writeName(stub.getName());
    }

    @NotNull
    @Override
    public DLangAliasDeclStub deserialize(@NotNull StubInputStream dataStream, StubElement parentStub) throws IOException {
        return new DLangAliasDeclStub(parentStub, this, dataStream.readName());
    }
}
