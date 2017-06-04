// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface DLangTemplateArguments extends PsiElement {

  @Nullable
  DLangTemplateArgumentList getTemplateArgumentList();

  @Nullable
  DLangTemplateSingleArgument getTemplateSingleArgument();

  @NotNull
  PsiElement getOpNot();

  @Nullable
  PsiElement getOpParLeft();

  @Nullable
  PsiElement getOpParRight();

}
