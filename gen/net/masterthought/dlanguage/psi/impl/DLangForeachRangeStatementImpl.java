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
import com.intellij.psi.ResolveState;
import com.intellij.psi.scope.PsiScopeProcessor;

public class DLangForeachRangeStatementImpl extends ASTWrapperPsiElement implements DLangForeachRangeStatement {

  public DLangForeachRangeStatementImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DLangVisitor visitor) {
    visitor.visitForeachRangeStatement(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DLangVisitor) accept((DLangVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public DLangForeach getForeach() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, DLangForeach.class));
  }

  @Override
  @NotNull
  public DLangForeachType getForeachType() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, DLangForeachType.class));
  }

  @Override
  @NotNull
  public DLangLwrExpression getLwrExpression() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, DLangLwrExpression.class));
  }

  @Override
  @NotNull
  public DLangScopeStatement getScopeStatement() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, DLangScopeStatement.class));
  }

  @Override
  @NotNull
  public DLangUprExpression getUprExpression() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, DLangUprExpression.class));
  }

  @Override
  @NotNull
  public PsiElement getOpDdot() {
    return notNullChild(findChildByType(OP_DDOT));
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

  @Override
  @NotNull
  public PsiElement getOpScolon() {
    return notNullChild(findChildByType(OP_SCOLON));
  }

  public boolean processDeclarations(PsiScopeProcessor processor, ResolveState state, PsiElement lastParent, PsiElement place) {
    return DPsiImplUtil.processDeclarations(this, processor, state, lastParent, place);
  }

}
