package net.masterthought.dlanguage.psi;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.stubs.StubElement;
import net.masterthought.dlanguage.DLangFileType;
import net.masterthought.dlanguage.DLanguage;
import net.masterthought.dlanguage.stubs.DLangFileStub;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class DLangFile extends PsiFileBase {

    public DLangFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, DLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public FileType getFileType() {
        return DLangFileType.INSTANCE;
    }

    @Override
    public String toString() {
        return "D Language File";
    }

    @Override
    public Icon getIcon(int flags) {
        return super.getIcon(flags);
    }

    /**
     * Returns the module name defined in the file or null if it doesn't exist.
     */
    @Nullable
    public String getModuleName() {
        final DLangModuleDeclaration module = findChildByClass(DLangModuleDeclaration.class);
        if (module == null) {
            return null;
        }
        return module.getText().replaceAll(";","").replaceAll("^module\\s+","");
    }

    /**
     * Returns the module name if it exists, otherwise returns the file name.
     */
    @NotNull
    public String getModuleOrFileName() {
        final String moduleName = getModuleName();
        return moduleName == null ? getName() : moduleName;
    }

    /**
     * Generates a stub for the current file, particularly so we can index names.
     */
    @Nullable
    @Override
    public DLangFileStub getStub() {
        final StubElement stub = super.getStub();
        if (stub == null) return null;
        return (DLangFileStub) stub;
    }
}
