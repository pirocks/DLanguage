package net.masterthought.dlanguage.stubs.types;

import com.intellij.lang.ASTNode;
import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.stubs.StubInputStream;
import com.intellij.psi.stubs.StubOutputStream;
import net.masterthought.dlanguage.psi.DLangModuleDeclaration;
import net.masterthought.dlanguage.psi.impl.DLangModuleDeclarationImpl;
import net.masterthought.dlanguage.stubs.DLangModuleDeclStub;
import net.masterthought.dlanguage.utils.DUtil;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class DLangModuleDeclStubElementType extends DNamedStubElementType<DLangModuleDeclStub, DLangModuleDeclaration> {
    public DLangModuleDeclStubElementType(String debugName) {
        super(debugName);
    }


    @Override
    public boolean shouldCreateStub(ASTNode node) {
        return DUtil.definitionNode(node);
    }

    @Override
    public DLangModuleDeclaration createPsi(@NotNull DLangModuleDeclStub stub) {
        return new DLangModuleDeclarationImpl(stub, this);
    }

    @NotNull
    @Override
    public DLangModuleDeclStub createStub(@NotNull DLangModuleDeclaration psi, StubElement parentStub) {
        return new DLangModuleDeclStub(parentStub, this, psi.getName());
    }

    @Override
    public void serialize(@NotNull DLangModuleDeclStub stub, @NotNull StubOutputStream dataStream) throws IOException {
        dataStream.writeName(stub.getName());
    }

    @NotNull
    @Override
    public DLangModuleDeclStub deserialize(@NotNull StubInputStream dataStream, StubElement parentStub) throws IOException {
        return new DLangModuleDeclStub(parentStub, this, dataStream.readName());
    }
}
