package net.masterthought.dlanguage.stubs.types;

import com.intellij.lang.ASTNode;
import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.stubs.StubInputStream;
import com.intellij.psi.stubs.StubOutputStream;
import net.masterthought.dlanguage.psi.DLangCatchParameter;
import net.masterthought.dlanguage.psi.impl.DLangCatchParameterImpl;
import net.masterthought.dlanguage.stubs.DLangCatchParameterStub;
import net.masterthought.dlanguage.utils.DUtil;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * Created by francis on 6/8/2017.
 */
public class DLangCatchParameterStubElementType extends DNamedStubElementType<DLangCatchParameterStub, DLangCatchParameter> {
    public DLangCatchParameterStubElementType(String debugName) {
        super(debugName);
    }

    @Override
    public DLangCatchParameter createPsi(@NotNull DLangCatchParameterStub stub) {
        return new DLangCatchParameterImpl(stub, this);
    }

    @Override
    public boolean shouldCreateStub(ASTNode node) {
        return DUtil.definitionNode(node);
    }

    @Override
    public DLangCatchParameterStub createStub(@NotNull DLangCatchParameter psi, StubElement parentStub) {
        return new DLangCatchParameterStub(parentStub, this, psi.getName());
    }

    @Override
    public void serialize(@NotNull DLangCatchParameterStub stub, @NotNull StubOutputStream dataStream) throws IOException {
        dataStream.writeName(stub.getName());
    }

    @NotNull
    @Override
    public DLangCatchParameterStub deserialize(@NotNull StubInputStream dataStream, StubElement parentStub) throws IOException {
        return new DLangCatchParameterStub(parentStub, this, dataStream.readName());
    }
}

