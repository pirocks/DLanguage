// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface DLanguageScopeGuardStatement extends PsiElement {

  @Nullable
  DLanguageStatement getStatement();

  @NotNull
  PsiElement getKwScope();

  @Nullable
  PsiElement getOpParLeft();

  @Nullable
  PsiElement getOpParRight();

}