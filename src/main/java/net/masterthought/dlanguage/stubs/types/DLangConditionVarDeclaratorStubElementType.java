package net.masterthought.dlanguage.stubs.types;

import com.intellij.lang.ASTNode;
import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.stubs.StubInputStream;
import com.intellij.psi.stubs.StubOutputStream;
import net.masterthought.dlanguage.psi.DLangConditionVarDeclarator;
import net.masterthought.dlanguage.psi.impl.DLangConditionVarDeclaratorImpl;
import net.masterthought.dlanguage.stubs.DLangConditionVarDeclaratorStub;
import net.masterthought.dlanguage.utils.DUtil;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * Created by francis on 6/7/2017.
 */
public class DLangConditionVarDeclaratorStubElementType extends DNamedStubElementType<DLangConditionVarDeclaratorStub, DLangConditionVarDeclarator> {
    public DLangConditionVarDeclaratorStubElementType(String debugName) {
        super(debugName);
    }

    @Override
    public DLangConditionVarDeclarator createPsi(@NotNull DLangConditionVarDeclaratorStub stub) {
        return new DLangConditionVarDeclaratorImpl(stub, this);
    }

    @Override
    public boolean shouldCreateStub(ASTNode node) {
        return DUtil.definitionNode(node);
    }

    @NotNull
    @Override
    public DLangConditionVarDeclaratorStub createStub(@NotNull DLangConditionVarDeclarator psi, StubElement parentStub) {
        return new DLangConditionVarDeclaratorStub(parentStub, this, psi.getName());
    }

    @Override
    public void serialize(@NotNull DLangConditionVarDeclaratorStub stub, @NotNull StubOutputStream dataStream) throws IOException {
        dataStream.writeName(stub.getName());
    }

    @NotNull
    @Override
    public DLangConditionVarDeclaratorStub deserialize(@NotNull StubInputStream dataStream, StubElement parentStub) throws IOException {
        return new DLangConditionVarDeclaratorStub(parentStub, this, dataStream.readName());
    }
}
