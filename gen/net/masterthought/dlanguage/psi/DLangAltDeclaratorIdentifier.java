// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface DLangAltDeclaratorIdentifier extends PsiElement {

  @Nullable
  DLangAltDeclaratorSuffixes getAltDeclaratorSuffixes();

  @Nullable
  DLangBasicType2 getBasicType2();

  @NotNull
  DLangIdentifier getIdentifier();

  @Nullable
  DLangInitializer getInitializer();

  @Nullable
  PsiElement getOpEq();

}
