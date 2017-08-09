package net.masterthought.dlanguage.psi;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.Nullable;


public interface DLanguageAssocArrayLiteral extends PsiElement {
    @Nullable
    DLanguageKeyValuePairs getKeyValuePairs();

    @Nullable
    PsiElement getOP_BRACKET_RIGHT();

    @Nullable
    PsiElement getOP_BRACKET_LEFT();

}
