// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface DLangVersionSpecification extends PsiElement {

  @Nullable
  DLangIdentifier getIdentifier();

  @Nullable
  PsiElement getIntegerLiteral();

  @NotNull
  PsiElement getKwVersion();

  @NotNull
  PsiElement getOpEq();

  @Nullable
  PsiElement getOpScolon();

}
