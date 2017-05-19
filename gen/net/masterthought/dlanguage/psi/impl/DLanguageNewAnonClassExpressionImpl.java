// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static net.masterthought.dlanguage.psi.DLanguageTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import net.masterthought.dlanguage.psi.*;

public class DLanguageNewAnonClassExpressionImpl extends ASTWrapperPsiElement implements DLanguageNewAnonClassExpression {

  public DLanguageNewAnonClassExpressionImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DLanguageVisitor visitor) {
    visitor.visitNewAnonClassExpression(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DLanguageVisitor) accept((DLanguageVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public DLanguageAggregateBody getAggregateBody() {
    return PsiTreeUtil.getChildOfType(this, DLanguageAggregateBody.class);
  }

  @Override
  @Nullable
  public DLanguageAllocatorArguments getAllocatorArguments() {
    return PsiTreeUtil.getChildOfType(this, DLanguageAllocatorArguments.class);
  }

  @Override
  @Nullable
  public DLanguageArgumentList getArgumentList() {
    return PsiTreeUtil.getChildOfType(this, DLanguageArgumentList.class);
  }

  @Override
  @Nullable
  public DLanguageInterfaces getInterfaces() {
    return PsiTreeUtil.getChildOfType(this, DLanguageInterfaces.class);
  }

  @Override
  @Nullable
  public DLanguageSuperClass getSuperClass() {
    return PsiTreeUtil.getChildOfType(this, DLanguageSuperClass.class);
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
