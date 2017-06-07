// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import net.masterthought.dlanguage.psi.interfaces.Mixin;

public interface DLangMixinDeclaration extends Mixin {

  @Nullable
  DLangAssignExpression getAssignExpression();

  @Nullable
  DLangTemplateInstance getTemplateInstance();

  @NotNull
  PsiElement getKwMixin();

  @NotNull
  PsiElement getOpParLeft();

  @NotNull
  PsiElement getOpParRight();

  @NotNull
  PsiElement getOpScolon();

  @NotNull
  @Deprecated
  String getName();

}
