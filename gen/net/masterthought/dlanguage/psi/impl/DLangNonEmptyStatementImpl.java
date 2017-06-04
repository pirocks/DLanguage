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

public class DLangNonEmptyStatementImpl extends ASTWrapperPsiElement implements DLangNonEmptyStatement {

  public DLangNonEmptyStatementImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DLangVisitor visitor) {
    visitor.visitNonEmptyStatement(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DLangVisitor) accept((DLangVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public DLangCaseRangeStatement getCaseRangeStatement() {
    return PsiTreeUtil.getChildOfType(this, DLangCaseRangeStatement.class);
  }

  @Override
  @Nullable
  public DLangCaseStatement getCaseStatement() {
    return PsiTreeUtil.getChildOfType(this, DLangCaseStatement.class);
  }

  @Override
  @Nullable
  public DLangDefaultStatement getDefaultStatement() {
    return PsiTreeUtil.getChildOfType(this, DLangDefaultStatement.class);
  }

  @Override
  @Nullable
  public DLangNonEmptyStatementNoCaseNoDefault getNonEmptyStatementNoCaseNoDefault() {
    return PsiTreeUtil.getChildOfType(this, DLangNonEmptyStatementNoCaseNoDefault.class);
  }

}
