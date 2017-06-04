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

public class DLangLinkageAttributeImpl extends ASTWrapperPsiElement implements DLangLinkageAttribute {

  public DLangLinkageAttributeImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DLangVisitor visitor) {
    visitor.visitLinkageAttribute(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DLangVisitor) accept((DLangVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public DLangIdentifier getIdentifier() {
    return PsiTreeUtil.getChildOfType(this, DLangIdentifier.class);
  }

  @Override
  @Nullable
  public DLangIdentifierList getIdentifierList() {
    return PsiTreeUtil.getChildOfType(this, DLangIdentifierList.class);
  }

  @Override
  @Nullable
  public DLangLinkageType getLinkageType() {
    return PsiTreeUtil.getChildOfType(this, DLangLinkageType.class);
  }

  @Override
  @NotNull
  public PsiElement getKwExtern() {
    return notNullChild(findChildByType(KW_EXTERN));
  }

  @Override
  @Nullable
  public PsiElement getOpComma() {
    return findChildByType(OP_COMMA);
  }

  @Override
  @NotNull
  public PsiElement getOpParLeft() {
    return notNullChild(findChildByType(OP_PAR_LEFT));
  }

  @Override
  @NotNull
  public PsiElement getOpParRight() {
    return notNullChild(findChildByType(OP_PAR_RIGHT));
  }

  @Override
  @Nullable
  public PsiElement getOpPlusPlus() {
    return findChildByType(OP_PLUS_PLUS);
  }

}
