package net.masterthought.dlanguage.stubs.types;

import com.intellij.lang.ASTNode;
import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.stubs.StubInputStream;
import com.intellij.psi.stubs.StubOutputStream;
import net.masterthought.dlanguage.psi.DLangImport;
import net.masterthought.dlanguage.psi.impl.DLangImportImpl;
import net.masterthought.dlanguage.stubs.DLangImportDeclStub;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * Created by francis on 3/15/2017.
 */
public class DLangImportDeclStubElementType extends DNamedStubElementType<DLangImportDeclStub, DLangImport> {
    public DLangImportDeclStubElementType(String debugName) {
        super(debugName);
    }

    @Override
    public DLangImport createPsi(@NotNull DLangImportDeclStub stub) {
        return new DLangImportImpl(stub, this);
    }

    @Override
    public boolean shouldCreateStub(ASTNode node) {
        return true;
    }

    @NotNull
    @Override
    public DLangImportDeclStub createStub(@NotNull DLangImport psi, StubElement parentStub) {
        return new DLangImportDeclStub(parentStub, this, psi.getName());
    }

    @Override
    public void serialize(@NotNull DLangImportDeclStub stub, @NotNull StubOutputStream dataStream) throws IOException {
        dataStream.writeName(stub.getName());
    }

    @NotNull
    @Override
    public DLangImportDeclStub deserialize(@NotNull StubInputStream dataStream, StubElement parentStub) throws IOException {
        return new DLangImportDeclStub(parentStub, this, dataStream.readName());
    }
}
