package net.masterthought.dlanguage.stubs.types;

import com.intellij.lang.ASTNode;
import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.stubs.StubInputStream;
import com.intellij.psi.stubs.StubOutputStream;
import net.masterthought.dlanguage.psi.DLangDestructor;
import net.masterthought.dlanguage.psi.impl.DLangDestructorImpl;
import net.masterthought.dlanguage.stubs.DLangDestructorStub;
import net.masterthought.dlanguage.utils.DUtil;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class DLangDestructorStubElementType extends DNamedStubElementType<DLangDestructorStub, DLangDestructor> {
    public DLangDestructorStubElementType(String debugName) {
        super(debugName);
    }

    @Override
    public DLangDestructor createPsi(@NotNull DLangDestructorStub stub) {
        return new DLangDestructorImpl(stub, this);
    }

    @Override
    public boolean shouldCreateStub(ASTNode node) {
        return DUtil.definitionNode(node);
    }

    @Override
    public DLangDestructorStub createStub(@NotNull DLangDestructor psi, StubElement parentStub) {
        return new DLangDestructorStub(parentStub, this, psi.getName());
    }

    @Override
    public void serialize(@NotNull DLangDestructorStub stub, @NotNull StubOutputStream dataStream) throws IOException {
        dataStream.writeName(stub.getName());
    }

    @NotNull
    @Override
    public DLangDestructorStub deserialize(@NotNull StubInputStream dataStream, StubElement parentStub) throws IOException {
        return new DLangDestructorStub(parentStub, this, dataStream.readName());
    }
}
