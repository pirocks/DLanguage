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

public class DLangDeclarationImpl extends ASTWrapperPsiElement implements DLangDeclaration {

  public DLangDeclarationImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DLangVisitor visitor) {
    visitor.visitDeclaration(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DLangVisitor) accept((DLangVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public DLangAggregateDeclaration getAggregateDeclaration() {
    return PsiTreeUtil.getChildOfType(this, DLangAggregateDeclaration.class);
  }

  @Override
  @Nullable
  public DLangAliasDeclaration getAliasDeclaration() {
    return PsiTreeUtil.getChildOfType(this, DLangAliasDeclaration.class);
  }

  @Override
  @Nullable
  public DLangEnumDeclaration getEnumDeclaration() {
    return PsiTreeUtil.getChildOfType(this, DLangEnumDeclaration.class);
  }

  @Override
  @Nullable
  public DLangFuncDeclaration getFuncDeclaration() {
    return PsiTreeUtil.getChildOfType(this, DLangFuncDeclaration.class);
  }

  @Override
  @Nullable
  public DLangImportDeclaration getImportDeclaration() {
    return PsiTreeUtil.getChildOfType(this, DLangImportDeclaration.class);
  }

  @Override
  @Nullable
  public DLangTemplateDeclaration getTemplateDeclaration() {
    return PsiTreeUtil.getChildOfType(this, DLangTemplateDeclaration.class);
  }

  @Override
  @Nullable
  public DLangVarDeclarations getVarDeclarations() {
    return PsiTreeUtil.getChildOfType(this, DLangVarDeclarations.class);
  }

  public boolean processDeclarations(PsiScopeProcessor processor, ResolveState state, PsiElement lastParent, PsiElement place) {
    return DPsiImplUtil.processDeclarations(this, processor, state, lastParent, place);
  }

}
