package net.masterthought.dlanguage.jps.model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.jps.model.JpsElementChildRole;
import org.jetbrains.jps.model.JpsProject;
import org.jetbrains.jps.model.ex.JpsCompositeElementBase;
import org.jetbrains.jps.model.ex.JpsElementChildRoleBase;

public class JpsDLangBuildOptionsExtension extends JpsCompositeElementBase<JpsDLangBuildOptionsExtension> {
    public static final JpsElementChildRole<JpsDLangBuildOptionsExtension> ROLE = JpsElementChildRoleBase.create("DLangBuildOptions");

    private DLangBuildOptions myOptions;

    public JpsDLangBuildOptionsExtension(DLangBuildOptions options) {
        myOptions = options;
    }

    @NotNull
    public static JpsDLangBuildOptionsExtension getOrCreateExtension(@NotNull JpsProject project) {
        JpsDLangBuildOptionsExtension extension = project.getContainer().getChild(ROLE);
        if (extension == null) {
            extension = project.getContainer().setChild(ROLE, new JpsDLangBuildOptionsExtension(new DLangBuildOptions()));
        }
        return extension;
    }

    @NotNull
    @Override
    public JpsDLangBuildOptionsExtension createCopy() {
        return new JpsDLangBuildOptionsExtension(new DLangBuildOptions(myOptions));
    }

    public DLangBuildOptions getOptions() {
        return myOptions;
    }

    public void setOptions(DLangBuildOptions options) {
        myOptions = options;
    }

    @Override
    public String toString() {
        return "JpsDLangBuildOptionsExtension{" +
            "myOptions=" + myOptions +
            '}';
    }
}
