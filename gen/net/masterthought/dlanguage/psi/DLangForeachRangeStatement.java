// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface DLangForeachRangeStatement extends PsiElement {

  @NotNull
  DLangForeach getForeach();

  @NotNull
  DLangForeachType getForeachType();

  @NotNull
  DLangLwrExpression getLwrExpression();

  @NotNull
  DLangScopeStatement getScopeStatement();

  @NotNull
  DLangUprExpression getUprExpression();

  @NotNull
  PsiElement getOpDdot();

  @NotNull
  PsiElement getOpParLeft();

  @NotNull
  PsiElement getOpParRight();

  @NotNull
  PsiElement getOpScolon();

}
