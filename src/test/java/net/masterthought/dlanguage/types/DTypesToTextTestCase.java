package net.masterthought.dlanguage.types;

import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import net.masterthought.dlanguage.psi.DLanguageType;

/**
 * Created by francis on 8/19/2017.
 */
public abstract class DTypesToTextTestCase extends DTypesTestCase {
    /**
     * Sets the expected input and outputs and calls the constructor of the parent.
     */
    protected DTypesToTextTestCase() {
        super("types", "types");
    }

    protected void doTest(final int offset, final String expectedTypeText) {
        final PsiElement element = psiFile.findElementAt(offset);
        final DLanguageType type;
        if (element instanceof DLanguageType) {
            type = (DLanguageType) element;
        } else {
            type = PsiTreeUtil.getTopmostParentOfType(element, DLanguageType.class);
        }
        assertNotNull(type);
        assertEquals(expectedTypeText, TypeUtilsKt.from(type, false).toText());
        assertEquals(expectedTypeText, TypeUtilsKt.from(type, true).toText());
    }
}
