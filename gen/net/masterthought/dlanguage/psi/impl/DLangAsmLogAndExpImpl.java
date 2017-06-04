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

public class DLangAsmLogAndExpImpl extends ASTWrapperPsiElement implements DLangAsmLogAndExp {

  public DLangAsmLogAndExpImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DLangVisitor visitor) {
    visitor.visitAsmLogAndExp(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DLangVisitor) accept((DLangVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public DLangAsmLogAndExp getAsmLogAndExp() {
    return PsiTreeUtil.getChildOfType(this, DLangAsmLogAndExp.class);
  }

  @Override
  @NotNull
  public DLangAsmOrExp getAsmOrExp() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, DLangAsmOrExp.class));
  }

  @Override
  @Nullable
  public PsiElement getOpBoolAnd() {
    return findChildByType(OP_BOOL_AND);
  }

}
