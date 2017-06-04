// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface DLangPostfixExpression extends PsiElement {

  @Nullable
  DLangArgumentList getArgumentList();

  @Nullable
  DLangBasicType getBasicType();

  @Nullable
  DLangIdentifier getIdentifier();

  @Nullable
  DLangIndexExpression getIndexExpression();

  @Nullable
  DLangNewExpression getNewExpression();

  @Nullable
  DLangPostfixExpression getPostfixExpression();

  @Nullable
  DLangPrimaryExpression getPrimaryExpression();

  @Nullable
  DLangSliceExpression getSliceExpression();

  @Nullable
  DLangTemplateInstance getTemplateInstance();

  @Nullable
  DLangTypeCtors getTypeCtors();

  @Nullable
  PsiElement getOpDot();

  @Nullable
  PsiElement getOpMinusMinus();

  @Nullable
  PsiElement getOpParLeft();

  @Nullable
  PsiElement getOpParRight();

  @Nullable
  PsiElement getOpPlusPlus();

}
