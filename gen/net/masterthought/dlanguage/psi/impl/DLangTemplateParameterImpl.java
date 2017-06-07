// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static net.masterthought.dlanguage.psi.DLangTypes.*;
import net.masterthought.dlanguage.stubs.DLangTemplateParameterStub;
import net.masterthought.dlanguage.psi.*;
import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiReference;
import com.intellij.psi.stubs.IStubElementType;

public class DLangTemplateParameterImpl extends DNamedStubbedPsiElementBase<DLangTemplateParameterStub> implements DLangTemplateParameter {

  public DLangTemplateParameterImpl(DLangTemplateParameterStub stub, IStubElementType type) {
    super(stub, type);
  }

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

  @NotNull
  public String getName() {
    return DPsiImplUtil.getName(this);
  }

  public String getFullName() {
    return DPsiImplUtil.getFullName(this);
  }

  @Nullable
  public PsiElement getNameIdentifier() {
    return DPsiImplUtil.getNameIdentifier(this);
  }

  @NotNull
  public PsiReference getReference() {
    return DPsiImplUtil.getReference(this);
  }

  @Nullable
  public PsiElement setName(String newName) {
    return DPsiImplUtil.setName(this, newName);
  }

  @NotNull
  public ItemPresentation getPresentation() {
    return DPsiImplUtil.getPresentation(this);
  }

}
