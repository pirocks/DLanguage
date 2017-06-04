package net.masterthought.dlanguage.stubs;

import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.stubs.StubBase;
import com.intellij.psi.stubs.StubElement;
import net.masterthought.dlanguage.psi.DLangSharedStaticDestructor;
import org.jetbrains.annotations.NotNull;

/**
 * Created by francis on 1/14/2017.
 */
public class DLangSharedStaticDestructorStub extends StubBase<DLangSharedStaticDestructor> {
    public DLangSharedStaticDestructorStub(StubElement parent, @NotNull IStubElementType elementType) {
        super(parent, elementType);
    }
}
