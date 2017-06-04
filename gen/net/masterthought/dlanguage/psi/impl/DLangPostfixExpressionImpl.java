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

public class DLangPostfixExpressionImpl extends ASTWrapperPsiElement implements DLangPostfixExpression {

  public DLangPostfixExpressionImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DLangVisitor visitor) {
    visitor.visitPostfixExpression(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DLangVisitor) accept((DLangVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public DLangArgumentList getArgumentList() {
    return PsiTreeUtil.getChildOfType(this, DLangArgumentList.class);
  }

  @Override
  @Nullable
  public DLangBasicType getBasicType() {
    return PsiTreeUtil.getChildOfType(this, DLangBasicType.class);
  }

  @Override
  @Nullable
  public DLangIdentifier getIdentifier() {
    return PsiTreeUtil.getChildOfType(this, DLangIdentifier.class);
  }

  @Override
  @Nullable
  public DLangIndexExpression getIndexExpression() {
    return PsiTreeUtil.getChildOfType(this, DLangIndexExpression.class);
  }

  @Override
  @Nullable
  public DLangNewExpression getNewExpression() {
    return PsiTreeUtil.getChildOfType(this, DLangNewExpression.class);
  }

  @Override
  @Nullable
  public DLangPostfixExpression getPostfixExpression() {
    return PsiTreeUtil.getChildOfType(this, DLangPostfixExpression.class);
  }

  @Override
  @Nullable
  public DLangPrimaryExpression getPrimaryExpression() {
    return PsiTreeUtil.getChildOfType(this, DLangPrimaryExpression.class);
  }

  @Override
  @Nullable
  public DLangSliceExpression getSliceExpression() {
    return PsiTreeUtil.getChildOfType(this, DLangSliceExpression.class);
  }

  @Override
  @Nullable
  public DLangTemplateInstance getTemplateInstance() {
    return PsiTreeUtil.getChildOfType(this, DLangTemplateInstance.class);
  }

  @Override
  @Nullable
  public DLangTypeCtors getTypeCtors() {
    return PsiTreeUtil.getChildOfType(this, DLangTypeCtors.class);
  }

  @Override
  @Nullable
  public PsiElement getOpDot() {
    return findChildByType(OP_DOT);
  }

  @Override
  @Nullable
  public PsiElement getOpMinusMinus() {
    return findChildByType(OP_MINUS_MINUS);
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
  public PsiElement getOpPlusPlus() {
    return findChildByType(OP_PLUS_PLUS);
  }

}
