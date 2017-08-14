package net.masterthought.dlanguage.stubs.types;

import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.stubs.StubInputStream;
import com.intellij.util.io.StringRef;
import kotlin.Pair;
import net.masterthought.dlanguage.attributes.DAttributes;
import net.masterthought.dlanguage.psi.DLanguageAutoDeclarationPart;
import net.masterthought.dlanguage.psi.impl.named.DLanguageAutoDeclarationPartImpl;
import net.masterthought.dlanguage.stubs.DLanguageAutoDeclarationPartStub;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class AutoDeclarationPartStubElementType extends DNamedStubElementType<DLanguageAutoDeclarationPartStub, DLanguageAutoDeclarationPart> {
    public AutoDeclarationPartStubElementType(final String debugName) {
        super(debugName);
    }

    @Override
    public DLanguageAutoDeclarationPart createPsi(@NotNull final DLanguageAutoDeclarationPartStub stub) {
        return new DLanguageAutoDeclarationPartImpl(stub, this);
    }

    @NotNull
    @Override
    public DLanguageAutoDeclarationPartStub createStub(@NotNull final DLanguageAutoDeclarationPart psi, final StubElement parentStub) {
        return new DLanguageAutoDeclarationPartStub(parentStub, this, psi.getName(), psi.getAttributes());
    }

    @NotNull
    @Override
    public DLanguageAutoDeclarationPartStub deserialize(@NotNull final StubInputStream dataStream, final StubElement parentStub) throws IOException {
        final Pair<StringRef, DAttributes> namedStubPair = deserializeNamedStub(dataStream, parentStub);
        return new DLanguageAutoDeclarationPartStub(parentStub, this, namedStubPair.component1(), namedStubPair.component2());

    }
}
