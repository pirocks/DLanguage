package net.masterthought.dlanguage.stubs;

import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.stubs.StubElement;
import com.intellij.util.io.StringRef;
import net.masterthought.dlanguage.attributes.DAttributes;
import net.masterthought.dlanguage.psi.DLanguageFunctionDeclaration;
import net.masterthought.dlanguage.types.DType;

public class DLanguageFunctionDeclarationStub extends DNamedStubBase<DLanguageFunctionDeclaration> {
    private DType dType;

    public DLanguageFunctionDeclarationStub(final StubElement parent, final IStubElementType elementType, final String name, final DAttributes attributes) {
        super(parent, elementType, name, attributes);
    }

    public DLanguageFunctionDeclarationStub(final StubElement parent, final IStubElementType elementType, final StringRef name, final DAttributes attributes) {
        super(parent, elementType, name, attributes);
    }

    public DType getDType() {
        return dType;
    }
}
