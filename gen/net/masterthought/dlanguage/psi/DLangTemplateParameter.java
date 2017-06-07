// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import net.masterthought.dlanguage.psi.interfaces.DNamedElement;
import net.masterthought.dlanguage.psi.interfaces.Declaration;
import com.intellij.psi.StubBasedPsiElement;
import net.masterthought.dlanguage.stubs.DLangTemplateParameterStub;
import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiReference;

public interface DLangTemplateParameter extends DNamedElement, Declaration, StubBasedPsiElement<DLangTemplateParameterStub> {

  @Nullable
  DLangTemplateAliasParameter getTemplateAliasParameter();

  @Nullable
  DLangTemplateThisParameter getTemplateThisParameter();

  @Nullable
  DLangTemplateTypeParameter getTemplateTypeParameter();

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
