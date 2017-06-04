// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface DLangImportDeclaration extends PsiElement {

  @Nullable
  DLangImportList getImportList();

  @NotNull
  PsiElement getKwImport();

  @Nullable
  PsiElement getKwStatic();

  @Nullable
  PsiElement getOpScolon();

}
