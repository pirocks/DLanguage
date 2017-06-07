// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static net.masterthought.dlanguage.psi.DLangTypes.*;
import net.masterthought.dlanguage.stubs.DLangLabeledStatementStub;
import net.masterthought.dlanguage.psi.*;
import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiReference;
import com.intellij.psi.stubs.IStubElementType;

public class DLangLabeledStatementImpl extends DNamedStubbedPsiElementBase<DLangLabeledStatementStub> implements DLangLabeledStatement {

  public DLangLabeledStatementImpl(DLangLabeledStatementStub stub, IStubElementType type) {
    super(stub, type);
  }

  public DLangLabeledStatementImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DLangVisitor visitor) {
    visitor.visitLabeledStatement(this);
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
  @Nullable
  public DLangStatement getStatement() {
    return PsiTreeUtil.getChildOfType(this, DLangStatement.class);
  }

  @Override
  @NotNull
  public PsiElement getOpColon() {
    return notNullChild(findChildByType(OP_COLON));
  }

  @NotNull
  public String getName() {
    return DPsiImplUtil.getName(this);
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
