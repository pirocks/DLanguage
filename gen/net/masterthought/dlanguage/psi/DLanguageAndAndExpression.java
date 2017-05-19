// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface DLanguageAndAndExpression extends PsiElement {

  @NotNull
  List<DLanguageAddExpression> getAddExpressionList();

  @Nullable
  DLanguageAndAndExpression getAndAndExpression();

  @NotNull
  List<DLanguageAndExpression> getAndExpressionList();

  @NotNull
  List<DLanguageCmpExpression> getCmpExpressionList();

  @NotNull
  List<DLanguageMulExpression> getMulExpressionList();

  @NotNull
  List<DLanguageOrExpression> getOrExpressionList();

  @NotNull
  List<DLanguagePostfixExpression> getPostfixExpressionList();

  @NotNull
  List<DLanguagePowExpression> getPowExpressionList();

  @NotNull
  List<DLanguageShiftExpression> getShiftExpressionList();

  @NotNull
  List<DLanguageUnaryExpression> getUnaryExpressionList();

  @NotNull
  List<DLanguageXorExpression> getXorExpressionList();

  @Nullable
  PsiElement getOpBoolAnd();

}
