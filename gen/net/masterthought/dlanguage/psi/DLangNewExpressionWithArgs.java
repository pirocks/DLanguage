// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface DLangNewExpressionWithArgs extends PsiElement {

  @Nullable
  DLangAllocatorArguments getAllocatorArguments();

  @Nullable
  DLangArgumentList getArgumentList();

  @Nullable
  DLangAssignExpression getAssignExpression();

  @Nullable
  DLangNewAnonClassExpression getNewAnonClassExpression();

  @Nullable
  DLangType getType();

  @Nullable
  PsiElement getKwNew();

  @Nullable
  PsiElement getOpBracketLeft();

  @Nullable
  PsiElement getOpBracketRight();

  @Nullable
  PsiElement getOpParLeft();

  @Nullable
  PsiElement getOpParRight();

}
