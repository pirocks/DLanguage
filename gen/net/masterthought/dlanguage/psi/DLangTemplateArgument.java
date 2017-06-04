// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface DLangTemplateArgument extends PsiElement {

  @Nullable
  DLangAssignExpression getAssignExpression();

  @Nullable
  DLangLambda getLambda();

  @Nullable
  DLangSymbol getSymbol();

  @Nullable
  DLangType getType();

}
