// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface DLangTemplateAliasParameter extends PsiElement {

  @NotNull
  List<DLangAssignExpression> getAssignExpressionList();

  @Nullable
  DLangIdentifier getIdentifier();

  @NotNull
  List<DLangType> getTypeList();

  @NotNull
  PsiElement getKwAlias();

  @Nullable
  PsiElement getOpColon();

  @Nullable
  PsiElement getOpEq();

}
