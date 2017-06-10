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

public class DLangAllocatorImpl extends ASTWrapperPsiElement implements DLangAllocator {

  public DLangAllocatorImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DLangVisitor visitor) {
    visitor.visitAllocator(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DLangVisitor) accept((DLangVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public DLangFunctionBody getFunctionBody() {
    return PsiTreeUtil.getChildOfType(this, DLangFunctionBody.class);
  }

  @Override
  @NotNull
  public DLangParameters getParameters() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, DLangParameters.class));
  }

  @Override
  @NotNull
  public PsiElement getKwNew() {
    return notNullChild(findChildByType(KW_NEW));
  }

  @Override
  @Nullable
  public PsiElement getOpScolon() {
    return findChildByType(OP_SCOLON);
  }

}