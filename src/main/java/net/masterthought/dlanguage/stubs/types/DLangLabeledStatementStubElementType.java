package net.masterthought.dlanguage.stubs.types;

import com.intellij.lang.ASTNode;
import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.stubs.StubInputStream;
import com.intellij.psi.stubs.StubOutputStream;
import net.masterthought.dlanguage.psi.DLangLabeledStatement;
import net.masterthought.dlanguage.psi.impl.DLangLabeledStatementImpl;
import net.masterthought.dlanguage.stubs.DLangLabeledStatementStub;
import net.masterthought.dlanguage.utils.DUtil;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class DLangLabeledStatementStubElementType extends DNamedStubElementType<DLangLabeledStatementStub, DLangLabeledStatement> {
    public DLangLabeledStatementStubElementType(String debugName) {
        super(debugName);
    }

    @Override
    public DLangLabeledStatement createPsi(@NotNull DLangLabeledStatementStub stub) {
        return new DLangLabeledStatementImpl(stub, this);
    }

    @Override
    public boolean shouldCreateStub(ASTNode node) {
        return DUtil.definitionNode(node);
    }

    @Override
    public DLangLabeledStatementStub createStub(@NotNull DLangLabeledStatement psi, StubElement parentStub) {
        return new DLangLabeledStatementStub(parentStub, this, psi.getName());
    }

    @Override
    public void serialize(@NotNull DLangLabeledStatementStub stub, @NotNull StubOutputStream dataStream) throws IOException {
        dataStream.writeName(stub.getName());
    }

    @NotNull
    @Override
    public DLangLabeledStatementStub deserialize(@NotNull StubInputStream dataStream, StubElement parentStub) throws IOException {
        return new DLangLabeledStatementStub(parentStub, this, dataStream.readName());
    }
}
