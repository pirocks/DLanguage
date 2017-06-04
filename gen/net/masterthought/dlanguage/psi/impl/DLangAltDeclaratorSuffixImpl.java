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

public class DLangAltDeclaratorSuffixImpl extends ASTWrapperPsiElement implements DLangAltDeclaratorSuffix {

  public DLangAltDeclaratorSuffixImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DLangVisitor visitor) {
    visitor.visitAltDeclaratorSuffix(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DLangVisitor) accept((DLangVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public DLangAssignExpression getAssignExpression() {
    return PsiTreeUtil.getChildOfType(this, DLangAssignExpression.class);
  }

  @Override
  @Nullable
  public DLangType getType() {
    return PsiTreeUtil.getChildOfType(this, DLangType.class);
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
