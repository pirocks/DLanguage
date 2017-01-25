package net.masterthought.dlanguage.stubs.types;

import com.intellij.lang.ASTNode;
import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.stubs.StubInputStream;
import com.intellij.psi.stubs.StubOutputStream;
import net.masterthought.dlanguage.psi.DLanguageAliasDeclaration;
import net.masterthought.dlanguage.psi.impl.DLanguageAliasDeclarationImpl;
import net.masterthought.dlanguage.stubs.DLanguageAliasDeclarationStub;
import net.masterthought.dlanguage.utils.DUtil;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class DLanguageAliasDeclarationStubElementType extends DNamedStubElementType<DLanguageAliasDeclarationStub, DLanguageAliasDeclaration> {
    public DLanguageAliasDeclarationStubElementType(String debugName) {
        super(debugName);
    }

    @Override
    public DLanguageAliasDeclaration createPsi(@NotNull DLanguageAliasDeclarationStub stub) {
        return new DLanguageAliasDeclarationImpl(stub, this);
    }

    @Override
    public boolean shouldCreateStub(ASTNode node) {
        return DUtil.definitionNode(node);
    }

    @Override
    public DLanguageAliasDeclarationStub createStub(@NotNull DLanguageAliasDeclaration psi, StubElement parentStub) {
        return new DLanguageAliasDeclarationStub(parentStub, this, psi.getName());
    }

    @Override
    public void serialize(@NotNull DLanguageAliasDeclarationStub stub, @NotNull StubOutputStream dataStream) throws IOException {
        dataStream.writeName(stub.getName());
    }

    @NotNull
    @Override
    public DLanguageAliasDeclarationStub deserialize(@NotNull StubInputStream dataStream, StubElement parentStub) throws IOException {
        return new DLanguageAliasDeclarationStub(parentStub, this, dataStream.readName());
    }
}
