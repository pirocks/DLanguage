// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static net.masterthought.dlanguage.psi.DLangTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import net.masterthought.dlanguage.psi.*;

public class DLangParameterAttributesImpl extends ASTWrapperPsiElement implements DLangParameterAttributes {

  public DLangParameterAttributesImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DLangVisitor visitor) {
    visitor.visitParameterAttributes(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DLangVisitor) accept((DLangVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public DLangFunctionAttributes getFunctionAttributes() {
    return PsiTreeUtil.getChildOfType(this, DLangFunctionAttributes.class);
  }

  @Override
  @NotNull
  public DLangParameters getParameters() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, DLangParameters.class));
  }

}
