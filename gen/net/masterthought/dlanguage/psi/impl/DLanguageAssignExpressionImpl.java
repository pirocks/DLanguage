// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static net.masterthought.dlanguage.psi.DLanguageTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import net.masterthought.dlanguage.psi.*;

public class DLanguageAssignExpressionImpl extends ASTWrapperPsiElement implements DLanguageAssignExpression {

  public DLanguageAssignExpressionImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DLanguageVisitor visitor) {
    visitor.visitAssignExpression(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DLanguageVisitor) accept((DLanguageVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public DLanguageAddExpression getAddExpression() {
    return PsiTreeUtil.getChildOfType(this, DLanguageAddExpression.class);
  }

  @Override
  @Nullable
  public DLanguageAndAndExpression getAndAndExpression() {
    return PsiTreeUtil.getChildOfType(this, DLanguageAndAndExpression.class);
  }

  @Override
  @Nullable
  public DLanguageAndExpression getAndExpression() {
    return PsiTreeUtil.getChildOfType(this, DLanguageAndExpression.class);
  }

  @Override
  @Nullable
  public DLanguageAssignExpression getAssignExpression() {
    return PsiTreeUtil.getChildOfType(this, DLanguageAssignExpression.class);
  }

  @Override
  @Nullable
  public DLanguageCmpExpression getCmpExpression() {
    return PsiTreeUtil.getChildOfType(this, DLanguageCmpExpression.class);
  }

  @Override
  @NotNull
  public List<DLanguageConditionalExpression> getConditionalExpressionList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, DLanguageConditionalExpression.class);
  }

  @Override
  @Nullable
  public DLanguageMulExpression getMulExpression() {
    return PsiTreeUtil.getChildOfType(this, DLanguageMulExpression.class);
  }

  @Override
  @Nullable
  public DLanguageOrExpression getOrExpression() {
    return PsiTreeUtil.getChildOfType(this, DLanguageOrExpression.class);
  }

  @Override
  @Nullable
  public DLanguageOrOrExpression getOrOrExpression() {
    return PsiTreeUtil.getChildOfType(this, DLanguageOrOrExpression.class);
  }

  @Override
  @Nullable
  public DLanguagePostfixExpression getPostfixExpression() {
    return PsiTreeUtil.getChildOfType(this, DLanguagePostfixExpression.class);
  }

  @Override
  @Nullable
  public DLanguagePowExpression getPowExpression() {
    return PsiTreeUtil.getChildOfType(this, DLanguagePowExpression.class);
  }

  @Override
  @Nullable
  public DLanguageShiftExpression getShiftExpression() {
    return PsiTreeUtil.getChildOfType(this, DLanguageShiftExpression.class);
  }

  @Override
  @Nullable
  public DLanguageUnaryExpression getUnaryExpression() {
    return PsiTreeUtil.getChildOfType(this, DLanguageUnaryExpression.class);
  }

  @Override
  @Nullable
  public DLanguageXorExpression getXorExpression() {
    return PsiTreeUtil.getChildOfType(this, DLanguageXorExpression.class);
  }

  @Override
  @Nullable
  public PsiElement getOpAndEq() {
    return findChildByType(OP_AND_EQ);
  }

  @Override
  @Nullable
  public PsiElement getOpDivEq() {
    return findChildByType(OP_DIV_EQ);
  }

  @Override
  @Nullable
  public PsiElement getOpEq() {
    return findChildByType(OP_EQ);
  }

  @Override
  @Nullable
  public PsiElement getOpMinusEq() {
    return findChildByType(OP_MINUS_EQ);
  }

  @Override
  @Nullable
  public PsiElement getOpModEq() {
    return findChildByType(OP_MOD_EQ);
  }

  @Override
  @Nullable
  public PsiElement getOpMulEq() {
    return findChildByType(OP_MUL_EQ);
  }

  @Override
  @Nullable
  public PsiElement getOpOrEq() {
    return findChildByType(OP_OR_EQ);
  }

  @Override
  @Nullable
  public PsiElement getOpPlusEq() {
    return findChildByType(OP_PLUS_EQ);
  }

  @Override
  @Nullable
  public PsiElement getOpPowEq() {
    return findChildByType(OP_POW_EQ);
  }

  @Override
  @Nullable
  public PsiElement getOpShLeftEq() {
    return findChildByType(OP_SH_LEFT_EQ);
  }

  @Override
  @Nullable
  public PsiElement getOpShRightEq() {
    return findChildByType(OP_SH_RIGHT_EQ);
  }

  @Override
  @Nullable
  public PsiElement getOpTildaEq() {
    return findChildByType(OP_TILDA_EQ);
  }

  @Override
  @Nullable
  public PsiElement getOpUshRightEq() {
    return findChildByType(OP_USH_RIGHT_EQ);
  }

  @Override
  @Nullable
  public PsiElement getOpXorEq() {
    return findChildByType(OP_XOR_EQ);
  }

}
