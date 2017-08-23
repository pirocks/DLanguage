package net.masterthought.dlanguage.types;

import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.CharsetToolkit;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.util.PsiTreeUtil;
import net.masterthought.dlanguage.DLightPlatformCodeInsightFixtureTestCase;
import net.masterthought.dlanguage.psi.DLanguageType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;

/**
 * Created by francis on 8/19/2017.
 */
public abstract class DTypesTestCase extends DLightPlatformCodeInsightFixtureTestCase {
    protected PsiFile psiFile = null;

    /**
     * Sets the expected input and outputs and calls the constructor of the parent.
     *
     * @param srcName    Directory name of test inputs.
     * @param expectName Directory name of expected outputs.
     */
    protected DTypesTestCase(final @NotNull String srcName, final @NotNull String expectName) {
        super(srcName, expectName);
    }

    @Nullable
    static DLanguageType getTypeFromOffset(final int offset, final PsiFile psiFile) {
        final PsiElement element = psiFile.findElementAt(offset);
        final DLanguageType type;
        if (element instanceof DLanguageType) {
            type = (DLanguageType) element;
        } else {
            type = PsiTreeUtil.getTopmostParentOfType(element, DLanguageType.class);
        }
        return type;
    }

    @Override
    protected String getTestDataPath() {
        return this.getClass().getClassLoader().getResource(super.srcPath + getTestDirectoryName()).getPath();
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        for (final File file : getTestDataFiles()) {
            String text = FileUtil.loadFile(file, CharsetToolkit.UTF8);
            text = StringUtil.convertLineSeparators(text);
            psiFile = myFixture.configureByText(file.getName(), text);
        }
    }

}
