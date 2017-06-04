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

public class DLangTemplateParameterImpl extends ASTWrapperPsiElement implements DLangTemplateParameter {

  public DLangTemplateParameterImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DLangVisitor visitor) {
    visitor.visitTemplateParameter(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DLangVisitor) accept((DLangVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public DLangTemplateAliasParameter getTemplateAliasParameter() {
    return PsiTreeUtil.getChildOfType(this, DLangTemplateAliasParameter.class);
  }

  @Override
  @Nullable
  public DLangTemplateThisParameter getTemplateThisParameter() {
    return PsiTreeUtil.getChildOfType(this, DLangTemplateThisParameter.class);
  }

  @Override
  @Nullable
  public DLangTemplateTypeParameter getTemplateTypeParameter() {
    return PsiTreeUtil.getChildOfType(this, DLangTemplateTypeParameter.class);
  }

}
