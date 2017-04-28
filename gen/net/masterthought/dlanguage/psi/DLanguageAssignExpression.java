// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface DLanguageAssignExpression extends PsiElement {

  @Nullable
  DLanguageAssignExpression getAssignExpression();

  @NotNull
  List<DLanguageCastExpression> getCastExpressionList();

  @NotNull
  List<DLanguageDeleteExpression> getDeleteExpressionList();

  @NotNull
  List<DLanguageExpression> getExpressionList();

  @NotNull
  List<DLanguageIdentifier> getIdentifierList();

  @NotNull
  List<DLanguagePowExpression> getPowExpressionList();

  @NotNull
  List<DLanguageTemplateInstance> getTemplateInstanceList();

  @NotNull
  List<DLanguageType> getTypeList();

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
