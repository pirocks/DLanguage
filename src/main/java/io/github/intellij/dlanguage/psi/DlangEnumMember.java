
package io.github.intellij.dlanguage.psi;

import com.intellij.psi.PsiElement;
import com.intellij.psi.StubBasedPsiElement;
import io.github.intellij.dlanguage.psi.interfaces.DNamedElement;
import io.github.intellij.dlanguage.stubs.DlangEnumMemberStub;
import org.jetbrains.annotations.Nullable;


public interface DlangEnumMember extends PsiElement, DNamedElement,
    StubBasedPsiElement<DlangEnumMemberStub> {

    @Nullable
    DlangIdentifier getIdentifier();

    @Nullable
    PsiElement getOP_EQ();

    @Nullable
    DLanguageType getType();

    @Nullable
    DLanguageAssignExpression getAssignExpression();
}
