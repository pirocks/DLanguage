package net.masterthought.dlanguage.stubs.types;

import com.intellij.lang.ASTNode;
import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.stubs.StubInputStream;
import com.intellij.psi.stubs.StubOutputStream;
import net.masterthought.dlanguage.psi.DLangIdentifier;
import net.masterthought.dlanguage.psi.impl.DLangIdentifierImpl;
import net.masterthought.dlanguage.stubs.DLangIdentifierStub;
import net.masterthought.dlanguage.utils.DUtil;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class DLangIdentifierStubElementType extends DNamedStubElementType<DLangIdentifierStub, DLangIdentifier> {
    public DLangIdentifierStubElementType(String debugName) {
        super(debugName);
    }

    @Override
    public DLangIdentifier createPsi(@NotNull DLangIdentifierStub stub) {
        return new DLangIdentifierImpl(stub, this);
    }

    @Override
    public boolean shouldCreateStub(ASTNode node) {
        return DUtil.definitionNode(node);
    }

    @Override
    public DLangIdentifierStub createStub(@NotNull DLangIdentifier psi, StubElement parentStub) {
        return new DLangIdentifierStub(parentStub, this, psi.getName());
    }

    @Override
    public void serialize(@NotNull DLangIdentifierStub stub, @NotNull StubOutputStream dataStream) throws IOException {
        dataStream.writeName(stub.getName());
    }

    @NotNull
    @Override
    public DLangIdentifierStub deserialize(@NotNull StubInputStream dataStream, StubElement parentStub) throws IOException {
        return new DLangIdentifierStub(parentStub, this, dataStream.readName());
    }
}

