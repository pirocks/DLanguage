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

public class DLangAsmBrExpImpl extends ASTWrapperPsiElement implements DLangAsmBrExp {

  public DLangAsmBrExpImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DLangVisitor visitor) {
    visitor.visitAsmBrExp(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DLangVisitor) accept((DLangVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public DLangAsmExp getAsmExp() {
    return PsiTreeUtil.getChildOfType(this, DLangAsmExp.class);
  }

  @Override
  @Nullable
  public DLangAsmUnaExp getAsmUnaExp() {
    return PsiTreeUtil.getChildOfType(this, DLangAsmUnaExp.class);
  }

  @Override
  @Nullable
  public PsiElement getOpBracketLeft() {
    return findChildByType(OP_BRACKET_LEFT);
  }

  @Override
  @Nullable
  public PsiElement getOpBracketRight() {
    return findChildByType(OP_BRACKET_RIGHT);
  }

}
