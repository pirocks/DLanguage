package net.masterthought.dlanguage.settings;

import com.intellij.openapi.components.*;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import net.masterthought.dlanguage.jps.model.DLangBuildOptions;
import net.masterthought.dlanguage.jps.model.JpsDLangBuildOptionsSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@State(
    name = JpsDLangBuildOptionsSerializer.DLANGUAGE_BUILD_OPTIONS_COMPONENT_NAME,
    storages = {
        @Storage(file = StoragePathMacros.MODULE_FILE),
        @Storage(value = "/compiler.xml", scheme = StorageScheme.DIRECTORY_BASED)
    }
)
public class DLangBuildSettings implements PersistentStateComponent<DLangBuildOptions> {

    private static final Logger LOG = Logger.getInstance(DLangBuildSettings.class);

    private DLangBuildOptions myBuildOptions = new DLangBuildOptions();

    @NotNull
    public static DLangBuildSettings getInstance(@NotNull Project project) {
        final DLangBuildSettings persisted = ServiceManager.getService(project, DLangBuildSettings.class);
        return persisted != null ? persisted : new DLangBuildSettings();
    }

    @Nullable
    @Override
    public DLangBuildOptions getState() {
        return myBuildOptions;
    }

    @Override
    public void loadState(DLangBuildOptions state) {
        LOG.info("loading build options : " + state);
        myBuildOptions = state;
    }

    @NotNull
    public String getDmdPath() {
        return myBuildOptions.myDmdPath;
    }

    public void setDmdPath(@NotNull String path) {
        myBuildOptions.myDmdPath = path;
    }

    @NotNull
    public String getrDmdPath() {
        return myBuildOptions.myrDmdPath;
    }

    public void setrDmdPath(@NotNull String path) {
        myBuildOptions.myrDmdPath = path;
    }
}
