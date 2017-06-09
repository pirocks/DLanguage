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

public class DLangIfConditionImpl extends ASTWrapperPsiElement implements DLangIfCondition {

  public DLangIfConditionImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DLangVisitor visitor) {
    visitor.visitIfCondition(this);
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
  public DLangConditionAutoDeclaration getConditionAutoDeclaration() {
    return PsiTreeUtil.getChildOfType(this, DLangConditionAutoDeclaration.class);
  }

  @Override
  @Nullable
  public DLangConditionVarDeclaration getConditionVarDeclaration() {
    return PsiTreeUtil.getChildOfType(this, DLangConditionVarDeclaration.class);
  }

  @Override
  @Nullable
  public DLangConditionVarDeclarator getConditionVarDeclarator() {
    return PsiTreeUtil.getChildOfType(this, DLangConditionVarDeclarator.class);
  }

}
