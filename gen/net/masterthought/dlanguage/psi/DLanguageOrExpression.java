// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface DLanguageOrExpression extends PsiElement {

  @NotNull
  List<DLanguageAddExpression> getAddExpressionList();

  @NotNull
  List<DLanguageAndExpression> getAndExpressionList();

  @NotNull
  List<DLanguageMulExpression> getMulExpressionList();

  @Nullable
  DLanguageOrExpression getOrExpression();

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

}
