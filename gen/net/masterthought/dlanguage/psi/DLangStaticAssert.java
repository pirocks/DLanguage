// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface DLangStaticAssert extends PsiElement {

  @NotNull
  List<DLangAssignExpression> getAssignExpressionList();

  @NotNull
  PsiElement getKwAssert();

  @NotNull
  PsiElement getKwStatic();

  @Nullable
  PsiElement getOpComma();

  @NotNull
  PsiElement getOpParLeft();

  @Nullable
  PsiElement getOpParRight();

  @Nullable
  PsiElement getOpScolon();

}
