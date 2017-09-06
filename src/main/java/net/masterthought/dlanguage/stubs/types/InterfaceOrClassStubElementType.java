package net.masterthought.dlanguage.stubs.types;

import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.stubs.StubInputStream;
import com.intellij.psi.stubs.StubOutputStream;
import com.intellij.util.io.StringRef;
import kotlin.Pair;
import net.masterthought.dlanguage.attributes.DAttributes;
import net.masterthought.dlanguage.psi.DLanguageInterfaceOrClass;
import net.masterthought.dlanguage.psi.impl.named.DLanguageInterfaceOrClassImpl;
import net.masterthought.dlanguage.stubs.DLanguageInterfaceOrClassStub;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class InterfaceOrClassStubElementType extends DNamedStubElementType<DLanguageInterfaceOrClassStub, DLanguageInterfaceOrClass> {
    public InterfaceOrClassStubElementType(final String debugName) {
        super(debugName);
    }

    @Override
    public DLanguageInterfaceOrClass createPsi(@NotNull final DLanguageInterfaceOrClassStub stub) {
        return new DLanguageInterfaceOrClassImpl(stub, this);
    }

    @NotNull
    @Override
    public DLanguageInterfaceOrClassStub createStub(@NotNull final DLanguageInterfaceOrClass psi, final StubElement parentStub) {
        return new DLanguageInterfaceOrClassStub(parentStub, this, psi.getName(), psi.getAttributes(), psi.isInterface());
    }

    @Override
    public void serialize(@NotNull final DLanguageInterfaceOrClassStub stub, @NotNull final StubOutputStream dataStream) throws IOException {
        super.serialize(stub, dataStream);
        dataStream.writeBoolean(stub.isInterface());
    }

    @NotNull
    @Override
    public DLanguageInterfaceOrClassStub deserialize(@NotNull final StubInputStream dataStream, final StubElement parentStub) throws IOException {
        final Pair<StringRef, DAttributes> namedStubPair = deserializeNamedStub(dataStream, parentStub);
        final boolean isInterface = dataStream.readBoolean();
        return new DLanguageInterfaceOrClassStub(parentStub, this, namedStubPair.component1(), namedStubPair.component2(), isInterface);
    }
}
