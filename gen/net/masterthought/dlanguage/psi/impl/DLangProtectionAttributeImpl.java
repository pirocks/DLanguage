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

public class DLangProtectionAttributeImpl extends ASTWrapperPsiElement implements DLangProtectionAttribute {

  public DLangProtectionAttributeImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DLangVisitor visitor) {
    visitor.visitProtectionAttribute(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DLangVisitor) accept((DLangVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public DLangIdentifierList getIdentifierList() {
    return PsiTreeUtil.getChildOfType(this, DLangIdentifierList.class);
  }

  @Override
  @Nullable
  public PsiElement getKwExport() {
    return findChildByType(KW_EXPORT);
  }

  @Override
  @Nullable
  public PsiElement getKwPackage() {
    return findChildByType(KW_PACKAGE);
  }

  @Override
  @Nullable
  public PsiElement getKwPrivate() {
    return findChildByType(KW_PRIVATE);
  }

  @Override
  @Nullable
  public PsiElement getKwProtected() {
    return findChildByType(KW_PROTECTED);
  }

  @Override
  @Nullable
  public PsiElement getKwPublic() {
    return findChildByType(KW_PUBLIC);
  }

  @Override
  @Nullable
  public PsiElement getOpParLeft() {
    return findChildByType(OP_PAR_LEFT);
  }

  @Override
  @Nullable
  public PsiElement getOpParRight() {
    return findChildByType(OP_PAR_RIGHT);
  }

}
