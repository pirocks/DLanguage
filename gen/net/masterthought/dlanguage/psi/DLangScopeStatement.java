// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface DLangScopeStatement extends PsiElement {

  @Nullable
  DLangBlockStatement getBlockStatement();

  @Nullable
  DLangNonEmptyStatement getNonEmptyStatement();

  //WARNING: processDeclarations(...) is skipped
  //matching processDeclarations(DLangScopeStatement, ...)
  //methods are not found in DPsiImplUtil

}