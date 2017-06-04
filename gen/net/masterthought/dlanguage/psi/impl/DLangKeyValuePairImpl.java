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

public class DLangKeyValuePairImpl extends ASTWrapperPsiElement implements DLangKeyValuePair {

  public DLangKeyValuePairImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DLangVisitor visitor) {
    visitor.visitKeyValuePair(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DLangVisitor) accept((DLangVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<DLangAssignExpression> getAssignExpressionList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, DLangAssignExpression.class);
  }

  @Override
  @NotNull
  public PsiElement getOpColon() {
    return notNullChild(findChildByType(OP_COLON));
  }

}
