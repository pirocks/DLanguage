// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface DLangGotoStatement extends PsiElement {

  @Nullable
  DLangCommaExpression getCommaExpression();

  @Nullable
  DLangIdentifier getIdentifier();

  @Nullable
  PsiElement getKwCase();

  @Nullable
  PsiElement getKwDefault();

  @NotNull
  PsiElement getKwGoto();

  @NotNull
  PsiElement getOpScolon();

}
