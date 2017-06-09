package net.masterthought.dlanguage.stubs;

import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.stubs.NamedStubBase;
import com.intellij.psi.stubs.StubElement;
import com.intellij.util.io.StringRef;
import net.masterthought.dlanguage.psi.DLangConditionAutoDeclaration;

/**
 * Created by francis on 6/7/2017.
 */
public class DLangConditionAutoDeclStub extends NamedStubBase<DLangConditionAutoDeclaration> {
    public DLangConditionAutoDeclStub(StubElement parent, IStubElementType elementType, StringRef name) {
        super(parent, elementType, name);
    }

    public DLangConditionAutoDeclStub(StubElement parent, IStubElementType elementType, String name) {
        super(parent, elementType, name);
    }
}
