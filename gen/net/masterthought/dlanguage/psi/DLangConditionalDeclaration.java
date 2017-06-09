// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.ResolveState;
import com.intellij.psi.scope.PsiScopeProcessor;

public interface DLangConditionalDeclaration extends PsiElement {

  @NotNull
  DLangCondition getCondition();

  @Nullable
  DLangDeclDefs getDeclDefs();

  @NotNull
  List<DLangDeclarationBlock> getDeclarationBlockList();

  @Nullable
  PsiElement getKwElse();

  @Nullable
  PsiElement getOpColon();

  boolean processDeclarations(PsiScopeProcessor processor, ResolveState state, PsiElement lastParent, PsiElement place);

}
