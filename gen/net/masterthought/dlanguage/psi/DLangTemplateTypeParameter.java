// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface DLangTemplateTypeParameter extends PsiElement {

  @Nullable
  DLangAssignExpression getAssignExpression();

  @Nullable
  DLangIdentifier getIdentifier();

  @Nullable
  DLangTemplateValueParameterDefault getTemplateValueParameterDefault();

  @NotNull
  List<DLangType> getTypeList();

  @Nullable
  PsiElement getOpEq();

  @Nullable
  PsiElement getOpTripledot();

}
