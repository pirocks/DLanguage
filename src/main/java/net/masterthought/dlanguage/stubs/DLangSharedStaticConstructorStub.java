package net.masterthought.dlanguage.stubs;

import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.stubs.StubBase;
import com.intellij.psi.stubs.StubElement;
import net.masterthought.dlanguage.psi.DLangSharedStaticConstructor;

/**
 * Created by francis on 1/14/2017.
 */
public class DLangSharedStaticConstructorStub extends StubBase<DLangSharedStaticConstructor> implements StubElement<DLangSharedStaticConstructor> {

    public DLangSharedStaticConstructorStub(StubElement parent, IStubElementType elementType) {
        super(parent, elementType);
    }
}
