// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface DLangIfCondition extends PsiElement {

  @Nullable
  DLangBasicType getBasicType();

  @NotNull
  DLangCommaExpression getCommaExpression();

  @Nullable
  DLangDeclarator getDeclarator();

  @Nullable
  DLangIdentifier getIdentifier();

  @Nullable
  DLangTypeCtors getTypeCtors();

  @Nullable
  PsiElement getKwAuto();

  @Nullable
  PsiElement getOpEq();

}
