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

public class DLangCatchesImpl extends ASTWrapperPsiElement implements DLangCatches {

  public DLangCatchesImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DLangVisitor visitor) {
    visitor.visitCatches(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DLangVisitor) accept((DLangVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<DLangCatch> getCatchList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, DLangCatch.class);
  }

  @Override
  @Nullable
  public DLangLastCatch getLastCatch() {
    return PsiTreeUtil.getChildOfType(this, DLangLastCatch.class);
  }

}
