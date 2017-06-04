package net.masterthought.dlanguage.jps.model;

import org.jetbrains.jps.model.JpsDummyElement;
import org.jetbrains.jps.model.ex.JpsElementTypeWithDummyProperties;
import org.jetbrains.jps.model.module.JpsModuleType;

/**
 * Empty shell DLangModuleType-alike.
 */
public class JpsDLangModuleType extends JpsElementTypeWithDummyProperties implements JpsModuleType<JpsDummyElement> {
    public static final JpsDLangModuleType INSTANCE = new JpsDLangModuleType();

    private JpsDLangModuleType() {
    }
}
