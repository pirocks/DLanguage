package net.masterthought.dlanguage.stubs.types;

import com.intellij.lang.ASTNode;
import com.intellij.psi.stubs.IndexSink;
import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.stubs.StubInputStream;
import com.intellij.psi.stubs.StubOutputStream;
import net.masterthought.dlanguage.psi.DLangSharedStaticDestructor;
import net.masterthought.dlanguage.psi.impl.DLangSharedStaticDestructorImpl;
import net.masterthought.dlanguage.stubs.DLangSharedStaticDestructorStub;
import net.masterthought.dlanguage.utils.DUtil;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class DLangSharedStaticDestructorStubElementType extends DStubElementType<DLangSharedStaticDestructorStub, DLangSharedStaticDestructor> {
    public DLangSharedStaticDestructorStubElementType(String debugName) {
        super(debugName);
    }

    @Override
    public DLangSharedStaticDestructor createPsi(@NotNull DLangSharedStaticDestructorStub stub) {
        return new DLangSharedStaticDestructorImpl(stub, this);
    }

    @Override
    public boolean shouldCreateStub(ASTNode node) {
        return DUtil.definitionNode(node);
    }

    @Override
    public DLangSharedStaticDestructorStub createStub(@NotNull DLangSharedStaticDestructor psi, StubElement parentStub) {
        return new DLangSharedStaticDestructorStub(parentStub, this);
    }

    @Override
    public void serialize(@NotNull DLangSharedStaticDestructorStub stub, @NotNull StubOutputStream dataStream) throws IOException {
    }

    @NotNull
    @Override
    public DLangSharedStaticDestructorStub deserialize(@NotNull StubInputStream dataStream, StubElement parentStub) throws IOException {
        return new DLangSharedStaticDestructorStub(parentStub, this);
    }

    @Override
    public void indexStub(@NotNull DLangSharedStaticDestructorStub stub, @NotNull IndexSink sink) {

    }
}
