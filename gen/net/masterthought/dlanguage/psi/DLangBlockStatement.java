// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface DLangBlockStatement extends PsiElement {

  @Nullable
  DLangStatementList getStatementList();

  @NotNull
  PsiElement getOpBracesLeft();

  @NotNull
  PsiElement getOpBracesRight();

}
