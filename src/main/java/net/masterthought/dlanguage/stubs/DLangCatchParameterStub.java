package net.masterthought.dlanguage.stubs;

import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.stubs.NamedStubBase;
import com.intellij.psi.stubs.StubElement;
import com.intellij.util.io.StringRef;
import net.masterthought.dlanguage.psi.DLangCatchParameter;

/**
 * Created by francis on 6/8/2017.
 */
public class DLangCatchParameterStub extends NamedStubBase<DLangCatchParameter> {
    public DLangCatchParameterStub(StubElement parent, IStubElementType elementType, StringRef name) {
        super(parent, elementType, name);
    }

    public DLangCatchParameterStub(StubElement parent, IStubElementType elementType, String name) {
        super(parent, elementType, name);
    }
}
