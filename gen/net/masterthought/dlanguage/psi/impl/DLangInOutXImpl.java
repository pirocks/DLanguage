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

public class DLangInOutXImpl extends ASTWrapperPsiElement implements DLangInOutX {

  public DLangInOutXImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DLangVisitor visitor) {
    visitor.visitInOutX(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DLangVisitor) accept((DLangVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public DLangTypeCtor getTypeCtor() {
    return PsiTreeUtil.getChildOfType(this, DLangTypeCtor.class);
  }

  @Override
  @Nullable
  public PsiElement getKwAuto() {
    return findChildByType(KW_AUTO);
  }

  @Override
  @Nullable
  public PsiElement getKwFinal() {
    return findChildByType(KW_FINAL);
  }

  @Override
  @Nullable
  public PsiElement getKwIn() {
    return findChildByType(KW_IN);
  }

  @Override
  @Nullable
  public PsiElement getKwLazy() {
    return findChildByType(KW_LAZY);
  }

  @Override
  @Nullable
  public PsiElement getKwOut() {
    return findChildByType(KW_OUT);
  }

  @Override
  @Nullable
  public PsiElement getKwRef() {
    return findChildByType(KW_REF);
  }

  @Override
  @Nullable
  public PsiElement getKwScope() {
    return findChildByType(KW_SCOPE);
  }

}
