// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static net.masterthought.dlanguage.psi.DLanguageTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import net.masterthought.dlanguage.psi.*;

public class DLanguageThrowStatementImpl extends ASTWrapperPsiElement implements DLanguageThrowStatement {

  public DLanguageThrowStatementImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DLanguageVisitor visitor) {
    visitor.visitThrowStatement(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DLanguageVisitor) accept((DLanguageVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public DLanguageCommaExpression getCommaExpression() {
    return PsiTreeUtil.getChildOfType(this, DLanguageCommaExpression.class);
  }

  @Override
  @Nullable
  public DLanguageTemplateInstance getTemplateInstance() {
    return PsiTreeUtil.getChildOfType(this, DLanguageTemplateInstance.class);
  }

  @Override
  @NotNull
  public PsiElement getKwThrow() {
    return notNullChild(findChildByType(KW_THROW));
  }

  @Override
  @Nullable
  public PsiElement getOpScolon() {
    return findChildByType(OP_SCOLON);
  }

}
