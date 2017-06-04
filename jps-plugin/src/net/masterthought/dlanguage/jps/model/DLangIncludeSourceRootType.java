package net.masterthought.dlanguage.jps.model;

import org.jetbrains.jps.model.JpsDummyElement;
import org.jetbrains.jps.model.ex.JpsElementTypeWithDummyProperties;
import org.jetbrains.jps.model.module.JpsModuleSourceRootType;

public class DLangIncludeSourceRootType extends JpsElementTypeWithDummyProperties implements JpsModuleSourceRootType<JpsDummyElement> {
    public static final DLangIncludeSourceRootType INSTANCE = new DLangIncludeSourceRootType();

    private DLangIncludeSourceRootType() {
    }
}
