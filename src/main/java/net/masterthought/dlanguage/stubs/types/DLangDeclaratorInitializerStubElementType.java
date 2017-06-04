package net.masterthought.dlanguage.stubs.types;

import com.intellij.lang.ASTNode;
import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.stubs.StubInputStream;
import com.intellij.psi.stubs.StubOutputStream;
import net.masterthought.dlanguage.psi.DLangDeclaratorInitializer;
import net.masterthought.dlanguage.psi.impl.DLangDeclaratorInitializerImpl;
import net.masterthought.dlanguage.stubs.DLangDeclaratorInitializerStub;
import net.masterthought.dlanguage.utils.DUtil;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class DLangDeclaratorInitializerStubElementType extends DNamedStubElementType<DLangDeclaratorInitializerStub, DLangDeclaratorInitializer> {
    public DLangDeclaratorInitializerStubElementType(String debugName) {
        super(debugName);
    }

    @Override
    public DLangDeclaratorInitializer createPsi(@NotNull DLangDeclaratorInitializerStub stub) {
        return new DLangDeclaratorInitializerImpl(stub, this);
    }

    @Override
    public boolean shouldCreateStub(ASTNode node) {
        return DUtil.definitionNode(node);
    }

    @Override
    public DLangDeclaratorInitializerStub createStub(@NotNull DLangDeclaratorInitializer psi, StubElement parentStub) {
        return new DLangDeclaratorInitializerStub(parentStub, this, psi.getName());
    }

    @Override
    public void serialize(@NotNull DLangDeclaratorInitializerStub stub, @NotNull StubOutputStream dataStream) throws IOException {
        dataStream.writeName(stub.getName());
    }

    @NotNull
    @Override
    public DLangDeclaratorInitializerStub deserialize(@NotNull StubInputStream dataStream, StubElement parentStub) throws IOException {
        return new DLangDeclaratorInitializerStub(parentStub, this, dataStream.readName());
    }
}
