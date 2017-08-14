package net.masterthought.dlanguage.stubs;

import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.stubs.StubElement;
import com.intellij.util.io.StringRef;
import net.masterthought.dlanguage.attributes.DAttributes;
import net.masterthought.dlanguage.psi.DLanguageAutoDeclarationPart;

public class DLanguageAutoDeclarationPartStub extends DNamedStubBase<DLanguageAutoDeclarationPart> {
    public DLanguageAutoDeclarationPartStub(final StubElement parent, final IStubElementType elementType, final String name, final DAttributes attributes) {
        super(parent, elementType, name, attributes);
    }

    public DLanguageAutoDeclarationPartStub(final StubElement parent, final IStubElementType elementType, final StringRef name, final DAttributes attributes) {
        super(parent, elementType, name, attributes);
    }
}
