// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface DLanguagePowExpression extends PsiElement {

  @NotNull
  List<DLanguagePostfixExpression> getPostfixExpressionList();

  @NotNull
  List<DLanguagePowExpression> getPowExpressionList();

  @Nullable
  DLanguageUnaryExpression getUnaryExpression();

  @Nullable
  PsiElement getOpPow();

}
