// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface DLangPrimaryExpression extends PsiElement {

  @Nullable
  DLangArrayLiteral getArrayLiteral();

  @Nullable
  DLangAssertExpression getAssertExpression();

  @Nullable
  DLangAssocArrayLiteral getAssocArrayLiteral();

  @Nullable
  DLangBasicTypeX getBasicTypeX();

  @Nullable
  DLangCommaExpression getCommaExpression();

  @Nullable
  DLangFunctionLiteral getFunctionLiteral();

  @Nullable
  DLangIdentifier getIdentifier();

  @Nullable
  DLangImportExpression getImportExpression();

  @Nullable
  DLangIsExpression getIsExpression();

  @Nullable
  DLangMixinExpression getMixinExpression();

  @Nullable
  DLangNewExpressionWithArgs getNewExpressionWithArgs();

  @Nullable
  DLangSpecialKeyword getSpecialKeyword();

  @Nullable
  DLangStringLiterals getStringLiterals();

  @Nullable
  DLangTemplateInstance getTemplateInstance();

  @Nullable
  DLangTraitsExpression getTraitsExpression();

  @Nullable
  DLangType getType();

  @Nullable
  DLangTypeidExpression getTypeidExpression();

  @Nullable
  DLangTypeof getTypeof();

  @Nullable
  PsiElement getCharacterLiteral();

  @Nullable
  PsiElement getFloatLiteral();

  @Nullable
  PsiElement getIntegerLiteral();

  @Nullable
  PsiElement getKwFalse();

  @Nullable
  PsiElement getKwNull();

  @Nullable
  PsiElement getKwSuper();

  @Nullable
  PsiElement getKwThis();

  @Nullable
  PsiElement getKwTrue();

  @Nullable
  PsiElement getOpDollar();

  @Nullable
  PsiElement getOpDot();

  @Nullable
  PsiElement getOpParLeft();

  @Nullable
  PsiElement getOpParRight();

}
