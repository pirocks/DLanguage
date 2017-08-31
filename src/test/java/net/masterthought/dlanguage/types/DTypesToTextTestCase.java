package net.masterthought.dlanguage.types;

import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import net.masterthought.dlanguage.psi.DLanguageType;
import net.masterthought.dlanguage.psi.DLanguageVariableDeclaration;

/**
 * Created by francis on 8/19/2017.
 */
public abstract class DTypesToTextTestCase extends DTypesTestCase {
    /**
     * Sets the expected input and outputs and calls the constructor of the parent.
     */
    protected DTypesToTextTestCase() {
        super("types/text/", "types/text/");
    }

    protected void doTestVariable(final int offset, final String expectedTypeText) {
        final PsiElement element = psiFile.findElementAt(offset);
        final DType type = PsiTreeUtil.getTopmostParentOfType(element, DLanguageVariableDeclaration.class).getDeclarators().get(0).getTypeOf();
//        if (element instanceof DLanguageType) {
//            type = (DLanguageType) element;
//        } else {
//            type = PsiTreeUtil.getTopmostParentOfType(element, DLanguageType.class);
//        }
        assertNotNull(type);
        assertEquals(expectedTypeText, type.toText());
        //todo force stubs and see if things work.
    }

    protected void doTestBasic(final int offset, final String expectedTypeText) {
        final PsiElement element = psiFile.findElementAt(offset);
        final DLanguageType type;
        if (element instanceof DLanguageType) {
            type = (DLanguageType) element;
        } else {
            type = PsiTreeUtil.getTopmostParentOfType(element, DLanguageType.class);
        }
        assertNotNull(type);
        assertEquals(expectedTypeText, DTypeUtilsKt.from(type, false, (Mods) null).toText());
        final DType dType = DTypeUtilsKt.from(type, true, (Mods) null);
        assertEquals(expectedTypeText, dType.toText());
        //todo force stubs and see if things work.
    }
}
