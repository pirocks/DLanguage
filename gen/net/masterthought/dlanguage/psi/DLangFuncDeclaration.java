// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import net.masterthought.dlanguage.psi.interfaces.containers.StatementContainer;
import net.masterthought.dlanguage.psi.interfaces.DNamedElement;
import net.masterthought.dlanguage.psi.interfaces.HasVisibility;
import net.masterthought.dlanguage.psi.interfaces.HasProperty;
import net.masterthought.dlanguage.psi.interfaces.HasTemplateArguments;
import net.masterthought.dlanguage.psi.interfaces.HasArguments;
import net.masterthought.dlanguage.psi.interfaces.Declaration;
import com.intellij.psi.StubBasedPsiElement;
import net.masterthought.dlanguage.stubs.DLangFuncDeclarationStub;
import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiReference;
import net.masterthought.dlanguage.psi.interfaces.containers.Container;
import net.masterthought.dlanguage.psi.interfaces.HasVisibility.Visibility;

public interface DLangFuncDeclaration extends StatementContainer, DNamedElement, HasVisibility, HasProperty, HasTemplateArguments, HasArguments, Declaration, StubBasedPsiElement<DLangFuncDeclarationStub> {

  @Nullable
  DLangBasicType getBasicType();

  @Nullable
  DLangBasicType2 getBasicType2();

  @NotNull
  DLangFuncDeclaratorSuffix getFuncDeclaratorSuffix();

  @Nullable
  DLangFunctionBody getFunctionBody();

  @NotNull
  DLangIdentifier getIdentifier();

  @Nullable
  DLangStorageClasses getStorageClasses();

  @Nullable
  PsiElement getOpEq();

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

  @NotNull
  List<DLangParameter> getArguments();

  boolean isSomeVisibility(Visibility visibility, Class<? extends Container> containerType);

  boolean isSomeVisibility(Visibility visibility);

  List<DLangTemplateParameter> getTemplateArguments();

  @NotNull
  List<DLangProtectionAttribute> getProtection();

  boolean isSystem();

  boolean isNoGC();

  boolean isTrusted();

  boolean hasCustomProperty();

  boolean isSafe();

  DLangUserDefinedAttribute getCustomProperty();

  boolean isPropertyFunction();

}
