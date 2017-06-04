package net.masterthought.dlanguage.module;

import com.intellij.execution.BeforeRunTask;
import com.intellij.execution.BeforeRunTaskProvider;
import com.intellij.execution.RunnerAndConfigurationSettings;
import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.ConfigurationType;
import com.intellij.execution.configurations.ModuleBasedConfiguration;
import com.intellij.execution.impl.RunConfigurationBeforeRunProvider;
import com.intellij.execution.impl.RunManagerImpl;
import com.intellij.ide.util.projectWizard.JavaModuleBuilder;
import com.intellij.openapi.extensions.Extensions;
import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.projectRoots.SdkTypeId;
import com.intellij.openapi.roots.ModifiableRootModel;
import com.intellij.openapi.util.Pair;
import net.masterthought.dlanguage.DLangBundle;
import net.masterthought.dlanguage.DLangSdkType;
import net.masterthought.dlanguage.icons.DLangIcons;
import net.masterthought.dlanguage.run.DLangRunAppConfigurationType;
import net.masterthought.dlanguage.run.DLangRunDmdConfigurationType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DLangModuleBuilder extends JavaModuleBuilder {

    public static final String DLANG_GROUP_NAME = "D Language";
    public static final String RUN_CONFIG_NAME = "Run D App";
    public static final String COMPILE_CONFIG_NAME = "Compile with DMD";
    private final String myBuilderId;
    private final String myPresentableName;
    private final String myDescription;
    private final Icon myBigIcon;

    private List<Pair<String, String>> sourcePaths;

    public DLangModuleBuilder() {
        this("DLangDmdApp", DLangBundle.INSTANCE.message("module.title"), DLangBundle.INSTANCE.message("module.description"), null);
    }

    protected DLangModuleBuilder(String builderId, String presentableName, String description, Icon bigIcon) {
        myBuilderId = builderId;
        myPresentableName = presentableName;
        myDescription = description;
        myBigIcon = bigIcon;
    }

    @Override
    public String getBuilderId() {
        return myBuilderId;
    }

    //    @Override
    public Icon getBigIcon() {
        return myBigIcon;
    }

    @Override
    public Icon getNodeIcon() {
        return DLangIcons.FILE;
    }

    @Override
    public String getDescription() {
        return myDescription;
    }

    @Override
    public String getPresentableName() {
        return myPresentableName;
    }

    @Override
    public String getGroupName() {
        return DLANG_GROUP_NAME;
    }

    @Override
    public String getParentGroup() {
        return DLANG_GROUP_NAME;
    }

    protected void setJavaRootModel(ModifiableRootModel rootModel) throws ConfigurationException {
        super.setupRootModel(rootModel);
    }

    @Override
    public void setupRootModel(ModifiableRootModel rootModel) throws ConfigurationException {
        setJavaRootModel(rootModel);
        Project project = rootModel.getProject();
        RunManagerImpl runManager = RunManagerImpl.getInstanceImpl(project);

        //Create "Compile with DMD" configuration
        RunnerAndConfigurationSettings runDmdSettings = runManager.findConfigurationByName(COMPILE_CONFIG_NAME);
        if (runDmdSettings == null) {
            final DLangRunDmdConfigurationType configurationType
                = Extensions.findExtension(ConfigurationType.CONFIGURATION_TYPE_EP, DLangRunDmdConfigurationType.class);
            final ConfigurationFactory factory = configurationType.getConfigurationFactories()[0];
            runDmdSettings = runManager.createRunConfiguration(COMPILE_CONFIG_NAME, factory);
            ((ModuleBasedConfiguration) runDmdSettings.getConfiguration()).setModule(rootModel.getModule());

            runManager.addConfiguration(runDmdSettings, false);
        }

        //Create "Run D App" configuration
        RunnerAndConfigurationSettings runAppSettings = runManager.findConfigurationByName(RUN_CONFIG_NAME);
        if (runAppSettings == null) {
            final DLangRunAppConfigurationType configurationType
                = Extensions.findExtension(ConfigurationType.CONFIGURATION_TYPE_EP, DLangRunAppConfigurationType.class);
            final ConfigurationFactory factory = configurationType.getConfigurationFactories()[0];
            runAppSettings = runManager.createRunConfiguration(RUN_CONFIG_NAME, factory);
            ((ModuleBasedConfiguration) runAppSettings.getConfiguration()).setModule(rootModel.getModule());

            runManager.addConfiguration(runAppSettings, false);

        }

        //Add dependency to exec "runDmdSettings" before running "runAppSettings".
        //XXX: next code doesn't add BeforeRunTask. I don't know why.
        BeforeRunTaskProvider provider = RunConfigurationBeforeRunProvider.getProvider(project, RunConfigurationBeforeRunProvider.ID);
        if (provider != null) {
            BeforeRunTask runDmdTask = provider.createTask(runDmdSettings.getConfiguration());
            List<BeforeRunTask> beforeRunTasks = new ArrayList<BeforeRunTask>(1);
            beforeRunTasks.add(runDmdTask);
            runManager.setBeforeRunTasks(runAppSettings.getConfiguration(), beforeRunTasks, false);
        }
    }

    /* By default sources are located in {WORKING_DIR}/source folder. */
    @NotNull
    @Override
    public List<Pair<String, String>> getSourcePaths() {
        if (sourcePaths == null) {
            final List<Pair<String, String>> paths = new ArrayList<Pair<String, String>>();
            @NonNls final String path = getContentEntryPath() + File.separator + "source";
            new File(path).mkdirs();
            paths.add(Pair.create(path, ""));
            sourcePaths = paths;
        }
        return sourcePaths;
    }

    @Override
    public boolean isSuitableSdkType(SdkTypeId sdkType) {
        return sdkType == DLangSdkType.getInstance();
    }

    @Override
    public ModuleType getModuleType() {
        return DLangModuleType.getInstance();
    }
}
