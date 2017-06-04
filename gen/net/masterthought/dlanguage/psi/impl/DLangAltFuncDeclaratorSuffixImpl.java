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

public class DLangAltFuncDeclaratorSuffixImpl extends ASTWrapperPsiElement implements DLangAltFuncDeclaratorSuffix {

  public DLangAltFuncDeclaratorSuffixImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DLangVisitor visitor) {
    visitor.visitAltFuncDeclaratorSuffix(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DLangVisitor) accept((DLangVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public DLangMemberFunctionAttributes getMemberFunctionAttributes() {
    return PsiTreeUtil.getChildOfType(this, DLangMemberFunctionAttributes.class);
  }

  @Override
  @NotNull
  public DLangParameters getParameters() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, DLangParameters.class));
  }

}
