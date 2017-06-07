// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import net.masterthought.dlanguage.psi.interfaces.containers.StatementContainer;
import net.masterthought.dlanguage.psi.interfaces.DNamedElement;
import net.masterthought.dlanguage.psi.interfaces.containers.MixinContainer;
import net.masterthought.dlanguage.psi.interfaces.HasVisibility;
import net.masterthought.dlanguage.psi.interfaces.HasTemplateArguments;
import net.masterthought.dlanguage.psi.interfaces.Declaration;
import com.intellij.psi.StubBasedPsiElement;
import net.masterthought.dlanguage.stubs.DLangStructDeclStub;
import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiReference;
import net.masterthought.dlanguage.psi.interfaces.containers.Container;
import net.masterthought.dlanguage.psi.interfaces.HasVisibility.Visibility;

public interface DLangStructDeclaration extends StatementContainer, DNamedElement, MixinContainer, HasVisibility, HasTemplateArguments, Declaration, StubBasedPsiElement<DLangStructDeclStub> {

  @Nullable
  DLangAggregateBody getAggregateBody();

  @Nullable
  DLangConstraint getConstraint();

  @Nullable
  DLangIdentifier getIdentifier();

  @Nullable
  DLangTemplateParameters getTemplateParameters();

  @NotNull
  PsiElement getKwStruct();

  @Nullable
  PsiElement getOpScolon();

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

  boolean isSomeVisibility(Visibility visibility, Class<? extends Container> containerType);

  boolean isSomeVisibility(Visibility visibility);

  //WARNING: getTemplateArguments(...) is skipped
  //matching getTemplateArguments(DLangStructDeclaration, ...)
  //methods are not found in DPsiImplUtil

}
