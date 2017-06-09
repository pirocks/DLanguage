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

public class DLangForStatementImpl extends ASTWrapperPsiElement implements DLangForStatement {

  public DLangForStatementImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DLangVisitor visitor) {
    visitor.visitForStatement(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DLangVisitor) accept((DLangVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public DLangIncrement getIncrement() {
    return PsiTreeUtil.getChildOfType(this, DLangIncrement.class);
  }

  @Override
  @Nullable
  public DLangInitialize getInitialize() {
    return PsiTreeUtil.getChildOfType(this, DLangInitialize.class);
  }

  @Override
  @Nullable
  public DLangScopeStatement getScopeStatement() {
    return PsiTreeUtil.getChildOfType(this, DLangScopeStatement.class);
  }

  @Override
  @Nullable
  public DLangTest getTest() {
    return PsiTreeUtil.getChildOfType(this, DLangTest.class);
  }

  @Override
  @NotNull
  public PsiElement getKwFor() {
    return notNullChild(findChildByType(KW_FOR));
  }

  @Override
  @Nullable
  public PsiElement getOpParLeft() {
    return findChildByType(OP_PAR_LEFT);
  }

  @Override
  @Nullable
  public PsiElement getOpParRight() {
    return findChildByType(OP_PAR_RIGHT);
  }

  @Override
  @Nullable
  public PsiElement getOpScolon() {
    return findChildByType(OP_SCOLON);
  }

  public boolean processDeclarations(PsiScopeProcessor processor, ResolveState state, PsiElement lastParent, PsiElement place) {
    return DPsiImplUtil.processDeclarations(this, processor, state, lastParent, place);
  }

}
