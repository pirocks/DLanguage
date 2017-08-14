package net.masterthought.dlanguage.stubs.types;

import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.stubs.StubInputStream;
import com.intellij.util.io.StringRef;
import kotlin.Pair;
import net.masterthought.dlanguage.attributes.DAttributes;
import net.masterthought.dlanguage.psi.DLanguageStructDeclaration;
import net.masterthought.dlanguage.psi.impl.named.DLanguageStructDeclarationImpl;
import net.masterthought.dlanguage.stubs.DLanguageStructDeclarationStub;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class StructDeclarationStubElementType extends DNamedStubElementType<DLanguageStructDeclarationStub, DLanguageStructDeclaration> {
    public StructDeclarationStubElementType(final String debugName) {
        super(debugName);
    }

    @Override
    public DLanguageStructDeclaration createPsi(final @NotNull DLanguageStructDeclarationStub stub) {
        return new DLanguageStructDeclarationImpl(stub, this);
    }

    @NotNull
    @Override
    public DLanguageStructDeclarationStub createStub(final @NotNull DLanguageStructDeclaration psi, final StubElement parentStub) {
        return new DLanguageStructDeclarationStub(parentStub, this, psi.getName(), psi.getAttributes());
    }

    @NotNull
    @Override
    public DLanguageStructDeclarationStub deserialize(final @NotNull StubInputStream dataStream, final StubElement parentStub) throws IOException {
        final Pair<StringRef, DAttributes> namedStubPair = deserializeNamedStub(dataStream, parentStub);
        return new DLanguageStructDeclarationStub(parentStub, this, namedStubPair.component1(), namedStubPair.component2());
    }
}
