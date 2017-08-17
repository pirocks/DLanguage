package net.masterthought.dlanguage.psi;

import com.intellij.psi.PsiElement;
import net.masterthought.dlanguage.psi.impl.named.ExitPoint;
import org.jetbrains.annotations.Nullable;


public interface DLanguageReturnStatement extends PsiElement, ExitPoint {
    @Nullable
    PsiElement getKW_RETURN();

    @Nullable
    DLanguageExpression getExpression();

    @Nullable
    PsiElement getOP_SCOLON();

}
