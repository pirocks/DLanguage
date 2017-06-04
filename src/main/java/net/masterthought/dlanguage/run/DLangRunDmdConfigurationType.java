package net.masterthought.dlanguage.run;

import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.ConfigurationType;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.openapi.project.Project;
import net.masterthought.dlanguage.DLangBundle;
import net.masterthought.dlanguage.icons.DLangIcons;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class DLangRunDmdConfigurationType implements ConfigurationType {
    private final DLangFactory myConfigurationFactory;

    public DLangRunDmdConfigurationType() {
        myConfigurationFactory = new DLangFactory(this);
    }

    @Override
    public String getDisplayName() {
        return DLangBundle.INSTANCE.message("run.dmd.text");
    }

    @Override
    public String getConfigurationTypeDescription() {
        return DLangBundle.INSTANCE.message("run.dmd.descr");
    }

    @Override
    public Icon getIcon() {
        return DLangIcons.FILE;
    }

    @NotNull
    @Override
    public String getId() {
        return "DLangRunDmdConfiguration";
    }

    @Override
    public ConfigurationFactory[] getConfigurationFactories() {
        return new ConfigurationFactory[]{myConfigurationFactory};
    }

    private static class DLangFactory extends ConfigurationFactory {
        public DLangFactory(ConfigurationType type) {
            super(type);
        }

        public RunConfiguration createTemplateConfiguration(Project project) {
            return new DLangRunDmdConfiguration("Compile with DMD", project, this);
        }
    }
}
