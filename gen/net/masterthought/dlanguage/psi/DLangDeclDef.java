// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.ResolveState;
import com.intellij.psi.scope.PsiScopeProcessor;

public interface DLangDeclDef extends PsiElement {

  @Nullable
  DLangAliasThis getAliasThis();

  @Nullable
  DLangAllocator getAllocator();

  @Nullable
  DLangAttributeSpecifier getAttributeSpecifier();

  @Nullable
  DLangConditionalDeclaration getConditionalDeclaration();

  @Nullable
  DLangConstructor getConstructor();

  @Nullable
  DLangDeallocator getDeallocator();

  @Nullable
  DLangDebugSpecification getDebugSpecification();

  @Nullable
  DLangDeclaration getDeclaration();

  @Nullable
  DLangDestructor getDestructor();

  @Nullable
  DLangInvariant getInvariant();

  @Nullable
  DLangMixinDeclaration getMixinDeclaration();

  @Nullable
  DLangPostblit getPostblit();

  @Nullable
  DLangSharedStaticConstructor getSharedStaticConstructor();

  @Nullable
  DLangSharedStaticDestructor getSharedStaticDestructor();

  @Nullable
  DLangStaticAssert getStaticAssert();

  @Nullable
  DLangStaticConstructor getStaticConstructor();

  @Nullable
  DLangStaticDestructor getStaticDestructor();

  @Nullable
  DLangStaticElseCondition getStaticElseCondition();

  @Nullable
  DLangStaticIfCondition getStaticIfCondition();

  @Nullable
  DLangTemplateDeclaration getTemplateDeclaration();

  @Nullable
  DLangTemplateMixin getTemplateMixin();

  @Nullable
  DLangTemplateMixinDeclaration getTemplateMixinDeclaration();

  @Nullable
  DLangUnitTesting getUnitTesting();

  @Nullable
  DLangVersionSpecification getVersionSpecification();

  @Nullable
  PsiElement getOpScolon();

  boolean processDeclarations(PsiScopeProcessor processor, ResolveState state, PsiElement lastParent, PsiElement place);

}
