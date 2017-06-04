package net.masterthought.dlanguage.stubs.types;

import com.intellij.lang.ASTNode;
import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.stubs.StubInputStream;
import com.intellij.psi.stubs.StubOutputStream;
import net.masterthought.dlanguage.psi.DLangAutoDeclarationY;
import net.masterthought.dlanguage.psi.impl.DLangAutoDeclarationYImpl;
import net.masterthought.dlanguage.stubs.DLangAutoDeclarationStub;
import net.masterthought.dlanguage.utils.DUtil;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class DLangAutoDeclarationStubElementType extends DNamedStubElementType<DLangAutoDeclarationStub, DLangAutoDeclarationY> {
    public DLangAutoDeclarationStubElementType(String debugName) {
        super(debugName);
    }

    @Override
    public DLangAutoDeclarationY createPsi(@NotNull DLangAutoDeclarationStub stub) {
        return new DLangAutoDeclarationYImpl(stub, this);
    }

    @Override
    public boolean shouldCreateStub(ASTNode node) {
        return DUtil.definitionNode(node);
    }

    @Override
    public DLangAutoDeclarationStub createStub(@NotNull DLangAutoDeclarationY psi, StubElement parentStub) {
        return new DLangAutoDeclarationStub(parentStub, this, psi.getName());
    }

    @Override
    public void serialize(@NotNull DLangAutoDeclarationStub stub, @NotNull StubOutputStream dataStream) throws IOException {
        dataStream.writeName(stub.getName());
    }

    @NotNull
    @Override
    public DLangAutoDeclarationStub deserialize(@NotNull StubInputStream dataStream, StubElement parentStub) throws IOException {
        return new DLangAutoDeclarationStub(parentStub, this, dataStream.readName());
    }
}
