// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface DLangNewAnonClassExpression extends PsiElement {

  @Nullable
  DLangAggregateBody getAggregateBody();

  @Nullable
  DLangAllocatorArguments getAllocatorArguments();

  @Nullable
  DLangClassArguments getClassArguments();

  @Nullable
  DLangInterfaces getInterfaces();

  @Nullable
  DLangSuperClass getSuperClass();

  @Nullable
  PsiElement getKwClass();

  @NotNull
  PsiElement getKwNew();

}
