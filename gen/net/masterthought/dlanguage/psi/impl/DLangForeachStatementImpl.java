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

public class DLangForeachStatementImpl extends ASTWrapperPsiElement implements DLangForeachStatement {

  public DLangForeachStatementImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DLangVisitor visitor) {
    visitor.visitForeachStatement(this);
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
  public DLangForeachAggregate getForeachAggregate() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, DLangForeachAggregate.class));
  }

  @Override
  @NotNull
  public DLangForeachTypeList getForeachTypeList() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, DLangForeachTypeList.class));
  }

  @Override
  @NotNull
  public DLangStatement getStatement() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, DLangStatement.class));
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

  public void processDeclarations(PsiScopeProcessor processor, ResolveState state, PsiElement lastParent, PsiElement place) {
    DPsiImplUtil.processDeclarations(this, processor, state, lastParent, place);
  }

}
