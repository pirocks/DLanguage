// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface DLanguageCastExpression extends PsiElement {

  @Nullable
  DLanguageCastExpression getCastExpression();

  @Nullable
  DLanguageDeleteExpression getDeleteExpression();

  @Nullable
  DLanguageIdentifier getIdentifier();

  @Nullable
  DLanguagePowExpression getPowExpression();

  @Nullable
  DLanguageTemplateInstance getTemplateInstance();

  @NotNull
  List<DLanguageType> getTypeList();

  @Nullable
  DLanguageTypeCtors getTypeCtors();

  @NotNull
  PsiElement getKwCast();

  @Nullable
  PsiElement getOpDot();

}
