// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface DLangAsmMulExp extends PsiElement {

  @NotNull
  DLangAsmBrExp getAsmBrExp();

  @Nullable
  DLangAsmMulExp getAsmMulExp();

  @Nullable
  PsiElement getOpAsterisk();

  @Nullable
  PsiElement getOpDiv();

  @Nullable
  PsiElement getOpMod();

}
