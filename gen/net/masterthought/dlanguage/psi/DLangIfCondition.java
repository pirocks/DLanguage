// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface DLangIfCondition extends PsiElement {

  @Nullable
  DLangCommaExpression getCommaExpression();

  @Nullable
  DLangConditionAutoDeclaration getConditionAutoDeclaration();

  @Nullable
  DLangConditionVarDeclaration getConditionVarDeclaration();

  @Nullable
  DLangConditionVarDeclarator getConditionVarDeclarator();

}
