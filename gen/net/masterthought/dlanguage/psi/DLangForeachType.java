// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import net.masterthought.dlanguage.psi.interfaces.DNamedElement;
import net.masterthought.dlanguage.psi.interfaces.Declaration;
import net.masterthought.dlanguage.psi.interfaces.VariableDeclaration;
import com.intellij.psi.StubBasedPsiElement;
import net.masterthought.dlanguage.stubs.DLangForeachTypeStub;
import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiReference;

public interface DLangForeachType extends DNamedElement, Declaration, VariableDeclaration, StubBasedPsiElement<DLangForeachTypeStub> {

  @Nullable
  DLangForeachTypeAttribute getForeachTypeAttribute();

  @Nullable
  DLangForeachTypeAttributes getForeachTypeAttributes();

  @NotNull
  DLangIdentifier getIdentifier();

  @Nullable
  DLangType getType();

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
