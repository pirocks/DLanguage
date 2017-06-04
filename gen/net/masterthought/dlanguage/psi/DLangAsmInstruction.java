// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface DLangAsmInstruction extends PsiElement {

  @Nullable
  DLangAsmBrExp getAsmBrExp();

  @Nullable
  DLangAsmInstruction getAsmInstruction();

  @Nullable
  DLangIdentifier getIdentifier();

  @Nullable
  DLangIntegerExpression getIntegerExpression();

  @Nullable
  DLangOpcode getOpcode();

  @Nullable
  DLangOperands getOperands();

  @Nullable
  PsiElement getKwAlign();

  @Nullable
  PsiElement getOpColon();

}
