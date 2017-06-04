package net.masterthought.dlanguage.psi.interfaces.containers;

import net.masterthought.dlanguage.psi.DLangConstructor;
import net.masterthought.dlanguage.psi.DLangSharedStaticConstructor;
import net.masterthought.dlanguage.psi.DLangStaticConstructor;

/**
 * Created by francis on 3/8/2017.
 */
public interface ConstructorContainer extends Container {
    Class staticsharedConstructorClass = DLangSharedStaticConstructor.class;
    Class staticConstructorClass = DLangStaticConstructor.class;
    Class constructorClass = DLangConstructor.class;

}
