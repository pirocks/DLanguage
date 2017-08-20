package net.masterthought.dlanguage.types;

import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.CharsetToolkit;
import com.intellij.psi.PsiFile;
import net.masterthought.dlanguage.DLightPlatformCodeInsightFixtureTestCase;
import org.jetbrains.annotations.NotNull;

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

    @Override
    protected String getTestDataPath() {
        return this.getClass().getClassLoader().getResource("gold/types/" + getTestDirectoryName()).getPath();
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
