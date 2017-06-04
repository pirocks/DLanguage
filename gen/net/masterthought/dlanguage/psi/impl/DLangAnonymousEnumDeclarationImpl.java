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

public class DLangAnonymousEnumDeclarationImpl extends ASTWrapperPsiElement implements DLangAnonymousEnumDeclaration {

  public DLangAnonymousEnumDeclarationImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DLangVisitor visitor) {
    visitor.visitAnonymousEnumDeclaration(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DLangVisitor) accept((DLangVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public DLangEnumBaseType getEnumBaseType() {
    return PsiTreeUtil.getChildOfType(this, DLangEnumBaseType.class);
  }

  @Override
  @Nullable
  public DLangEnumMembers getEnumMembers() {
    return PsiTreeUtil.getChildOfType(this, DLangEnumMembers.class);
  }

  @Override
  @NotNull
  public PsiElement getKwEnum() {
    return notNullChild(findChildByType(KW_ENUM));
  }

  @Override
  @Nullable
  public PsiElement getOpBracesLeft() {
    return findChildByType(OP_BRACES_LEFT);
  }

  @Override
  @Nullable
  public PsiElement getOpBracesRight() {
    return findChildByType(OP_BRACES_RIGHT);
  }

  @Override
  @Nullable
  public PsiElement getOpColon() {
    return findChildByType(OP_COLON);
  }

  @Override
  @Nullable
  public PsiElement getOpComma() {
    return findChildByType(OP_COMMA);
  }

}
