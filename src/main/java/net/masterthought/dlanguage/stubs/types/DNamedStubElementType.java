package net.masterthought.dlanguage.stubs.types;

import com.intellij.psi.stubs.IndexSink;
import com.intellij.psi.stubs.NamedStubBase;
import com.intellij.psi.stubs.StubElement;
import net.masterthought.dlanguage.psi.interfaces.DNamedElement;
import net.masterthought.dlanguage.stubs.DLangFuncDeclStub;
import net.masterthought.dlanguage.stubs.DLangIdentifierStub;
import net.masterthought.dlanguage.stubs.index.DAllNameIndex;
import net.masterthought.dlanguage.stubs.index.DTopLevelDeclarationIndex;
import net.masterthought.dlanguage.stubs.interfaces.UnitTestingStub;
import org.jetbrains.annotations.NotNull;


public abstract class DNamedStubElementType<S extends NamedStubBase<T>, T extends DNamedElement> extends DStubElementType<S, T> {
    public DNamedStubElementType(String debugName) {
        super(debugName);
    }

    @Override
    public void indexStub(@NotNull S stub, @NotNull IndexSink sink) {
        final String name = stub.getName();
        if (name == null) {
            return;
        }
        sink.occurrence(DAllNameIndex.KEY, name);
        if ((!(stub instanceof DLangIdentifierStub)) && topLevelDeclaration(stub)) {
            sink.occurrence(DTopLevelDeclarationIndex.KEY, name);
        }
    }

    public boolean topLevelDeclaration(S stub) {
        //stuff within unittests does not count as top level
        //stuff within func declarations does not count as tp level b/c not globally accessible todo check if this is true for all declaration types
        //stuff within templates does not count. todo handle mixins?
        StubElement stubParent = stub;
        while (true) {
            stubParent = stubParent.getParentStub();
            if (stubParent == null) {
                return true;
            }
            if (stubParent instanceof DLangFuncDeclStub) {
                return false;
            }
            if (stubParent instanceof UnitTestingStub) {
                return false;
            }
        }
    }
}

