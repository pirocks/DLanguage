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

public class DLangDeclaratorsImpl extends ASTWrapperPsiElement implements DLangDeclarators {

  public DLangDeclaratorsImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DLangVisitor visitor) {
    visitor.visitDeclarators(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DLangVisitor) accept((DLangVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public DLangDeclaratorIdentifierList getDeclaratorIdentifierList() {
    return PsiTreeUtil.getChildOfType(this, DLangDeclaratorIdentifierList.class);
  }

  @Override
  @NotNull
  public DLangDeclaratorInitializer getDeclaratorInitializer() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, DLangDeclaratorInitializer.class));
  }

  @Override
  @Nullable
  public PsiElement getOpComma() {
    return findChildByType(OP_COMMA);
  }

}
