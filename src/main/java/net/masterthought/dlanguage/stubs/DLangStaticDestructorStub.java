package net.masterthought.dlanguage.stubs;

import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.stubs.StubBase;
import com.intellij.psi.stubs.StubElement;
import net.masterthought.dlanguage.psi.DLangStaticDestructor;
import org.jetbrains.annotations.NotNull;

/**
 * Created by francis on 1/14/2017.
 */
public class DLangStaticDestructorStub extends StubBase<DLangStaticDestructor> {
    public DLangStaticDestructorStub(StubElement parent, @NotNull IStubElementType elementType) {
        super(parent, elementType);
    }
}
