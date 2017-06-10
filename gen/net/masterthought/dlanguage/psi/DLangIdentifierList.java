// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface DLangIdentifierList extends PsiElement {

  @Nullable
  DLangAssignExpression getAssignExpression();

  @NotNull
  DLangIdentifier getIdentifier();

  @Nullable
  DLangIdentifierList getIdentifierList();

  @Nullable
  DLangTemplateInstance getTemplateInstance();

  @Nullable
  PsiElement getOpBracketLeft();

  @Nullable
  PsiElement getOpBracketRight();

  @Nullable
  PsiElement getOpDot();

}