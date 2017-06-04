// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface DLangDeclaratorIdentifierList extends PsiElement {

  @NotNull
  DLangDeclaratorIdentifier getDeclaratorIdentifier();

  @Nullable
  DLangDeclaratorIdentifierList getDeclaratorIdentifierList();

  @Nullable
  PsiElement getOpComma();

}
