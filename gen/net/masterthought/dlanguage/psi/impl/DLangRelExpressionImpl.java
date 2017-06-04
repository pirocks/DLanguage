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

public class DLangRelExpressionImpl extends ASTWrapperPsiElement implements DLangRelExpression {

  public DLangRelExpressionImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DLangVisitor visitor) {
    visitor.visitRelExpression(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DLangVisitor) accept((DLangVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public DLangAddExpression_ getAddExpression_() {
    return PsiTreeUtil.getChildOfType(this, DLangAddExpression_.class);
  }

  @Override
  @Nullable
  public DLangCastExpression getCastExpression() {
    return PsiTreeUtil.getChildOfType(this, DLangCastExpression.class);
  }

  @Override
  @Nullable
  public DLangDeleteExpression getDeleteExpression() {
    return PsiTreeUtil.getChildOfType(this, DLangDeleteExpression.class);
  }

  @Override
  @Nullable
  public DLangIdentifier getIdentifier() {
    return PsiTreeUtil.getChildOfType(this, DLangIdentifier.class);
  }

  @Override
  @Nullable
  public DLangMulExpression_ getMulExpression_() {
    return PsiTreeUtil.getChildOfType(this, DLangMulExpression_.class);
  }

  @Override
  @Nullable
  public DLangPostfixExpression getPostfixExpression() {
    return PsiTreeUtil.getChildOfType(this, DLangPostfixExpression.class);
  }

  @Override
  @Nullable
  public DLangPowExpression_ getPowExpression_() {
    return PsiTreeUtil.getChildOfType(this, DLangPowExpression_.class);
  }

  @Override
  @Nullable
  public DLangShiftExpression_ getShiftExpression_() {
    return PsiTreeUtil.getChildOfType(this, DLangShiftExpression_.class);
  }

  @Override
  @Nullable
  public DLangTemplateInstance getTemplateInstance() {
    return PsiTreeUtil.getChildOfType(this, DLangTemplateInstance.class);
  }

  @Override
  @Nullable
  public DLangType getType() {
    return PsiTreeUtil.getChildOfType(this, DLangType.class);
  }

  @Override
  @Nullable
  public DLangTypeCtor getTypeCtor() {
    return PsiTreeUtil.getChildOfType(this, DLangTypeCtor.class);
  }

  @Override
  @Nullable
  public PsiElement getOpDot() {
    return findChildByType(OP_DOT);
  }

  @Override
  @Nullable
  public PsiElement getOpGt() {
    return findChildByType(OP_GT);
  }

  @Override
  @Nullable
  public PsiElement getOpGtEq() {
    return findChildByType(OP_GT_EQ);
  }

  @Override
  @Nullable
  public PsiElement getOpLess() {
    return findChildByType(OP_LESS);
  }

  @Override
  @Nullable
  public PsiElement getOpLessEq() {
    return findChildByType(OP_LESS_EQ);
  }

  @Override
  @Nullable
  public PsiElement getOpLessGr() {
    return findChildByType(OP_LESS_GR);
  }

  @Override
  @Nullable
  public PsiElement getOpLessGrEq() {
    return findChildByType(OP_LESS_GR_EQ);
  }

  @Override
  @Nullable
  public PsiElement getOpNotGr() {
    return findChildByType(OP_NOT_GR);
  }

  @Override
  @Nullable
  public PsiElement getOpNotGrEq() {
    return findChildByType(OP_NOT_GR_EQ);
  }

  @Override
  @Nullable
  public PsiElement getOpNotLess() {
    return findChildByType(OP_NOT_LESS);
  }

  @Override
  @Nullable
  public PsiElement getOpNotLessEq() {
    return findChildByType(OP_NOT_LESS_EQ);
  }

  @Override
  @Nullable
  public PsiElement getOpParLeft() {
    return findChildByType(OP_PAR_LEFT);
  }

  @Override
  @Nullable
  public PsiElement getOpParRight() {
    return findChildByType(OP_PAR_RIGHT);
  }

  @Override
  @Nullable
  public PsiElement getOpUnord() {
    return findChildByType(OP_UNORD);
  }

  @Override
  @Nullable
  public PsiElement getOpUnordEq() {
    return findChildByType(OP_UNORD_EQ);
  }

}
