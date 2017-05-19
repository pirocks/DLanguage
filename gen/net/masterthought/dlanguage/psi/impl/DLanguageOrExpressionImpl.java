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

public class DLanguageOrExpressionImpl extends ASTWrapperPsiElement implements DLanguageOrExpression {

  public DLanguageOrExpressionImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DLanguageVisitor visitor) {
    visitor.visitOrExpression(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DLanguageVisitor) accept((DLanguageVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<DLanguageAddExpression> getAddExpressionList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, DLanguageAddExpression.class);
  }

  @Override
  @NotNull
  public List<DLanguageAndExpression> getAndExpressionList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, DLanguageAndExpression.class);
  }

  @Override
  @NotNull
  public List<DLanguageMulExpression> getMulExpressionList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, DLanguageMulExpression.class);
  }

  @Override
  @Nullable
  public DLanguageOrExpression getOrExpression() {
    return PsiTreeUtil.getChildOfType(this, DLanguageOrExpression.class);
  }

  @Override
  @NotNull
  public List<DLanguagePostfixExpression> getPostfixExpressionList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, DLanguagePostfixExpression.class);
  }

  @Override
  @NotNull
  public List<DLanguagePowExpression> getPowExpressionList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, DLanguagePowExpression.class);
  }

  @Override
  @NotNull
  public List<DLanguageShiftExpression> getShiftExpressionList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, DLanguageShiftExpression.class);
  }

  @Override
  @NotNull
  public List<DLanguageUnaryExpression> getUnaryExpressionList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, DLanguageUnaryExpression.class);
  }

  @Override
  @NotNull
  public List<DLanguageXorExpression> getXorExpressionList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, DLanguageXorExpression.class);
  }

}
