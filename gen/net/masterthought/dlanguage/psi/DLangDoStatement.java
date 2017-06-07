// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.ResolveState;
import com.intellij.psi.scope.PsiScopeProcessor;

public interface DLangDoStatement extends PsiElement {

  @Nullable
  DLangCommaExpression getCommaExpression();

  @Nullable
  DLangScopeStatement getScopeStatement();

  @NotNull
  PsiElement getKwDo();

  @Nullable
  PsiElement getKwWhile();

  @Nullable
  PsiElement getOpParLeft();

  @Nullable
  PsiElement getOpParRight();

  @Nullable
  PsiElement getOpScolon();

  void processDeclarations(PsiScopeProcessor processor, ResolveState state, PsiElement lastParent, PsiElement place);

}
