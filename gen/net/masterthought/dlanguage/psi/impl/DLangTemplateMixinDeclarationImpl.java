// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static net.masterthought.dlanguage.psi.DLangTypes.*;
import net.masterthought.dlanguage.stubs.DLangTemplateMixinDeclStub;
import net.masterthought.dlanguage.psi.*;
import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiReference;
import net.masterthought.dlanguage.psi.interfaces.containers.Container;
import net.masterthought.dlanguage.psi.interfaces.HasVisibility.Visibility;
import com.intellij.psi.stubs.IStubElementType;

public class DLangTemplateMixinDeclarationImpl extends DNamedStubbedPsiElementBase<DLangTemplateMixinDeclStub> implements DLangTemplateMixinDeclaration {

  public DLangTemplateMixinDeclarationImpl(DLangTemplateMixinDeclStub stub, IStubElementType type) {
    super(stub, type);
  }

  public DLangTemplateMixinDeclarationImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DLangVisitor visitor) {
    visitor.visitTemplateMixinDeclaration(this);
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
  public DLangConstraint getConstraint() {
    return PsiTreeUtil.getChildOfType(this, DLangConstraint.class);
  }

  @Override
  @NotNull
  public DLangIdentifier getIdentifier() {
    return notNullChild(PsiTreeUtil.getStubChildOfType(this, DLangIdentifier.class));
  }

  @Override
  @Nullable
  public DLangTemplateParameters getTemplateParameters() {
    return PsiTreeUtil.getChildOfType(this, DLangTemplateParameters.class);
  }

  @Override
  @NotNull
  public PsiElement getKwMixin() {
    return notNullChild(findChildByType(KW_MIXIN));
  }

  @Override
  @NotNull
  public PsiElement getKwTemplate() {
    return notNullChild(findChildByType(KW_TEMPLATE));
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

  public boolean isSomeVisibility(Visibility visibility, Class<? extends Container> containerType) {
    return DPsiImplUtil.isSomeVisibility(this, visibility, containerType);
  }

  public boolean isSomeVisibility(Visibility visibility) {
    return DPsiImplUtil.isSomeVisibility(this, visibility);
  }

}
