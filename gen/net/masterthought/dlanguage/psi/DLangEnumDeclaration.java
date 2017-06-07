// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import net.masterthought.dlanguage.psi.interfaces.DNamedElement;
import net.masterthought.dlanguage.psi.interfaces.HasVisibility;
import net.masterthought.dlanguage.psi.interfaces.Declaration;
import com.intellij.psi.StubBasedPsiElement;
import net.masterthought.dlanguage.stubs.DLangEnumDeclStub;
import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiReference;
import net.masterthought.dlanguage.psi.interfaces.containers.Container;
import net.masterthought.dlanguage.psi.interfaces.HasVisibility.Visibility;

public interface DLangEnumDeclaration extends DNamedElement, HasVisibility, Declaration, StubBasedPsiElement<DLangEnumDeclStub> {

  @Nullable
  DLangAnonymousEnumDeclaration getAnonymousEnumDeclaration();

  @Nullable
  DLangEnumBaseType getEnumBaseType();

  @Nullable
  DLangEnumBody getEnumBody();

  @Nullable
  DLangIdentifier getIdentifier();

  @Nullable
  PsiElement getKwEnum();

  @Nullable
  PsiElement getOpColon();

  @NotNull
  String getName();

  String getFullName();

  @Nullable
  PsiElement getNameIdentifier();

  @NotNull
  PsiReference getReference();

  @Nullable
  PsiElement setName(String newName);

  @NotNull
  ItemPresentation getPresentation();

  boolean isSomeVisibility(Visibility visibility);

  boolean isSomeVisibility(Visibility visibility, Class<? extends Container> containerType);

}
