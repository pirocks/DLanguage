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
import com.intellij.psi.ResolveState;
import com.intellij.psi.scope.PsiScopeProcessor;

public class DLangDeclDefImpl extends ASTWrapperPsiElement implements DLangDeclDef {

  public DLangDeclDefImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DLangVisitor visitor) {
    visitor.visitDeclDef(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DLangVisitor) accept((DLangVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public DLangAliasThis getAliasThis() {
    return PsiTreeUtil.getChildOfType(this, DLangAliasThis.class);
  }

  @Override
  @Nullable
  public DLangAllocator getAllocator() {
    return PsiTreeUtil.getChildOfType(this, DLangAllocator.class);
  }

  @Override
  @Nullable
  public DLangAttributeSpecifier getAttributeSpecifier() {
    return PsiTreeUtil.getChildOfType(this, DLangAttributeSpecifier.class);
  }

  @Override
  @Nullable
  public DLangConditionalDeclaration getConditionalDeclaration() {
    return PsiTreeUtil.getChildOfType(this, DLangConditionalDeclaration.class);
  }

  @Override
  @Nullable
  public DLangConstructor getConstructor() {
    return PsiTreeUtil.getChildOfType(this, DLangConstructor.class);
  }

  @Override
  @Nullable
  public DLangDeallocator getDeallocator() {
    return PsiTreeUtil.getChildOfType(this, DLangDeallocator.class);
  }

  @Override
  @Nullable
  public DLangDebugSpecification getDebugSpecification() {
    return PsiTreeUtil.getChildOfType(this, DLangDebugSpecification.class);
  }

  @Override
  @Nullable
  public DLangDeclaration getDeclaration() {
    return PsiTreeUtil.getChildOfType(this, DLangDeclaration.class);
  }

  @Override
  @Nullable
  public DLangDestructor getDestructor() {
    return PsiTreeUtil.getChildOfType(this, DLangDestructor.class);
  }

  @Override
  @Nullable
  public DLangInvariant getInvariant() {
    return PsiTreeUtil.getChildOfType(this, DLangInvariant.class);
  }

  @Override
  @Nullable
  public DLangMixinDeclaration getMixinDeclaration() {
    return PsiTreeUtil.getChildOfType(this, DLangMixinDeclaration.class);
  }

  @Override
  @Nullable
  public DLangPostblit getPostblit() {
    return PsiTreeUtil.getChildOfType(this, DLangPostblit.class);
  }

  @Override
  @Nullable
  public DLangSharedStaticConstructor getSharedStaticConstructor() {
    return PsiTreeUtil.getChildOfType(this, DLangSharedStaticConstructor.class);
  }

  @Override
  @Nullable
  public DLangSharedStaticDestructor getSharedStaticDestructor() {
    return PsiTreeUtil.getChildOfType(this, DLangSharedStaticDestructor.class);
  }

  @Override
  @Nullable
  public DLangStaticAssert getStaticAssert() {
    return PsiTreeUtil.getChildOfType(this, DLangStaticAssert.class);
  }

  @Override
  @Nullable
  public DLangStaticConstructor getStaticConstructor() {
    return PsiTreeUtil.getChildOfType(this, DLangStaticConstructor.class);
  }

  @Override
  @Nullable
  public DLangStaticDestructor getStaticDestructor() {
    return PsiTreeUtil.getChildOfType(this, DLangStaticDestructor.class);
  }

  @Override
  @Nullable
  public DLangStaticElseCondition getStaticElseCondition() {
    return PsiTreeUtil.getChildOfType(this, DLangStaticElseCondition.class);
  }

  @Override
  @Nullable
  public DLangStaticIfCondition getStaticIfCondition() {
    return PsiTreeUtil.getChildOfType(this, DLangStaticIfCondition.class);
  }

  @Override
  @Nullable
  public DLangTemplateDeclaration getTemplateDeclaration() {
    return PsiTreeUtil.getChildOfType(this, DLangTemplateDeclaration.class);
  }

  @Override
  @Nullable
  public DLangTemplateMixin getTemplateMixin() {
    return PsiTreeUtil.getChildOfType(this, DLangTemplateMixin.class);
  }

  @Override
  @Nullable
  public DLangTemplateMixinDeclaration getTemplateMixinDeclaration() {
    return PsiTreeUtil.getChildOfType(this, DLangTemplateMixinDeclaration.class);
  }

  @Override
  @Nullable
  public DLangUnitTesting getUnitTesting() {
    return PsiTreeUtil.getChildOfType(this, DLangUnitTesting.class);
  }

  @Override
  @Nullable
  public DLangVersionSpecification getVersionSpecification() {
    return PsiTreeUtil.getChildOfType(this, DLangVersionSpecification.class);
  }

  @Override
  @Nullable
  public PsiElement getOpScolon() {
    return findChildByType(OP_SCOLON);
  }

  public boolean processDeclarations(PsiScopeProcessor processor, ResolveState state, PsiElement lastParent, PsiElement place) {
    return DPsiImplUtil.processDeclarations(this, processor, state, lastParent, place);
  }

}
