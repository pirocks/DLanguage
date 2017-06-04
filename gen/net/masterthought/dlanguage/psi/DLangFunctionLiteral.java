// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface DLangFunctionLiteral extends PsiElement {

  @Nullable
  DLangFunctionLiteralBody getFunctionLiteralBody();

  @Nullable
  DLangLambda getLambda();

  @Nullable
  DLangParameterAttributes getParameterAttributes();

  @Nullable
  DLangParameterMemberAttributes getParameterMemberAttributes();

  @Nullable
  DLangType getType();

  @Nullable
  PsiElement getKwDelegate();

  @Nullable
  PsiElement getKwFunction();

}
