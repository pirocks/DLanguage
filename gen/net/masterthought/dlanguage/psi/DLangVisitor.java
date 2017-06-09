// This is a generated file. Not intended for manual editing.
package net.masterthought.dlanguage.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiElement;
import net.masterthought.dlanguage.psi.interfaces.DNamedElement;
import net.masterthought.dlanguage.psi.interfaces.Declaration;
import net.masterthought.dlanguage.psi.interfaces.VariableDeclaration;
import net.masterthought.dlanguage.psi.interfaces.containers.StatementContainer;
import net.masterthought.dlanguage.psi.interfaces.containers.GlobalDeclarationContainer;
import net.masterthought.dlanguage.psi.interfaces.HasVisibility;
import net.masterthought.dlanguage.psi.interfaces.Mixin;
import net.masterthought.dlanguage.psi.interfaces.HasTemplateArguments;
import net.masterthought.dlanguage.psi.interfaces.HasArguments;
import net.masterthought.dlanguage.psi.interfaces.containers.MixinContainer;
import net.masterthought.dlanguage.psi.interfaces.HasProperty;
import net.masterthought.dlanguage/.psi.interfaces.DNamedElement;
import net.masterthought.dlanguage.psi.interfaces.Mixinable;
import net.masterthought.dlanguage.psi.interfaces.CanInherit;
import net.masterthought.dlanguage.psi.interfaces.DCompositeElement;

public class DLangVisitor extends PsiElementVisitor {

  public void visitAddExpression_(@NotNull DLangAddExpression_ o) {
    visitPsiElement(o);
  }

  public void visitAggregateBody(@NotNull DLangAggregateBody o) {
    visitPsiElement(o);
  }

  public void visitAggregateDeclaration(@NotNull DLangAggregateDeclaration o) {
    visitPsiElement(o);
  }

  public void visitAliasDeclaration(@NotNull DLangAliasDeclaration o) {
    visitDNamedElement(o);
    // visitHasVisibility(o);
    // visitDeclaration(o);
  }

  public void visitAliasDeclarationX(@NotNull DLangAliasDeclarationX o) {
    visitPsiElement(o);
  }

  public void visitAliasDeclarationY(@NotNull DLangAliasDeclarationY o) {
    visitPsiElement(o);
  }

  public void visitAliasThis(@NotNull DLangAliasThis o) {
    visitPsiElement(o);
  }

  public void visitAlignAttribute(@NotNull DLangAlignAttribute o) {
    visitPsiElement(o);
  }

  public void visitAllocator(@NotNull DLangAllocator o) {
    visitPsiElement(o);
  }

  public void visitAllocatorArguments(@NotNull DLangAllocatorArguments o) {
    visitPsiElement(o);
  }

  public void visitAltDeclarator(@NotNull DLangAltDeclarator o) {
    visitPsiElement(o);
  }

  public void visitAltDeclaratorIdentifier(@NotNull DLangAltDeclaratorIdentifier o) {
    visitPsiElement(o);
  }

  public void visitAltDeclaratorSuffix(@NotNull DLangAltDeclaratorSuffix o) {
    visitPsiElement(o);
  }

  public void visitAltDeclaratorSuffixes(@NotNull DLangAltDeclaratorSuffixes o) {
    visitPsiElement(o);
  }

  public void visitAltDeclaratorX(@NotNull DLangAltDeclaratorX o) {
    visitPsiElement(o);
  }

  public void visitAltFuncDeclaratorSuffix(@NotNull DLangAltFuncDeclaratorSuffix o) {
    visitPsiElement(o);
  }

  public void visitAndExxpression_(@NotNull DLangAndExxpression_ o) {
    visitPsiElement(o);
  }

  public void visitAnonUnionDeclaration(@NotNull DLangAnonUnionDeclaration o) {
    visitPsiElement(o);
  }

  public void visitAnonymousEnumDeclaration(@NotNull DLangAnonymousEnumDeclaration o) {
    visitPsiElement(o);
  }

  public void visitArgumentList(@NotNull DLangArgumentList o) {
    visitPsiElement(o);
  }

  public void visitArrayInitializer(@NotNull DLangArrayInitializer o) {
    visitPsiElement(o);
  }

  public void visitArrayLiteral(@NotNull DLangArrayLiteral o) {
    visitPsiElement(o);
  }

  public void visitArrayMemberInitialization(@NotNull DLangArrayMemberInitialization o) {
    visitPsiElement(o);
  }

  public void visitArrayMemberInitializations(@NotNull DLangArrayMemberInitializations o) {
    visitPsiElement(o);
  }

  public void visitAsmAddExp(@NotNull DLangAsmAddExp o) {
    visitPsiElement(o);
  }

  public void visitAsmAndExp(@NotNull DLangAsmAndExp o) {
    visitPsiElement(o);
  }

  public void visitAsmBrExp(@NotNull DLangAsmBrExp o) {
    visitPsiElement(o);
  }

  public void visitAsmEqualExp(@NotNull DLangAsmEqualExp o) {
    visitPsiElement(o);
  }

  public void visitAsmExp(@NotNull DLangAsmExp o) {
    visitPsiElement(o);
  }

  public void visitAsmInstruction(@NotNull DLangAsmInstruction o) {
    visitPsiElement(o);
  }

  public void visitAsmInstructionList(@NotNull DLangAsmInstructionList o) {
    visitPsiElement(o);
  }

  public void visitAsmLogAndExp(@NotNull DLangAsmLogAndExp o) {
    visitPsiElement(o);
  }

  public void visitAsmLogOrExp(@NotNull DLangAsmLogOrExp o) {
    visitPsiElement(o);
  }

  public void visitAsmMulExp(@NotNull DLangAsmMulExp o) {
    visitPsiElement(o);
  }

  public void visitAsmOrExp(@NotNull DLangAsmOrExp o) {
    visitPsiElement(o);
  }

  public void visitAsmPrimaryExp(@NotNull DLangAsmPrimaryExp o) {
    visitPsiElement(o);
  }

  public void visitAsmRelExp(@NotNull DLangAsmRelExp o) {
    visitPsiElement(o);
  }

  public void visitAsmShiftExp(@NotNull DLangAsmShiftExp o) {
    visitPsiElement(o);
  }

  public void visitAsmStatement(@NotNull DLangAsmStatement o) {
    visitPsiElement(o);
  }

  public void visitAsmTypePrefix(@NotNull DLangAsmTypePrefix o) {
    visitPsiElement(o);
  }

  public void visitAsmUnaExp(@NotNull DLangAsmUnaExp o) {
    visitPsiElement(o);
  }

  public void visitAsmXorExp(@NotNull DLangAsmXorExp o) {
    visitPsiElement(o);
  }

  public void visitAssertExpression(@NotNull DLangAssertExpression o) {
    visitPsiElement(o);
  }

  public void visitAssignExpression(@NotNull DLangAssignExpression o) {
    visitPsiElement(o);
  }

  public void visitAssocArrayLiteral(@NotNull DLangAssocArrayLiteral o) {
    visitPsiElement(o);
  }

  public void visitAttribute(@NotNull DLangAttribute o) {
    visitPsiElement(o);
  }

  public void visitAttributeSpecifier(@NotNull DLangAttributeSpecifier o) {
    visitPsiElement(o);
  }

  public void visitAutoDeclaration(@NotNull DLangAutoDeclaration o) {
    visitPsiElement(o);
  }

  public void visitAutoDeclarationX(@NotNull DLangAutoDeclarationX o) {
    visitPsiElement(o);
  }

  public void visitAutoDeclarationY(@NotNull DLangAutoDeclarationY o) {
    visitDNamedElement(o);
    // visitVariableDeclaration(o);
    // visitDeclaration(o);
  }

  public void visitBaseClassList(@NotNull DLangBaseClassList o) {
    visitPsiElement(o);
  }

  public void visitBaseInterfaceList(@NotNull DLangBaseInterfaceList o) {
    visitPsiElement(o);
  }

  public void visitBasicType(@NotNull DLangBasicType o) {
    visitPsiElement(o);
  }

  public void visitBasicType2(@NotNull DLangBasicType2 o) {
    visitPsiElement(o);
  }

  public void visitBasicType2X(@NotNull DLangBasicType2X o) {
    visitPsiElement(o);
  }

  public void visitBasicTypeX(@NotNull DLangBasicTypeX o) {
    visitPsiElement(o);
  }

  public void visitBlockStatement(@NotNull DLangBlockStatement o) {
    visitPsiElement(o);
  }

  public void visitBodyStatement(@NotNull DLangBodyStatement o) {
    visitPsiElement(o);
  }

  public void visitBreakStatement(@NotNull DLangBreakStatement o) {
    visitPsiElement(o);
  }

  public void visitCaseRangeStatement(@NotNull DLangCaseRangeStatement o) {
    visitPsiElement(o);
  }

  public void visitCaseStatement(@NotNull DLangCaseStatement o) {
    visitPsiElement(o);
  }

  public void visitCastExpression(@NotNull DLangCastExpression o) {
    visitPsiElement(o);
  }

  public void visitCatch(@NotNull DLangCatch o) {
    visitPsiElement(o);
  }

  public void visitCatchParameter(@NotNull DLangCatchParameter o) {
    visitDNamedElement(o);
    // visitDeclaration(o);
    // visitVariableDeclaration(o);
  }

  public void visitCatches(@NotNull DLangCatches o) {
    visitPsiElement(o);
  }

  public void visitClassArguments(@NotNull DLangClassArguments o) {
    visitPsiElement(o);
  }

  public void visitClassDeclaration(@NotNull DLangClassDeclaration o) {
    visitStatementContainer(o);
    // visitMixinContainer(o);
    // visitDNamedElement(o);
    // visitHasVisibility(o);
    // visitHasTemplateArguments(o);
    // visitCanInherit(o);
    // visitDeclaration(o);
  }

  public void visitClassTemplateDeclaration(@NotNull DLangClassTemplateDeclaration o) {
    visitPsiElement(o);
  }

  public void visitCommaExpression(@NotNull DLangCommaExpression o) {
    visitPsiElement(o);
  }

  public void visitCondition(@NotNull DLangCondition o) {
    visitPsiElement(o);
  }

  public void visitConditionAutoDeclaration(@NotNull DLangConditionAutoDeclaration o) {
    visitDNamedElement(o);
    // visitDeclaration(o);
    // visitVariableDeclaration(o);
  }

  public void visitConditionVarDeclaration(@NotNull DLangConditionVarDeclaration o) {
    visitDNamedElement(o);
    // visitDeclaration(o);
    // visitVariableDeclaration(o);
  }

  public void visitConditionVarDeclarator(@NotNull DLangConditionVarDeclarator o) {
    visitDNamedElement(o);
    // visitDeclaration(o);
    // visitVariableDeclaration(o);
  }

  public void visitConditionalDeclaration(@NotNull DLangConditionalDeclaration o) {
    visitPsiElement(o);
  }

  public void visitConditionalExpression_(@NotNull DLangConditionalExpression_ o) {
    visitPsiElement(o);
  }

  public void visitConditionalStatement(@NotNull DLangConditionalStatement o) {
    visitPsiElement(o);
  }

  public void visitConstraint(@NotNull DLangConstraint o) {
    visitPsiElement(o);
  }

  public void visitConstructor(@NotNull DLangConstructor o) {
    visitStatementContainer(o);
    // visitDNamedElement(o);
    // visitHasVisibility(o);
    // visitHasTemplateArguments(o);
    // visitHasArguments(o);
    // visitDeclaration(o);
  }

  public void visitConstructorTemplate(@NotNull DLangConstructorTemplate o) {
    visitPsiElement(o);
  }

  public void visitContinueStatement(@NotNull DLangContinueStatement o) {
    visitPsiElement(o);
  }

  public void visitDeallocator(@NotNull DLangDeallocator o) {
    visitPsiElement(o);
  }

  public void visitDebugCondition(@NotNull DLangDebugCondition o) {
    visitPsiElement(o);
  }

  public void visitDebugSpecification(@NotNull DLangDebugSpecification o) {
    visitPsiElement(o);
  }

  public void visitDeclDef(@NotNull DLangDeclDef o) {
    visitPsiElement(o);
  }

  public void visitDeclDefs(@NotNull DLangDeclDefs o) {
    visitPsiElement(o);
  }

  public void visitDeclaration(@NotNull DLangDeclaration o) {
    visitPsiElement(o);
  }

  public void visitDeclarationBlock(@NotNull DLangDeclarationBlock o) {
    visitPsiElement(o);
  }

  public void visitDeclarationStatement(@NotNull DLangDeclarationStatement o) {
    visitPsiElement(o);
  }

  public void visitDeclarator(@NotNull DLangDeclarator o) {
    visitPsiElement(o);
  }

  public void visitDeclaratorIdentifier(@NotNull DLangDeclaratorIdentifier o) {
    visitPsiElement(o);
  }

  public void visitDeclaratorIdentifierList(@NotNull DLangDeclaratorIdentifierList o) {
    visitPsiElement(o);
  }

  public void visitDeclaratorInitializer(@NotNull DLangDeclaratorInitializer o) {
    visitDNamedElement(o);
    // visitVariableDeclaration(o);
    // visitDeclaration(o);
  }

  public void visitDeclarators(@NotNull DLangDeclarators o) {
    visitPsiElement(o);
  }

  public void visitDefaultStatement(@NotNull DLangDefaultStatement o) {
    visitPsiElement(o);
  }

  public void visitDeleteExpression(@NotNull DLangDeleteExpression o) {
    visitPsiElement(o);
  }

  public void visitDeprecatedAttribute(@NotNull DLangDeprecatedAttribute o) {
    visitPsiElement(o);
  }

  public void visitDestructor(@NotNull DLangDestructor o) {
    visitStatementContainer(o);
    // visitDNamedElement(o);
    // visitHasVisibility(o);
    // visitDeclaration(o);
  }

  public void visitDoStatement(@NotNull DLangDoStatement o) {
    visitPsiElement(o);
  }

  public void visitDotIdentifier(@NotNull DLangDotIdentifier o) {
    visitPsiElement(o);
  }

  public void visitElseStatement(@NotNull DLangElseStatement o) {
    visitPsiElement(o);
  }

  public void visitEnumBaseType(@NotNull DLangEnumBaseType o) {
    visitPsiElement(o);
  }

  public void visitEnumBody(@NotNull DLangEnumBody o) {
    visitPsiElement(o);
  }

  public void visitEnumDeclaration(@NotNull DLangEnumDeclaration o) {
    visitDNamedElement(o);
    // visitHasVisibility(o);
    // visitDeclaration(o);
  }

  public void visitEnumMember(@NotNull DLangEnumMember o) {
    visitPsiElement(o);
  }

  public void visitEnumMembers(@NotNull DLangEnumMembers o) {
    visitPsiElement(o);
  }

  public void visitEqualExpression(@NotNull DLangEqualExpression o) {
    visitPsiElement(o);
  }

  public void visitExpressionStatement(@NotNull DLangExpressionStatement o) {
    visitPsiElement(o);
  }

  public void visitFinalSwitchStatement(@NotNull DLangFinalSwitchStatement o) {
    visitPsiElement(o);
  }

  public void visitFinallyStatement(@NotNull DLangFinallyStatement o) {
    visitPsiElement(o);
  }

  public void visitFirstExp(@NotNull DLangFirstExp o) {
    visitPsiElement(o);
  }

  public void visitForStatement(@NotNull DLangForStatement o) {
    visitPsiElement(o);
  }

  public void visitForeach(@NotNull DLangForeach o) {
    visitPsiElement(o);
  }

  public void visitForeachAggregate(@NotNull DLangForeachAggregate o) {
    visitPsiElement(o);
  }

  public void visitForeachRangeStatement(@NotNull DLangForeachRangeStatement o) {
    visitPsiElement(o);
  }

  public void visitForeachStatement(@NotNull DLangForeachStatement o) {
    visitPsiElement(o);
  }

  public void visitForeachType(@NotNull DLangForeachType o) {
    visitDNamedElement(o);
    // visitDeclaration(o);
    // visitVariableDeclaration(o);
  }

  public void visitForeachTypeAttribute(@NotNull DLangForeachTypeAttribute o) {
    visitPsiElement(o);
  }

  public void visitForeachTypeAttributes(@NotNull DLangForeachTypeAttributes o) {
    visitPsiElement(o);
  }

  public void visitForeachTypeList(@NotNull DLangForeachTypeList o) {
    visitPsiElement(o);
  }

  public void visitFuncDeclaration(@NotNull DLangFuncDeclaration o) {
    visitStatementContainer(o);
    // visitDNamedElement(o);
    // visitHasVisibility(o);
    // visitHasProperty(o);
    // visitHasTemplateArguments(o);
    // visitHasArguments(o);
    // visitDeclaration(o);
  }

  public void visitFuncDeclaratorSuffix(@NotNull DLangFuncDeclaratorSuffix o) {
    visitPsiElement(o);
  }

  public void visitFunctionAttribute(@NotNull DLangFunctionAttribute o) {
    visitPsiElement(o);
  }

  public void visitFunctionAttributes(@NotNull DLangFunctionAttributes o) {
    visitPsiElement(o);
  }

  public void visitFunctionBody(@NotNull DLangFunctionBody o) {
    visitPsiElement(o);
  }

  public void visitFunctionContracts(@NotNull DLangFunctionContracts o) {
    visitPsiElement(o);
  }

  public void visitFunctionLiteral(@NotNull DLangFunctionLiteral o) {
    visitPsiElement(o);
  }

  public void visitFunctionLiteralBody(@NotNull DLangFunctionLiteralBody o) {
    visitPsiElement(o);
  }

  public void visitGotoStatement(@NotNull DLangGotoStatement o) {
    visitPsiElement(o);
  }

  public void visitIdentifier(@NotNull DLangIdentifier o) {
    visitDNamedElement(o);
  }

  public void visitIdentifierList(@NotNull DLangIdentifierList o) {
    visitPsiElement(o);
  }

  public void visitIdentityExpression(@NotNull DLangIdentityExpression o) {
    visitPsiElement(o);
  }

  public void visitIfCondition(@NotNull DLangIfCondition o) {
    visitPsiElement(o);
  }

  public void visitIfStatement(@NotNull DLangIfStatement o) {
    visitPsiElement(o);
  }

  public void visitImport(@NotNull DLangImport o) {
    visitDNamedElement(o);
    // visitHasVisibility(o);
  }

  public void visitImportBind(@NotNull DLangImportBind o) {
    visitPsiElement(o);
  }

  public void visitImportBindList(@NotNull DLangImportBindList o) {
    visitPsiElement(o);
  }

  public void visitImportDeclaration(@NotNull DLangImportDeclaration o) {
    visitPsiElement(o);
  }

  public void visitImportExpression(@NotNull DLangImportExpression o) {
    visitPsiElement(o);
  }

  public void visitImportList(@NotNull DLangImportList o) {
    visitPsiElement(o);
  }

  public void visitInExpression(@NotNull DLangInExpression o) {
    visitPsiElement(o);
  }

  public void visitInOut(@NotNull DLangInOut o) {
    visitPsiElement(o);
  }

  public void visitInOutX(@NotNull DLangInOutX o) {
    visitPsiElement(o);
  }

  public void visitInStatement(@NotNull DLangInStatement o) {
    visitPsiElement(o);
  }

  public void visitIncrement(@NotNull DLangIncrement o) {
    visitPsiElement(o);
  }

  public void visitIndexExpression(@NotNull DLangIndexExpression o) {
    visitPsiElement(o);
  }

  public void visitInitialize(@NotNull DLangInitialize o) {
    visitPsiElement(o);
  }

  public void visitInitializer(@NotNull DLangInitializer o) {
    visitPsiElement(o);
  }

  public void visitIntegerExpression(@NotNull DLangIntegerExpression o) {
    visitPsiElement(o);
  }

  public void visitInterface(@NotNull DLangInterface o) {
    visitPsiElement(o);
  }

  public void visitInterfaceDeclaration(@NotNull DLangInterfaceDeclaration o) {
    visitStatementContainer(o);
    // visitDNamedElement(o);
    // visitMixinContainer(o);
    // visitHasVisibility(o);
    // visitHasTemplateArguments(o);
    // visitCanInherit(o);
    // visitDeclaration(o);
  }

  public void visitInterfaceTemplateDeclaration(@NotNull DLangInterfaceTemplateDeclaration o) {
    visitPsiElement(o);
  }

  public void visitInterfaces(@NotNull DLangInterfaces o) {
    visitPsiElement(o);
  }

  public void visitInvariant(@NotNull DLangInvariant o) {
    visitPsiElement(o);
  }

  public void visitIsExpression(@NotNull DLangIsExpression o) {
    visitPsiElement(o);
  }

  public void visitKeyValuePair(@NotNull DLangKeyValuePair o) {
    visitPsiElement(o);
  }

  public void visitKeyValuePairs(@NotNull DLangKeyValuePairs o) {
    visitPsiElement(o);
  }

  public void visitLabeledStatement(@NotNull DLangLabeledStatement o) {
    visitDNamedElement(o);
    // visitStatementContainer(o);
  }

  public void visitLambda(@NotNull DLangLambda o) {
    visitPsiElement(o);
  }

  public void visitLastCatch(@NotNull DLangLastCatch o) {
    visitPsiElement(o);
  }

  public void visitLastExp(@NotNull DLangLastExp o) {
    visitPsiElement(o);
  }

  public void visitLinkageAttribute(@NotNull DLangLinkageAttribute o) {
    visitPsiElement(o);
  }

  public void visitLinkageType(@NotNull DLangLinkageType o) {
    visitPsiElement(o);
  }

  public void visitLwrExpression(@NotNull DLangLwrExpression o) {
    visitPsiElement(o);
  }

  public void visitMemberFunctionAttribute(@NotNull DLangMemberFunctionAttribute o) {
    visitPsiElement(o);
  }

  public void visitMemberFunctionAttributes(@NotNull DLangMemberFunctionAttributes o) {
    visitPsiElement(o);
  }

  public void visitMixinDeclaration(@NotNull DLangMixinDeclaration o) {
    visitMixin(o);
  }

  public void visitMixinExpression(@NotNull DLangMixinExpression o) {
    visitMixin(o);
  }

  public void visitMixinStatement(@NotNull DLangMixinStatement o) {
    visitMixin(o);
  }

  public void visitMixinTemplateName(@NotNull DLangMixinTemplateName o) {
    visitPsiElement(o);
  }

  public void visitModuleDeclaration(@NotNull DLangModuleDeclaration o) {
    visitDNamedElement(o);
    // visitGlobalDeclarationContainer(o);
    // visitHasVisibility(o);
    // visitDeclaration(o);
  }

  public void visitModuleFullyQualifiedName(@NotNull DLangModuleFullyQualifiedName o) {
    visitPsiElement(o);
  }

  public void visitMulExpression_(@NotNull DLangMulExpression_ o) {
    visitPsiElement(o);
  }

  public void visitMultipleAssign(@NotNull DLangMultipleAssign o) {
    visitPsiElement(o);
  }

  public void visitNewAnonClassExpression(@NotNull DLangNewAnonClassExpression o) {
    visitPsiElement(o);
  }

  public void visitNewExpression(@NotNull DLangNewExpression o) {
    visitPsiElement(o);
  }

  public void visitNewExpressionWithArgs(@NotNull DLangNewExpressionWithArgs o) {
    visitPsiElement(o);
  }

  public void visitNonEmptyStatement(@NotNull DLangNonEmptyStatement o) {
    visitPsiElement(o);
  }

  public void visitNonEmptyStatementNoCaseNoDefault(@NotNull DLangNonEmptyStatementNoCaseNoDefault o) {
    visitPsiElement(o);
  }

  public void visitNonVoidInitializer(@NotNull DLangNonVoidInitializer o) {
    visitPsiElement(o);
  }

  public void visitOpcode(@NotNull DLangOpcode o) {
    visitPsiElement(o);
  }

  public void visitOperand(@NotNull DLangOperand o) {
    visitPsiElement(o);
  }

  public void visitOperands(@NotNull DLangOperands o) {
    visitPsiElement(o);
  }

  public void visitOrOrExpression(@NotNull DLangOrOrExpression o) {
    visitPsiElement(o);
  }

  public void visitOutStatement(@NotNull DLangOutStatement o) {
    visitPsiElement(o);
  }

  public void visitParameter(@NotNull DLangParameter o) {
    visitDNamedElement(o);
    // visitDeclaration(o);
  }

  public void visitParameterAttributes(@NotNull DLangParameterAttributes o) {
    visitPsiElement(o);
  }

  public void visitParameterList(@NotNull DLangParameterList o) {
    visitPsiElement(o);
  }

  public void visitParameterMemberAttributes(@NotNull DLangParameterMemberAttributes o) {
    visitPsiElement(o);
  }

  public void visitParameters(@NotNull DLangParameters o) {
    visitPsiElement(o);
  }

  public void visitPostblit(@NotNull DLangPostblit o) {
    visitPsiElement(o);
  }

  public void visitPostfixExpression(@NotNull DLangPostfixExpression o) {
    visitPsiElement(o);
  }

  public void visitPowExpression_(@NotNull DLangPowExpression_ o) {
    visitPsiElement(o);
  }

  public void visitPragma(@NotNull DLangPragma o) {
    visitPsiElement(o);
  }

  public void visitPragmaStatement(@NotNull DLangPragmaStatement o) {
    visitPsiElement(o);
  }

  public void visitPrimaryExpression(@NotNull DLangPrimaryExpression o) {
    visitPsiElement(o);
  }

  public void visitProperty(@NotNull DLangProperty o) {
    visitPsiElement(o);
  }

  public void visitPropertyIdentifier(@NotNull DLangPropertyIdentifier o) {
    visitPsiElement(o);
  }

  public void visitProtectionAttribute(@NotNull DLangProtectionAttribute o) {
    visitPsiElement(o);
  }

  public void visitQualifiedIdentifierList(@NotNull DLangQualifiedIdentifierList o) {
    visitPsiElement(o);
  }

  public void visitRegister(@NotNull DLangRegister o) {
    visitPsiElement(o);
  }

  public void visitRegister64(@NotNull DLangRegister64 o) {
    visitPsiElement(o);
  }

  public void visitRelExpression(@NotNull DLangRelExpression o) {
    visitPsiElement(o);
  }

  public void visitReturnStatement(@NotNull DLangReturnStatement o) {
    visitPsiElement(o);
  }

  public void visitScopeGuardStatement(@NotNull DLangScopeGuardStatement o) {
    visitPsiElement(o);
  }

  public void visitScopeStatement(@NotNull DLangScopeStatement o) {
    visitPsiElement(o);
  }

  public void visitScopeStatementList(@NotNull DLangScopeStatementList o) {
    visitPsiElement(o);
  }

  public void visitSharedStaticConstructor(@NotNull DLangSharedStaticConstructor o) {
    visitStatementContainer(o);
    // visitDNamedElement(o);
    // visitHasVisibility(o);
    // visitDeclaration(o);
  }

  public void visitSharedStaticDestructor(@NotNull DLangSharedStaticDestructor o) {
    visitStatementContainer(o);
    // visitDNamedElement(o);
    // visitHasVisibility(o);
    // visitDeclaration(o);
  }

  public void visitShiftExpression_(@NotNull DLangShiftExpression_ o) {
    visitPsiElement(o);
  }

  public void visitSliceExpression(@NotNull DLangSliceExpression o) {
    visitPsiElement(o);
  }

  public void visitSpecialKeyword(@NotNull DLangSpecialKeyword o) {
    visitPsiElement(o);
  }

  public void visitStatement(@NotNull DLangStatement o) {
    visitPsiElement(o);
  }

  public void visitStatementList(@NotNull DLangStatementList o) {
    visitPsiElement(o);
  }

  public void visitStatementListNoCaseNoDefault(@NotNull DLangStatementListNoCaseNoDefault o) {
    visitPsiElement(o);
  }

  public void visitStatementNoCaseNoDefault(@NotNull DLangStatementNoCaseNoDefault o) {
    visitPsiElement(o);
  }

  public void visitStaticAssert(@NotNull DLangStaticAssert o) {
    visitPsiElement(o);
  }

  public void visitStaticConstructor(@NotNull DLangStaticConstructor o) {
    visitStatementContainer(o);
    // visitDNamedElement(o);
    // visitHasVisibility(o);
    // visitDeclaration(o);
  }

  public void visitStaticDestructor(@NotNull DLangStaticDestructor o) {
    visitStatementContainer(o);
    // visitDNamedElement(o);
    // visitHasVisibility(o);
    // visitDeclaration(o);
  }

  public void visitStaticElseCondition(@NotNull DLangStaticElseCondition o) {
    visitPsiElement(o);
  }

  public void visitStaticIfCondition(@NotNull DLangStaticIfCondition o) {
    visitPsiElement(o);
  }

  public void visitStorageClass(@NotNull DLangStorageClass o) {
    visitPsiElement(o);
  }

  public void visitStorageClasses(@NotNull DLangStorageClasses o) {
    visitPsiElement(o);
  }

  public void visitStringLiteral(@NotNull DLangStringLiteral o) {
    visitPsiElement(o);
  }

  public void visitStringLiterals(@NotNull DLangStringLiterals o) {
    visitPsiElement(o);
  }

  public void visitStructDeclaration(@NotNull DLangStructDeclaration o) {
    visitStatementContainer(o);
    // visitDNamedElement(o);
    // visitMixinContainer(o);
    // visitHasVisibility(o);
    // visitHasTemplateArguments(o);
    // visitDeclaration(o);
  }

  public void visitStructInitializer(@NotNull DLangStructInitializer o) {
    visitPsiElement(o);
  }

  public void visitStructMemberInitializer(@NotNull DLangStructMemberInitializer o) {
    visitPsiElement(o);
  }

  public void visitStructMemberInitializers(@NotNull DLangStructMemberInitializers o) {
    visitPsiElement(o);
  }

  public void visitSuperClass(@NotNull DLangSuperClass o) {
    visitPsiElement(o);
  }

  public void visitSwitchStatement(@NotNull DLangSwitchStatement o) {
    visitPsiElement(o);
  }

  public void visitSymbol(@NotNull DLangSymbol o) {
    visitPsiElement(o);
  }

  public void visitSymbolTail(@NotNull DLangSymbolTail o) {
    visitPsiElement(o);
  }

  public void visitSynchronizedStatement(@NotNull DLangSynchronizedStatement o) {
    visitPsiElement(o);
  }

  public void visitTemplateAliasParameter(@NotNull DLangTemplateAliasParameter o) {
    visitPsiElement(o);
  }

  public void visitTemplateArgument(@NotNull DLangTemplateArgument o) {
    visitPsiElement(o);
  }

  public void visitTemplateArgumentList(@NotNull DLangTemplateArgumentList o) {
    visitPsiElement(o);
  }

  public void visitTemplateArguments(@NotNull DLangTemplateArguments o) {
    visitPsiElement(o);
  }

  public void visitTemplateDeclaration(@NotNull DLangTemplateDeclaration o) {
    visitDNamedElement(o);
    // visitStatementContainer(o);
    // visitMixinContainer(o);
    // visitGlobalDeclarationContainer(o);
    // visitHasVisibility(o);
    // visitHasTemplateArguments(o);
    // visitMixinable(o);
    // visitDeclaration(o);
  }

  public void visitTemplateInstance(@NotNull DLangTemplateInstance o) {
    visitPsiElement(o);
  }

  public void visitTemplateMixin(@NotNull DLangTemplateMixin o) {
    visitMixin(o);
  }

  public void visitTemplateMixinDeclaration(@NotNull DLangTemplateMixinDeclaration o) {
    visitDNamedElement(o);
    // visitStatementContainer(o);
    // visitMixinContainer(o);
    // visitGlobalDeclarationContainer(o);
    // visitHasVisibility(o);
    // visitHasTemplateArguments(o);
    // visitMixinable(o);
    // visitDeclaration(o);
  }

  public void visitTemplateParameter(@NotNull DLangTemplateParameter o) {
    visitDNamedElement(o);
    // visitDeclaration(o);
  }

  public void visitTemplateParameterList(@NotNull DLangTemplateParameterList o) {
    visitPsiElement(o);
  }

  public void visitTemplateParameters(@NotNull DLangTemplateParameters o) {
    visitPsiElement(o);
  }

  public void visitTemplateSingleArgument(@NotNull DLangTemplateSingleArgument o) {
    visitPsiElement(o);
  }

  public void visitTemplateThisParameter(@NotNull DLangTemplateThisParameter o) {
    visitPsiElement(o);
  }

  public void visitTemplateTypeParameter(@NotNull DLangTemplateTypeParameter o) {
    visitPsiElement(o);
  }

  public void visitTemplateValueParameterDefault(@NotNull DLangTemplateValueParameterDefault o) {
    visitPsiElement(o);
  }

  public void visitTest(@NotNull DLangTest o) {
    visitPsiElement(o);
  }

  public void visitThenStatement(@NotNull DLangThenStatement o) {
    visitPsiElement(o);
  }

  public void visitThrowStatement(@NotNull DLangThrowStatement o) {
    visitPsiElement(o);
  }

  public void visitTraitsArgument(@NotNull DLangTraitsArgument o) {
    visitPsiElement(o);
  }

  public void visitTraitsArguments(@NotNull DLangTraitsArguments o) {
    visitPsiElement(o);
  }

  public void visitTraitsExpression(@NotNull DLangTraitsExpression o) {
    visitPsiElement(o);
  }

  public void visitTraitsKeyword(@NotNull DLangTraitsKeyword o) {
    visitPsiElement(o);
  }

  public void visitTryStatement(@NotNull DLangTryStatement o) {
    visitPsiElement(o);
  }

  public void visitType(@NotNull DLangType o) {
    visitPsiElement(o);
  }

  public void visitTypeCtor(@NotNull DLangTypeCtor o) {
    visitPsiElement(o);
  }

  public void visitTypeCtors(@NotNull DLangTypeCtors o) {
    visitPsiElement(o);
  }

  public void visitTypeSpecialization(@NotNull DLangTypeSpecialization o) {
    visitPsiElement(o);
  }

  public void visitTypeVector(@NotNull DLangTypeVector o) {
    visitPsiElement(o);
  }

  public void visitTypeidExpression(@NotNull DLangTypeidExpression o) {
    visitPsiElement(o);
  }

  public void visitTypeof(@NotNull DLangTypeof o) {
    visitPsiElement(o);
  }

  public void visitUnionDeclaration(@NotNull DLangUnionDeclaration o) {
    visitStatementContainer(o);
    // visitDNamedElement(o);
    // visitHasVisibility(o);
    // visitHasTemplateArguments(o);
    // visitDeclaration(o);
  }

  public void visitUnionTemplateDeclaration(@NotNull DLangUnionTemplateDeclaration o) {
    visitPsiElement(o);
  }

  public void visitUnitTesting(@NotNull DLangUnitTesting o) {
    visitDCompositeElement(o);
  }

  public void visitUprExpression(@NotNull DLangUprExpression o) {
    visitPsiElement(o);
  }

  public void visitUserDefinedAttribute(@NotNull DLangUserDefinedAttribute o) {
    visitPsiElement(o);
  }

  public void visitVarDeclarations(@NotNull DLangVarDeclarations o) {
    visitPsiElement(o);
  }

  public void visitVarDeclarator(@NotNull DLangVarDeclarator o) {
    visitPsiElement(o);
  }

  public void visitVarDeclaratorIdentifier(@NotNull DLangVarDeclaratorIdentifier o) {
    visitPsiElement(o);
  }

  public void visitVersionCondition(@NotNull DLangVersionCondition o) {
    visitPsiElement(o);
  }

  public void visitVersionSpecification(@NotNull DLangVersionSpecification o) {
    visitPsiElement(o);
  }

  public void visitVoidInitializer(@NotNull DLangVoidInitializer o) {
    visitPsiElement(o);
  }

  public void visitWhileStatement(@NotNull DLangWhileStatement o) {
    visitPsiElement(o);
  }

  public void visitWithStatement(@NotNull DLangWithStatement o) {
    visitPsiElement(o);
  }

  public void visitXorExpression_(@NotNull DLangXorExpression_ o) {
    visitPsiElement(o);
  }

  public void visitDCompositeElement(@NotNull DCompositeElement o) {
    visitElement(o);
  }

  public void visitDNamedElement(@NotNull DNamedElement o) {
    visitElement(o);
  }

  public void visitMixin(@NotNull Mixin o) {
    visitElement(o);
  }

  public void visitStatementContainer(@NotNull StatementContainer o) {
    visitElement(o);
  }

  public void visitPsiElement(@NotNull PsiElement o) {
    visitElement(o);
  }

}
