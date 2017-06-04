// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import net.masterthought.dlanguage.psi.interfaces.DCompositeElement;
import com.intellij.psi.StubBasedPsiElement;
import net.masterthought.dlanguage.stubs.interfaces.UnitTestingStub;

public interface DLangUnitTesting extends DCompositeElement, StubBasedPsiElement<UnitTestingStub> {

  @Nullable
  DLangBlockStatement getBlockStatement();

  @NotNull
  PsiElement getKwUnittest();

}
