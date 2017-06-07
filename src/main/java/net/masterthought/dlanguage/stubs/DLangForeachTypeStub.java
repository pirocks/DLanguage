package net.masterthought.dlanguage.stubs;

import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.stubs.NamedStubBase;
import com.intellij.psi.stubs.StubElement;
import com.intellij.util.io.StringRef;
import net.masterthought.dlanguage.psi.DLangForeachType;

/**
 * Created by francis on 6/5/2017.
 */
public class DLangForeachTypeStub extends NamedStubBase<DLangForeachType> {
    public DLangForeachTypeStub(StubElement parent, IStubElementType elementType, StringRef name) {
        super(parent, elementType, name);
    }

    public DLangForeachTypeStub(StubElement parent, IStubElementType elementType, String name) {
        super(parent, elementType, name);
    }
}
