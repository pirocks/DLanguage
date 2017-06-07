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

public class DLangTemplateMixinImpl extends ASTWrapperPsiElement implements DLangTemplateMixin {

  public DLangTemplateMixinImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DLangVisitor visitor) {
    visitor.visitTemplateMixin(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DLangVisitor) accept((DLangVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public DLangIdentifier getIdentifier() {
    return PsiTreeUtil.getChildOfType(this, DLangIdentifier.class);
  }

  @Override
  @NotNull
  public DLangMixinTemplateName getMixinTemplateName() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, DLangMixinTemplateName.class));
  }

  @Override
  @Nullable
  public DLangTemplateArguments getTemplateArguments() {
    return PsiTreeUtil.getChildOfType(this, DLangTemplateArguments.class);
  }

  @Override
  @NotNull
  public PsiElement getKwMixin() {
    return notNullChild(findChildByType(KW_MIXIN));
  }

  @Override
  @NotNull
  public PsiElement getOpScolon() {
    return notNullChild(findChildByType(OP_SCOLON));
  }

  @NotNull
  @Deprecated
  public String getName() {
    return DPsiImplUtil.getName(this);
  }

}
