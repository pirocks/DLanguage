// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface DLangMultipleAssign extends PsiElement {

  @NotNull
  List<DLangAssignExpression> getAssignExpressionList();

  @Nullable
  DLangMultipleAssign getMultipleAssign();

  @Nullable
  PsiElement getOpComma();

  @Nullable
  PsiElement getOpDdot();

}
