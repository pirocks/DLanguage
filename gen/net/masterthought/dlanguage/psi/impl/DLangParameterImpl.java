// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static net.masterthought.dlanguage.psi.DLangTypes.*;
import net.masterthought.dlanguage.stubs.DLangParameterStub;
import net.masterthought.dlanguage.psi.*;
import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiReference;
import com.intellij.psi.stubs.IStubElementType;

public class DLangParameterImpl extends DNamedStubbedPsiElementBase<DLangParameterStub> implements DLangParameter {

  public DLangParameterImpl(DLangParameterStub stub, IStubElementType type) {
    super(stub, type);
  }

  public DLangParameterImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DLangVisitor visitor) {
    visitor.visitParameter(this);
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
  public DLangBasicType getBasicType() {
    return PsiTreeUtil.getChildOfType(this, DLangBasicType.class);
  }

  @Override
  @Nullable
  public DLangDeclarator getDeclarator() {
    return PsiTreeUtil.getChildOfType(this, DLangDeclarator.class);
  }

  @Override
  @Nullable
  public DLangIdentifier getIdentifier() {
    return PsiTreeUtil.getStubChildOfType(this, DLangIdentifier.class);
  }

  @Override
  @Nullable
  public DLangInOut getInOut() {
    return PsiTreeUtil.getChildOfType(this, DLangInOut.class);
  }

  @Override
  @Nullable
  public DLangType getType() {
    return PsiTreeUtil.getChildOfType(this, DLangType.class);
  }

  @Override
  @Nullable
  public PsiElement getKwAlias() {
    return findChildByType(KW_ALIAS);
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
