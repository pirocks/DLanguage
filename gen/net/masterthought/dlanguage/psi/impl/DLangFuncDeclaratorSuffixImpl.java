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

public class DLangFuncDeclaratorSuffixImpl extends ASTWrapperPsiElement implements DLangFuncDeclaratorSuffix {

  public DLangFuncDeclaratorSuffixImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DLangVisitor visitor) {
    visitor.visitFuncDeclaratorSuffix(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DLangVisitor) accept((DLangVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public DLangConstraint getConstraint() {
    return PsiTreeUtil.getChildOfType(this, DLangConstraint.class);
  }

  @Override
  @Nullable
  public DLangMemberFunctionAttributes getMemberFunctionAttributes() {
    return PsiTreeUtil.getChildOfType(this, DLangMemberFunctionAttributes.class);
  }

  @Override
  @NotNull
  public DLangParameters getParameters() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, DLangParameters.class));
  }

  @Override
  @Nullable
  public DLangTemplateParameters getTemplateParameters() {
    return PsiTreeUtil.getChildOfType(this, DLangTemplateParameters.class);
  }

}
