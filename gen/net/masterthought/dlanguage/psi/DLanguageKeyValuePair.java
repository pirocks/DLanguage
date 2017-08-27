package net.masterthought.dlanguage.psi;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;


public interface DLanguageKeyValuePair extends PsiElement {
    @NotNull
    public List<DLanguageAssignExpression> getAssignExpressions();

    @Nullable
    public PsiElement getOP_COLON();

}
