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

public class DLangTemplateSingleArgumentImpl extends ASTWrapperPsiElement implements DLangTemplateSingleArgument {

  public DLangTemplateSingleArgumentImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DLangVisitor visitor) {
    visitor.visitTemplateSingleArgument(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DLangVisitor) accept((DLangVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public DLangAssignExpression getAssignExpression() {
    return PsiTreeUtil.getChildOfType(this, DLangAssignExpression.class);
  }

  @Override
  @Nullable
  public DLangBasicTypeX getBasicTypeX() {
    return PsiTreeUtil.getChildOfType(this, DLangBasicTypeX.class);
  }

  @Override
  @Nullable
  public DLangIdentifierList getIdentifierList() {
    return PsiTreeUtil.getChildOfType(this, DLangIdentifierList.class);
  }

  @Override
  @Nullable
  public DLangSpecialKeyword getSpecialKeyword() {
    return PsiTreeUtil.getChildOfType(this, DLangSpecialKeyword.class);
  }

  @Override
  @Nullable
  public DLangStringLiteral getStringLiteral() {
    return PsiTreeUtil.getChildOfType(this, DLangStringLiteral.class);
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
  public PsiElement getKwThis() {
    return findChildByType(KW_THIS);
  }

  @Override
  @Nullable
  public PsiElement getKwTrue() {
    return findChildByType(KW_TRUE);
  }

}
