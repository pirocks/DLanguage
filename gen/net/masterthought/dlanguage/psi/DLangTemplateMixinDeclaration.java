// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import net.masterthought.dlanguage.psi.interfaces.DNamedElement;
import net.masterthought.dlanguage.psi.interfaces.containers.StatementContainer;
import net.masterthought.dlanguage.psi.interfaces.containers.MixinContainer;
import net.masterthought.dlanguage.psi.interfaces.containers.GlobalDeclarationContainer;
import net.masterthought.dlanguage.psi.interfaces.HasVisibility;
import net.masterthought.dlanguage.psi.interfaces.HasTemplateArguments;
import net.masterthought.dlanguage.psi.interfaces.Mixinable;
import net.masterthought.dlanguage.psi.interfaces.Declaration;
import com.intellij.psi.StubBasedPsiElement;
import net.masterthought.dlanguage.stubs.DLangTemplateMixinDeclStub;
import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiReference;
import net.masterthought.dlanguage.psi.interfaces.containers.Container;
import net.masterthought.dlanguage.psi.interfaces.HasVisibility.Visibility;

public interface DLangTemplateMixinDeclaration extends DNamedElement, StatementContainer, MixinContainer, GlobalDeclarationContainer, HasVisibility, HasTemplateArguments, Mixinable, Declaration, StubBasedPsiElement<DLangTemplateMixinDeclStub> {

  @Nullable
  DLangAggregateBody getAggregateBody();

  @Nullable
  DLangConstraint getConstraint();

  @NotNull
  DLangIdentifier getIdentifier();

  @Nullable
  DLangTemplateParameters getTemplateParameters();

  @NotNull
  PsiElement getKwMixin();

  @NotNull
  PsiElement getKwTemplate();

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

  //WARNING: processDeclarations(...) is skipped
  //matching processDeclarations(DLangTemplateMixinDeclaration, ...)
  //methods are not found in DPsiImplUtil

}
