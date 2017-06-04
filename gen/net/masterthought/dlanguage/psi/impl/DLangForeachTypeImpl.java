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

public class DLangForeachTypeImpl extends ASTWrapperPsiElement implements DLangForeachType {

  public DLangForeachTypeImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DLangVisitor visitor) {
    visitor.visitForeachType(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DLangVisitor) accept((DLangVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public DLangForeachTypeAttribute getForeachTypeAttribute() {
    return PsiTreeUtil.getChildOfType(this, DLangForeachTypeAttribute.class);
  }

  @Override
  @Nullable
  public DLangForeachTypeAttributes getForeachTypeAttributes() {
    return PsiTreeUtil.getChildOfType(this, DLangForeachTypeAttributes.class);
  }

  @Override
  @NotNull
  public DLangIdentifier getIdentifier() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, DLangIdentifier.class));
  }

  @Override
  @Nullable
  public DLangType getType() {
    return PsiTreeUtil.getChildOfType(this, DLangType.class);
  }

}
