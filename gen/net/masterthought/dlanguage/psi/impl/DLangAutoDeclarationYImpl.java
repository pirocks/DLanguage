// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static net.masterthought.dlanguage.psi.DLangTypes.*;
import net.masterthought.dlanguage.stubs.DLangAutoDeclStub;
import net.masterthought.dlanguage.psi.*;
import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiReference;
import net.masterthought.dlanguage.psi.interfaces.containers.Container;
import net.masterthought.dlanguage.psi.interfaces.HasVisibility.Visibility;
import net.masterthought.dlanguage.psi.interfaces.Type;
import com.intellij.psi.stubs.IStubElementType;

public class DLangAutoDeclarationYImpl extends DNamedStubbedPsiElementBase<DLangAutoDeclStub> implements DLangAutoDeclarationY {

  public DLangAutoDeclarationYImpl(DLangAutoDeclStub stub, IStubElementType type) {
    super(stub, type);
  }

  public DLangAutoDeclarationYImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DLangVisitor visitor) {
    visitor.visitAutoDeclarationY(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DLangVisitor) accept((DLangVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public DLangIdentifier getIdentifier() {
    return notNullChild(PsiTreeUtil.getStubChildOfType(this, DLangIdentifier.class));
  }

  @Override
  @NotNull
  public DLangInitializer getInitializer() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, DLangInitializer.class));
  }

  @Override
  @Nullable
  public DLangTemplateParameters getTemplateParameters() {
    return PsiTreeUtil.getChildOfType(this, DLangTemplateParameters.class);
  }

  @Override
  @NotNull
  public PsiElement getOpEq() {
    return notNullChild(findChildByType(OP_EQ));
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

  public boolean isSomeVisibility(Visibility visibility, Class<? extends Container> containerType) {
    return DPsiImplUtil.isSomeVisibility(this, visibility, containerType);
  }

  public boolean isSomeVisibility(Visibility visibility) {
    return DPsiImplUtil.isSomeVisibility(this, visibility);
  }

  public boolean actuallyIsDeclaration() {
    return DPsiImplUtil.actuallyIsDeclaration(this);
  }

  public Type getVariableDeclarationType() {
    return DPsiImplUtil.getVariableDeclarationType(this);
  }

}
