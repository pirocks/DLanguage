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

public class DLangTypeImpl extends ASTWrapperPsiElement implements DLangType {

  public DLangTypeImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DLangVisitor visitor) {
    visitor.visitType(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DLangVisitor) accept((DLangVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public DLangBasicType getBasicType() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, DLangBasicType.class));
  }

  @Override
  @Nullable
  public DLangBasicType2 getBasicType2() {
    return PsiTreeUtil.getChildOfType(this, DLangBasicType2.class);
  }

  @Override
  @Nullable
  public DLangTypeCtors getTypeCtors() {
    return PsiTreeUtil.getChildOfType(this, DLangTypeCtors.class);
  }

}
