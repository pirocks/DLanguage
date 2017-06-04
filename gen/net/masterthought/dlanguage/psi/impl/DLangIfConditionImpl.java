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

public class DLangIfConditionImpl extends ASTWrapperPsiElement implements DLangIfCondition {

  public DLangIfConditionImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DLangVisitor visitor) {
    visitor.visitIfCondition(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DLangVisitor) accept((DLangVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public DLangBasicType getBasicType() {
    return PsiTreeUtil.getChildOfType(this, DLangBasicType.class);
  }

  @Override
  @NotNull
  public DLangCommaExpression getCommaExpression() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, DLangCommaExpression.class));
  }

  @Override
  @Nullable
  public DLangDeclarator getDeclarator() {
    return PsiTreeUtil.getChildOfType(this, DLangDeclarator.class);
  }

  @Override
  @Nullable
  public DLangIdentifier getIdentifier() {
    return PsiTreeUtil.getChildOfType(this, DLangIdentifier.class);
  }

  @Override
  @Nullable
  public DLangTypeCtors getTypeCtors() {
    return PsiTreeUtil.getChildOfType(this, DLangTypeCtors.class);
  }

  @Override
  @Nullable
  public PsiElement getKwAuto() {
    return findChildByType(KW_AUTO);
  }

  @Override
  @Nullable
  public PsiElement getOpEq() {
    return findChildByType(OP_EQ);
  }

}
