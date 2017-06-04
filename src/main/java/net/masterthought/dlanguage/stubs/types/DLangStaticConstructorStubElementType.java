package net.masterthought.dlanguage.stubs.types;

import com.intellij.lang.ASTNode;
import com.intellij.psi.stubs.IndexSink;
import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.stubs.StubInputStream;
import com.intellij.psi.stubs.StubOutputStream;
import net.masterthought.dlanguage.psi.DLangStaticConstructor;
import net.masterthought.dlanguage.psi.impl.DLangStaticConstructorImpl;
import net.masterthought.dlanguage.stubs.DLangStaticConstructorStub;
import net.masterthought.dlanguage.utils.DUtil;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class DLangStaticConstructorStubElementType extends DStubElementType<DLangStaticConstructorStub, DLangStaticConstructor> {
    public DLangStaticConstructorStubElementType(String debugName) {
        super(debugName);
    }

    @Override
    public DLangStaticConstructor createPsi(@NotNull DLangStaticConstructorStub stub) {
        return new DLangStaticConstructorImpl(stub, this);
    }

    @Override
    public boolean shouldCreateStub(ASTNode node) {
        return DUtil.definitionNode(node);
    }

    @Override
    public DLangStaticConstructorStub createStub(@NotNull DLangStaticConstructor psi, StubElement parentStub) {
        return new DLangStaticConstructorStub(parentStub, this);
    }

    @Override
    public void serialize(@NotNull DLangStaticConstructorStub stub, @NotNull StubOutputStream dataStream) throws IOException {
    }

    @NotNull
    @Override
    public DLangStaticConstructorStub deserialize(@NotNull StubInputStream dataStream, StubElement parentStub) throws IOException {
        return new DLangStaticConstructorStub(parentStub, this);
    }

    @Override
    public void indexStub(@NotNull DLangStaticConstructorStub stub, @NotNull IndexSink sink) {

    }
}
