// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface DLangParameter extends PsiElement {

  @Nullable
  DLangAssignExpression getAssignExpression();

  @Nullable
  DLangBasicType getBasicType();

  @Nullable
  DLangDeclarator getDeclarator();

  @Nullable
  DLangIdentifier getIdentifier();

  @Nullable
  DLangInOut getInOut();

  @Nullable
  DLangType getType();

  @Nullable
  PsiElement getKwAlias();

  @Nullable
  PsiElement getOpEq();

  @Nullable
  PsiElement getOpTripledot();

}
