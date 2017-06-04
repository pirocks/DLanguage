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

public class DLangAltDeclaratorXImpl extends ASTWrapperPsiElement implements DLangAltDeclaratorX {

  public DLangAltDeclaratorXImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DLangVisitor visitor) {
    visitor.visitAltDeclaratorX(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DLangVisitor) accept((DLangVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public DLangAltDeclarator getAltDeclarator() {
    return PsiTreeUtil.getChildOfType(this, DLangAltDeclarator.class);
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

}
