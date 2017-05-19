// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface DLanguageEqualExpression extends PsiElement {

  @Nullable
  DLanguageAddExpression getAddExpression();

  @Nullable
  DLanguageMulExpression getMulExpression();

  @Nullable
  DLanguagePostfixExpression getPostfixExpression();

  @Nullable
  DLanguagePowExpression getPowExpression();

  @Nullable
  DLanguageShiftExpression getShiftExpression();

  @Nullable
  DLanguageUnaryExpression getUnaryExpression();

  @Nullable
  PsiElement getOpEqEq();

  @Nullable
  PsiElement getOpNotEq();

}
