// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static net.masterthought.dlanguage.psi.DLangTypes.*;
import net.masterthought.dlanguage.stubs.interfaces.UnitTestingStub;
import net.masterthought.dlanguage.psi.*;
import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.tree.IElementType;

public class DLangUnitTestingImpl extends DStubBasedPsiElementBase<UnitTestingStub> implements DLangUnitTesting {

  public DLangUnitTestingImpl(UnitTestingStub stub, IStubElementType type) {
    super(stub, type);
  }

  public DLangUnitTestingImpl(ASTNode node) {
    super(node);
  }

  public DLangUnitTestingImpl(UnitTestingStub stub, IElementType type, ASTNode node) {
    super(stub, type, node);
  }

  public void accept(@NotNull DLangVisitor visitor) {
    visitor.visitUnitTesting(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DLangVisitor) accept((DLangVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public DLangBlockStatement getBlockStatement() {
    return PsiTreeUtil.getChildOfType(this, DLangBlockStatement.class);
  }

  @Override
  @NotNull
  public PsiElement getKwUnittest() {
    return notNullChild(findChildByType(KW_UNITTEST));
  }

}
