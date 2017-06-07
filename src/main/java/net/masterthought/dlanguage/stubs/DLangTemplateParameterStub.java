package net.masterthought.dlanguage.stubs;

import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.stubs.NamedStubBase;
import com.intellij.psi.stubs.StubElement;
import com.intellij.util.io.StringRef;
import net.masterthought.dlanguage.psi.DLangTemplateParameter;
import org.jetbrains.annotations.NotNull;

/**
 * Created by francis on 6/4/2017.
 */
public class DLangTemplateParameterStub extends NamedStubBase<DLangTemplateParameter> {
    public DLangTemplateParameterStub(StubElement parent, @NotNull IStubElementType elementType, StringRef name) {
        super(parent, elementType, name);
    }

    public DLangTemplateParameterStub(StubElement parent, @NotNull IStubElementType elementType, String name) {
        super(parent, elementType, name);
    }
}
