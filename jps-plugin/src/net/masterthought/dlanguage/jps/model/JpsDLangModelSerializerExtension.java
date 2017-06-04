package net.masterthought.dlanguage.jps.model;


import com.intellij.util.xmlb.SkipDefaultValuesSerializationFilters;
import com.intellij.util.xmlb.XmlSerializer;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.jps.model.JpsDummyElement;
import org.jetbrains.jps.model.JpsElement;
import org.jetbrains.jps.model.JpsElementFactory;
import org.jetbrains.jps.model.module.JpsModule;
import org.jetbrains.jps.model.serialization.JpsModelSerializerExtension;
import org.jetbrains.jps.model.serialization.JpsProjectExtensionSerializer;
import org.jetbrains.jps.model.serialization.facet.JpsFacetConfigurationSerializer;
import org.jetbrains.jps.model.serialization.library.JpsSdkPropertiesSerializer;
import org.jetbrains.jps.model.serialization.module.JpsModulePropertiesSerializer;
import org.jetbrains.jps.model.serialization.module.JpsModuleSourceRootDummyPropertiesSerializer;
import org.jetbrains.jps.model.serialization.module.JpsModuleSourceRootPropertiesSerializer;

import java.util.Collections;
import java.util.List;

/**
 * Main entry point for the serializer server.
 */
public class JpsDLangModelSerializerExtension extends JpsModelSerializerExtension {
    public static final String DLANGUAGE_SDK_TYPE_ID = "D Language SDK";

    @NotNull
    @Override
    public List<? extends JpsModuleSourceRootPropertiesSerializer<?>> getModuleSourceRootPropertiesSerializers() {
        return Collections.singletonList(new JpsModuleSourceRootDummyPropertiesSerializer(DLangIncludeSourceRootType.INSTANCE, "dlanguage-include"));
    }

    @NotNull
    @Override
    public List<? extends JpsModulePropertiesSerializer<?>> getModulePropertiesSerializers() {
        return Collections.singletonList(new JpsModulePropertiesSerializer<JpsDummyElement>(JpsDLangModuleType.INSTANCE, "DLANGUAGE_MODULE", null) {
            @Override
            public JpsDummyElement loadProperties(@Nullable Element componentElement) {
                return JpsElementFactory.getInstance().createDummyElement();
            }

            @Override
            public void saveProperties(@NotNull JpsDummyElement properties, @NotNull Element componentElement) {
            }
        });
    }

    @NotNull
    @Override
    public List<? extends JpsSdkPropertiesSerializer<?>> getSdkPropertiesSerializers() {
        return Collections.singletonList(new JpsSdkPropertiesSerializer<JpsDummyElement>(DLANGUAGE_SDK_TYPE_ID, JpsDLangSdkType.INSTANCE) {
            @NotNull
            @Override
            public JpsDummyElement loadProperties(@Nullable Element propertiesElement) {
                return JpsElementFactory.getInstance().createDummyElement();
            }

            @Override
            public void saveProperties(@NotNull JpsDummyElement properties, @NotNull Element element) {
            }
        });
    }

    @NotNull
    @Override
    public List<? extends JpsFacetConfigurationSerializer<?>> getFacetConfigurationSerializers() {
        return Collections.singletonList(new JpsFacetConfigurationSerializer<JpsDLangModuleExtension>(JpsDLangModuleExtension.ROLE, DLangFacetConstants.ID, DLangFacetConstants.NAME) {
            @Override
            protected JpsDLangModuleExtension loadExtension(@NotNull Element facetConfigurationElement, String name, JpsElement parent, JpsModule module) {
                DLangModuleExtensionProperties props = XmlSerializer.deserialize(facetConfigurationElement, DLangModuleExtensionProperties.class);
                return new JpsDLangModuleExtension(props == null ? new DLangModuleExtensionProperties() : props);
            }

            @Override
            protected void saveExtension(JpsDLangModuleExtension extension, Element facetConfigurationTag, JpsModule module) {
                XmlSerializer.serializeInto(extension.getProperties(), facetConfigurationTag, new SkipDefaultValuesSerializationFilters());
            }
        });
    }

    @NotNull
    @Override
    public List<? extends JpsProjectExtensionSerializer> getProjectExtensionSerializers() {
        return Collections.singletonList(new JpsDLangBuildOptionsSerializer());
    }
}

