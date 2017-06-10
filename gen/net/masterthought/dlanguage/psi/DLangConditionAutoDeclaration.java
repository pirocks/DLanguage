// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import net.masterthought.dlanguage.psi.interfaces.DNamedElement;
import net.masterthought.dlanguage.psi.interfaces.Declaration;
import net.masterthought.dlanguage.psi.interfaces.VariableDeclaration;
import com.intellij.psi.StubBasedPsiElement;
import net.masterthought.dlanguage.stubs.DLangConditionAutoDeclStub;
import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiReference;

public interface DLangConditionAutoDeclaration extends DNamedElement, Declaration, VariableDeclaration, StubBasedPsiElement<DLangConditionAutoDeclStub> {

  @Nullable
  DLangCommaExpression getCommaExpression();

  @Nullable
  DLangIdentifier getIdentifier();

  @NotNull
  PsiElement getKwAuto();

  @Nullable
  PsiElement getOpEq();

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

  //WARNING: processDeclarations(...) is skipped
  //matching processDeclarations(DLangConditionAutoDeclaration, ...)
  //methods are not found in DPsiImplUtil

  //WARNING: actuallyIsDeclaration(...) is skipped
  //matching actuallyIsDeclaration(DLangConditionAutoDeclaration, ...)
  //methods are not found in DPsiImplUtil

}
