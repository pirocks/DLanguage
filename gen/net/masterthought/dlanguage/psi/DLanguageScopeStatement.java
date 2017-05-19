// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface DLanguageScopeStatement extends PsiElement {

  @Nullable
  DLanguageAggregateDeclaration getAggregateDeclaration();

  @Nullable
  DLanguageAliasDeclaration getAliasDeclaration();

  @Nullable
  DLanguageAsmStatement getAsmStatement();

  @Nullable
  DLanguageBlockStatement getBlockStatement();

  @Nullable
  DLanguageBreakStatement getBreakStatement();

  @Nullable
  DLanguageCaseRangeStatement getCaseRangeStatement();

  @Nullable
  DLanguageCaseStatement getCaseStatement();

  @Nullable
  DLanguageConditionalStatement getConditionalStatement();

  @Nullable
  DLanguageContinueStatement getContinueStatement();

  @Nullable
  DLanguageDefaultStatement getDefaultStatement();

  @Nullable
  DLanguageDoStatement getDoStatement();

  @Nullable
  DLanguageEnumDeclaration getEnumDeclaration();

  @Nullable
  DLanguageExpressionStatement getExpressionStatement();

  @Nullable
  DLanguageFinalSwitchStatement getFinalSwitchStatement();

  @Nullable
  DLanguageForStatement getForStatement();

  @Nullable
  DLanguageForeachRangeStatement getForeachRangeStatement();

  @Nullable
  DLanguageForeachStatement getForeachStatement();

  @Nullable
  DLanguageFuncDeclaration getFuncDeclaration();

  @Nullable
  DLanguageGotoStatement getGotoStatement();

  @Nullable
  DLanguageIfStatement getIfStatement();

  @Nullable
  DLanguageImportDeclaration getImportDeclaration();

  @Nullable
  DLanguageLabeledStatement getLabeledStatement();

  @Nullable
  DLanguageMixinStatement getMixinStatement();

  @Nullable
  DLanguagePragmaStatement getPragmaStatement();

  @Nullable
  DLanguageReturnStatement getReturnStatement();

  @Nullable
  DLanguageScopeGuardStatement getScopeGuardStatement();

  @Nullable
  DLanguageStaticAssert getStaticAssert();

  @Nullable
  DLanguageStorageClasses getStorageClasses();

  @Nullable
  DLanguageSwitchStatement getSwitchStatement();

  @Nullable
  DLanguageSynchronizedStatement getSynchronizedStatement();

  @Nullable
  DLanguageTemplateDeclaration getTemplateDeclaration();

  @Nullable
  DLanguageTemplateMixin getTemplateMixin();

  @Nullable
  DLanguageThrowStatement getThrowStatement();

  @Nullable
  DLanguageTryStatement getTryStatement();

  @Nullable
  DLanguageVarDeclarations getVarDeclarations();

  @Nullable
  DLanguageWhileStatement getWhileStatement();

  @Nullable
  DLanguageWithStatement getWithStatement();

}
