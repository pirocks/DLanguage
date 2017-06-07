// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import net.masterthought.dlanguage.psi.interfaces.DNamedElement;
import net.masterthought.dlanguage.psi.interfaces.Declaration;
import com.intellij.psi.StubBasedPsiElement;
import net.masterthought.dlanguage.stubs.DLangParameterStub;
import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiReference;

public interface DLangParameter extends DNamedElement, Declaration, StubBasedPsiElement<DLangParameterStub> {

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

  @NotNull
  String getName();

  String getFullName();

  @Nullable
  PsiElement getNameIdentifier();

  @NotNull
  PsiReference getReference();

  @NotNull
  PsiElement setName(String newName);

  @NotNull
  ItemPresentation getPresentation();

}
