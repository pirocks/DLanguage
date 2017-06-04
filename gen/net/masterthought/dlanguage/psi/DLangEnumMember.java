// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface DLangEnumMember extends PsiElement {

  @Nullable
  DLangAssignExpression getAssignExpression();

  @NotNull
  DLangIdentifier getIdentifier();

  @Nullable
  DLangType getType();

  @Nullable
  PsiElement getOpEq();

}
