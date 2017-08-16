package net.masterthought.dlanguage.stubs.types;

import com.intellij.lang.ASTNode;
import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.stubs.IndexSink;
import com.intellij.psi.stubs.StubOutputStream;
import net.masterthought.dlanguage.DLanguage;
import net.masterthought.dlanguage.psi.interfaces.DCompositeElement;
import net.masterthought.dlanguage.stubs.DStubElement;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;


public abstract class DStubElementType<S extends DStubElement<T>, T extends DCompositeElement> extends IStubElementType<S, T> {
    public DStubElementType(final String debugName) {
        super(debugName, DLanguage.INSTANCE);
    }

    @Override
    public void indexStub(@NotNull final S stub, @NotNull final IndexSink sink) {

    }

    protected void serializeModuleName(final S stub, final StubOutputStream stream) {

    }

    @Override
    public void serialize(@NotNull final S stub, @NotNull final StubOutputStream dataStream) throws IOException {

    }

    @NotNull
    @Override
    public String getExternalId() {
        return "d." + super.toString();
    }

    @Override
    public boolean shouldCreateStub(final ASTNode node) {
        return true;
    }
}

