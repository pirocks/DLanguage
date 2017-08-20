package net.masterthought.dlanguage.stubs;

import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.stubs.StubElement;
import com.intellij.util.io.StringRef;
import net.masterthought.dlanguage.attributes.DAttributes;
import net.masterthought.dlanguage.psi.DLanguageNamedImportBind;
import net.masterthought.dlanguage.types.DType;
import net.masterthought.dlanguage.types.ForwardingType;

/**
 * Created by francis on 8/8/2017.
 */
public class DLanguageNamedImportBindStub extends DNamedStubBase<DLanguageNamedImportBind> implements ForwardingType {
    public DLanguageNamedImportBindStub(final StubElement parent, final IStubElementType elementType, final String name, final DAttributes attributes) {
        super(parent, elementType, name, attributes);
    }

    public DLanguageNamedImportBindStub(final StubElement parent, final IStubElementType elementType, final StringRef name, final DAttributes attributes) {
        super(parent, elementType, name, attributes);
    }

    @Override
    public DType getForwardedType() {
        return null;
    }
}
