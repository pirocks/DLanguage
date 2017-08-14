package net.masterthought.dlanguage.stubs.types;

import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.stubs.StubInputStream;
import com.intellij.util.io.StringRef;
import kotlin.Pair;
import net.masterthought.dlanguage.attributes.DAttributes;
import net.masterthought.dlanguage.psi.DLanguageModuleDeclaration;
import net.masterthought.dlanguage.psi.impl.named.DLanguageModuleDeclarationImpl;
import net.masterthought.dlanguage.stubs.DLanguageModuleDeclarationStub;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class ModuleDeclarationStubElementType extends DNamedStubElementType<DLanguageModuleDeclarationStub, DLanguageModuleDeclaration> {
    public ModuleDeclarationStubElementType(final String debugName) {
        super(debugName);
    }

    @Override
    public DLanguageModuleDeclaration createPsi(@NotNull final DLanguageModuleDeclarationStub stub) {
        return new DLanguageModuleDeclarationImpl(stub, this);
    }

    @NotNull
    @Override
    public DLanguageModuleDeclarationStub createStub(@NotNull final DLanguageModuleDeclaration psi, final StubElement parentStub) {
        return new DLanguageModuleDeclarationStub(parentStub, this, psi.getName(), psi.getAttributes());
    }

    @NotNull
    @Override
    public DLanguageModuleDeclarationStub deserialize(@NotNull final StubInputStream dataStream, final StubElement parentStub) throws IOException {
        final Pair<StringRef, DAttributes> namedStubPair = deserializeNamedStub(dataStream, parentStub);
        return new DLanguageModuleDeclarationStub(parentStub, this, namedStubPair.component1(), namedStubPair.component2());
    }
}
