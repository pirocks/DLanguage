// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import net.masterthought.dlanguage.psi.interfaces.containers.StatementContainer;
import net.masterthought.dlanguage.psi.interfaces.containers.MixinContainer;
import net.masterthought.dlanguage.psi.interfaces.DNamedElement;
import net.masterthought.dlanguage.psi.interfaces.HasVisibility;
import net.masterthought.dlanguage.psi.interfaces.HasTemplateArguments;
import net.masterthought.dlanguage.psi.interfaces.CanInherit;
import net.masterthought.dlanguage.psi.interfaces.Declaration;
import com.intellij.psi.StubBasedPsiElement;
import net.masterthought.dlanguage.stubs.DLangClassDeclStub;
import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiReference;
import com.intellij.psi.ResolveState;
import com.intellij.psi.scope.PsiScopeProcessor;
import net.masterthought.dlanguage.psi.interfaces.containers.Container;
import java.util.Map;
import net.masterthought.dlanguage.psi.interfaces.HasVisibility.Visibility;

public interface DLangClassDeclaration extends StatementContainer, MixinContainer, DNamedElement, HasVisibility, HasTemplateArguments, CanInherit, Declaration, StubBasedPsiElement<DLangClassDeclStub> {

  @Nullable
  DLangAggregateBody getAggregateBody();

  @Nullable
  DLangBaseClassList getBaseClassList();

  @Nullable
  DLangClassTemplateDeclaration getClassTemplateDeclaration();

  @Nullable
  DLangIdentifier getIdentifier();

  @Nullable
  PsiElement getKwClass();

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

  DLangProtectionAttribute getProtection();

  @NotNull
  List<CanInherit> whatInheritsFrom();

  @NotNull
  Map<String, DLangIdentifier> getSuperClassNames();

  boolean processDeclarations(PsiScopeProcessor processor, ResolveState state, PsiElement lastParent, PsiElement place);

}