// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface DLangAnonymousEnumDeclaration extends PsiElement {

  @Nullable
  DLangEnumBaseType getEnumBaseType();

  @Nullable
  DLangEnumMembers getEnumMembers();

  @NotNull
  PsiElement getKwEnum();

  @Nullable
  PsiElement getOpBracesLeft();

  @Nullable
  PsiElement getOpBracesRight();

  @Nullable
  PsiElement getOpColon();

  @Nullable
  PsiElement getOpComma();

}
