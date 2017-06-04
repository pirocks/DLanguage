package net.masterthought.dlanguage.psi.interfaces.containers;

import net.masterthought.dlanguage.psi.DLangDestructor;
import net.masterthought.dlanguage.psi.DLangSharedStaticDestructor;
import net.masterthought.dlanguage.psi.DLangStaticDestructor;

/**
 * Created by francis on 3/8/2017.
 */
public interface DestructorContainer extends Container {
    Class staticsharedDestructorClass = DLangSharedStaticDestructor.class;
    Class staticDestructorClass = DLangStaticDestructor.class;
    Class destructorClass = DLangDestructor.class;
}
