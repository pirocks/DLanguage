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

public class DLangTraitsExpressionImpl extends ASTWrapperPsiElement implements DLangTraitsExpression {

  public DLangTraitsExpressionImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DLangVisitor visitor) {
    visitor.visitTraitsExpression(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DLangVisitor) accept((DLangVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public DLangTraitsArguments getTraitsArguments() {
    return PsiTreeUtil.getChildOfType(this, DLangTraitsArguments.class);
  }

  @Override
  @Nullable
  public DLangTraitsKeyword getTraitsKeyword() {
    return PsiTreeUtil.getChildOfType(this, DLangTraitsKeyword.class);
  }

  @Override
  @NotNull
  public PsiElement getKwTraits() {
    return notNullChild(findChildByType(KW___TRAITS));
  }

  @Override
  @Nullable
  public PsiElement getOpComma() {
    return findChildByType(OP_COMMA);
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
