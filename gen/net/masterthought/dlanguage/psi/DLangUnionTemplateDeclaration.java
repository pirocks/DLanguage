// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface DLangUnionTemplateDeclaration extends PsiElement {

  @Nullable
  DLangAggregateBody getAggregateBody();

  @Nullable
  DLangConstraint getConstraint();

  @NotNull
  DLangIdentifier getIdentifier();

  @Nullable
  DLangTemplateParameters getTemplateParameters();

  @NotNull
  PsiElement getKwUnion();

}
