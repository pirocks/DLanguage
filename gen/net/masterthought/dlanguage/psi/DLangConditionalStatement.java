// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.ResolveState;
import com.intellij.psi.scope.PsiScopeProcessor;

public interface DLangConditionalStatement extends PsiElement {

  @Nullable
  DLangBlockStatement getBlockStatement();

  @NotNull
  DLangCondition getCondition();

  @NotNull
  List<DLangDeclarationBlock> getDeclarationBlockList();

  @NotNull
  List<DLangStatement> getStatementList();

  @Nullable
  PsiElement getKwElse();

  void processDeclarations(PsiScopeProcessor processor, ResolveState state, PsiElement lastParent, PsiElement place);

}
