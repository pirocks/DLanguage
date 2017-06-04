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

public class DLangStatementListNoCaseNoDefaultImpl extends ASTWrapperPsiElement implements DLangStatementListNoCaseNoDefault {

  public DLangStatementListNoCaseNoDefaultImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DLangVisitor visitor) {
    visitor.visitStatementListNoCaseNoDefault(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DLangVisitor) accept((DLangVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public DLangStatementListNoCaseNoDefault getStatementListNoCaseNoDefault() {
    return PsiTreeUtil.getChildOfType(this, DLangStatementListNoCaseNoDefault.class);
  }

  @Override
  @NotNull
  public DLangStatementNoCaseNoDefault getStatementNoCaseNoDefault() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, DLangStatementNoCaseNoDefault.class));
  }

}
