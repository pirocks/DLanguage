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

public class DLangFunctionContractsImpl extends ASTWrapperPsiElement implements DLangFunctionContracts {

  public DLangFunctionContractsImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DLangVisitor visitor) {
    visitor.visitFunctionContracts(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DLangVisitor) accept((DLangVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public DLangInStatement getInStatement() {
    return PsiTreeUtil.getChildOfType(this, DLangInStatement.class);
  }

  @Override
  @Nullable
  public DLangOutStatement getOutStatement() {
    return PsiTreeUtil.getChildOfType(this, DLangOutStatement.class);
  }

}
