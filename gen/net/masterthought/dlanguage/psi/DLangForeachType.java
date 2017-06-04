// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface DLangForeachType extends PsiElement {

  @Nullable
  DLangForeachTypeAttribute getForeachTypeAttribute();

  @Nullable
  DLangForeachTypeAttributes getForeachTypeAttributes();

  @NotNull
  DLangIdentifier getIdentifier();

  @Nullable
  DLangType getType();

}
