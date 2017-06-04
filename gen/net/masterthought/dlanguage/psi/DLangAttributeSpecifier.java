// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface DLangAttributeSpecifier extends PsiElement {

  @NotNull
  DLangAttribute getAttribute();

  @Nullable
  DLangDeclarationBlock getDeclarationBlock();

  @Nullable
  PsiElement getOpColon();

}
