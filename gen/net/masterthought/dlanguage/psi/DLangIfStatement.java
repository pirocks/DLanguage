// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.ResolveState;
import com.intellij.psi.scope.PsiScopeProcessor;

public interface DLangIfStatement extends PsiElement {

  @Nullable
  DLangElseStatement getElseStatement();

  @Nullable
  DLangIfCondition getIfCondition();

  @Nullable
  DLangThenStatement getThenStatement();

  @Nullable
  PsiElement getKwElse();

  @NotNull
  PsiElement getKwIf();

  @Nullable
  PsiElement getOpParLeft();

  @Nullable
  PsiElement getOpParRight();

  boolean processDeclarations(PsiScopeProcessor processor, ResolveState state, PsiElement lastParent, PsiElement place);

}
