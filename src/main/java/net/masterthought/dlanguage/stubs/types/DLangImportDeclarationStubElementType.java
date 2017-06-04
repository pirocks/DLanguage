package net.masterthought.dlanguage.stubs.types;

import com.intellij.lang.ASTNode;
import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.stubs.StubInputStream;
import com.intellij.psi.stubs.StubOutputStream;
import net.masterthought.dlanguage.psi.DLangImport;
import net.masterthought.dlanguage.psi.impl.DLangImportImpl;
import net.masterthought.dlanguage.stubs.DLangImportDeclarationStub;
import net.masterthought.dlanguage.utils.DUtil;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * Created by francis on 3/15/2017.
 */
public class DLangImportDeclarationStubElementType extends DNamedStubElementType<DLangImportDeclarationStub, DLangImport> {
    public DLangImportDeclarationStubElementType(String debugName) {
        super(debugName);
    }

    @Override
    public DLangImport createPsi(@NotNull DLangImportDeclarationStub stub) {
        return new DLangImportImpl(stub, this);
    }

    @Override
    public boolean shouldCreateStub(ASTNode node) {
        return DUtil.definitionNode(node);
    }

    @NotNull
    @Override
    public DLangImportDeclarationStub createStub(@NotNull DLangImport psi, StubElement parentStub) {
        return new DLangImportDeclarationStub(parentStub, this, psi.getName());
    }

    @Override
    public void serialize(@NotNull DLangImportDeclarationStub stub, @NotNull StubOutputStream dataStream) throws IOException {
        dataStream.writeName(stub.getName());
    }

    @NotNull
    @Override
    public DLangImportDeclarationStub deserialize(@NotNull StubInputStream dataStream, StubElement parentStub) throws IOException {
        return new DLangImportDeclarationStub(parentStub, this, dataStream.readName());
    }
}
