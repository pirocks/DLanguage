package net.masterthought.dlanguage.stubs.types;

import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.stubs.StubInputStream;
import com.intellij.util.io.StringRef;
import kotlin.Pair;
import net.masterthought.dlanguage.attributes.DAttributes;
import net.masterthought.dlanguage.psi.DLanguageThisIdentifier;
import net.masterthought.dlanguage.psi.impl.named.DLanguageThisIdentifierImpl;
import net.masterthought.dlanguage.stubs.DLanguageThisIdentifierStub;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class ThisIdentifierStubElementType extends DNamedStubElementType<DLanguageThisIdentifierStub, DLanguageThisIdentifier> {
    public ThisIdentifierStubElementType(final String debugName) {
        super(debugName);
    }

    @Override
    public DLanguageThisIdentifier createPsi(final @NotNull DLanguageThisIdentifierStub stub) {
        return new DLanguageThisIdentifierImpl(stub, this);
    }

    @NotNull
    @Override
    public DLanguageThisIdentifierStub createStub(final @NotNull DLanguageThisIdentifier psi, final StubElement parentStub) {
        return new DLanguageThisIdentifierStub(parentStub, this, psi.getName(), psi.getAttributes());
    }

    @NotNull
    @Override
    public DLanguageThisIdentifierStub deserialize(final @NotNull StubInputStream dataStream, final StubElement parentStub) throws IOException {
        final Pair<StringRef, DAttributes> namedStubPair = deserializeNamedStub(dataStream, parentStub);
        return new DLanguageThisIdentifierStub(parentStub, this, namedStubPair.component1(), namedStubPair.component2());
    }
}
