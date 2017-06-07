package net.masterthought.dlanguage.stubs.types;

import com.intellij.lang.ASTNode;
import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.stubs.StubInputStream;
import com.intellij.psi.stubs.StubOutputStream;
import net.masterthought.dlanguage.psi.DLangInterfaceDeclaration;
import net.masterthought.dlanguage.psi.impl.DLangInterfaceDeclarationImpl;
import net.masterthought.dlanguage.stubs.DLangInterfaceDeclStub;
import net.masterthought.dlanguage.utils.DUtil;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class DLangInterfaceDeclStubElementType extends DNamedStubElementType<DLangInterfaceDeclStub, DLangInterfaceDeclaration> {
    public DLangInterfaceDeclStubElementType(String debugName) {
        super(debugName);
    }

    @Override
    public DLangInterfaceDeclaration createPsi(@NotNull DLangInterfaceDeclStub stub) {
        return new DLangInterfaceDeclarationImpl(stub, this);
    }

    @Override
    public boolean shouldCreateStub(ASTNode node) {
        return DUtil.definitionNode(node);
    }

    @Override
    public DLangInterfaceDeclStub createStub(@NotNull DLangInterfaceDeclaration psi, StubElement parentStub) {
        return new DLangInterfaceDeclStub(parentStub, this, psi.getName());
    }

    @Override
    public void serialize(@NotNull DLangInterfaceDeclStub stub, @NotNull StubOutputStream dataStream) throws IOException {
        dataStream.writeName(stub.getName());
    }

    @NotNull
    @Override
    public DLangInterfaceDeclStub deserialize(@NotNull StubInputStream dataStream, StubElement parentStub) throws IOException {
        return new DLangInterfaceDeclStub(parentStub, this, dataStream.readName());
    }
}
