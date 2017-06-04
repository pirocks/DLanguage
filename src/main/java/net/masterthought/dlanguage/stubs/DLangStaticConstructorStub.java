package net.masterthought.dlanguage.stubs;

import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.stubs.StubBase;
import com.intellij.psi.stubs.StubElement;
import net.masterthought.dlanguage.psi.DLangStaticConstructor;
import org.jetbrains.annotations.NotNull;

/**
 * Created by francis on 1/14/2017.
 */
public class DLangStaticConstructorStub extends StubBase<DLangStaticConstructor> {
    public DLangStaticConstructorStub(StubElement parent, @NotNull IStubElementType elementType) {
        super(parent, elementType);
    }


}
