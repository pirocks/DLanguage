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

public class DLangImportListImpl extends ASTWrapperPsiElement implements DLangImportList {

  public DLangImportListImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DLangVisitor visitor) {
    visitor.visitImportList(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DLangVisitor) accept((DLangVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public DLangImport getImport() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, DLangImport.class));
  }

  @Override
  @Nullable
  public DLangImportBindList getImportBindList() {
    return PsiTreeUtil.getChildOfType(this, DLangImportBindList.class);
  }

  @Override
  @Nullable
  public DLangImportList getImportList() {
    return PsiTreeUtil.getChildOfType(this, DLangImportList.class);
  }

  @Override
  @Nullable
  public PsiElement getOpColon() {
    return findChildByType(OP_COLON);
  }

  @Override
  @Nullable
  public PsiElement getOpComma() {
    return findChildByType(OP_COMMA);
  }

}
