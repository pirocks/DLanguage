// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface DLangIndexExpression extends PsiElement {

  @NotNull
  DLangArgumentList getArgumentList();

  @Nullable
  DLangPostfixExpression getPostfixExpression();

  @NotNull
  PsiElement getOpBracketLeft();

  @NotNull
  PsiElement getOpBracketRight();

}
