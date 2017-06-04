package net.masterthought.dlanguage.run;

import com.intellij.application.options.ModulesComboBox;
import com.intellij.execution.configuration.EnvironmentVariablesComponent;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.ui.TextComponentAccessor;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.ui.RawCommandLineEditor;
import net.masterthought.dlanguage.DLangBundle;
import net.masterthought.dlanguage.module.DLangModuleType;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.Map;


public class DLangRunDubConfigurationEditor extends SettingsEditor<DLangRunDubConfiguration> {

    private JTabbedPane myMainPanel;
    private JPanel panel1;
    private JPanel tabGeneral;

    //General tab
    private ModulesComboBox comboModules;
    private JComboBox comboGeneralDubOptions;

    // Common
    private JCheckBox cbRdmd;
    private JCheckBox cbForce;
    private JCheckBox cbNoDeps;
    private JCheckBox cbForceRemove;
    private JCheckBox cbCombined;
    private JCheckBox cbParallel;
    private JTextField tfBuild;
    private JTextField tfConfig;
    private JTextField tfArch;
    private JTextField tfDebug;
    private JTextField tfCompiler;
    private JComboBox tfBuildMode;
    private JCheckBox cbVerbose;
    private JCheckBox cbQuiet;

    // Run
    private JCheckBox cbTempBuild;

    // Test
    private JTextField tfMainFile;
    private JCheckBox cbCoverage;

    private TextFieldWithBrowseButton pathWorkingDir;
    private RawCommandLineEditor textParameters;
    private EnvironmentVariablesComponent envVariables;

    /**
     * Update editor UI with data of DLangRunDubConfiguration.
     * All components must be changed according to "config" data.
     */
    @Override
    protected void resetEditorFrom(DLangRunDubConfiguration config) {
        resetGeneralTabForm(config);
    }


    /**
     * Save state of editor UI to DLangRunDubConfiguration instance.
     */
    @Override
    protected void applyEditorTo(DLangRunDubConfiguration config) throws ConfigurationException {
        applyGeneralTabForm(config);
    }


    @NotNull
    @Override
    protected JComponent createEditor() {
        FileChooserDescriptor fcd = FileChooserDescriptorFactory.createSingleFolderDescriptor();
        fcd.setShowFileSystemRoots(true);
        fcd.setTitle(DLangBundle.INSTANCE.message("dmd.run.config.selectworkingdir.title"));
        fcd.setDescription(DLangBundle.INSTANCE.message("dmd.run.config.selectworkingdir.description"));
        fcd.setHideIgnored(false);

        pathWorkingDir.addActionListener(new TextFieldWithBrowseButton.BrowseFolderActionListener<>(fcd.getTitle(), fcd.getDescription(),
            pathWorkingDir, null, fcd, TextComponentAccessor.TEXT_FIELD_WHOLE_TEXT));

        return myMainPanel;
    }

    @Override
    protected void disposeEditor() {
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    private void applyGeneralTabForm(DLangRunDubConfiguration config) {
        config.setModule(comboModules.getSelectedModule());
        config.setGeneralDubOptions(comboGeneralDubOptions.getSelectedIndex());

        boolean inBuildState = comboGeneralDubOptions.getSelectedIndex() == 0;
        boolean inRunState = comboGeneralDubOptions.getSelectedIndex() == 1;
        boolean inTestState = comboGeneralDubOptions.getSelectedIndex() == 2;

        cbTempBuild.setEnabled(inRunState);
        cbCoverage.setEnabled(inTestState);
        tfMainFile.setEnabled(inTestState);
        cbRdmd.setEnabled(inBuildState || inRunState);
        cbParallel.setEnabled(inBuildState || inRunState);

        config.setCbRdmd(cbRdmd.isSelected());
        config.setCbNoDeps(cbNoDeps.isSelected());
        config.setCbForce(cbForce.isSelected());
        config.setCbForceRemove(cbForceRemove.isSelected());
        config.setCbCombined(cbCombined.isSelected());
        config.setCbParallel(cbParallel.isSelected());
        config.setTfBuild(tfBuild.getText());
        config.setTfConfig(tfConfig.getText());
        config.setTfArch(tfArch.getText());
        config.setTfDebug(tfDebug.getText());
        config.setTfCompiler(tfCompiler.getText());
        config.setBuildMode(tfBuildMode.getSelectedIndex());
        config.setVerbose(cbVerbose.isSelected());
        config.setQuiet(cbQuiet.isSelected());

        config.setCbTempBuild(cbTempBuild.isSelected());
        config.setCbCoverage(cbCoverage.isSelected());
        config.setTfMainFile(tfMainFile.getText());

        config.setWorkingDir(pathWorkingDir.getText());
        config.setAdditionalParams(textParameters.getText());
        config.setEnvVars(envVariables.getEnvs());
    }

    private void resetGeneralTabForm(DLangRunDubConfiguration config) {
        comboModules.fillModules(config.getProject(), DLangModuleType.getInstance());
        comboModules.setSelectedModule(config.getConfigurationModule().getModule());

        comboGeneralDubOptions.setSelectedIndex(config.getGeneralDubOptions());

        cbRdmd.setSelected(config.isCbRdmd());
        cbNoDeps.setSelected(config.isCbNoDeps());
        cbForce.setSelected(config.isCbForce());
        cbForceRemove.setSelected(config.isCbForceRemove());
        cbCombined.setSelected(config.isCbCombined());
        cbParallel.setSelected(config.isCbParallel());
        cbVerbose.setSelected(config.isVerbose());
        cbQuiet.setSelected(config.isQuiet());

        tfBuild.setText(config.getTfBuild());
        tfConfig.setText(config.getTfConfig());
        tfArch.setText(config.getTfArch());
        tfDebug.setText(config.getTfDebug());
        tfCompiler.setText(config.getTfCompiler());
        tfBuildMode.setSelectedIndex(config.getBuildMode());

        cbTempBuild.setSelected(config.isCbTempBuild());
        tfMainFile.setText(config.getTfMainFile());
        cbCoverage.setSelected(config.isCbCoverage());

        pathWorkingDir.setText(config.getWorkingDir());
        textParameters.setText(config.getAdditionalParams());
        Map<String, String> envVars = config.getEnvVars();
        if (envVars != null) {
            envVariables.setEnvs(config.getEnvVars());
        }
    }

}
