package net.masterthought.dlanguage.stubs;

import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.stubs.StubElement;
import com.intellij.util.io.StringRef;
import net.masterthought.dlanguage.attributes.DAttributes;
import net.masterthought.dlanguage.psi.DLanguageDeclarator;
import net.masterthought.dlanguage.types.DType;
import net.masterthought.dlanguage.types.TypeOf;

/**
 * Created by francis nixon on 1/11/2017.
 */
public class DLanguageDeclaratorStub extends DNamedStubBase<DLanguageDeclarator> implements TypeOf {
    private DType typeOf;

    public DLanguageDeclaratorStub(final StubElement parent, final IStubElementType elementType, final String name, final DAttributes attributes) {
        super(parent, elementType, name, attributes);
    }

    public DLanguageDeclaratorStub(final StubElement parent, final IStubElementType elementType, final StringRef name, final DAttributes attributes) {
        super(parent, elementType, name, attributes);
    }

    public DType getTypeOf() {
        return typeOf;
    }
}
