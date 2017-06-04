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

public class DLangAttributeImpl extends ASTWrapperPsiElement implements DLangAttribute {

  public DLangAttributeImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DLangVisitor visitor) {
    visitor.visitAttribute(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DLangVisitor) accept((DLangVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public DLangAlignAttribute getAlignAttribute() {
    return PsiTreeUtil.getChildOfType(this, DLangAlignAttribute.class);
  }

  @Override
  @Nullable
  public DLangDeprecatedAttribute getDeprecatedAttribute() {
    return PsiTreeUtil.getChildOfType(this, DLangDeprecatedAttribute.class);
  }

  @Override
  @Nullable
  public DLangLinkageAttribute getLinkageAttribute() {
    return PsiTreeUtil.getChildOfType(this, DLangLinkageAttribute.class);
  }

  @Override
  @Nullable
  public DLangPragma getPragma() {
    return PsiTreeUtil.getChildOfType(this, DLangPragma.class);
  }

  @Override
  @Nullable
  public DLangProperty getProperty() {
    return PsiTreeUtil.getChildOfType(this, DLangProperty.class);
  }

  @Override
  @Nullable
  public DLangProtectionAttribute getProtectionAttribute() {
    return PsiTreeUtil.getChildOfType(this, DLangProtectionAttribute.class);
  }

  @Override
  @Nullable
  public PsiElement getKwAbstract() {
    return findChildByType(KW_ABSTRACT);
  }

  @Override
  @Nullable
  public PsiElement getKwAuto() {
    return findChildByType(KW_AUTO);
  }

  @Override
  @Nullable
  public PsiElement getKwConst() {
    return findChildByType(KW_CONST);
  }

  @Override
  @Nullable
  public PsiElement getKwExtern() {
    return findChildByType(KW_EXTERN);
  }

  @Override
  @Nullable
  public PsiElement getKwFinal() {
    return findChildByType(KW_FINAL);
  }

  @Override
  @Nullable
  public PsiElement getKwImmutable() {
    return findChildByType(KW_IMMUTABLE);
  }

  @Override
  @Nullable
  public PsiElement getKwInout() {
    return findChildByType(KW_INOUT);
  }

  @Override
  @Nullable
  public PsiElement getKwNothrow() {
    return findChildByType(KW_NOTHROW);
  }

  @Override
  @Nullable
  public PsiElement getKwOverride() {
    return findChildByType(KW_OVERRIDE);
  }

  @Override
  @Nullable
  public PsiElement getKwPure() {
    return findChildByType(KW_PURE);
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

  @Override
  @Nullable
  public PsiElement getKwShared() {
    return findChildByType(KW_SHARED);
  }

  @Override
  @Nullable
  public PsiElement getKwStatic() {
    return findChildByType(KW_STATIC);
  }

  @Override
  @Nullable
  public PsiElement getKwSynchronized() {
    return findChildByType(KW_SYNCHRONIZED);
  }

  @Override
  @Nullable
  public PsiElement getKwGshared() {
    return findChildByType(KW___GSHARED);
  }

}
