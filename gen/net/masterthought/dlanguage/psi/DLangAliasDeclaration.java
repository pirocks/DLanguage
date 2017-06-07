// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import net.masterthought.dlanguage.psi.interfaces.DNamedElement;
import net.masterthought.dlanguage.psi.interfaces.HasVisibility;
import net.masterthought.dlanguage.psi.interfaces.Declaration;
import com.intellij.psi.StubBasedPsiElement;
import net.masterthought.dlanguage.stubs.DLangAliasDeclStub;
import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiReference;
import net.masterthought.dlanguage.psi.interfaces.containers.Container;
import net.masterthought.dlanguage.psi.interfaces.HasVisibility.Visibility;
import net.masterthought.dlanguage.psi.interfaces.Type;

public interface DLangAliasDeclaration extends DNamedElement, HasVisibility, Declaration, StubBasedPsiElement<DLangAliasDeclStub> {

  @Nullable
  DLangAliasDeclarationX getAliasDeclarationX();

  @Nullable
  DLangBasicType getBasicType();

  @Nullable
  DLangBasicType2 getBasicType2();

  @Nullable
  DLangDeclarator getDeclarator();

  @Nullable
  DLangFuncDeclaratorSuffix getFuncDeclaratorSuffix();

  @Nullable
  DLangIdentifier getIdentifier();

  @Nullable
  DLangInitializer getInitializer();

  @Nullable
  DLangStorageClasses getStorageClasses();

  @Nullable
  DLangTemplateArguments getTemplateArguments();

  @Nullable
  DLangType getType();

  @NotNull
  PsiElement getKwAlias();

  @Nullable
  PsiElement getOpEq();

  @NotNull
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

  boolean isSomeVisibility(Visibility visibility);

  boolean isSomeVisibility(Visibility visibility, Class<? extends Container> containerType);

  boolean actuallyIsDeclaration();

  Type getDeclarationType();

}
