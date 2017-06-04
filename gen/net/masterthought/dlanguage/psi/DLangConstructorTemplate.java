// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface DLangConstructorTemplate extends PsiElement {

  @Nullable
  DLangConstraint getConstraint();

  @Nullable
  DLangFunctionBody getFunctionBody();

  @Nullable
  DLangMemberFunctionAttributes getMemberFunctionAttributes();

  @NotNull
  DLangParameters getParameters();

  @NotNull
  DLangTemplateParameters getTemplateParameters();

  @NotNull
  PsiElement getKwThis();

  @Nullable
  PsiElement getOpScolon();

}
