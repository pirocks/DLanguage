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

public class DLangOrOrExpressionImpl extends ASTWrapperPsiElement implements DLangOrOrExpression {

  public DLangOrOrExpressionImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DLangVisitor visitor) {
    visitor.visitOrOrExpression(this);
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
  @NotNull
  public List<DLangCastExpression> getCastExpressionList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, DLangCastExpression.class);
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
  @NotNull
  public PsiElement getOpBoolOr() {
    return notNullChild(findChildByType(OP_BOOL_OR));
  }

}
