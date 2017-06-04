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

public class DLangAutoDeclarationImpl extends ASTWrapperPsiElement implements DLangAutoDeclaration {

  public DLangAutoDeclarationImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DLangVisitor visitor) {
    visitor.visitAutoDeclaration(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DLangVisitor) accept((DLangVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public DLangAutoDeclarationX getAutoDeclarationX() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, DLangAutoDeclarationX.class));
  }

  @Override
  @Nullable
  public DLangStorageClasses getStorageClasses() {
    return PsiTreeUtil.getChildOfType(this, DLangStorageClasses.class);
  }

  @Override
  @NotNull
  public PsiElement getOpScolon() {
    return notNullChild(findChildByType(OP_SCOLON));
  }

}
