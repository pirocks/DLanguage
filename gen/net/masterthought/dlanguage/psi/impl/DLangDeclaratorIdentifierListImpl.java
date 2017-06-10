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

public class DLangDeclaratorIdentifierListImpl extends ASTWrapperPsiElement implements DLangDeclaratorIdentifierList {

  public DLangDeclaratorIdentifierListImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DLangVisitor visitor) {
    visitor.visitDeclaratorIdentifierList(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DLangVisitor) accept((DLangVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public DLangDeclaratorIdentifier getDeclaratorIdentifier() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, DLangDeclaratorIdentifier.class));
  }

  @Override
  @Nullable
  public DLangDeclaratorIdentifierList getDeclaratorIdentifierList() {
    return PsiTreeUtil.getChildOfType(this, DLangDeclaratorIdentifierList.class);
  }

  @Override
  @Nullable
  public PsiElement getOpComma() {
    return findChildByType(OP_COMMA);
  }

}