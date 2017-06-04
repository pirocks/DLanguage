// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static net.masterthought.dlanguage.psi.DLangTypes.*;
import net.masterthought.dlanguage.stubs.DLangAliasDeclarationStub;
import net.masterthought.dlanguage.psi.*;
import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiReference;
import net.masterthought.dlanguage.psi.interfaces.containers.Container;
import net.masterthought.dlanguage.psi.interfaces.HasVisibility.Visibility;
import net.masterthought.dlanguage.psi.interfaces.Type;
import com.intellij.psi.stubs.IStubElementType;

public class DLangAliasDeclarationImpl extends DNamedStubbedPsiElementBase<DLangAliasDeclarationStub> implements DLangAliasDeclaration {

  public DLangAliasDeclarationImpl(DLangAliasDeclarationStub stub, IStubElementType type) {
    super(stub, type);
  }

  public DLangAliasDeclarationImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DLangVisitor visitor) {
    visitor.visitAliasDeclaration(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DLangVisitor) accept((DLangVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public DLangAliasDeclarationX getAliasDeclarationX() {
    return PsiTreeUtil.getChildOfType(this, DLangAliasDeclarationX.class);
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
  @Nullable
  public DLangDeclarator getDeclarator() {
    return PsiTreeUtil.getChildOfType(this, DLangDeclarator.class);
  }

  @Override
  @Nullable
  public DLangFuncDeclaratorSuffix getFuncDeclaratorSuffix() {
    return PsiTreeUtil.getChildOfType(this, DLangFuncDeclaratorSuffix.class);
  }

  @Override
  @Nullable
  public DLangIdentifier getIdentifier() {
    return PsiTreeUtil.getStubChildOfType(this, DLangIdentifier.class);
  }

  @Override
  @Nullable
  public DLangInitializer getInitializer() {
    return PsiTreeUtil.getChildOfType(this, DLangInitializer.class);
  }

  @Override
  @Nullable
  public DLangStorageClasses getStorageClasses() {
    return PsiTreeUtil.getChildOfType(this, DLangStorageClasses.class);
  }

  @Override
  @Nullable
  public DLangTemplateArguments getTemplateArguments() {
    return PsiTreeUtil.getChildOfType(this, DLangTemplateArguments.class);
  }

  @Override
  @Nullable
  public DLangType getType() {
    return PsiTreeUtil.getChildOfType(this, DLangType.class);
  }

  @Override
  @NotNull
  public PsiElement getKwAlias() {
    return notNullChild(findChildByType(KW_ALIAS));
  }

  @Override
  @Nullable
  public PsiElement getOpEq() {
    return findChildByType(OP_EQ);
  }

  @Override
  @NotNull
  public PsiElement getOpScolon() {
    return notNullChild(findChildByType(OP_SCOLON));
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

  public boolean isSomeVisibility(Visibility visibility) {
    return DPsiImplUtil.isSomeVisibility(this, visibility);
  }

  public boolean isSomeVisibility(Visibility visibility, Class<? extends Container> containerType) {
    return DPsiImplUtil.isSomeVisibility(this, visibility, containerType);
  }

  public boolean actuallyIsDeclaration() {
    return DPsiImplUtil.actuallyIsDeclaration(this);
  }

  public Type getDeclarationType() {
    return DPsiImplUtil.getDeclarationType(this);
  }

}
