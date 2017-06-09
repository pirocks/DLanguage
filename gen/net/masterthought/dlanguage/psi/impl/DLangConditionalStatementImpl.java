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
import com.intellij.psi.ResolveState;
import com.intellij.psi.scope.PsiScopeProcessor;

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

  public boolean processDeclarations(PsiScopeProcessor processor, ResolveState state, PsiElement lastParent, PsiElement place) {
    return DPsiImplUtil.processDeclarations(this, processor, state, lastParent, place);
  }

}
