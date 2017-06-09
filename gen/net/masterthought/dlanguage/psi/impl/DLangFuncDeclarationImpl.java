// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static net.masterthought.dlanguage.psi.DLangTypes.*;
import net.masterthought.dlanguage.stubs.DLangFuncDeclStub;
import net.masterthought.dlanguage.psi.*;
import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiReference;
import com.intellij.psi.ResolveState;
import com.intellij.psi.scope.PsiScopeProcessor;
import net.masterthought.dlanguage.psi.interfaces.containers.Container;
import net.masterthought.dlanguage.psi.interfaces.HasVisibility.Visibility;
import com.intellij.psi.stubs.IStubElementType;

public class DLangFuncDeclarationImpl extends DNamedStubbedPsiElementBase<DLangFuncDeclStub> implements DLangFuncDeclaration {

  public DLangFuncDeclarationImpl(DLangFuncDeclStub stub, IStubElementType type) {
    super(stub, type);
  }

  public DLangFuncDeclarationImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DLangVisitor visitor) {
    visitor.visitFuncDeclaration(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DLangVisitor) accept((DLangVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public DLangBasicType getBasicType() {
    return PsiTreeUtil.getChildOfType(this, DLangBasicType.class);
  }

  @Override
  @Nullable
  public DLangBasicType2 getBasicType2() {
    return PsiTreeUtil.getChildOfType(this, DLangBasicType2.class);
  }

  @Override
  @NotNull
  public DLangFuncDeclaratorSuffix getFuncDeclaratorSuffix() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, DLangFuncDeclaratorSuffix.class));
  }

  @Override
  @Nullable
  public DLangFunctionBody getFunctionBody() {
    return PsiTreeUtil.getChildOfType(this, DLangFunctionBody.class);
  }

  @Override
  @NotNull
  public DLangIdentifier getIdentifier() {
    return notNullChild(PsiTreeUtil.getStubChildOfType(this, DLangIdentifier.class));
  }

  @Override
  @Nullable
  public DLangStorageClasses getStorageClasses() {
    return PsiTreeUtil.getChildOfType(this, DLangStorageClasses.class);
  }

  @Override
  @Nullable
  public PsiElement getOpEq() {
    return findChildByType(OP_EQ);
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

  @NotNull
  public PsiElement setName(String newName) {
    return DPsiImplUtil.setName(this, newName);
  }

  @NotNull
  public ItemPresentation getPresentation() {
    return DPsiImplUtil.getPresentation(this);
  }

  @NotNull
  public List<DLangParameter> getArguments() {
    return DPsiImplUtil.getArguments(this);
  }

  public boolean isSomeVisibility(Visibility visibility, Class<? extends Container> containerType) {
    return DPsiImplUtil.isSomeVisibility(this, visibility, containerType);
  }

  public boolean isSomeVisibility(Visibility visibility) {
    return DPsiImplUtil.isSomeVisibility(this, visibility);
  }

  @NotNull
  public List<DLangTemplateParameter> getTemplateArguments() {
    return DPsiImplUtil.getTemplateArguments(this);
  }

  @NotNull
  public List<DLangProtectionAttribute> getProtection() {
    return DPsiImplUtil.getProtection(this);
  }

  public boolean isSystem() {
    return DPsiImplUtil.isSystem(this);
  }

  public boolean isNoGC() {
    return DPsiImplUtil.isNoGC(this);
  }

  public boolean isTrusted() {
    return DPsiImplUtil.isTrusted(this);
  }

  public boolean hasCustomProperty() {
    return DPsiImplUtil.hasCustomProperty(this);
  }

  public boolean isSafe() {
    return DPsiImplUtil.isSafe(this);
  }

  public DLangUserDefinedAttribute getCustomProperty() {
    return DPsiImplUtil.getCustomProperty(this);
  }

  public boolean isPropertyFunction() {
    return DPsiImplUtil.isPropertyFunction(this);
  }

  public boolean processDeclarations(PsiScopeProcessor processor, ResolveState state, PsiElement lastParent, PsiElement place) {
    return DPsiImplUtil.processDeclarations(this, processor, state, lastParent, place);
  }

}
