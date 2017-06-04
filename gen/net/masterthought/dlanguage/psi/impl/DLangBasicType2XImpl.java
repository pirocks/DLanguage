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

public class DLangBasicType2XImpl extends ASTWrapperPsiElement implements DLangBasicType2X {

  public DLangBasicType2XImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DLangVisitor visitor) {
    visitor.visitBasicType2X(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DLangVisitor) accept((DLangVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public DLangAssignExpression getAssignExpression() {
    return PsiTreeUtil.getChildOfType(this, DLangAssignExpression.class);
  }

  @Override
  @Nullable
  public DLangFunctionAttributes getFunctionAttributes() {
    return PsiTreeUtil.getChildOfType(this, DLangFunctionAttributes.class);
  }

  @Override
  @Nullable
  public DLangMemberFunctionAttributes getMemberFunctionAttributes() {
    return PsiTreeUtil.getChildOfType(this, DLangMemberFunctionAttributes.class);
  }

  @Override
  @Nullable
  public DLangParameters getParameters() {
    return PsiTreeUtil.getChildOfType(this, DLangParameters.class);
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

  @Override
  @Nullable
  public PsiElement getOpAsterisk() {
    return findChildByType(OP_ASTERISK);
  }

  @Override
  @Nullable
  public PsiElement getOpBracketLeft() {
    return findChildByType(OP_BRACKET_LEFT);
  }

  @Override
  @Nullable
  public PsiElement getOpBracketRight() {
    return findChildByType(OP_BRACKET_RIGHT);
  }

  @Override
  @Nullable
  public PsiElement getOpDdot() {
    return findChildByType(OP_DDOT);
  }

}
