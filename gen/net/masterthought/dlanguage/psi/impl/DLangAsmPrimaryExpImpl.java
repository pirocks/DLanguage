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

public class DLangAsmPrimaryExpImpl extends ASTWrapperPsiElement implements DLangAsmPrimaryExp {

  public DLangAsmPrimaryExpImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DLangVisitor visitor) {
    visitor.visitAsmPrimaryExp(this);
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
  public DLangDotIdentifier getDotIdentifier() {
    return PsiTreeUtil.getChildOfType(this, DLangDotIdentifier.class);
  }

  @Override
  @Nullable
  public DLangRegister getRegister() {
    return PsiTreeUtil.getChildOfType(this, DLangRegister.class);
  }

  @Override
  @Nullable
  public DLangRegister64 getRegister64() {
    return PsiTreeUtil.getChildOfType(this, DLangRegister64.class);
  }

  @Override
  @Nullable
  public DLangStringLiteral getStringLiteral() {
    return PsiTreeUtil.getChildOfType(this, DLangStringLiteral.class);
  }

  @Override
  @Nullable
  public PsiElement getFloatLiteral() {
    return findChildByType(FLOAT_LITERAL);
  }

  @Override
  @Nullable
  public PsiElement getIntegerLiteral() {
    return findChildByType(INTEGER_LITERAL);
  }

  @Override
  @Nullable
  public PsiElement getKwThis() {
    return findChildByType(KW_THIS);
  }

  @Override
  @Nullable
  public PsiElement getOpColon() {
    return findChildByType(OP_COLON);
  }

  @Override
  @Nullable
  public PsiElement getOpDollar() {
    return findChildByType(OP_DOLLAR);
  }

}
