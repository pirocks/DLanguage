package net.masterthought.dlanguage.psi.interfaces;

import net.masterthought.dlanguage.psi.DLangUserDefinedAttribute;

/**
 * Created by francis on 2/28/2017.
 */
public interface HasProperty {
    boolean isPropertyFunction();

    boolean hasCustomProperty();

    DLangUserDefinedAttribute getCustomProperty();

    boolean isSafe();

    boolean isTrusted();

    boolean isNoGC();

    boolean isSystem();
}
