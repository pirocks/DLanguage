package net.masterthought.dlanguage.stubs.types;

import com.intellij.lang.ASTNode;
import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.stubs.StubInputStream;
import com.intellij.psi.stubs.StubOutputStream;
import net.masterthought.dlanguage.psi.DLangForeachType;
import net.masterthought.dlanguage.psi.impl.DLangForeachTypeImpl;
import net.masterthought.dlanguage.stubs.DLangForeachTypeStub;
import net.masterthought.dlanguage.utils.DUtil;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * Created by francis on 6/5/2017.
 */
public class DLangForeachTypeStubElementType extends DNamedStubElementType<DLangForeachTypeStub, DLangForeachType> {
    public DLangForeachTypeStubElementType(String debugName) {
        super(debugName);
    }

    @Override
    public DLangForeachType createPsi(@NotNull DLangForeachTypeStub stub) {
        return new DLangForeachTypeImpl(stub, this);
    }

    @Override
    public boolean shouldCreateStub(ASTNode node) {
        return DUtil.definitionNode(node);
    }

    @NotNull
    @Override
    public DLangForeachTypeStub createStub(@NotNull DLangForeachType psi, StubElement parentStub) {
        return new DLangForeachTypeStub(parentStub, this, psi.getName());
    }

    @Override
    public void serialize(@NotNull DLangForeachTypeStub stub, @NotNull StubOutputStream dataStream) throws IOException {
        dataStream.writeName(stub.getName());
    }

    @NotNull
    @Override
    public DLangForeachTypeStub deserialize(@NotNull StubInputStream dataStream, StubElement parentStub) throws IOException {
        return new DLangForeachTypeStub(parentStub, this, dataStream.readName());
    }
}
