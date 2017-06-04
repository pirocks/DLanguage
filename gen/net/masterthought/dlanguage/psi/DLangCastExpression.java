// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface DLangCastExpression extends PsiElement {

  @Nullable
  DLangCastExpression getCastExpression();

  @Nullable
  DLangDeleteExpression getDeleteExpression();

  @Nullable
  DLangIdentifier getIdentifier();

  @Nullable
  DLangPostfixExpression getPostfixExpression();

  @Nullable
  DLangPowExpression_ getPowExpression_();

  @Nullable
  DLangTemplateInstance getTemplateInstance();

  @NotNull
  List<DLangType> getTypeList();

  @Nullable
  DLangTypeCtor getTypeCtor();

  @Nullable
  DLangTypeCtors getTypeCtors();

  @NotNull
  PsiElement getKwCast();

  @Nullable
  PsiElement getOpDot();

}
