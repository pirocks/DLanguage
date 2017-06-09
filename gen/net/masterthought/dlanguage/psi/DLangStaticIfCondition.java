// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface DLangStaticIfCondition extends PsiElement {

  @Nullable
  DLangAssignExpression getAssignExpression();

  @Nullable
  DLangDeclarationBlock getDeclarationBlock();

  @Nullable
  DLangStaticElseCondition getStaticElseCondition();

  @NotNull
  PsiElement getKwIf();

  @NotNull
  PsiElement getKwStatic();

  @NotNull
  PsiElement getOpParLeft();

  @Nullable
  PsiElement getOpParRight();

}
