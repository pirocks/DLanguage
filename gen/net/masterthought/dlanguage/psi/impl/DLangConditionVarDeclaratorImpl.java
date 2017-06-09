// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static net.masterthought.dlanguage.psi.DLangTypes.*;
import net.masterthought.dlanguage.stubs.DLangConditionVarDeclaratorStub;
import net.masterthought.dlanguage.psi.*;
import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiReference;
import com.intellij.psi.stubs.IStubElementType;

public class DLangConditionVarDeclaratorImpl extends DNamedStubbedPsiElementBase<DLangConditionVarDeclaratorStub> implements DLangConditionVarDeclarator {

  public DLangConditionVarDeclaratorImpl(DLangConditionVarDeclaratorStub stub, IStubElementType type) {
    super(stub, type);
  }

  public DLangConditionVarDeclaratorImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DLangVisitor visitor) {
    visitor.visitConditionVarDeclarator(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DLangVisitor) accept((DLangVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public DLangBasicType getBasicType() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, DLangBasicType.class));
  }

  @Override
  @NotNull
  public DLangCommaExpression getCommaExpression() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, DLangCommaExpression.class));
  }

  @Override
  @NotNull
  public DLangDeclarator getDeclarator() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, DLangDeclarator.class));
  }

  @Override
  @Nullable
  public DLangTypeCtors getTypeCtors() {
    return PsiTreeUtil.getChildOfType(this, DLangTypeCtors.class);
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

}
