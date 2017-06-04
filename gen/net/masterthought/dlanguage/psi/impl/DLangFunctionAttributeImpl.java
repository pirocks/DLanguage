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

public class DLangFunctionAttributeImpl extends ASTWrapperPsiElement implements DLangFunctionAttribute {

  public DLangFunctionAttributeImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DLangVisitor visitor) {
    visitor.visitFunctionAttribute(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DLangVisitor) accept((DLangVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public DLangProperty getProperty() {
    return PsiTreeUtil.getChildOfType(this, DLangProperty.class);
  }

  @Override
  @Nullable
  public PsiElement getKwNothrow() {
    return findChildByType(KW_NOTHROW);
  }

  @Override
  @Nullable
  public PsiElement getKwPure() {
    return findChildByType(KW_PURE);
  }

}
