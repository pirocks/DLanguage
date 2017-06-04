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

public class DLangTemplateArgumentsImpl extends ASTWrapperPsiElement implements DLangTemplateArguments {

  public DLangTemplateArgumentsImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DLangVisitor visitor) {
    visitor.visitTemplateArguments(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DLangVisitor) accept((DLangVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public DLangTemplateArgumentList getTemplateArgumentList() {
    return PsiTreeUtil.getChildOfType(this, DLangTemplateArgumentList.class);
  }

  @Override
  @Nullable
  public DLangTemplateSingleArgument getTemplateSingleArgument() {
    return PsiTreeUtil.getChildOfType(this, DLangTemplateSingleArgument.class);
  }

  @Override
  @NotNull
  public PsiElement getOpNot() {
    return notNullChild(findChildByType(OP_NOT));
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
