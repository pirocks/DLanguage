package net.masterthought.dlanguage.psi;

import com.intellij.psi.PsiElement;
import com.intellij.psi.StubBasedPsiElement;
import net.masterthought.dlanguage.psi.interfaces.DNamedElement;
import net.masterthought.dlanguage.stubs.DLanguageSuperIdentifierStub;

/**
 * Created by francis on 8/14/2017.
 */
public interface DLanguageSuperIdentifier extends PsiElement, DNamedElement, StubBasedPsiElement<DLanguageSuperIdentifierStub> {

}
