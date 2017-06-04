package net.masterthought.dlanguage.stubs.types;

import com.intellij.lang.ASTNode;
import com.intellij.psi.stubs.IndexSink;
import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.stubs.StubInputStream;
import com.intellij.psi.stubs.StubOutputStream;
import net.masterthought.dlanguage.psi.DLangStaticDestructor;
import net.masterthought.dlanguage.psi.impl.DLangStaticDestructorImpl;
import net.masterthought.dlanguage.stubs.DLangStaticDestructorStub;
import net.masterthought.dlanguage.utils.DUtil;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class DLangStaticDestructorStubElementType extends DStubElementType<DLangStaticDestructorStub, DLangStaticDestructor> {
    public DLangStaticDestructorStubElementType(String debugName) {
        super(debugName);
    }

    @Override
    public DLangStaticDestructor createPsi(@NotNull DLangStaticDestructorStub stub) {
        return new DLangStaticDestructorImpl(stub, this);
    }

    @Override
    public boolean shouldCreateStub(ASTNode node) {
        return DUtil.definitionNode(node);
    }

    @Override
    public DLangStaticDestructorStub createStub(@NotNull DLangStaticDestructor psi, StubElement parentStub) {
        return new DLangStaticDestructorStub(parentStub, this);
    }

    @Override
    public void serialize(@NotNull DLangStaticDestructorStub stub, @NotNull StubOutputStream dataStream) throws IOException {
    }

    @NotNull
    @Override
    public DLangStaticDestructorStub deserialize(@NotNull StubInputStream dataStream, StubElement parentStub) throws IOException {
        return new DLangStaticDestructorStub(parentStub, this);
    }

    @Override
    public void indexStub(@NotNull DLangStaticDestructorStub stub, @NotNull IndexSink sink) {

    }
}
