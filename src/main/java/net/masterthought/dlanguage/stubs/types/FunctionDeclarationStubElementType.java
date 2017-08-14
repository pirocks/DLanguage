package net.masterthought.dlanguage.stubs.types;

import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.stubs.StubInputStream;
import com.intellij.util.io.StringRef;
import kotlin.Pair;
import net.masterthought.dlanguage.attributes.DAttributes;
import net.masterthought.dlanguage.psi.DLanguageFunctionDeclaration;
import net.masterthought.dlanguage.psi.impl.named.DLanguageFunctionDeclarationImpl;
import net.masterthought.dlanguage.stubs.DLanguageFunctionDeclarationStub;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class FunctionDeclarationStubElementType extends DNamedStubElementType<DLanguageFunctionDeclarationStub, DLanguageFunctionDeclaration> {
    public FunctionDeclarationStubElementType(final String debugName) {
        super(debugName);
    }

    @Override
    public DLanguageFunctionDeclaration createPsi(@NotNull final DLanguageFunctionDeclarationStub stub) {
        return new DLanguageFunctionDeclarationImpl(stub, this);
    }

    @NotNull
    @Override
    public DLanguageFunctionDeclarationStub createStub(@NotNull final DLanguageFunctionDeclaration psi, final StubElement parentStub) {
        return new DLanguageFunctionDeclarationStub(parentStub, this, psi.getName(), psi.getAttributes());
    }

    @NotNull
    @Override
    public DLanguageFunctionDeclarationStub deserialize(@NotNull final StubInputStream dataStream, final StubElement parentStub) throws IOException {
        final Pair<StringRef, DAttributes> namedStubPair = deserializeNamedStub(dataStream, parentStub);
        return new DLanguageFunctionDeclarationStub(parentStub, this, namedStubPair.component1(), namedStubPair.component2());
    }
}
