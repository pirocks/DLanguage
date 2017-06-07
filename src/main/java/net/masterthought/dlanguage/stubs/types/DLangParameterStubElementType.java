package net.masterthought.dlanguage.stubs.types;

import com.intellij.lang.ASTNode;
import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.stubs.StubInputStream;
import com.intellij.psi.stubs.StubOutputStream;
import net.masterthought.dlanguage.psi.DLangParameter;
import net.masterthought.dlanguage.psi.impl.DLangParameterImpl;
import net.masterthought.dlanguage.stubs.DLangParameterStub;
import net.masterthought.dlanguage.utils.DUtil;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * Created by francis on 6/4/2017.
 */
public class DLangParameterStubElementType extends DNamedStubElementType<DLangParameterStub, DLangParameter> {
    public DLangParameterStubElementType(String debugName) {
        super(debugName);
    }

    @Override
    public DLangParameter createPsi(@NotNull DLangParameterStub stub) {
        return new DLangParameterImpl(stub, this);
    }

    @Override
    public boolean shouldCreateStub(ASTNode node) {
        return DUtil.definitionNode(node);
    }

    @Override
    public DLangParameterStub createStub(@NotNull DLangParameter psi, StubElement parentStub) {
        return new DLangParameterStub(parentStub, this, psi.getName());
    }

    @Override
    public void serialize(@NotNull DLangParameterStub stub, @NotNull StubOutputStream dataStream) throws IOException {
        dataStream.writeName(stub.getName());
    }

    @NotNull
    @Override
    public DLangParameterStub deserialize(@NotNull StubInputStream dataStream, StubElement parentStub) throws IOException {
        return new DLangParameterStub(parentStub, this, dataStream.readName());
    }
}
