package net.masterthought.dlanguage.stubs.types;

import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.stubs.StubInputStream;
import com.intellij.util.io.StringRef;
import kotlin.Pair;
import net.masterthought.dlanguage.attributes.DAttributes;
import net.masterthought.dlanguage.psi.DLanguageConstructor;
import net.masterthought.dlanguage.psi.impl.named.DLanguageConstructorImpl;
import net.masterthought.dlanguage.stubs.DLanguageConstructorStub;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class ConstructorStubElementType extends DNamedStubElementType<DLanguageConstructorStub, DLanguageConstructor> {
    public ConstructorStubElementType(final String debugName) {
        super(debugName);
    }

    @Override
    public DLanguageConstructor createPsi(@NotNull final DLanguageConstructorStub stub) {
        return new DLanguageConstructorImpl(stub, this);
    }

    @NotNull
    @Override
    public DLanguageConstructorStub createStub(@NotNull final DLanguageConstructor psi, final StubElement parentStub) {
        return new DLanguageConstructorStub(parentStub, this, psi.getName(), psi.getAttributes());
    }

    @NotNull
    @Override
    public DLanguageConstructorStub deserialize(@NotNull final StubInputStream dataStream, final StubElement parentStub) throws IOException {
        final Pair<StringRef, DAttributes> namedStubPair = deserializeNamedStub(dataStream, parentStub);
        return new DLanguageConstructorStub(parentStub, this, namedStubPair.component1(), namedStubPair.component2());
    }
}
