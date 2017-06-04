// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface DLangFinalSwitchStatement extends PsiElement {

  @Nullable
  DLangCommaExpression getCommaExpression();

  @Nullable
  DLangScopeStatement getScopeStatement();

  @NotNull
  PsiElement getKwFinal();

  @NotNull
  PsiElement getKwSwitch();

  @Nullable
  PsiElement getOpParLeft();

  @Nullable
  PsiElement getOpParRight();

}
