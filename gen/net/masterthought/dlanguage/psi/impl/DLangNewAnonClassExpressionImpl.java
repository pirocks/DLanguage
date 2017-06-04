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

public class DLangNewAnonClassExpressionImpl extends ASTWrapperPsiElement implements DLangNewAnonClassExpression {

  public DLangNewAnonClassExpressionImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DLangVisitor visitor) {
    visitor.visitNewAnonClassExpression(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DLangVisitor) accept((DLangVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public DLangAggregateBody getAggregateBody() {
    return PsiTreeUtil.getChildOfType(this, DLangAggregateBody.class);
  }

  @Override
  @Nullable
  public DLangAllocatorArguments getAllocatorArguments() {
    return PsiTreeUtil.getChildOfType(this, DLangAllocatorArguments.class);
  }

  @Override
  @Nullable
  public DLangClassArguments getClassArguments() {
    return PsiTreeUtil.getChildOfType(this, DLangClassArguments.class);
  }

  @Override
  @Nullable
  public DLangInterfaces getInterfaces() {
    return PsiTreeUtil.getChildOfType(this, DLangInterfaces.class);
  }

  @Override
  @Nullable
  public DLangSuperClass getSuperClass() {
    return PsiTreeUtil.getChildOfType(this, DLangSuperClass.class);
  }

  @Override
  @Nullable
  public PsiElement getKwClass() {
    return findChildByType(KW_CLASS);
  }

  @Override
  @NotNull
  public PsiElement getKwNew() {
    return notNullChild(findChildByType(KW_NEW));
  }

}
