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

public class DLangPrimaryExpressionImpl extends ASTWrapperPsiElement implements DLangPrimaryExpression {

  public DLangPrimaryExpressionImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DLangVisitor visitor) {
    visitor.visitPrimaryExpression(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DLangVisitor) accept((DLangVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public DLangArrayLiteral getArrayLiteral() {
    return PsiTreeUtil.getChildOfType(this, DLangArrayLiteral.class);
  }

  @Override
  @Nullable
  public DLangAssertExpression getAssertExpression() {
    return PsiTreeUtil.getChildOfType(this, DLangAssertExpression.class);
  }

  @Override
  @Nullable
  public DLangAssocArrayLiteral getAssocArrayLiteral() {
    return PsiTreeUtil.getChildOfType(this, DLangAssocArrayLiteral.class);
  }

  @Override
  @Nullable
  public DLangBasicTypeX getBasicTypeX() {
    return PsiTreeUtil.getChildOfType(this, DLangBasicTypeX.class);
  }

  @Override
  @Nullable
  public DLangCommaExpression getCommaExpression() {
    return PsiTreeUtil.getChildOfType(this, DLangCommaExpression.class);
  }

  @Override
  @Nullable
  public DLangFunctionLiteral getFunctionLiteral() {
    return PsiTreeUtil.getChildOfType(this, DLangFunctionLiteral.class);
  }

  @Override
  @Nullable
  public DLangIdentifier getIdentifier() {
    return PsiTreeUtil.getChildOfType(this, DLangIdentifier.class);
  }

  @Override
  @Nullable
  public DLangImportExpression getImportExpression() {
    return PsiTreeUtil.getChildOfType(this, DLangImportExpression.class);
  }

  @Override
  @Nullable
  public DLangIsExpression getIsExpression() {
    return PsiTreeUtil.getChildOfType(this, DLangIsExpression.class);
  }

  @Override
  @Nullable
  public DLangMixinExpression getMixinExpression() {
    return PsiTreeUtil.getChildOfType(this, DLangMixinExpression.class);
  }

  @Override
  @Nullable
  public DLangNewExpressionWithArgs getNewExpressionWithArgs() {
    return PsiTreeUtil.getChildOfType(this, DLangNewExpressionWithArgs.class);
  }

  @Override
  @Nullable
  public DLangSpecialKeyword getSpecialKeyword() {
    return PsiTreeUtil.getChildOfType(this, DLangSpecialKeyword.class);
  }

  @Override
  @Nullable
  public DLangStringLiterals getStringLiterals() {
    return PsiTreeUtil.getChildOfType(this, DLangStringLiterals.class);
  }

  @Override
  @Nullable
  public DLangTemplateInstance getTemplateInstance() {
    return PsiTreeUtil.getChildOfType(this, DLangTemplateInstance.class);
  }

  @Override
  @Nullable
  public DLangTraitsExpression getTraitsExpression() {
    return PsiTreeUtil.getChildOfType(this, DLangTraitsExpression.class);
  }

  @Override
  @Nullable
  public DLangType getType() {
    return PsiTreeUtil.getChildOfType(this, DLangType.class);
  }

  @Override
  @Nullable
  public DLangTypeidExpression getTypeidExpression() {
    return PsiTreeUtil.getChildOfType(this, DLangTypeidExpression.class);
  }

  @Override
  @Nullable
  public DLangTypeof getTypeof() {
    return PsiTreeUtil.getChildOfType(this, DLangTypeof.class);
  }

  @Override
  @Nullable
  public PsiElement getCharacterLiteral() {
    return findChildByType(CHARACTER_LITERAL);
  }

  @Override
  @Nullable
  public PsiElement getFloatLiteral() {
    return findChildByType(FLOAT_LITERAL);
  }

  @Override
  @Nullable
  public PsiElement getIntegerLiteral() {
    return findChildByType(INTEGER_LITERAL);
  }

  @Override
  @Nullable
  public PsiElement getKwFalse() {
    return findChildByType(KW_FALSE);
  }

  @Override
  @Nullable
  public PsiElement getKwNull() {
    return findChildByType(KW_NULL);
  }

  @Override
  @Nullable
  public PsiElement getKwSuper() {
    return findChildByType(KW_SUPER);
  }

  @Override
  @Nullable
  public PsiElement getKwThis() {
    return findChildByType(KW_THIS);
  }

  @Override
  @Nullable
  public PsiElement getKwTrue() {
    return findChildByType(KW_TRUE);
  }

  @Override
  @Nullable
  public PsiElement getOpDollar() {
    return findChildByType(OP_DOLLAR);
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
