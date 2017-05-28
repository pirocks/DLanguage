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

public class DLanguageConditionalExpression_Impl extends ASTWrapperPsiElement implements DLanguageConditionalExpression_ {

  public DLanguageConditionalExpression_Impl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DLanguageVisitor visitor) {
    visitor.visitConditionalExpression_(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DLanguageVisitor) accept((DLanguageVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<DLanguageAddExpression_> getAddExpression_List() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, DLanguageAddExpression_.class);
  }

  @Override
  @NotNull
  public List<DLanguageAndExxpression_> getAndExxpression_List() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, DLanguageAndExxpression_.class);
  }

  @Override
  @NotNull
  public List<DLanguageCastExpression> getCastExpressionList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, DLanguageCastExpression.class);
  }

  @Override
  @Nullable
  public DLanguageCommaExpression getCommaExpression() {
    return PsiTreeUtil.getChildOfType(this, DLanguageCommaExpression.class);
  }

  @Override
  @Nullable
  public DLanguageConditionalExpression_ getConditionalExpression_() {
    return PsiTreeUtil.getChildOfType(this, DLanguageConditionalExpression_.class);
  }

  @Override
  @NotNull
  public List<DLanguageDeleteExpression> getDeleteExpressionList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, DLanguageDeleteExpression.class);
  }

  @Override
  @NotNull
  public List<DLanguageEqualExpression> getEqualExpressionList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, DLanguageEqualExpression.class);
  }

  @Override
  @NotNull
  public List<DLanguageIdentifier> getIdentifierList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, DLanguageIdentifier.class);
  }

  @Override
  @NotNull
  public List<DLanguageIdentityExpression> getIdentityExpressionList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, DLanguageIdentityExpression.class);
  }

  @Override
  @NotNull
  public List<DLanguageInExpression> getInExpressionList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, DLanguageInExpression.class);
  }

  @Override
  @NotNull
  public List<DLanguageMulExpression_> getMulExpression_List() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, DLanguageMulExpression_.class);
  }

  @Override
  @Nullable
  public DLanguageOrOrExpression getOrOrExpression() {
    return PsiTreeUtil.getChildOfType(this, DLanguageOrOrExpression.class);
  }

  @Override
  @NotNull
  public List<DLanguagePostfixExpression> getPostfixExpressionList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, DLanguagePostfixExpression.class);
  }

  @Override
  @NotNull
  public List<DLanguagePowExpression_> getPowExpression_List() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, DLanguagePowExpression_.class);
  }

  @Override
  @NotNull
  public List<DLanguageRelExpression> getRelExpressionList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, DLanguageRelExpression.class);
  }

  @Override
  @NotNull
  public List<DLanguageShiftExpression_> getShiftExpression_List() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, DLanguageShiftExpression_.class);
  }

  @Override
  @NotNull
  public List<DLanguageTemplateInstance> getTemplateInstanceList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, DLanguageTemplateInstance.class);
  }

  @Override
  @NotNull
  public List<DLanguageType> getTypeList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, DLanguageType.class);
  }

  @Override
  @NotNull
  public List<DLanguageTypeCtor> getTypeCtorList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, DLanguageTypeCtor.class);
  }

  @Override
  @NotNull
  public List<DLanguageXorExpression_> getXorExpression_List() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, DLanguageXorExpression_.class);
  }

  @Override
  @NotNull
  public PsiElement getOpColon() {
    return notNullChild(findChildByType(OP_COLON));
  }

  @Override
  @NotNull
  public PsiElement getOpQuest() {
    return notNullChild(findChildByType(OP_QUEST));
  }

}
