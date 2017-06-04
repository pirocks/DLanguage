// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static net.masterthought.dlanguage.psi.DLangTypes.*;
import net.masterthought.dlanguage.stubs.DLangInterfaceDeclarationStub;
import net.masterthought.dlanguage.psi.*;
import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiReference;
import net.masterthought.dlanguage.psi.interfaces.containers.Container;
import net.masterthought.dlanguage.psi.interfaces.CanInherit;
import java.util.Map;
import net.masterthought.dlanguage.psi.interfaces.HasVisibility.Visibility;
import com.intellij.psi.stubs.IStubElementType;

public class DLangInterfaceDeclarationImpl extends DNamedStubbedPsiElementBase<DLangInterfaceDeclarationStub> implements DLangInterfaceDeclaration {

  public DLangInterfaceDeclarationImpl(DLangInterfaceDeclarationStub stub, IStubElementType type) {
    super(stub, type);
  }

  public DLangInterfaceDeclarationImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DLangVisitor visitor) {
    visitor.visitInterfaceDeclaration(this);
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
  public DLangBaseInterfaceList getBaseInterfaceList() {
    return PsiTreeUtil.getChildOfType(this, DLangBaseInterfaceList.class);
  }

  @Override
  @Nullable
  public DLangIdentifier getIdentifier() {
    return PsiTreeUtil.getStubChildOfType(this, DLangIdentifier.class);
  }

  @Override
  @Nullable
  public DLangInterfaceTemplateDeclaration getInterfaceTemplateDeclaration() {
    return PsiTreeUtil.getChildOfType(this, DLangInterfaceTemplateDeclaration.class);
  }

  @Override
  @Nullable
  public PsiElement getKwInterface() {
    return findChildByType(KW_INTERFACE);
  }

  @Override
  @Nullable
  public PsiElement getOpScolon() {
    return findChildByType(OP_SCOLON);
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

  public List<CanInherit> whatInheritsFrom() {
    return DPsiImplUtil.whatInheritsFrom(this);
  }

  public List<DLangTemplateParameter> getTemplateArguments() {
    return DPsiImplUtil.getTemplateArguments(this);
  }

  public Map<String, DLangIdentifier> getSuperClassNames() {
    return DPsiImplUtil.getSuperClassNames(this);
  }

}
