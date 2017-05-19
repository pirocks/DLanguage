// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface DLanguageAssignExpression extends PsiElement {

  @Nullable
  DLanguageAddExpression getAddExpression();

  @Nullable
  DLanguageAndAndExpression getAndAndExpression();

  @Nullable
  DLanguageAndExpression getAndExpression();

  @Nullable
  DLanguageAssignExpression getAssignExpression();

  @Nullable
  DLanguageCmpExpression getCmpExpression();

  @NotNull
  List<DLanguageConditionalExpression> getConditionalExpressionList();

  @Nullable
  DLanguageMulExpression getMulExpression();

  @Nullable
  DLanguageOrExpression getOrExpression();

  @Nullable
  DLanguageOrOrExpression getOrOrExpression();

  @Nullable
  DLanguagePostfixExpression getPostfixExpression();

  @Nullable
  DLanguagePowExpression getPowExpression();

  @Nullable
  DLanguageShiftExpression getShiftExpression();

  @Nullable
  DLanguageUnaryExpression getUnaryExpression();

  @Nullable
  DLanguageXorExpression getXorExpression();

  @Nullable
  PsiElement getOpAndEq();

  @Nullable
  PsiElement getOpDivEq();

  @Nullable
  PsiElement getOpEq();

  @Nullable
  PsiElement getOpMinusEq();

  @Nullable
  PsiElement getOpModEq();

  @Nullable
  PsiElement getOpMulEq();

  @Nullable
  PsiElement getOpOrEq();

  @Nullable
  PsiElement getOpPlusEq();

  @Nullable
  PsiElement getOpPowEq();

  @Nullable
  PsiElement getOpShLeftEq();

  @Nullable
  PsiElement getOpShRightEq();

  @Nullable
  PsiElement getOpTildaEq();

  @Nullable
  PsiElement getOpUshRightEq();

  @Nullable
  PsiElement getOpXorEq();

}
