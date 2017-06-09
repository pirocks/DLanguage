package net.masterthought.dlanguage.stubs.types;

import com.intellij.lang.ASTNode;
import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.stubs.StubInputStream;
import com.intellij.psi.stubs.StubOutputStream;
import net.masterthought.dlanguage.psi.DLangConditionVarDeclaration;
import net.masterthought.dlanguage.psi.impl.DLangConditionVarDeclarationImpl;
import net.masterthought.dlanguage.stubs.DLangConditionVarDeclStub;
import net.masterthought.dlanguage.utils.DUtil;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * Created by francis on 6/7/2017.
 */
public class DLangConditionVarDeclStubElementType extends DNamedStubElementType<DLangConditionVarDeclStub, DLangConditionVarDeclaration> {
    public DLangConditionVarDeclStubElementType(String debugName) {
        super(debugName);
    }

    @Override
    public DLangConditionVarDeclaration createPsi(@NotNull DLangConditionVarDeclStub stub) {
        return new DLangConditionVarDeclarationImpl(stub, this);
    }

    @Override
    public boolean shouldCreateStub(ASTNode node) {
        return DUtil.definitionNode(node);
    }

    @NotNull
    @Override
    public DLangConditionVarDeclStub createStub(@NotNull DLangConditionVarDeclaration psi, StubElement parentStub) {
        return new DLangConditionVarDeclStub(parentStub, this, psi.getName());
    }

    @Override
    public void serialize(@NotNull DLangConditionVarDeclStub stub, @NotNull StubOutputStream dataStream) throws IOException {
        dataStream.writeName(stub.getName());
    }

    @NotNull
    @Override
    public DLangConditionVarDeclStub deserialize(@NotNull StubInputStream dataStream, StubElement parentStub) throws IOException {
        return new DLangConditionVarDeclStub(parentStub, this, dataStream.readName());
    }
}

