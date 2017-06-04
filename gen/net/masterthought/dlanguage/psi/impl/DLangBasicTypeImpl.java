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

public class DLangBasicTypeImpl extends ASTWrapperPsiElement implements DLangBasicType {

  public DLangBasicTypeImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DLangVisitor visitor) {
    visitor.visitBasicType(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DLangVisitor) accept((DLangVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public DLangBasicTypeX getBasicTypeX() {
    return PsiTreeUtil.getChildOfType(this, DLangBasicTypeX.class);
  }

  @Override
  @Nullable
  public DLangIdentifierList getIdentifierList() {
    return PsiTreeUtil.getChildOfType(this, DLangIdentifierList.class);
  }

  @Override
  @Nullable
  public DLangType getType() {
    return PsiTreeUtil.getChildOfType(this, DLangType.class);
  }

  @Override
  @Nullable
  public DLangTypeVector getTypeVector() {
    return PsiTreeUtil.getChildOfType(this, DLangTypeVector.class);
  }

  @Override
  @Nullable
  public DLangTypeof getTypeof() {
    return PsiTreeUtil.getChildOfType(this, DLangTypeof.class);
  }

  @Override
  @Nullable
  public PsiElement getOpDot() {
    return findChildByType(OP_DOT);
  }

  @Override
  @Nullable
  public PsiElement getOpParLeft() {
    return findChildByType(OP_PAR_LEFT);
  }

  @Override
  @Nullable
  public PsiElement getOpParRight() {
    return findChildByType(OP_PAR_RIGHT);
  }

}
