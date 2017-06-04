// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface DLangStructInitializer extends PsiElement {

  @Nullable
  DLangStructMemberInitializers getStructMemberInitializers();

  @NotNull
  PsiElement getOpBracesLeft();

  @NotNull
  PsiElement getOpBracesRight();

  @Nullable
  PsiElement getOpComma();

}
