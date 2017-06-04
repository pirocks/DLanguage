package net.masterthought.dlanguage.jps.model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.jps.model.JpsElementChildRole;
import org.jetbrains.jps.model.ex.JpsCompositeElementBase;
import org.jetbrains.jps.model.ex.JpsElementChildRoleBase;
import org.jetbrains.jps.model.module.JpsModule;

import java.util.Collections;
import java.util.List;


public class JpsDLangModuleExtension extends JpsCompositeElementBase<JpsDLangModuleExtension> {
    public static final JpsElementChildRole<JpsDLangModuleExtension> ROLE = JpsElementChildRoleBase.create("DLanguage");

    private final DLangModuleExtensionProperties myProperties;

    @SuppressWarnings("UnusedDeclaration")
    public JpsDLangModuleExtension() {
        myProperties = new DLangModuleExtensionProperties();
    }

    public JpsDLangModuleExtension(DLangModuleExtensionProperties properties) {
        myProperties = properties;
    }

    public JpsDLangModuleExtension(JpsDLangModuleExtension moduleExtension) {
        myProperties = new DLangModuleExtensionProperties(moduleExtension.myProperties);
    }

    @Nullable
    public static JpsDLangModuleExtension getExtension(@Nullable JpsModule module) {
        return module != null ? module.getContainer().getChild(ROLE) : null;
    }

    @NotNull
    @Override
    public JpsDLangModuleExtension createCopy() {
        return new JpsDLangModuleExtension(this);
    }

    public DLangModuleExtensionProperties getProperties() {
        return myProperties;
    }

    public List<String> getParseTransforms() {
        return Collections.unmodifiableList(myProperties.myParseTransforms);
    }
}
