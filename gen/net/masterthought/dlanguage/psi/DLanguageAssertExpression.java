package net.masterthought.dlanguage.psi;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;


public interface DLanguageAssertExpression extends PsiElement {
    @Nullable
    public PsiElement getKW_ASSERT();

    @Nullable
    public PsiElement getOP_BRACES_LEFT();

    @Nullable
    public PsiElement getOP_BRACES_RIGHT();

    @NotNull
    public List<DLanguageAssignExpression> getAssignExpressions();

    @NotNull
    public List<PsiElement> getOP_COMMAs();

}
