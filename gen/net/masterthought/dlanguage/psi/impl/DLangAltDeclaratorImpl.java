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

public class DLangAltDeclaratorImpl extends ASTWrapperPsiElement implements DLangAltDeclarator {

  public DLangAltDeclaratorImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DLangVisitor visitor) {
    visitor.visitAltDeclarator(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DLangVisitor) accept((DLangVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public DLangAltDeclaratorSuffixes getAltDeclaratorSuffixes() {
    return PsiTreeUtil.getChildOfType(this, DLangAltDeclaratorSuffixes.class);
  }

  @Override
  @Nullable
  public DLangAltDeclaratorX getAltDeclaratorX() {
    return PsiTreeUtil.getChildOfType(this, DLangAltDeclaratorX.class);
  }

  @Override
  @Nullable
  public DLangAltFuncDeclaratorSuffix getAltFuncDeclaratorSuffix() {
    return PsiTreeUtil.getChildOfType(this, DLangAltFuncDeclaratorSuffix.class);
  }

  @Override
  @Nullable
  public DLangBasicType2 getBasicType2() {
    return PsiTreeUtil.getChildOfType(this, DLangBasicType2.class);
  }

  @Override
  @Nullable
  public DLangIdentifier getIdentifier() {
    return PsiTreeUtil.getChildOfType(this, DLangIdentifier.class);
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
