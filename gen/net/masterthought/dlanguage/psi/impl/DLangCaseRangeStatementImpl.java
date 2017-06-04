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

public class DLangCaseRangeStatementImpl extends ASTWrapperPsiElement implements DLangCaseRangeStatement {

  public DLangCaseRangeStatementImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DLangVisitor visitor) {
    visitor.visitCaseRangeStatement(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DLangVisitor) accept((DLangVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public DLangFirstExp getFirstExp() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, DLangFirstExp.class));
  }

  @Override
  @Nullable
  public DLangLastExp getLastExp() {
    return PsiTreeUtil.getChildOfType(this, DLangLastExp.class);
  }

  @Override
  @Nullable
  public DLangScopeStatementList getScopeStatementList() {
    return PsiTreeUtil.getChildOfType(this, DLangScopeStatementList.class);
  }

  @Override
  @NotNull
  public PsiElement getOpDdot() {
    return notNullChild(findChildByType(OP_DDOT));
  }

}
