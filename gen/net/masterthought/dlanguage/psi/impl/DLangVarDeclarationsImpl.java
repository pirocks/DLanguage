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

public class DLangVarDeclarationsImpl extends ASTWrapperPsiElement implements DLangVarDeclarations {

  public DLangVarDeclarationsImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DLangVisitor visitor) {
    visitor.visitVarDeclarations(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DLangVisitor) accept((DLangVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public DLangAutoDeclaration getAutoDeclaration() {
    return PsiTreeUtil.getChildOfType(this, DLangAutoDeclaration.class);
  }

  @Override
  @Nullable
  public DLangBasicType getBasicType() {
    return PsiTreeUtil.getChildOfType(this, DLangBasicType.class);
  }

  @Override
  @Nullable
  public DLangDeclarators getDeclarators() {
    return PsiTreeUtil.getChildOfType(this, DLangDeclarators.class);
  }

  @Override
  @Nullable
  public DLangStorageClasses getStorageClasses() {
    return PsiTreeUtil.getChildOfType(this, DLangStorageClasses.class);
  }

  @Override
  @Nullable
  public PsiElement getOpScolon() {
    return findChildByType(OP_SCOLON);
  }

}
