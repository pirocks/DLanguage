// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface DLangCaseRangeStatement extends PsiElement {

  @NotNull
  DLangFirstExp getFirstExp();

  @Nullable
  DLangLastExp getLastExp();

  @Nullable
  DLangScopeStatementList getScopeStatementList();

  @NotNull
  PsiElement getOpDdot();

}
