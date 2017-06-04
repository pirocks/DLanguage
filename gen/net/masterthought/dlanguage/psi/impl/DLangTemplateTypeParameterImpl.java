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

public class DLangTemplateTypeParameterImpl extends ASTWrapperPsiElement implements DLangTemplateTypeParameter {

  public DLangTemplateTypeParameterImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DLangVisitor visitor) {
    visitor.visitTemplateTypeParameter(this);
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
  public DLangIdentifier getIdentifier() {
    return PsiTreeUtil.getChildOfType(this, DLangIdentifier.class);
  }

  @Override
  @Nullable
  public DLangTemplateValueParameterDefault getTemplateValueParameterDefault() {
    return PsiTreeUtil.getChildOfType(this, DLangTemplateValueParameterDefault.class);
  }

  @Override
  @NotNull
  public List<DLangType> getTypeList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, DLangType.class);
  }

  @Override
  @Nullable
  public PsiElement getOpEq() {
    return findChildByType(OP_EQ);
  }

  @Override
  @Nullable
  public PsiElement getOpTripledot() {
    return findChildByType(OP_TRIPLEDOT);
  }

}
