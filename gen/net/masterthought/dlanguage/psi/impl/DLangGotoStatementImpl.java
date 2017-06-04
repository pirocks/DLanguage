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

public class DLangGotoStatementImpl extends ASTWrapperPsiElement implements DLangGotoStatement {

  public DLangGotoStatementImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DLangVisitor visitor) {
    visitor.visitGotoStatement(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DLangVisitor) accept((DLangVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public DLangCommaExpression getCommaExpression() {
    return PsiTreeUtil.getChildOfType(this, DLangCommaExpression.class);
  }

  @Override
  @Nullable
  public DLangIdentifier getIdentifier() {
    return PsiTreeUtil.getChildOfType(this, DLangIdentifier.class);
  }

  @Override
  @Nullable
  public PsiElement getKwCase() {
    return findChildByType(KW_CASE);
  }

  @Override
  @Nullable
  public PsiElement getKwDefault() {
    return findChildByType(KW_DEFAULT);
  }

  @Override
  @NotNull
  public PsiElement getKwGoto() {
    return notNullChild(findChildByType(KW_GOTO));
  }

  @Override
  @NotNull
  public PsiElement getOpScolon() {
    return notNullChild(findChildByType(OP_SCOLON));
  }

}
