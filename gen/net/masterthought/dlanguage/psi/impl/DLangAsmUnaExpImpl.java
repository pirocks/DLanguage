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

public class DLangAsmUnaExpImpl extends ASTWrapperPsiElement implements DLangAsmUnaExp {

  public DLangAsmUnaExpImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DLangVisitor visitor) {
    visitor.visitAsmUnaExp(this);
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
  public DLangAsmPrimaryExp getAsmPrimaryExp() {
    return PsiTreeUtil.getChildOfType(this, DLangAsmPrimaryExp.class);
  }

  @Override
  @Nullable
  public DLangAsmTypePrefix getAsmTypePrefix() {
    return PsiTreeUtil.getChildOfType(this, DLangAsmTypePrefix.class);
  }

  @Override
  @Nullable
  public DLangAsmUnaExp getAsmUnaExp() {
    return PsiTreeUtil.getChildOfType(this, DLangAsmUnaExp.class);
  }

  @Override
  @Nullable
  public PsiElement getOpMinus() {
    return findChildByType(OP_MINUS);
  }

  @Override
  @Nullable
  public PsiElement getOpNot() {
    return findChildByType(OP_NOT);
  }

  @Override
  @Nullable
  public PsiElement getOpPlus() {
    return findChildByType(OP_PLUS);
  }

  @Override
  @Nullable
  public PsiElement getOpTilda() {
    return findChildByType(OP_TILDA);
  }

}