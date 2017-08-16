package net.masterthought.dlanguage.stubs.types;

import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.stubs.StubInputStream;
import com.intellij.util.io.StringRef;
import kotlin.Pair;
import net.masterthought.dlanguage.attributes.DAttributes;
import net.masterthought.dlanguage.psi.DLanguageNamedImportBind;
import net.masterthought.dlanguage.psi.impl.named.DLanguageNamedImportBindImpl;
import net.masterthought.dlanguage.stubs.DLanguageNamedImportBindStub;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class NamedImportBindStubElementType extends DNamedStubElementType<DLanguageNamedImportBindStub, DLanguageNamedImportBind> {
    public NamedImportBindStubElementType(final String debugName) {
        super(debugName);
    }

    @Override
    public DLanguageNamedImportBind createPsi(@NotNull final DLanguageNamedImportBindStub stub) {
        return new DLanguageNamedImportBindImpl(stub, this);
    }

    @NotNull
    @Override
    public DLanguageNamedImportBindStub createStub(@NotNull final DLanguageNamedImportBind psi, final StubElement parentStub) {
        return new DLanguageNamedImportBindStub(parentStub, this, psi.getName(), psi.getAttributes());
    }

    @NotNull
    @Override
    public DLanguageNamedImportBindStub deserialize(@NotNull final StubInputStream dataStream, final StubElement parentStub) throws IOException {
        final Pair<StringRef, DAttributes> namedStubPair = deserializeNamedStub(dataStream, parentStub);
        return new DLanguageNamedImportBindStub(parentStub, this, namedStubPair.component1(), namedStubPair.component2());

    }
}
