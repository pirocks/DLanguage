package net.masterthought.dlanguage.stubs;

import com.intellij.psi.stubs.PsiFileStubImpl;
import net.masterthought.dlanguage.psi.DLangFile;

/**
 * Basic implementation of a stub for a D file so we can index its contents.
 */
public class DLangFileStub extends PsiFileStubImpl<DLangFile> {
    public DLangFileStub(DLangFile file) {
        super(file);
    }
}

