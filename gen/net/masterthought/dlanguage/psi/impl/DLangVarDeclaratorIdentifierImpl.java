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

public class DLangVarDeclaratorIdentifierImpl extends ASTWrapperPsiElement implements DLangVarDeclaratorIdentifier {

  public DLangVarDeclaratorIdentifierImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DLangVisitor visitor) {
    visitor.visitVarDeclaratorIdentifier(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DLangVisitor) accept((DLangVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public DLangIdentifier getIdentifier() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, DLangIdentifier.class));
  }

  @Override
  @NotNull
  public DLangInitializer getInitializer() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, DLangInitializer.class));
  }

  @Override
  @Nullable
  public DLangTemplateParameters getTemplateParameters() {
    return PsiTreeUtil.getChildOfType(this, DLangTemplateParameters.class);
  }

  @Override
  @NotNull
  public PsiElement getOpEq() {
    return notNullChild(findChildByType(OP_EQ));
  }

}
