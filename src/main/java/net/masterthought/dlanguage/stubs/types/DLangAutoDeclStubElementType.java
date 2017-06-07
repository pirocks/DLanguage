package net.masterthought.dlanguage.stubs.types;

import com.intellij.lang.ASTNode;
import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.stubs.StubInputStream;
import com.intellij.psi.stubs.StubOutputStream;
import net.masterthought.dlanguage.psi.DLangAutoDeclarationY;
import net.masterthought.dlanguage.psi.impl.DLangAutoDeclarationYImpl;
import net.masterthought.dlanguage.stubs.DLangAutoDeclStub;
import net.masterthought.dlanguage.utils.DUtil;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class DLangAutoDeclStubElementType extends DNamedStubElementType<DLangAutoDeclStub, DLangAutoDeclarationY> {
    public DLangAutoDeclStubElementType(String debugName) {
        super(debugName);
    }

    @Override
    public DLangAutoDeclarationY createPsi(@NotNull DLangAutoDeclStub stub) {
        return new DLangAutoDeclarationYImpl(stub, this);
    }

    @Override
    public boolean shouldCreateStub(ASTNode node) {
        return DUtil.definitionNode(node);
    }

    @Override
    public DLangAutoDeclStub createStub(@NotNull DLangAutoDeclarationY psi, StubElement parentStub) {
        return new DLangAutoDeclStub(parentStub, this, psi.getName());
    }

    @Override
    public void serialize(@NotNull DLangAutoDeclStub stub, @NotNull StubOutputStream dataStream) throws IOException {
        dataStream.writeName(stub.getName());
    }

    @NotNull
    @Override
    public DLangAutoDeclStub deserialize(@NotNull StubInputStream dataStream, StubElement parentStub) throws IOException {
        return new DLangAutoDeclStub(parentStub, this, dataStream.readName());
    }
}
