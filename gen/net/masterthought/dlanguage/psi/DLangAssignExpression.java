// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface DLangAssignExpression extends PsiElement {

  @NotNull
  List<DLangAddExpression_> getAddExpression_List();

  @NotNull
  List<DLangAndExxpression_> getAndExxpression_List();

  @Nullable
  DLangAssignExpression getAssignExpression();

  @NotNull
  List<DLangCastExpression> getCastExpressionList();

  @Nullable
  DLangConditionalExpression_ getConditionalExpression_();

  @NotNull
  List<DLangDeleteExpression> getDeleteExpressionList();

  @NotNull
  List<DLangEqualExpression> getEqualExpressionList();

  @NotNull
  List<DLangIdentifier> getIdentifierList();

  @NotNull
  List<DLangIdentityExpression> getIdentityExpressionList();

  @NotNull
  List<DLangInExpression> getInExpressionList();

  @NotNull
  List<DLangMulExpression_> getMulExpression_List();

  @Nullable
  DLangOrOrExpression getOrOrExpression();

  @NotNull
  List<DLangPostfixExpression> getPostfixExpressionList();

  @NotNull
  List<DLangPowExpression_> getPowExpression_List();

  @NotNull
  List<DLangRelExpression> getRelExpressionList();

  @NotNull
  List<DLangShiftExpression_> getShiftExpression_List();

  @NotNull
  List<DLangTemplateInstance> getTemplateInstanceList();

  @NotNull
  List<DLangType> getTypeList();

  @NotNull
  List<DLangTypeCtor> getTypeCtorList();

  @NotNull
  List<DLangXorExpression_> getXorExpression_List();

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
