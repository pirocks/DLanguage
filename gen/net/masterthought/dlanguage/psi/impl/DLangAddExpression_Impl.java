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

public class DLangAddExpression_Impl extends ASTWrapperPsiElement implements DLangAddExpression_ {

  public DLangAddExpression_Impl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DLangVisitor visitor) {
    visitor.visitAddExpression_(this);
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
  public PsiElement getOpParLeft() {
    return findChildByType(OP_PAR_LEFT);
  }

  @Override
  @Nullable
  public PsiElement getOpParRight() {
    return findChildByType(OP_PAR_RIGHT);
  }

}
