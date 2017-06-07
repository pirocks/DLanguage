package net.masterthought.dlanguage.stubs.types;

import com.intellij.lang.ASTNode;
import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.stubs.StubInputStream;
import com.intellij.psi.stubs.StubOutputStream;
import net.masterthought.dlanguage.psi.DLangTemplateParameter;
import net.masterthought.dlanguage.psi.impl.DLangTemplateParameterImpl;
import net.masterthought.dlanguage.stubs.DLangTemplateParameterStub;
import net.masterthought.dlanguage.utils.DUtil;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * Created by francis on 6/4/2017.
 */
public class DLangTemplateParameterStubElementType extends DNamedStubElementType<DLangTemplateParameterStub, DLangTemplateParameter> {
    public DLangTemplateParameterStubElementType(String debugName) {
        super(debugName);
    }

    @Override
    public DLangTemplateParameter createPsi(@NotNull DLangTemplateParameterStub stub) {
        return new DLangTemplateParameterImpl(stub, this);
    }

    @Override
    public boolean shouldCreateStub(ASTNode node) {
        return DUtil.definitionNode(node);
    }

    @Override
    public DLangTemplateParameterStub createStub(@NotNull DLangTemplateParameter psi, StubElement parentStub) {
        return new DLangTemplateParameterStub(parentStub, this, psi.getName());
    }

    @Override
    public void serialize(@NotNull DLangTemplateParameterStub stub, @NotNull StubOutputStream dataStream) throws IOException {
        dataStream.writeName(stub.getName());
    }

    @NotNull
    @Override
    public DLangTemplateParameterStub deserialize(@NotNull StubInputStream dataStream, StubElement parentStub) throws IOException {
        return new DLangTemplateParameterStub(parentStub, this, dataStream.readName());
    }
}
