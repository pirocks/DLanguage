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

public class DLangClassTemplateDeclarationImpl extends ASTWrapperPsiElement implements DLangClassTemplateDeclaration {

  public DLangClassTemplateDeclarationImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DLangVisitor visitor) {
    visitor.visitClassTemplateDeclaration(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DLangVisitor) accept((DLangVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public DLangAggregateBody getAggregateBody() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, DLangAggregateBody.class));
  }

  @Override
  @Nullable
  public DLangBaseClassList getBaseClassList() {
    return PsiTreeUtil.getChildOfType(this, DLangBaseClassList.class);
  }

  @Override
  @Nullable
  public DLangConstraint getConstraint() {
    return PsiTreeUtil.getChildOfType(this, DLangConstraint.class);
  }

  @Override
  @NotNull
  public DLangIdentifier getIdentifier() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, DLangIdentifier.class));
  }

  @Override
  @NotNull
  public DLangTemplateParameters getTemplateParameters() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, DLangTemplateParameters.class));
  }

  @Override
  @NotNull
  public PsiElement getKwClass() {
    return notNullChild(findChildByType(KW_CLASS));
  }

}
