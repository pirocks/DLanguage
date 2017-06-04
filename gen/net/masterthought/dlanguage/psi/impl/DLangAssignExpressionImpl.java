// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static net.masterthought.dlanguage.psi.DLangTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import net.masterthought.dlanguage.psi.*;

public class DLangAssignExpressionImpl extends ASTWrapperPsiElement implements DLangAssignExpression {

  public DLangAssignExpressionImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DLangVisitor visitor) {
    visitor.visitAssignExpression(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DLangVisitor) accept((DLangVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<DLangAddExpression_> getAddExpression_List() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, DLangAddExpression_.class);
  }

  @Override
  @NotNull
  public List<DLangAndExxpression_> getAndExxpression_List() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, DLangAndExxpression_.class);
  }

  @Override
  @Nullable
  public DLangAssignExpression getAssignExpression() {
    return PsiTreeUtil.getChildOfType(this, DLangAssignExpression.class);
  }

  @Override
  @NotNull
  public List<DLangCastExpression> getCastExpressionList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, DLangCastExpression.class);
  }

  @Override
  @Nullable
  public DLangConditionalExpression_ getConditionalExpression_() {
    return PsiTreeUtil.getChildOfType(this, DLangConditionalExpression_.class);
  }

  @Override
  @NotNull
  public List<DLangDeleteExpression> getDeleteExpressionList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, DLangDeleteExpression.class);
  }

  @Override
  @NotNull
  public List<DLangEqualExpression> getEqualExpressionList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, DLangEqualExpression.class);
  }

  @Override
  @NotNull
  public List<DLangIdentifier> getIdentifierList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, DLangIdentifier.class);
  }

  @Override
  @NotNull
  public List<DLangIdentityExpression> getIdentityExpressionList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, DLangIdentityExpression.class);
  }

  @Override
  @NotNull
  public List<DLangInExpression> getInExpressionList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, DLangInExpression.class);
  }

  @Override
  @NotNull
  public List<DLangMulExpression_> getMulExpression_List() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, DLangMulExpression_.class);
  }

  @Override
  @Nullable
  public DLangOrOrExpression getOrOrExpression() {
    return PsiTreeUtil.getChildOfType(this, DLangOrOrExpression.class);
  }

  @Override
  @NotNull
  public List<DLangPostfixExpression> getPostfixExpressionList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, DLangPostfixExpression.class);
  }

  @Override
  @NotNull
  public List<DLangPowExpression_> getPowExpression_List() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, DLangPowExpression_.class);
  }

  @Override
  @NotNull
  public List<DLangRelExpression> getRelExpressionList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, DLangRelExpression.class);
  }

  @Override
  @NotNull
  public List<DLangShiftExpression_> getShiftExpression_List() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, DLangShiftExpression_.class);
  }

  @Override
  @NotNull
  public List<DLangTemplateInstance> getTemplateInstanceList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, DLangTemplateInstance.class);
  }

  @Override
  @NotNull
  public List<DLangType> getTypeList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, DLangType.class);
  }

  @Override
  @NotNull
  public List<DLangTypeCtor> getTypeCtorList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, DLangTypeCtor.class);
  }

  @Override
  @NotNull
  public List<DLangXorExpression_> getXorExpression_List() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, DLangXorExpression_.class);
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
