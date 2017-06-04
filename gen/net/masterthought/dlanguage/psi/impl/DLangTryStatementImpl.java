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

public class DLangTryStatementImpl extends ASTWrapperPsiElement implements DLangTryStatement {

  public DLangTryStatementImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DLangVisitor visitor) {
    visitor.visitTryStatement(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DLangVisitor) accept((DLangVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public DLangCatches getCatches() {
    return PsiTreeUtil.getChildOfType(this, DLangCatches.class);
  }

  @Override
  @Nullable
  public DLangFinallyStatement getFinallyStatement() {
    return PsiTreeUtil.getChildOfType(this, DLangFinallyStatement.class);
  }

  @Override
  @NotNull
  public DLangScopeStatement getScopeStatement() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, DLangScopeStatement.class));
  }

  @Override
  @NotNull
  public PsiElement getKwTry() {
    return notNullChild(findChildByType(KW_TRY));
  }

}
