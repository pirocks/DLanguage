// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface DLangPostblit extends PsiElement {

  @Nullable
  DLangFunctionBody getFunctionBody();

  @Nullable
  DLangMemberFunctionAttributes getMemberFunctionAttributes();

  @NotNull
  PsiElement getOpParLeft();

  @NotNull
  PsiElement getOpParRight();

  @Nullable
  PsiElement getOpScolon();

}
