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

public class DLangAltDeclaratorIdentifierImpl extends ASTWrapperPsiElement implements DLangAltDeclaratorIdentifier {

  public DLangAltDeclaratorIdentifierImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DLangVisitor visitor) {
    visitor.visitAltDeclaratorIdentifier(this);
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
  public DLangBasicType2 getBasicType2() {
    return PsiTreeUtil.getChildOfType(this, DLangBasicType2.class);
  }

  @Override
  @NotNull
  public DLangIdentifier getIdentifier() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, DLangIdentifier.class));
  }

  @Override
  @Nullable
  public DLangInitializer getInitializer() {
    return PsiTreeUtil.getChildOfType(this, DLangInitializer.class);
  }

  @Override
  @Nullable
  public PsiElement getOpEq() {
    return findChildByType(OP_EQ);
  }

}
