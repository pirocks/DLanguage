// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface DLangImportList extends PsiElement {

  @NotNull
  DLangImport getImport();

  @Nullable
  DLangImportBindList getImportBindList();

  @Nullable
  DLangImportList getImportList();

  @Nullable
  PsiElement getOpColon();

  @Nullable
  PsiElement getOpComma();

}
