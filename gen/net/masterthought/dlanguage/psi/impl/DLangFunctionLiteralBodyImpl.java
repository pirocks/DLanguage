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

public class DLangFunctionLiteralBodyImpl extends ASTWrapperPsiElement implements DLangFunctionLiteralBody {

  public DLangFunctionLiteralBodyImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DLangVisitor visitor) {
    visitor.visitFunctionLiteralBody(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DLangVisitor) accept((DLangVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public DLangBlockStatement getBlockStatement() {
    return PsiTreeUtil.getChildOfType(this, DLangBlockStatement.class);
  }

  @Override
  @Nullable
  public DLangBodyStatement getBodyStatement() {
    return PsiTreeUtil.getChildOfType(this, DLangBodyStatement.class);
  }

  @Override
  @Nullable
  public DLangFunctionContracts getFunctionContracts() {
    return PsiTreeUtil.getChildOfType(this, DLangFunctionContracts.class);
  }

  @Override
  @NotNull
  public List<DLangProperty> getPropertyList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, DLangProperty.class);
  }

}
