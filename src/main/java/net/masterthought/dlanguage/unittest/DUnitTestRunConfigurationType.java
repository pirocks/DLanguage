package net.masterthought.dlanguage.unittest;

import com.intellij.execution.configurations.ConfigurationTypeBase;
import com.intellij.openapi.extensions.Extensions;
import net.masterthought.dlanguage.icons.DLangIcons;

public class DUnitTestRunConfigurationType extends ConfigurationTypeBase
{
    protected DUnitTestRunConfigurationType()
    {
        super("D",
                "dUnit Unit Tests",
                "dUnit unit tests run configuration",
            DLangIcons.RUN);
        addFactory(new DUnitTestRunConfigurationFactory(this));
    }

    public static DUnitTestRunConfigurationType getInstance() {
        return Extensions.findExtension(CONFIGURATION_TYPE_EP, DUnitTestRunConfigurationType.class);
    }
}
