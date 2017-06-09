// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.ResolveState;
import com.intellij.psi.scope.PsiScopeProcessor;

public interface DLangForStatement extends PsiElement {

  @Nullable
  DLangIncrement getIncrement();

  @Nullable
  DLangInitialize getInitialize();

  @Nullable
  DLangScopeStatement getScopeStatement();

  @Nullable
  DLangTest getTest();

  @NotNull
  PsiElement getKwFor();

  @Nullable
  PsiElement getOpParLeft();

  @Nullable
  PsiElement getOpParRight();

  @Nullable
  PsiElement getOpScolon();

  boolean processDeclarations(PsiScopeProcessor processor, ResolveState state, PsiElement lastParent, PsiElement place);

}
