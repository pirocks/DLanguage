package net.masterthought.dlanguage.stubs.types;

import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.stubs.StubInputStream;
import com.intellij.util.io.StringRef;
import kotlin.Pair;
import net.masterthought.dlanguage.attributes.DAttributes;
import net.masterthought.dlanguage.psi.DLanguageLabeledStatement;
import net.masterthought.dlanguage.psi.impl.named.DLanguageLabeledStatementImpl;
import net.masterthought.dlanguage.stubs.DLanguageLabeledStatementStub;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class LabeledStatementStubElementType extends DNamedStubElementType<DLanguageLabeledStatementStub, DLanguageLabeledStatement> {
    public LabeledStatementStubElementType(final String debugName) {
        super(debugName);
    }

    @Override
    public DLanguageLabeledStatement createPsi(@NotNull final DLanguageLabeledStatementStub stub) {
        return new DLanguageLabeledStatementImpl(stub, this);
    }

    @NotNull
    @Override
    public DLanguageLabeledStatementStub createStub(@NotNull final DLanguageLabeledStatement psi, final StubElement parentStub) {
        return new DLanguageLabeledStatementStub(parentStub, this, psi.getName(), psi.getAttributes());
    }

    @NotNull
    @Override
    public DLanguageLabeledStatementStub deserialize(@NotNull final StubInputStream dataStream, final StubElement parentStub) throws IOException {
        final Pair<StringRef, DAttributes> namedStubPair = deserializeNamedStub(dataStream, parentStub);
        return new DLanguageLabeledStatementStub(parentStub, this, namedStubPair.component1(), namedStubPair.component2());
    }
}
