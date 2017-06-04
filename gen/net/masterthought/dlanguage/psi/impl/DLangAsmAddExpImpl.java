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

public class DLangAsmAddExpImpl extends ASTWrapperPsiElement implements DLangAsmAddExp {

  public DLangAsmAddExpImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DLangVisitor visitor) {
    visitor.visitAsmAddExp(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DLangVisitor) accept((DLangVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public DLangAsmAddExp getAsmAddExp() {
    return PsiTreeUtil.getChildOfType(this, DLangAsmAddExp.class);
  }

  @Override
  @NotNull
  public DLangAsmMulExp getAsmMulExp() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, DLangAsmMulExp.class));
  }

  @Override
  @Nullable
  public PsiElement getOpMinus() {
    return findChildByType(OP_MINUS);
  }

  @Override
  @Nullable
  public PsiElement getOpPlus() {
    return findChildByType(OP_PLUS);
  }

}
