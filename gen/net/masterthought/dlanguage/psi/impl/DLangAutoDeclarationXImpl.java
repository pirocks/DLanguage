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

public class DLangAutoDeclarationXImpl extends ASTWrapperPsiElement implements DLangAutoDeclarationX {

  public DLangAutoDeclarationXImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DLangVisitor visitor) {
    visitor.visitAutoDeclarationX(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DLangVisitor) accept((DLangVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public DLangAutoDeclarationX getAutoDeclarationX() {
    return PsiTreeUtil.getChildOfType(this, DLangAutoDeclarationX.class);
  }

  @Override
  @NotNull
  public DLangAutoDeclarationY getAutoDeclarationY() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, DLangAutoDeclarationY.class));
  }

  @Override
  @Nullable
  public PsiElement getOpComma() {
    return findChildByType(OP_COMMA);
  }

}
