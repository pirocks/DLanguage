package net.masterthought.dlanguage.stubs.types;

import com.intellij.lang.ASTNode;
import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.stubs.StubInputStream;
import com.intellij.psi.stubs.StubOutputStream;
import net.masterthought.dlanguage.psi.DLangEnumDeclaration;
import net.masterthought.dlanguage.psi.impl.DLangEnumDeclarationImpl;
import net.masterthought.dlanguage.stubs.DLangEnumDeclStub;
import net.masterthought.dlanguage.utils.DUtil;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * Created by francis on 3/9/2017.
 */
public class DLangEnumDeclStubElementType extends DNamedStubElementType<DLangEnumDeclStub, DLangEnumDeclaration> {
    public DLangEnumDeclStubElementType(String debugName) {
        super(debugName);
    }

    @Override
    public DLangEnumDeclaration createPsi(@NotNull DLangEnumDeclStub stub) {
        return new DLangEnumDeclarationImpl(stub, this);
    }

    @Override
    public boolean shouldCreateStub(ASTNode node) {
        return DUtil.definitionNode(node);
    }

    @Override
    public DLangEnumDeclStub createStub(@NotNull DLangEnumDeclaration psi, StubElement parentStub) {
        return new DLangEnumDeclStub(parentStub, this, psi.getName());
    }

    @Override
    public void serialize(@NotNull DLangEnumDeclStub stub, @NotNull StubOutputStream dataStream) throws IOException {
        dataStream.writeName(stub.getName());
    }

    @NotNull
    @Override
    public DLangEnumDeclStub deserialize(@NotNull StubInputStream dataStream, StubElement parentStub) throws IOException {
        return new DLangEnumDeclStub(parentStub, this, dataStream.readName());
    }
}
