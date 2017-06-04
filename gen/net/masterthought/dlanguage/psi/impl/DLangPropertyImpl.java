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

public class DLangPropertyImpl extends ASTWrapperPsiElement implements DLangProperty {

  public DLangPropertyImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DLangVisitor visitor) {
    visitor.visitProperty(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DLangVisitor) accept((DLangVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public DLangPropertyIdentifier getPropertyIdentifier() {
    return PsiTreeUtil.getChildOfType(this, DLangPropertyIdentifier.class);
  }

  @Override
  @Nullable
  public DLangUserDefinedAttribute getUserDefinedAttribute() {
    return PsiTreeUtil.getChildOfType(this, DLangUserDefinedAttribute.class);
  }

  @Override
  @Nullable
  public PsiElement getOpAt() {
    return findChildByType(OP_AT);
  }

}
