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

public class DLangIsExpressionImpl extends ASTWrapperPsiElement implements DLangIsExpression {

  public DLangIsExpressionImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DLangVisitor visitor) {
    visitor.visitIsExpression(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DLangVisitor) accept((DLangVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public DLangIdentifier getIdentifier() {
    return PsiTreeUtil.getChildOfType(this, DLangIdentifier.class);
  }

  @Override
  @Nullable
  public DLangTemplateParameterList getTemplateParameterList() {
    return PsiTreeUtil.getChildOfType(this, DLangTemplateParameterList.class);
  }

  @Override
  @NotNull
  public DLangType getType() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, DLangType.class));
  }

  @Override
  @Nullable
  public DLangTypeSpecialization getTypeSpecialization() {
    return PsiTreeUtil.getChildOfType(this, DLangTypeSpecialization.class);
  }

  @Override
  @Nullable
  public PsiElement getKwIs() {
    return findChildByType(KW_IS);
  }

  @Override
  @Nullable
  public PsiElement getKwNotIs() {
    return findChildByType(KW_NOT_IS);
  }

  @Override
  @Nullable
  public PsiElement getOpColon() {
    return findChildByType(OP_COLON);
  }

  @Override
  @Nullable
  public PsiElement getOpComma() {
    return findChildByType(OP_COMMA);
  }

  @Override
  @Nullable
  public PsiElement getOpEqEq() {
    return findChildByType(OP_EQ_EQ);
  }

  @Override
  @NotNull
  public PsiElement getOpParLeft() {
    return notNullChild(findChildByType(OP_PAR_LEFT));
  }

  @Override
  @NotNull
  public PsiElement getOpParRight() {
    return notNullChild(findChildByType(OP_PAR_RIGHT));
  }

}
