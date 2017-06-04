package net.masterthought.dlanguage.stubs.types;

import com.intellij.lang.ASTNode;
import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.stubs.StubInputStream;
import com.intellij.psi.stubs.StubOutputStream;
import net.masterthought.dlanguage.psi.DLangConstructor;
import net.masterthought.dlanguage.psi.impl.DLangConstructorImpl;
import net.masterthought.dlanguage.stubs.DLangConstructorStub;
import net.masterthought.dlanguage.utils.DUtil;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class DLangConstructorStubElementType extends DNamedStubElementType<DLangConstructorStub, DLangConstructor> {
    public DLangConstructorStubElementType(String debugName) {
        super(debugName);
    }

    @Override
    public DLangConstructor createPsi(@NotNull DLangConstructorStub stub) {
        return new DLangConstructorImpl(stub, this);
    }

    @Override
    public boolean shouldCreateStub(ASTNode node) {
        return DUtil.definitionNode(node);
    }

    @Override
    public DLangConstructorStub createStub(@NotNull DLangConstructor psi, StubElement parentStub) {
        return new DLangConstructorStub(parentStub, this, psi.getName());
    }

    @Override
    public void serialize(@NotNull DLangConstructorStub stub, @NotNull StubOutputStream dataStream) throws IOException {
        dataStream.writeName(stub.getName());
    }

    @NotNull
    @Override
    public DLangConstructorStub deserialize(@NotNull StubInputStream dataStream, StubElement parentStub) throws IOException {
        return new DLangConstructorStub(parentStub, this, dataStream.readName());
    }
}
