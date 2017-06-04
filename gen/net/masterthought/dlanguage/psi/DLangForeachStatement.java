// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface DLangForeachStatement extends PsiElement {

  @NotNull
  DLangForeach getForeach();

  @NotNull
  DLangForeachAggregate getForeachAggregate();

  @NotNull
  DLangForeachTypeList getForeachTypeList();

  @NotNull
  DLangStatement getStatement();

  @NotNull
  PsiElement getOpParLeft();

  @NotNull
  PsiElement getOpParRight();

  @NotNull
  PsiElement getOpScolon();

}
