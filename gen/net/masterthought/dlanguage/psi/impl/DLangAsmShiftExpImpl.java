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

public class DLangAsmShiftExpImpl extends ASTWrapperPsiElement implements DLangAsmShiftExp {

  public DLangAsmShiftExpImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DLangVisitor visitor) {
    visitor.visitAsmShiftExp(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DLangVisitor) accept((DLangVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public DLangAsmAddExp getAsmAddExp() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, DLangAsmAddExp.class));
  }

  @Override
  @Nullable
  public DLangAsmShiftExp getAsmShiftExp() {
    return PsiTreeUtil.getChildOfType(this, DLangAsmShiftExp.class);
  }

  @Override
  @Nullable
  public PsiElement getOpShLeft() {
    return findChildByType(OP_SH_LEFT);
  }

  @Override
  @Nullable
  public PsiElement getOpShRight() {
    return findChildByType(OP_SH_RIGHT);
  }

  @Override
  @Nullable
  public PsiElement getOpUshRight() {
    return findChildByType(OP_USH_RIGHT);
  }

}
