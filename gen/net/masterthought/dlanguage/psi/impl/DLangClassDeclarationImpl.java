// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static net.masterthought.dlanguage.psi.DLangTypes.*;
import net.masterthought.dlanguage.stubs.DLangClassDeclStub;
import net.masterthought.dlanguage.psi.*;
import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiReference;
import net.masterthought.dlanguage.psi.interfaces.containers.Container;
import net.masterthought.dlanguage.psi.interfaces.CanInherit;
import java.util.Map;
import net.masterthought.dlanguage.psi.interfaces.HasVisibility.Visibility;
import com.intellij.psi.stubs.IStubElementType;

public class DLangClassDeclarationImpl extends DNamedStubbedPsiElementBase<DLangClassDeclStub> implements DLangClassDeclaration {

  public DLangClassDeclarationImpl(DLangClassDeclStub stub, IStubElementType type) {
    super(stub, type);
  }

  public DLangClassDeclarationImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DLangVisitor visitor) {
    visitor.visitClassDeclaration(this);
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
  public DLangBaseClassList getBaseClassList() {
    return PsiTreeUtil.getChildOfType(this, DLangBaseClassList.class);
  }

  @Override
  @Nullable
  public DLangClassTemplateDeclaration getClassTemplateDeclaration() {
    return PsiTreeUtil.getChildOfType(this, DLangClassTemplateDeclaration.class);
  }

  @Override
  @Nullable
  public DLangIdentifier getIdentifier() {
    return PsiTreeUtil.getStubChildOfType(this, DLangIdentifier.class);
  }

  @Override
  @Nullable
  public PsiElement getKwClass() {
    return findChildByType(KW_CLASS);
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

  public DLangProtectionAttribute getProtection() {
    return DPsiImplUtil.getProtection(this);
  }

  public List<CanInherit> whatInheritsFrom() {
    return DPsiImplUtil.whatInheritsFrom(this);
  }

  public Map<String, DLangIdentifier> getSuperClassNames() {
    return DPsiImplUtil.getSuperClassNames(this);
  }

}
