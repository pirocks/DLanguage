// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static net.masterthought.dlanguage.psi.DLangTypes.*;
import com.intellij.extapi.psi.StubBasedPsiElementBase;
import net.masterthought.dlanguage.stubs.DLangStaticDestructorStub;
import net.masterthought.dlanguage.psi.*;
import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiReference;
import net.masterthought.dlanguage.psi.interfaces.containers.Container;
import net.masterthought.dlanguage.psi.interfaces.HasVisibility.Visibility;
import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.tree.IElementType;

public class DLangStaticDestructorImpl extends StubBasedPsiElementBase<DLangStaticDestructorStub> implements DLangStaticDestructor {

  public DLangStaticDestructorImpl(DLangStaticDestructorStub stub, IStubElementType type) {
    super(stub, type);
  }

  public DLangStaticDestructorImpl(ASTNode node) {
    super(node);
  }

  public DLangStaticDestructorImpl(DLangStaticDestructorStub stub, IElementType type, ASTNode node) {
    super(stub, type, node);
  }

  public void accept(@NotNull DLangVisitor visitor) {
    visitor.visitStaticDestructor(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DLangVisitor) accept((DLangVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public DLangFunctionBody getFunctionBody() {
    return PsiTreeUtil.getChildOfType(this, DLangFunctionBody.class);
  }

  @Override
  @Nullable
  public DLangMemberFunctionAttributes getMemberFunctionAttributes() {
    return PsiTreeUtil.getChildOfType(this, DLangMemberFunctionAttributes.class);
  }

  @Override
  @NotNull
  public PsiElement getKwStatic() {
    return notNullChild(findChildByType(KW_STATIC));
  }

  @Override
  @NotNull
  public PsiElement getKwThis() {
    return notNullChild(findChildByType(KW_THIS));
  }

  @Override
  @NotNull
  public PsiElement getOpParLeft() {
    return notNullChild(findChildByType(OP_PAR_LEFT));
  }

  @Override
  @NotNull
  public PsiElement getOpParRight() {
    return notNullChild(findChildByType(OP_PAR_RIGHT));
  }

  @Override
  @Nullable
  public PsiElement getOpScolon() {
    return findChildByType(OP_SCOLON);
  }

  @Override
  @NotNull
  public PsiElement getOpTilda() {
    return notNullChild(findChildByType(OP_TILDA));
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

}
