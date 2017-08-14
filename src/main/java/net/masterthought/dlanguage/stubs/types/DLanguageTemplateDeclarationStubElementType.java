package net.masterthought.dlanguage.stubs.types;

import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.stubs.StubInputStream;
import com.intellij.util.io.StringRef;
import kotlin.Pair;
import net.masterthought.dlanguage.attributes.DAttributes;
import net.masterthought.dlanguage.psi.DLanguageTemplateDeclaration;
import net.masterthought.dlanguage.psi.impl.named.DLanguageTemplateDeclarationImpl;
import net.masterthought.dlanguage.stubs.DLanguageTemplateDeclarationStub;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class DLanguageTemplateDeclarationStubElementType extends DNamedStubElementType<DLanguageTemplateDeclarationStub, DLanguageTemplateDeclaration> {
    public DLanguageTemplateDeclarationStubElementType(final String debugName) {
        super(debugName);
    }

    @Override
    public DLanguageTemplateDeclaration createPsi(@NotNull final DLanguageTemplateDeclarationStub stub) {
        return new DLanguageTemplateDeclarationImpl(stub, this);
    }

    @NotNull
    @Override
    public DLanguageTemplateDeclarationStub createStub(@NotNull final DLanguageTemplateDeclaration psi, final StubElement parentStub) {
        return new DLanguageTemplateDeclarationStub(parentStub, this, psi.getName(), psi.getAttributes());
    }

    @NotNull
    @Override
    public DLanguageTemplateDeclarationStub deserialize(@NotNull final StubInputStream dataStream, final StubElement parentStub) throws IOException {
        final Pair<StringRef, DAttributes> namedStubPair = deserializeNamedStub(dataStream, parentStub);
        return new DLanguageTemplateDeclarationStub(parentStub, this, namedStubPair.component1(), namedStubPair.component2());

    }
}
