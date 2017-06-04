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

public class DLangSliceExpressionImpl extends ASTWrapperPsiElement implements DLangSliceExpression {

  public DLangSliceExpressionImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DLangVisitor visitor) {
    visitor.visitSliceExpression(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DLangVisitor) accept((DLangVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public DLangMultipleAssign getMultipleAssign() {
    return PsiTreeUtil.getChildOfType(this, DLangMultipleAssign.class);
  }

  @Override
  @Nullable
  public DLangPostfixExpression getPostfixExpression() {
    return PsiTreeUtil.getChildOfType(this, DLangPostfixExpression.class);
  }

  @Override
  @NotNull
  public PsiElement getOpBracketLeft() {
    return notNullChild(findChildByType(OP_BRACKET_LEFT));
  }

  @Override
  @NotNull
  public PsiElement getOpBracketRight() {
    return notNullChild(findChildByType(OP_BRACKET_RIGHT));
  }

}
