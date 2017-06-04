// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import net.masterthought.dlanguage.psi.interfaces.Mixin;

public interface DLangTemplateMixin extends Mixin {

  @Nullable
  DLangIdentifier getIdentifier();

  @NotNull
  DLangMixinTemplateName getMixinTemplateName();

  @Nullable
  DLangTemplateArguments getTemplateArguments();

  @NotNull
  PsiElement getKwMixin();

  @NotNull
  PsiElement getOpScolon();

  @NotNull
  String getName();

}
