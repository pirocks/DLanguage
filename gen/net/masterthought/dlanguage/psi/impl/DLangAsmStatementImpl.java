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

public class DLangAsmStatementImpl extends ASTWrapperPsiElement implements DLangAsmStatement {

  public DLangAsmStatementImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DLangVisitor visitor) {
    visitor.visitAsmStatement(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DLangVisitor) accept((DLangVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public DLangAsmInstructionList getAsmInstructionList() {
    return PsiTreeUtil.getChildOfType(this, DLangAsmInstructionList.class);
  }

  @Override
  @Nullable
  public DLangFunctionAttributes getFunctionAttributes() {
    return PsiTreeUtil.getChildOfType(this, DLangFunctionAttributes.class);
  }

  @Override
  @NotNull
  public PsiElement getKwAsm() {
    return notNullChild(findChildByType(KW_ASM));
  }

  @Override
  @Nullable
  public PsiElement getOpBracesLeft() {
    return findChildByType(OP_BRACES_LEFT);
  }

  @Override
  @Nullable
  public PsiElement getOpBracesRight() {
    return findChildByType(OP_BRACES_RIGHT);
  }

}
