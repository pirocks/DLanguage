package net.masterthought.dlanguage.stubs.types;

import com.intellij.lang.ASTNode;
import com.intellij.psi.stubs.IndexSink;
import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.stubs.StubInputStream;
import com.intellij.psi.stubs.StubOutputStream;
import net.masterthought.dlanguage.psi.DLangSharedStaticConstructor;
import net.masterthought.dlanguage.psi.impl.DLangSharedStaticConstructorImpl;
import net.masterthought.dlanguage.stubs.DLangSharedStaticConstructorStub;
import net.masterthought.dlanguage.utils.DUtil;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class DLangSharedStaticConstructorStubElementType extends DStubElementType<DLangSharedStaticConstructorStub, DLangSharedStaticConstructor> {
    public DLangSharedStaticConstructorStubElementType(String debugName) {
        super(debugName);
    }

    @Override
    public DLangSharedStaticConstructor createPsi(@NotNull DLangSharedStaticConstructorStub stub) {
        return new DLangSharedStaticConstructorImpl(stub, this);
    }

    @Override
    public boolean shouldCreateStub(ASTNode node) {
        return DUtil.definitionNode(node);
    }

    @Override
    public DLangSharedStaticConstructorStub createStub(@NotNull DLangSharedStaticConstructor psi, StubElement parentStub) {
        return new DLangSharedStaticConstructorStub(parentStub, this);
    }

    @Override
    public void serialize(@NotNull DLangSharedStaticConstructorStub stub, @NotNull StubOutputStream dataStream) throws IOException {

    }

    @NotNull
    @Override
    public DLangSharedStaticConstructorStub deserialize(@NotNull StubInputStream dataStream, StubElement parentStub) throws IOException {
        return new DLangSharedStaticConstructorStub(parentStub, this);
    }

    @Override
    public void indexStub(@NotNull DLangSharedStaticConstructorStub stub, @NotNull IndexSink sink) {
        //todo at some point add static constructos to index under module name
    }
}
