// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface DLangCatch extends PsiElement {

  @NotNull
  DLangCatchParameter getCatchParameter();

  @Nullable
  DLangStatement getStatement();

  @NotNull
  PsiElement getKwCatch();

  @NotNull
  PsiElement getOpParLeft();

  @Nullable
  PsiElement getOpParRight();

}
