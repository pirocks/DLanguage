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

public class DLangDeclaratorImpl extends ASTWrapperPsiElement implements DLangDeclarator {

  public DLangDeclaratorImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DLangVisitor visitor) {
    visitor.visitDeclarator(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DLangVisitor) accept((DLangVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public DLangAltDeclarator getAltDeclarator() {
    return PsiTreeUtil.getChildOfType(this, DLangAltDeclarator.class);
  }

  @Override
  @Nullable
  public DLangVarDeclarator getVarDeclarator() {
    return PsiTreeUtil.getChildOfType(this, DLangVarDeclarator.class);
  }

}
