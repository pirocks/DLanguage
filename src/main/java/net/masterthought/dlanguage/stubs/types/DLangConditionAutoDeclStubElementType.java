package net.masterthought.dlanguage.stubs.types;

import com.intellij.lang.ASTNode;
import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.stubs.StubInputStream;
import com.intellij.psi.stubs.StubOutputStream;
import net.masterthought.dlanguage.psi.DLangConditionAutoDeclaration;
import net.masterthought.dlanguage.psi.impl.DLangConditionAutoDeclarationImpl;
import net.masterthought.dlanguage.stubs.DLangConditionAutoDeclStub;
import net.masterthought.dlanguage.utils.DUtil;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * Created by francis on 6/7/2017.
 */
public class DLangConditionAutoDeclStubElementType extends DNamedStubElementType<DLangConditionAutoDeclStub, DLangConditionAutoDeclaration> {
    public DLangConditionAutoDeclStubElementType(String debugName) {
        super(debugName);
    }

    @Override
    public DLangConditionAutoDeclaration createPsi(@NotNull DLangConditionAutoDeclStub stub) {
        return new DLangConditionAutoDeclarationImpl(stub, this);
    }

    @Override
    public boolean shouldCreateStub(ASTNode node) {
        return DUtil.definitionNode(node);
    }

    @NotNull
    @Override
    public DLangConditionAutoDeclStub createStub(@NotNull DLangConditionAutoDeclaration psi, StubElement parentStub) {
        return new DLangConditionAutoDeclStub(parentStub, this, psi.getName());
    }

    @Override
    public void serialize(@NotNull DLangConditionAutoDeclStub stub, @NotNull StubOutputStream dataStream) throws IOException {
        dataStream.writeName(stub.getName());
    }

    @NotNull
    @Override
    public DLangConditionAutoDeclStub deserialize(@NotNull StubInputStream dataStream, StubElement parentStub) throws IOException {
        return new DLangConditionAutoDeclStub(parentStub, this, dataStream.readName());
    }
}
