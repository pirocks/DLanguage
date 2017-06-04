// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface DLangNewExpression extends PsiElement {

  @Nullable
  DLangAllocatorArguments getAllocatorArguments();

  @Nullable
  DLangNewExpressionWithArgs getNewExpressionWithArgs();

  @Nullable
  DLangType getType();

  @Nullable
  PsiElement getKwNew();

}
