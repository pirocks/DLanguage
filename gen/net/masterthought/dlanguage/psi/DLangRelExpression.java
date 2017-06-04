// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface DLangRelExpression extends PsiElement {

  @Nullable
  DLangAddExpression_ getAddExpression_();

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
  PsiElement getOpDot();

  @Nullable
  PsiElement getOpGt();

  @Nullable
  PsiElement getOpGtEq();

  @Nullable
  PsiElement getOpLess();

  @Nullable
  PsiElement getOpLessEq();

  @Nullable
  PsiElement getOpLessGr();

  @Nullable
  PsiElement getOpLessGrEq();

  @Nullable
  PsiElement getOpNotGr();

  @Nullable
  PsiElement getOpNotGrEq();

  @Nullable
  PsiElement getOpNotLess();

  @Nullable
  PsiElement getOpNotLessEq();

  @Nullable
  PsiElement getOpParLeft();

  @Nullable
  PsiElement getOpParRight();

  @Nullable
  PsiElement getOpUnord();

  @Nullable
  PsiElement getOpUnordEq();

}
