package net.masterthought.dlanguage.attributes;

import com.intellij.psi.PsiElement;
import com.intellij.psi.stubs.StubInputStream;
import com.intellij.psi.stubs.StubOutputStream;
import net.masterthought.dlanguage.resolve.processors.parameters.DAttributesFinder;

import java.io.IOException;

import static net.masterthought.dlanguage.resolve.processors.parameters.DAttributesFinder.Visibility.Companion;

/**
 * Created by francis on 8/13/2017.
 */
public class DAttributes {
    private final DAttributesFinder.Visibility visibility;
    private final Boolean isStatic;
    private final Boolean isProperty;
    private final Boolean isNoGC;
    private final Boolean isExtern;
    private final Boolean isPure;
    private final Boolean isNothrow;
    private final Boolean isConst;
    private final Boolean isImmutable;

    public DAttributes(final DAttributesFinder.Visibility visibility, final Boolean isStatic, final Boolean isProperty, final Boolean isNoGC, final Boolean isExtern, final Boolean isPure, final Boolean isNothrow, final Boolean isConst, final Boolean isImmutable) {
        this.visibility = visibility;
        this.isStatic = isStatic;
        this.isProperty = isProperty;
        this.isNoGC = isNoGC;
        this.isExtern = isExtern;
        this.isPure = isPure;
        this.isNothrow = isNothrow;
        this.isConst = isConst;
        this.isImmutable = isImmutable;
    }

    public DAttributes(final PsiElement start) {
        final DAttributesFinder finder = new DAttributesFinder(start);
        finder.recurseUp();
        this.visibility = finder.isVisible();
        this.isStatic = finder.isStatic();
        this.isProperty = finder.isProperty();
        this.isNoGC = finder.isNoGC();
        this.isExtern = finder.isExtern();
        this.isPure = finder.isPure();
        this.isNothrow = finder.isNothrow();
        this.isConst = finder.isConst();
        this.isImmutable = finder.isImmutable();
    }

    public static DAttributes read(final StubInputStream stream) throws IOException {
        final DAttributesFinder.Visibility visibility = Companion.read(stream);
        final Boolean isStatic = stream.readBoolean();
        final Boolean isProperty = stream.readBoolean();
        final Boolean isNoGC = stream.readBoolean();
        final Boolean isExtern = stream.readBoolean();
        final Boolean isPure = stream.readBoolean();
        final Boolean isNothrow = stream.readBoolean();
        final Boolean isConst = stream.readBoolean();
        final Boolean isImmutable = stream.readBoolean();
        return new DAttributes(visibility, isStatic, isProperty, isNoGC, isExtern, isPure, isNothrow, isConst, isImmutable);
    }

    public DAttributesFinder.Visibility isVisible() {
        return visibility;
    }

    public Boolean isStatic() {
        return isStatic;
    }

    public Boolean isProperty() {
        return isProperty;
    }

    public Boolean isNoGC() {
        return isNoGC;
    }

    public Boolean isExtern() {
        return isExtern;
    }

    public Boolean isPure() {
        return isPure;
    }

    public Boolean isNothrow() {
        return isNothrow;
    }

    public Boolean isConst() {
        return isConst;
    }

    public Boolean isImmutable() {
        return isImmutable;
    }

    public void write(final StubOutputStream stream) throws IOException {
        visibility.write(stream);
        stream.writeBoolean(isStatic);
        stream.writeBoolean(isProperty);
        stream.writeBoolean(isNoGC);
        stream.writeBoolean(isExtern);
        stream.writeBoolean(isPure);
        stream.writeBoolean(isNothrow);
        stream.writeBoolean(isConst);
        stream.writeBoolean(isImmutable);
    }
}
