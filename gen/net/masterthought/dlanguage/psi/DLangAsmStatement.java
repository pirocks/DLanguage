// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface DLangAsmStatement extends PsiElement {

  @Nullable
  DLangAsmInstructionList getAsmInstructionList();

  @Nullable
  DLangFunctionAttributes getFunctionAttributes();

  @NotNull
  PsiElement getKwAsm();

  @Nullable
  PsiElement getOpBracesLeft();

  @Nullable
  PsiElement getOpBracesRight();

}
