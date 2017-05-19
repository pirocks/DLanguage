// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface DLanguageShiftExpression extends PsiElement {

  @NotNull
  List<DLanguageAddExpression> getAddExpressionList();

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
  PsiElement getOpShLeft();

  @Nullable
  PsiElement getOpShRight();

  @Nullable
  PsiElement getOpUshRight();

}
