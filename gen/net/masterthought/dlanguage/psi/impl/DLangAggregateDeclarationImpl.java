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

public class DLangAggregateDeclarationImpl extends ASTWrapperPsiElement implements DLangAggregateDeclaration {

  public DLangAggregateDeclarationImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DLangVisitor visitor) {
    visitor.visitAggregateDeclaration(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DLangVisitor) accept((DLangVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public DLangClassDeclaration getClassDeclaration() {
    return PsiTreeUtil.getChildOfType(this, DLangClassDeclaration.class);
  }

  @Override
  @Nullable
  public DLangInterfaceDeclaration getInterfaceDeclaration() {
    return PsiTreeUtil.getChildOfType(this, DLangInterfaceDeclaration.class);
  }

  @Override
  @Nullable
  public DLangStructDeclaration getStructDeclaration() {
    return PsiTreeUtil.getChildOfType(this, DLangStructDeclaration.class);
  }

  @Override
  @Nullable
  public DLangUnionDeclaration getUnionDeclaration() {
    return PsiTreeUtil.getChildOfType(this, DLangUnionDeclaration.class);
  }

  public boolean processDeclarations(PsiScopeProcessor processor, ResolveState state, PsiElement lastParent, PsiElement place) {
    return DPsiImplUtil.processDeclarations(this, processor, state, lastParent, place);
  }

}
