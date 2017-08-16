package net.masterthought.dlanguage.stubs;

import com.intellij.psi.stubs.PsiFileStubImpl;
import net.masterthought.dlanguage.psi.DLanguageFile;

/**
 * Basic implementation of a stub for a D file so we can index its contents.
 */
public class DLanguageFileStub extends PsiFileStubImpl<DLanguageFile> {
    private String moduleName;

    public DLanguageFileStub(final DLanguageFile file, final String moduleName) {
        super(file);
        this.moduleName = moduleName;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(final String moduleName) {
        this.moduleName = moduleName;
    }
}

