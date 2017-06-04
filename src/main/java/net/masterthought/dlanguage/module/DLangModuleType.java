package net.masterthought.dlanguage.module;

import com.intellij.ide.util.projectWizard.ModuleWizardStep;
import com.intellij.ide.util.projectWizard.ProjectJdkForModuleStep;
import com.intellij.ide.util.projectWizard.WizardContext;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.module.ModuleTypeManager;
import com.intellij.openapi.module.ModuleUtil;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ui.configuration.ModulesProvider;
import net.masterthought.dlanguage.DLangBundle;
import net.masterthought.dlanguage.DLangSdkType;
import net.masterthought.dlanguage.icons.DLangIcons;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DLangModuleType extends ModuleType<DLangModuleBuilder> {
    @NonNls
    private static final String ID = "DLANGUAGE_MODULE";

    public DLangModuleType() {
        super(ID);
    }

    public static DLangModuleType getInstance() {
        return (DLangModuleType) ModuleTypeManager.getInstance().findByID(ID);
    }

    public static Collection<Module> findModules(Project project) {
        return ModuleUtil.getModulesOfType(project, DLangModuleType.getInstance());
    }

    @NotNull
    @Override
    public DLangModuleBuilder createModuleBuilder() {
        return new DLangModuleBuilder();
    }

    @NotNull
    @Override
    public String getName() {
        return DLangBundle.INSTANCE.message("module.title");
    }

    @NotNull
    @Override
    public String getDescription() {
        return DLangBundle.INSTANCE.message("module.description");
    }

    //    @Override
    public Icon getBigIcon() {
        return DLangIcons.FILE;
    }

    @Override
    public Icon getNodeIcon(@Deprecated boolean b) {
        return DLangIcons.FILE;
    }

    @NotNull
    @Override
    public ModuleWizardStep[] createWizardSteps(@NotNull WizardContext wizardContext,
                                                @NotNull final DLangModuleBuilder moduleBuilder,
                                                @NotNull ModulesProvider modulesProvider) {

        List<ModuleWizardStep> steps = new ArrayList<>();

        ModuleWizardStep setCompiler = new ProjectJdkForModuleStep(wizardContext, DLangSdkType.getInstance()) {
            public void updateDataModel() {
                super.updateDataModel();
                moduleBuilder.setModuleJdk(getJdk());
            }
        };
        ModuleWizardStep setDubBinary = new DubBinaryForModuleStep(wizardContext);
        ModuleWizardStep setDubInit = new DubInitForModuleStep(wizardContext);

        steps.add(setCompiler);

        if ((moduleBuilder.getBuilderId() != null && moduleBuilder.getBuilderId().equals("DLangDubApp"))) {
            steps.add(setDubBinary);
            steps.add(setDubInit);
        }

        return steps.toArray(new ModuleWizardStep[steps.size()]);
    }
}
