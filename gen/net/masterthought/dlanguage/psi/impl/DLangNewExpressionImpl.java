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

public class DLangNewExpressionImpl extends ASTWrapperPsiElement implements DLangNewExpression {

  public DLangNewExpressionImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DLangVisitor visitor) {
    visitor.visitNewExpression(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DLangVisitor) accept((DLangVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public DLangAllocatorArguments getAllocatorArguments() {
    return PsiTreeUtil.getChildOfType(this, DLangAllocatorArguments.class);
  }

  @Override
  @Nullable
  public DLangNewExpressionWithArgs getNewExpressionWithArgs() {
    return PsiTreeUtil.getChildOfType(this, DLangNewExpressionWithArgs.class);
  }

  @Override
  @Nullable
  public DLangType getType() {
    return PsiTreeUtil.getChildOfType(this, DLangType.class);
  }

  @Override
  @Nullable
  public PsiElement getKwNew() {
    return findChildByType(KW_NEW);
  }

}