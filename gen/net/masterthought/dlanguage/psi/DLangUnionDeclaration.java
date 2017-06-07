// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import net.masterthought.dlanguage.psi.interfaces.containers.StatementContainer;
import net.masterthought.dlanguage.psi.interfaces.DNamedElement;
import net.masterthought.dlanguage.psi.interfaces.HasVisibility;
import net.masterthought.dlanguage.psi.interfaces.HasTemplateArguments;
import net.masterthought.dlanguage.psi.interfaces.Declaration;
import com.intellij.psi.StubBasedPsiElement;
import net.masterthought.dlanguage.stubs.DLangUnionDeclStub;
import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiReference;
import net.masterthought.dlanguage.psi.interfaces.containers.Container;
import net.masterthought.dlanguage.psi.interfaces.HasVisibility.Visibility;

public interface DLangUnionDeclaration extends StatementContainer, DNamedElement, HasVisibility, HasTemplateArguments, Declaration, StubBasedPsiElement<DLangUnionDeclStub> {

  @Nullable
  DLangAggregateBody getAggregateBody();

  @Nullable
  DLangAnonUnionDeclaration getAnonUnionDeclaration();

  @Nullable
  DLangIdentifier getIdentifier();

  @Nullable
  DLangUnionTemplateDeclaration getUnionTemplateDeclaration();

  @Nullable
  PsiElement getKwUnion();

  @Nullable
  PsiElement getOpScolon();

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

  boolean isSomeVisibility(Visibility visibility, Class<? extends Container> containerType);

  boolean isSomeVisibility(Visibility visibility);

  //WARNING: getTemplateArguments(...) is skipped
  //matching getTemplateArguments(DLangUnionDeclaration, ...)
  //methods are not found in DPsiImplUtil

}
