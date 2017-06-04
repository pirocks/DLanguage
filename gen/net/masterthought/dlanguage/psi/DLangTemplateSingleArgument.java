// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface DLangTemplateSingleArgument extends PsiElement {

  @Nullable
  DLangAssignExpression getAssignExpression();

  @Nullable
  DLangBasicTypeX getBasicTypeX();

  @Nullable
  DLangIdentifierList getIdentifierList();

  @Nullable
  DLangSpecialKeyword getSpecialKeyword();

  @Nullable
  DLangStringLiteral getStringLiteral();

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
  PsiElement getKwThis();

  @Nullable
  PsiElement getKwTrue();

}
