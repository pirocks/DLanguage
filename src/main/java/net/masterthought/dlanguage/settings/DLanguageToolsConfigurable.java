package net.masterthought.dlanguage.settings;

import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SearchableConfigurable;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.ui.RawCommandLineEditor;
import com.intellij.ui.TextAccessor;
import com.intellij.util.messages.Topic;
import net.masterthought.dlanguage.utils.ExecUtil;
import net.masterthought.dlanguage.utils.GuiUtil;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;

/**
 * The "D Tools" option in Preferences->Project Settings.
 */
public class DLanguageToolsConfigurable implements SearchableConfigurable {

    public static final String D_TOOLS_ID = "D Tools";
    private static final Logger LOG = Logger.getInstance(DLanguageToolsConfigurable.class);
    private final PropertiesComponent propertiesComponent;
    private final List<Tool> properties;
    // Swing components.
    private JPanel mainPanel;
    private TextFieldWithBrowseButton dubPath;
    private RawCommandLineEditor dubFlags;
    private JButton dubAutoFind;
    private JTextField dubVersion;
    private TextFieldWithBrowseButton dscannerPath;
    private RawCommandLineEditor dscannerFlags;
    private JButton dscannerAutoFind;
    private JTextField dscannerVersion;
    private TextFieldWithBrowseButton dcdPath;
    private RawCommandLineEditor dcdFlags;
    private JButton dcdAutoFind;
    private JTextField dcdVersion;
    private TextFieldWithBrowseButton dcdClientPath;
    private RawCommandLineEditor dcdClientFlags;
    private JButton dcdClientAutoFind;
    private JTextField dcdClientVersion;
    private TextFieldWithBrowseButton dFormatPath;
    private RawCommandLineEditor dFormatFlags;
    private JButton dFormatAutoFind;
    private JTextField dFormatVersion;
    private TextFieldWithBrowseButton dFixPath;
    private RawCommandLineEditor dFixFlags;
    private JButton dFixAutoFind;
    private JTextField dFixVersion;
    private JButton dubBuild;
    private JButton dScannerBuild;
    private JButton dcdServerBuild;
    private JButton dcdClientBuild;
    private JButton dFormatBuild;
    private JButton dFixBuild;

    public DLanguageToolsConfigurable(@NotNull final Project project) {
        this.propertiesComponent = PropertiesComponent.getInstance(project);
        properties = Arrays.asList(
            new Tool(project, "dub", ToolKey.DUB_KEY, dubPath, dubFlags,
                dubAutoFind, dubVersion, dubBuild),
            new Tool(project, "dscanner", ToolKey.DSCANNER_KEY, dscannerPath, dscannerFlags,
                dscannerAutoFind, dscannerVersion, dScannerBuild),
            new Tool(project, "dcd-server", ToolKey.DCD_SERVER_KEY, dcdPath, dcdFlags,
                dcdAutoFind, dcdVersion, "--version", dcdServerBuild, SettingsChangeNotifier.DCD_TOPIC),
            new Tool(project, "dcd-client", ToolKey.DCD_CLIENT_KEY, dcdClientPath, dcdClientFlags,
                dcdClientAutoFind, dcdClientVersion, dcdClientBuild),
            new Tool(project, "dfmt", ToolKey.DFORMAT_KEY, dFormatPath, dFormatFlags,
                dFormatAutoFind, dFormatVersion, dFormatBuild),
            new Tool(project, "dfix", ToolKey.DFIX_KEY, dFixPath, dFixFlags,
                dFixAutoFind, dFixVersion, dFixBuild)
        );
    }

    /**
     * Heuristically finds the version number. Current implementation is the
     * identity function since cabal plays nice.
     */
    private static String getVersion(final String cmd, final String versionFlag) {
        return ExecUtil.readCommandLine(null, cmd, versionFlag);
    }

    @NotNull
    @Override
    public String getId() {
        return D_TOOLS_ID;
    }

    @Nullable
    @Override
    public Runnable enableSearch(final String s) {
        // TODO
        return null;
    }

    @Nls
    @Override
    public String getDisplayName() {
        return D_TOOLS_ID;
    }

    @Nullable
    @Override
    public String getHelpTopic() {
        return null;
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        return mainPanel;
    }

    /**
     * Enables the apply button if anything changed.
     */
    @Override
    public boolean isModified() {
        for (final Property property : properties) {
            if (property.isModified()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Triggered when the user pushes the apply button.
     */
    @Override
    public void apply() throws ConfigurationException {
        updateVersionInfoFields();
        saveState();
    }

    /**
     * Triggered when the user pushes the cancel button.
     */
    @Override
    public void reset() {
        restoreState();
    }

    @Override
    public void disposeUIResources() {

    }

    /**
     * Updates the version info fields for all files configured.
     */
    private void updateVersionInfoFields() {
        for (Property property : properties) {
            if (property instanceof Versioned) {
                ((Versioned) property).updateVersion();
            }
        }
    }

    /**
     * Persistent save of the current state.
     */
    private void saveState() {
        LOG.info("Saving D Tools Config");
        for (Property property : properties) {
            property.saveState();
        }
    }

    /**
     * Restore components to the initial state.
     */
    private void restoreState() {
        LOG.info("Restore D Tools Config");
        for (Property property : properties) {
            property.restoreState();
        }
    }

    interface Property {
        boolean isModified();

        void saveState();

        void restoreState();
    }

    interface Versioned {
        void updateVersion();
    }

    /**
     * Manages the state of a PropertyComponent and its respective field.
     */
    class PropertyField implements Property {
        public final TextAccessor field;
        public final String propertyKey;
        public String oldValue;

        PropertyField(@NotNull String propertyKey, @NotNull TextAccessor field) {
            this(propertyKey, field, "");
        }

        PropertyField(@NotNull String propertyKey, @NotNull TextAccessor field, @NotNull String defaultValue) {
            this.propertyKey = propertyKey;
            this.field = field;
            this.oldValue = propertiesComponent.getValue(propertyKey, defaultValue);
            field.setText(oldValue);
        }

        public boolean isModified() {
            return !field.getText().equals(oldValue);
        }

        public void saveState() {
            propertiesComponent.setValue(propertyKey, oldValue = field.getText());
        }

        public void restoreState() {
            field.setText(oldValue);
        }
    }

    /**
     * Manages the group of fields which reside to a particular tool.
     */
    class Tool implements Property, Versioned {
        public final Project project;
        public final String command;
        public final ToolKey key;
        public final TextFieldWithBrowseButton pathField;
        public final RawCommandLineEditor flagsField;
        public final JTextField versionField;
        public final String versionParam;
        public final JButton autoFindButton;
        public final JButton autoBuildButton;
        public final List<PropertyField> propertyFields;
        public final
        @Nullable
        Topic<SettingsChangeNotifier> topic;
        private final
        @Nullable
        SettingsChangeNotifier publisher;

        Tool(final Project project, final String command, final ToolKey key, final TextFieldWithBrowseButton pathField,
             final RawCommandLineEditor flagsField, final JButton autoFindButton, final JTextField versionField, final JButton autoBuildButton) {
            this(project, command, key, pathField, flagsField, autoFindButton, versionField, "--version", autoBuildButton);
        }

        Tool(final Project project, final String command, final ToolKey key, final TextFieldWithBrowseButton pathField,
             final RawCommandLineEditor flagsField, final JButton autoFindButton, final JTextField versionField, final String versionParam, final JButton autoBuildButton) {
            this(project, command, key, pathField, flagsField, autoFindButton, versionField, versionParam, autoBuildButton, null);
        }

        Tool(final Project project, final String command, final ToolKey key, final TextFieldWithBrowseButton pathField, final
        RawCommandLineEditor flagsField, final JButton autoFindButton, final JTextField versionField, final String versionParam,
             final JButton autoBuildButton, @Nullable final Topic<SettingsChangeNotifier> topic) {
            this.project = project;
            this.command = command;
            this.key = key;
            this.pathField = pathField;
            this.flagsField = flagsField;
            this.versionField = versionField;
            this.versionParam = versionParam;
            this.autoFindButton = autoFindButton;
            this.autoBuildButton = autoBuildButton;
            this.topic = topic;
            this.publisher = topic == null ? null : project.getMessageBus().syncPublisher(topic);

            this.propertyFields = Arrays.asList(
                new PropertyField(key.pathKey, pathField),
                new PropertyField(key.flagsKey, flagsField));

            GuiUtil.addFolderListener(pathField, command);
            GuiUtil.addApplyPathAction(autoFindButton, pathField, command);
            GuiUtil.addBuildAction(autoBuildButton, key);
            updateVersion();
        }

        public void updateVersion() {
            final String pathText = pathField.getText();
            if (pathText.isEmpty()) {
                versionField.setText("");
            } else {
                versionField.setText(getVersion(pathText, versionParam));
            }
        }

        public boolean isModified() {
            for (final PropertyField propertyField : propertyFields) {
                if (propertyField.isModified()) {
                    return true;
                }
            }
            return false;
        }

        public void saveState() {
            if (isModified() && publisher != null) {
                publisher.onSettingsChanged(new ToolSettings(pathField.getText(), flagsField.getText()));
            }
            for (final PropertyField propertyField : propertyFields) {
                propertyField.saveState();
            }
        }

        public void restoreState() {
            for (final PropertyField propertyField : propertyFields) {
                propertyField.restoreState();
            }
        }
    }
}
