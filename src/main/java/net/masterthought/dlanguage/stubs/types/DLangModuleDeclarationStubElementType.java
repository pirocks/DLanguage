package net.masterthought.dlanguage.stubs.types;

import com.intellij.lang.ASTNode;
import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.stubs.StubInputStream;
import com.intellij.psi.stubs.StubOutputStream;
import net.masterthought.dlanguage.psi.DLangModuleDeclaration;
import net.masterthought.dlanguage.stubs.DLangModuleDeclarationStub;
import net.masterthought.dlanguage.utils.DUtil;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class DLangModuleDeclarationStubElementType extends DNamedStubElementType<DLangModuleDeclarationStub, DLangModuleDeclaration> {
    public DLangModuleDeclarationStubElementType(String debugName) {
        super(debugName);
    }


    @Override
    public boolean shouldCreateStub(ASTNode node) {
        return DUtil.definitionNode(node);
    }

    @Override
    public DLangModuleDeclaration createPsi(@NotNull DLangModuleDeclarationStub stub) {
        return null;//todo
    }

    @Override
    public DLangModuleDeclarationStub createStub(@NotNull DLangModuleDeclaration psi, StubElement parentStub) {
        return new DLangModuleDeclarationStub(parentStub, this, psi.getName());
    }

    @Override
    public void serialize(@NotNull DLangModuleDeclarationStub stub, @NotNull StubOutputStream dataStream) throws IOException {
        dataStream.writeName(stub.getName());
    }

    @NotNull
    @Override
    public DLangModuleDeclarationStub deserialize(@NotNull StubInputStream dataStream, StubElement parentStub) throws IOException {
        return new DLangModuleDeclarationStub(parentStub, this, dataStream.readName());
    }
}
