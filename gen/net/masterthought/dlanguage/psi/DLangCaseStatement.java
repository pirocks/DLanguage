// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface DLangCaseStatement extends PsiElement {

  @NotNull
  DLangArgumentList getArgumentList();

  @Nullable
  DLangScopeStatementList getScopeStatementList();

  @NotNull
  PsiElement getKwCase();

  @NotNull
  PsiElement getOpColon();

}
