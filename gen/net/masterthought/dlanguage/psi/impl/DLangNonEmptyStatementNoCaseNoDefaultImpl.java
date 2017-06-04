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

public class DLangNonEmptyStatementNoCaseNoDefaultImpl extends ASTWrapperPsiElement implements DLangNonEmptyStatementNoCaseNoDefault {

  public DLangNonEmptyStatementNoCaseNoDefaultImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DLangVisitor visitor) {
    visitor.visitNonEmptyStatementNoCaseNoDefault(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DLangVisitor) accept((DLangVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public DLangAsmStatement getAsmStatement() {
    return PsiTreeUtil.getChildOfType(this, DLangAsmStatement.class);
  }

  @Override
  @Nullable
  public DLangBlockStatement getBlockStatement() {
    return PsiTreeUtil.getChildOfType(this, DLangBlockStatement.class);
  }

  @Override
  @Nullable
  public DLangBreakStatement getBreakStatement() {
    return PsiTreeUtil.getChildOfType(this, DLangBreakStatement.class);
  }

  @Override
  @Nullable
  public DLangConditionalStatement getConditionalStatement() {
    return PsiTreeUtil.getChildOfType(this, DLangConditionalStatement.class);
  }

  @Override
  @Nullable
  public DLangContinueStatement getContinueStatement() {
    return PsiTreeUtil.getChildOfType(this, DLangContinueStatement.class);
  }

  @Override
  @Nullable
  public DLangDeclaration getDeclaration() {
    return PsiTreeUtil.getChildOfType(this, DLangDeclaration.class);
  }

  @Override
  @Nullable
  public DLangDeclarationStatement getDeclarationStatement() {
    return PsiTreeUtil.getChildOfType(this, DLangDeclarationStatement.class);
  }

  @Override
  @Nullable
  public DLangDoStatement getDoStatement() {
    return PsiTreeUtil.getChildOfType(this, DLangDoStatement.class);
  }

  @Override
  @Nullable
  public DLangExpressionStatement getExpressionStatement() {
    return PsiTreeUtil.getChildOfType(this, DLangExpressionStatement.class);
  }

  @Override
  @Nullable
  public DLangFinalSwitchStatement getFinalSwitchStatement() {
    return PsiTreeUtil.getChildOfType(this, DLangFinalSwitchStatement.class);
  }

  @Override
  @Nullable
  public DLangForStatement getForStatement() {
    return PsiTreeUtil.getChildOfType(this, DLangForStatement.class);
  }

  @Override
  @Nullable
  public DLangForeachRangeStatement getForeachRangeStatement() {
    return PsiTreeUtil.getChildOfType(this, DLangForeachRangeStatement.class);
  }

  @Override
  @Nullable
  public DLangForeachStatement getForeachStatement() {
    return PsiTreeUtil.getChildOfType(this, DLangForeachStatement.class);
  }

  @Override
  @Nullable
  public DLangGotoStatement getGotoStatement() {
    return PsiTreeUtil.getChildOfType(this, DLangGotoStatement.class);
  }

  @Override
  @Nullable
  public DLangIfStatement getIfStatement() {
    return PsiTreeUtil.getChildOfType(this, DLangIfStatement.class);
  }

  @Override
  @Nullable
  public DLangImportDeclaration getImportDeclaration() {
    return PsiTreeUtil.getChildOfType(this, DLangImportDeclaration.class);
  }

  @Override
  @Nullable
  public DLangLabeledStatement getLabeledStatement() {
    return PsiTreeUtil.getChildOfType(this, DLangLabeledStatement.class);
  }

  @Override
  @Nullable
  public DLangMixinStatement getMixinStatement() {
    return PsiTreeUtil.getChildOfType(this, DLangMixinStatement.class);
  }

  @Override
  @Nullable
  public DLangPragmaStatement getPragmaStatement() {
    return PsiTreeUtil.getChildOfType(this, DLangPragmaStatement.class);
  }

  @Override
  @Nullable
  public DLangReturnStatement getReturnStatement() {
    return PsiTreeUtil.getChildOfType(this, DLangReturnStatement.class);
  }

  @Override
  @Nullable
  public DLangScopeGuardStatement getScopeGuardStatement() {
    return PsiTreeUtil.getChildOfType(this, DLangScopeGuardStatement.class);
  }

  @Override
  @Nullable
  public DLangStaticAssert getStaticAssert() {
    return PsiTreeUtil.getChildOfType(this, DLangStaticAssert.class);
  }

  @Override
  @Nullable
  public DLangSwitchStatement getSwitchStatement() {
    return PsiTreeUtil.getChildOfType(this, DLangSwitchStatement.class);
  }

  @Override
  @Nullable
  public DLangSynchronizedStatement getSynchronizedStatement() {
    return PsiTreeUtil.getChildOfType(this, DLangSynchronizedStatement.class);
  }

  @Override
  @Nullable
  public DLangTemplateMixin getTemplateMixin() {
    return PsiTreeUtil.getChildOfType(this, DLangTemplateMixin.class);
  }

  @Override
  @Nullable
  public DLangThrowStatement getThrowStatement() {
    return PsiTreeUtil.getChildOfType(this, DLangThrowStatement.class);
  }

  @Override
  @Nullable
  public DLangTryStatement getTryStatement() {
    return PsiTreeUtil.getChildOfType(this, DLangTryStatement.class);
  }

  @Override
  @Nullable
  public DLangWhileStatement getWhileStatement() {
    return PsiTreeUtil.getChildOfType(this, DLangWhileStatement.class);
  }

  @Override
  @Nullable
  public DLangWithStatement getWithStatement() {
    return PsiTreeUtil.getChildOfType(this, DLangWithStatement.class);
  }

}
