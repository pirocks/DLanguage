package net.masterthought.dlanguage.stubs.types;

import com.intellij.lang.ASTNode;
import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.stubs.StubInputStream;
import com.intellij.psi.stubs.StubOutputStream;
import net.masterthought.dlanguage.psi.DLangEnumDeclaration;
import net.masterthought.dlanguage.psi.impl.DLangEnumDeclarationImpl;
import net.masterthought.dlanguage.stubs.DLangEnumDeclarationStub;
import net.masterthought.dlanguage.utils.DUtil;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * Created by francis on 3/9/2017.
 */
public class DLangEnumDeclarationStubElementType extends DNamedStubElementType<DLangEnumDeclarationStub, DLangEnumDeclaration> {
    public DLangEnumDeclarationStubElementType(String debugName) {
        super(debugName);
    }

    @Override
    public DLangEnumDeclaration createPsi(@NotNull DLangEnumDeclarationStub stub) {
        return new DLangEnumDeclarationImpl(stub, this);
    }

    @Override
    public boolean shouldCreateStub(ASTNode node) {
        return DUtil.definitionNode(node);
    }

    @Override
    public DLangEnumDeclarationStub createStub(@NotNull DLangEnumDeclaration psi, StubElement parentStub) {
        return new DLangEnumDeclarationStub(parentStub, this, psi.getName());
    }

    @Override
    public void serialize(@NotNull DLangEnumDeclarationStub stub, @NotNull StubOutputStream dataStream) throws IOException {
        dataStream.writeName(stub.getName());
    }

    @NotNull
    @Override
    public DLangEnumDeclarationStub deserialize(@NotNull StubInputStream dataStream, StubElement parentStub) throws IOException {
        return new DLangEnumDeclarationStub(parentStub, this, dataStream.readName());
    }
}
