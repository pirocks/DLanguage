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

public class DLangConditionalStatementImpl extends ASTWrapperPsiElement implements DLangConditionalStatement {

  public DLangConditionalStatementImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DLangVisitor visitor) {
    visitor.visitConditionalStatement(this);
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
  @NotNull
  public DLangCondition getCondition() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, DLangCondition.class));
  }

  @Override
  @NotNull
  public List<DLangDeclarationBlock> getDeclarationBlockList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, DLangDeclarationBlock.class);
  }

  @Override
  @NotNull
  public List<DLangStatement> getStatementList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, DLangStatement.class);
  }

  @Override
  @Nullable
  public PsiElement getKwElse() {
    return findChildByType(KW_ELSE);
  }

}
