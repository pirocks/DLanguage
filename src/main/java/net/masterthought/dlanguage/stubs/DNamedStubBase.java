package net.masterthought.dlanguage.stubs;

import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.stubs.NamedStubBase;
import com.intellij.psi.stubs.StubElement;
import com.intellij.util.io.StringRef;
import net.masterthought.dlanguage.attributes.DAttributes;
import net.masterthought.dlanguage.psi.interfaces.DNamedElement;
import net.masterthought.dlanguage.resolve.processors.parameters.DAttributesFinder;

/**
 * Created by francis on 8/13/2017.
 */
public class DNamedStubBase<T extends DNamedElement> extends NamedStubBase<T> implements DStubElement<T> {

    private final DAttributes attributes;

    public DNamedStubBase(final StubElement parent, final IStubElementType elementType, final String name, final DAttributes attributes) {
        super(parent, elementType, name);
        this.attributes = attributes;
    }

    public DNamedStubBase(final StubElement parent, final IStubElementType elementType, final StringRef name, final DAttributes attributes) {
        super(parent, elementType, name);
        this.attributes = attributes;
    }

    public DAttributesFinder.Visibility isVisible() {
        return attributes.isVisible();
    }

    public Boolean isStatic() {
        return attributes.isStatic();
    }

    public Boolean isProperty() {
        return attributes.isProperty();
    }

    public Boolean isNoGC() {
        return attributes.isNoGC();
    }

    public Boolean isExtern() {
        return attributes.isExtern();
    }

    public Boolean isPure() {
        return attributes.isPure();
    }

    public Boolean isNothrow() {
        return attributes.isNothrow();
    }

    public Boolean isConst() {
        return attributes.isConst();
    }

    public Boolean isImmutable() {
        return attributes.isImmutable();
    }

    public DAttributes getAttributes() {
        return attributes;
    }
}
