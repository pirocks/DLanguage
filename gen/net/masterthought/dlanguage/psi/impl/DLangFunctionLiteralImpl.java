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

public class DLangFunctionLiteralImpl extends ASTWrapperPsiElement implements DLangFunctionLiteral {

  public DLangFunctionLiteralImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DLangVisitor visitor) {
    visitor.visitFunctionLiteral(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DLangVisitor) accept((DLangVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public DLangFunctionLiteralBody getFunctionLiteralBody() {
    return PsiTreeUtil.getChildOfType(this, DLangFunctionLiteralBody.class);
  }

  @Override
  @Nullable
  public DLangLambda getLambda() {
    return PsiTreeUtil.getChildOfType(this, DLangLambda.class);
  }

  @Override
  @Nullable
  public DLangParameterAttributes getParameterAttributes() {
    return PsiTreeUtil.getChildOfType(this, DLangParameterAttributes.class);
  }

  @Override
  @Nullable
  public DLangParameterMemberAttributes getParameterMemberAttributes() {
    return PsiTreeUtil.getChildOfType(this, DLangParameterMemberAttributes.class);
  }

  @Override
  @Nullable
  public DLangType getType() {
    return PsiTreeUtil.getChildOfType(this, DLangType.class);
  }

  @Override
  @Nullable
  public PsiElement getKwDelegate() {
    return findChildByType(KW_DELEGATE);
  }

  @Override
  @Nullable
  public PsiElement getKwFunction() {
    return findChildByType(KW_FUNCTION);
  }

}
