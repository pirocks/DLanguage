// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static net.masterthought.dlanguage.psi.DLangTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import net.masterthought.dlanguage.psi.*;

public class DLangAsmRelExpImpl extends ASTWrapperPsiElement implements DLangAsmRelExp {

  public DLangAsmRelExpImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DLangVisitor visitor) {
    visitor.visitAsmRelExp(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DLangVisitor) accept((DLangVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public DLangAsmRelExp getAsmRelExp() {
    return PsiTreeUtil.getChildOfType(this, DLangAsmRelExp.class);
  }

  @Override
  @NotNull
  public DLangAsmShiftExp getAsmShiftExp() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, DLangAsmShiftExp.class));
  }

  @Override
  @Nullable
  public PsiElement getOpGt() {
    return findChildByType(OP_GT);
  }

  @Override
  @Nullable
  public PsiElement getOpGtEq() {
    return findChildByType(OP_GT_EQ);
  }

  @Override
  @Nullable
  public PsiElement getOpLess() {
    return findChildByType(OP_LESS);
  }

  @Override
  @Nullable
  public PsiElement getOpLessEq() {
    return findChildByType(OP_LESS_EQ);
  }

}
