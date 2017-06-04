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

public class DLangIfStatementImpl extends ASTWrapperPsiElement implements DLangIfStatement {

  public DLangIfStatementImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DLangVisitor visitor) {
    visitor.visitIfStatement(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DLangVisitor) accept((DLangVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public DLangElseStatement getElseStatement() {
    return PsiTreeUtil.getChildOfType(this, DLangElseStatement.class);
  }

  @Override
  @Nullable
  public DLangIfCondition getIfCondition() {
    return PsiTreeUtil.getChildOfType(this, DLangIfCondition.class);
  }

  @Override
  @Nullable
  public DLangThenStatement getThenStatement() {
    return PsiTreeUtil.getChildOfType(this, DLangThenStatement.class);
  }

  @Override
  @Nullable
  public PsiElement getKwElse() {
    return findChildByType(KW_ELSE);
  }

  @Override
  @NotNull
  public PsiElement getKwIf() {
    return notNullChild(findChildByType(KW_IF));
  }

  @Override
  @Nullable
  public PsiElement getOpParLeft() {
    return findChildByType(OP_PAR_LEFT);
  }

  @Override
  @Nullable
  public PsiElement getOpParRight() {
    return findChildByType(OP_PAR_RIGHT);
  }

}
