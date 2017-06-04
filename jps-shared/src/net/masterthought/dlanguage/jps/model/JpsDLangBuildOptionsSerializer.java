package net.masterthought.dlanguage.jps.model;

import com.intellij.util.xmlb.XmlSerializer;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.jps.model.JpsProject;
import org.jetbrains.jps.model.serialization.JpsProjectExtensionSerializer;

public class JpsDLangBuildOptionsSerializer extends JpsProjectExtensionSerializer {
    public static final String DLANGUAGE_BUILD_OPTIONS_COMPONENT_NAME = "DLangBuildOptions";

    public JpsDLangBuildOptionsSerializer() {
        super("compiler.xml", DLANGUAGE_BUILD_OPTIONS_COMPONENT_NAME);
    }

    @Override
    public void loadExtension(@NotNull JpsProject project, @NotNull Element componentTag) {
        JpsDLangBuildOptionsExtension extension = JpsDLangBuildOptionsExtension.getOrCreateExtension(project);
        DLangBuildOptions options = XmlSerializer.deserialize(componentTag, DLangBuildOptions.class);
        if (options != null) {
            extension.setOptions(options);
        }
    }

    @Override
    public void saveExtension(@NotNull JpsProject project, @NotNull Element componentTag) {
    }
}
