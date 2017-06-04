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

public class DLangStringLiteralImpl extends ASTWrapperPsiElement implements DLangStringLiteral {

  public DLangStringLiteralImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DLangVisitor visitor) {
    visitor.visitStringLiteral(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DLangVisitor) accept((DLangVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public PsiElement getAlternateWysiwygString() {
    return findChildByType(ALTERNATE_WYSIWYG_STRING);
  }

  @Override
  @Nullable
  public PsiElement getDelimitedString() {
    return findChildByType(DELIMITED_STRING);
  }

  @Override
  @Nullable
  public PsiElement getDoubleQuotedString() {
    return findChildByType(DOUBLE_QUOTED_STRING);
  }

  @Override
  @Nullable
  public PsiElement getHexString() {
    return findChildByType(HEX_STRING);
  }

  @Override
  @Nullable
  public PsiElement getWysiwygString() {
    return findChildByType(WYSIWYG_STRING);
  }

}
