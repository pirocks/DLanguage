package net.masterthought.dlanguage.stubs.types;

import com.intellij.psi.stubs.IndexSink;
import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.stubs.StubInputStream;
import com.intellij.psi.stubs.StubOutputStream;
import com.intellij.util.io.StringRef;
import kotlin.Pair;
import net.masterthought.dlanguage.attributes.DAttributes;
import net.masterthought.dlanguage.psi.interfaces.DNamedElement;
import net.masterthought.dlanguage.stubs.DNamedStubBase;
import net.masterthought.dlanguage.stubs.index.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;


public abstract class DNamedStubElementType<S extends DNamedStubBase<T>, T extends DNamedElement> extends DStubElementType<S, T> {
    public DNamedStubElementType(final String debugName) {
        super(debugName);
    }

    @Override
    public void serialize(@NotNull final S stub, @NotNull final StubOutputStream dataStream) throws IOException {
        dataStream.writeName(stub.getName());
        stub.getAttributes().write(dataStream);
    }

    @NotNull
    public StringRef deserializeName(@NotNull final StubInputStream dataStream, final StubElement parentStub) throws IOException {
        return dataStream.readName();
    }

    @NotNull
    public DAttributes deserializeAttributes(@NotNull final StubInputStream dataStream, final StubElement parentStub) throws IOException {
        return DAttributes.Companion.read(dataStream);
    }

    @NotNull
    public Pair<StringRef, DAttributes> deserializeNamedStub(@NotNull final StubInputStream dataStream, final StubElement parentStub) throws IOException {
        return new Pair<>(deserializeName(dataStream, parentStub), deserializeAttributes(dataStream, parentStub));
    }

    @Override
    public void indexStub(@NotNull final S stub, @NotNull final IndexSink sink) {
        final String name = stub.getName();
        if (name == null) {
            return;
        }
        sink.occurrence(DAllNameIndex.KEY, name);
        DModuleDeclarationIndex.Companion.indexModuleDeclarations(stub, sink, name);
        DTopLevelDeclarationIndex.Companion.indexTopLevelDeclarations(stub, sink, name);
        DTopLevelDeclarationsByModule.Companion.indexTopLevelDeclarationsByModule(stub, sink);
        DPublicImportIndex.Companion.indexPublicImports(stub, sink);
        DMembersIndex.Companion.indexMembers(stub, sink);
        ImportsByModule.Companion.indexTopLevelImports(stub, sink);

    }

}

