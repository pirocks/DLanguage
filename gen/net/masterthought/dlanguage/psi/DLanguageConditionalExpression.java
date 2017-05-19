// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface DLanguageConditionalExpression extends PsiElement {

  @Nullable
  DLanguageAddExpression getAddExpression();

  @Nullable
  DLanguageAndAndExpression getAndAndExpression();

  @Nullable
  DLanguageAndExpression getAndExpression();

  @Nullable
  DLanguageCmpExpression getCmpExpression();

  @Nullable
  DLanguageConditionalExpression getConditionalExpression();

  @Nullable
  DLanguageExpression getExpression();

  @Nullable
  DLanguageMulExpression getMulExpression();

  @Nullable
  DLanguageOrExpression getOrExpression();

  @Nullable
  DLanguageOrOrExpression getOrOrExpression();

  @Nullable
  DLanguagePostfixExpression getPostfixExpression();

  @Nullable
  DLanguagePowExpression getPowExpression();

  @Nullable
  DLanguageShiftExpression getShiftExpression();

  @Nullable
  DLanguageUnaryExpression getUnaryExpression();

  @Nullable
  DLanguageXorExpression getXorExpression();

  @Nullable
  PsiElement getOpColon();

  @Nullable
  PsiElement getOpQuest();

}
