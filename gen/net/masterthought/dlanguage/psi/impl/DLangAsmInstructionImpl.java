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

public class DLangAsmInstructionImpl extends ASTWrapperPsiElement implements DLangAsmInstruction {

  public DLangAsmInstructionImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DLangVisitor visitor) {
    visitor.visitAsmInstruction(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DLangVisitor) accept((DLangVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public DLangAsmBrExp getAsmBrExp() {
    return PsiTreeUtil.getChildOfType(this, DLangAsmBrExp.class);
  }

  @Override
  @Nullable
  public DLangAsmInstruction getAsmInstruction() {
    return PsiTreeUtil.getChildOfType(this, DLangAsmInstruction.class);
  }

  @Override
  @Nullable
  public DLangIdentifier getIdentifier() {
    return PsiTreeUtil.getChildOfType(this, DLangIdentifier.class);
  }

  @Override
  @Nullable
  public DLangIntegerExpression getIntegerExpression() {
    return PsiTreeUtil.getChildOfType(this, DLangIntegerExpression.class);
  }

  @Override
  @Nullable
  public DLangOpcode getOpcode() {
    return PsiTreeUtil.getChildOfType(this, DLangOpcode.class);
  }

  @Override
  @Nullable
  public DLangOperands getOperands() {
    return PsiTreeUtil.getChildOfType(this, DLangOperands.class);
  }

  @Override
  @Nullable
  public PsiElement getKwAlign() {
    return findChildByType(KW_ALIGN);
  }

  @Override
  @Nullable
  public PsiElement getOpColon() {
    return findChildByType(OP_COLON);
  }

}
