// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface DLangXorExpression_ extends PsiElement {

  @Nullable
  DLangAddExpression_ getAddExpression_();

  @Nullable
  DLangAndExxpression_ getAndExxpression_();

  @Nullable
  DLangCastExpression getCastExpression();

  @Nullable
  DLangDeleteExpression getDeleteExpression();

  @Nullable
  DLangIdentifier getIdentifier();

  @Nullable
  DLangMulExpression_ getMulExpression_();

  @Nullable
  DLangPostfixExpression getPostfixExpression();

  @Nullable
  DLangPowExpression_ getPowExpression_();

  @Nullable
  DLangShiftExpression_ getShiftExpression_();

  @Nullable
  DLangTemplateInstance getTemplateInstance();

  @Nullable
  DLangType getType();

  @Nullable
  DLangTypeCtor getTypeCtor();

  @Nullable
  DLangXorExpression_ getXorExpression_();

  @Nullable
  PsiElement getOpDot();

  @Nullable
  PsiElement getOpParLeft();

  @Nullable
  PsiElement getOpParRight();

  @NotNull
  PsiElement getOpXor();

}