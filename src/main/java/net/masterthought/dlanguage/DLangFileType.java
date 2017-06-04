package net.masterthought.dlanguage;

import com.intellij.openapi.fileTypes.LanguageFileType;
import net.masterthought.dlanguage.icons.DLangIcons;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class DLangFileType extends LanguageFileType {

    public static final DLangFileType INSTANCE = new DLangFileType();
    public static final String DEFAULT_EXTENSION = "d";

    private DLangFileType() {
        super(DLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public String getName() {
        return "D file";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "D language file";
    }

    @NotNull
    @Override
    public String getDefaultExtension() {
        return DEFAULT_EXTENSION;
    }

    @Nullable
    @Override
    public Icon getIcon() {
        return DLangIcons.FILE;
    }

}
