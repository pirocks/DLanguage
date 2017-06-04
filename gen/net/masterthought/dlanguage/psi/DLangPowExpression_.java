// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface DLangPowExpression_ extends PsiElement {

  @Nullable
  DLangCastExpression getCastExpression();

  @Nullable
  DLangDeleteExpression getDeleteExpression();

  @Nullable
  DLangIdentifier getIdentifier();

  @NotNull
  List<DLangPostfixExpression> getPostfixExpressionList();

  @NotNull
  List<DLangPowExpression_> getPowExpression_List();

  @Nullable
  DLangTemplateInstance getTemplateInstance();

  @Nullable
  DLangType getType();

  @Nullable
  DLangTypeCtor getTypeCtor();

  @Nullable
  PsiElement getOpDot();

  @Nullable
  PsiElement getOpParLeft();

  @Nullable
  PsiElement getOpParRight();

}
