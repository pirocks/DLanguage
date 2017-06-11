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

public class DLangStaticIfConditionImpl extends ASTWrapperPsiElement implements DLangStaticIfCondition {

  public DLangStaticIfConditionImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DLangVisitor visitor) {
    visitor.visitStaticIfCondition(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DLangVisitor) accept((DLangVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public DLangAggregateBody getAggregateBody() {
    return PsiTreeUtil.getChildOfType(this, DLangAggregateBody.class);
  }

  @Override
  @Nullable
  public DLangAssignExpression getAssignExpression() {
    return PsiTreeUtil.getChildOfType(this, DLangAssignExpression.class);
  }

  @Override
  @Nullable
  public DLangDeclDef getDeclDef() {
    return PsiTreeUtil.getChildOfType(this, DLangDeclDef.class);
  }

  @Override
  @Nullable
  public DLangStaticElseCondition getStaticElseCondition() {
    return PsiTreeUtil.getChildOfType(this, DLangStaticElseCondition.class);
  }

  @Override
  @NotNull
  public PsiElement getKwIf() {
    return notNullChild(findChildByType(KW_IF));
  }

  @Override
  @NotNull
  public PsiElement getKwStatic() {
    return notNullChild(findChildByType(KW_STATIC));
  }

  @Override
  @NotNull
  public PsiElement getOpParLeft() {
    return notNullChild(findChildByType(OP_PAR_LEFT));
  }

  @Override
  @Nullable
  public PsiElement getOpParRight() {
    return findChildByType(OP_PAR_RIGHT);
  }

}
