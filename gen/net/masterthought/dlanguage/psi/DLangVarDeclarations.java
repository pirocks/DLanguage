// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface DLangVarDeclarations extends PsiElement {

  @Nullable
  DLangAutoDeclaration getAutoDeclaration();

  @Nullable
  DLangBasicType getBasicType();

  @Nullable
  DLangDeclarators getDeclarators();

  @Nullable
  DLangStorageClasses getStorageClasses();

  @Nullable
  PsiElement getOpScolon();

}
