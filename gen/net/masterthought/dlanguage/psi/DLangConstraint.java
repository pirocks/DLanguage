// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface DLangConstraint extends PsiElement {

  @Nullable
  DLangCommaExpression getCommaExpression();

  @NotNull
  PsiElement getKwIf();

  @Nullable
  PsiElement getOpParLeft();

  @Nullable
  PsiElement getOpParRight();

}