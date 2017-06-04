package net.masterthought.dlanguage.stubs;

import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.stubs.NamedStubBase;
import com.intellij.psi.stubs.StubElement;
import com.intellij.util.io.StringRef;
import net.masterthought.dlanguage.psi.DLangDeclaratorInitializer;
import org.jetbrains.annotations.NotNull;

/**
 * Created by francis nixon on 1/11/2017.
 */
public class DLangDeclaratorInitializerStub extends NamedStubBase<DLangDeclaratorInitializer> {
    public DLangDeclaratorInitializerStub(StubElement parent, @NotNull IStubElementType elementType, StringRef name) {
        super(parent, elementType, name);
    }

    public DLangDeclaratorInitializerStub(StubElement parent, IStubElementType elementType, String name) {
        super(parent, elementType, name);
    }
}
