package net.masterthought.dlanguage.stubs.types;

import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.stubs.StubInputStream;
import com.intellij.util.io.StringRef;
import kotlin.Pair;
import net.masterthought.dlanguage.attributes.DAttributes;
import net.masterthought.dlanguage.psi.DLanguageSuperIdentifier;
import net.masterthought.dlanguage.psi.impl.named.DLanguageSuperIdentifierImpl;
import net.masterthought.dlanguage.stubs.DLanguageSuperIdentifierStub;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class SuperIdentifierStubElementType extends DNamedStubElementType<DLanguageSuperIdentifierStub, DLanguageSuperIdentifier> {
    public SuperIdentifierStubElementType(final String debugName) {
        super(debugName);
    }

    @Override
    public DLanguageSuperIdentifier createPsi(final @NotNull DLanguageSuperIdentifierStub stub) {
        return new DLanguageSuperIdentifierImpl(stub, this);
    }

    @NotNull
    @Override
    public DLanguageSuperIdentifierStub createStub(final @NotNull DLanguageSuperIdentifier psi, final StubElement parentStub) {
        return new DLanguageSuperIdentifierStub(parentStub, this, psi.getName(), psi.getAttributes());
    }

    @NotNull
    @Override
    public DLanguageSuperIdentifierStub deserialize(final @NotNull StubInputStream dataStream, final StubElement parentStub) throws IOException {
        final Pair<StringRef, DAttributes> namedStubPair = deserializeNamedStub(dataStream, parentStub);
        return new DLanguageSuperIdentifierStub(parentStub, this, namedStubPair.component1(), namedStubPair.component2());
    }
}
