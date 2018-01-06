package io.github.intellij.dlanguage.psi;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import io.github.intellij.dlanguage.psi.impl.DElementTypeFactory;
import io.github.intellij.dlanguage.psi.impl.DLanguageAddExpressionImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageAliasDeclarationImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageAliasThisDeclarationImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageAlignAttributeImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageAndAndExpressionImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageAndExpressionImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageAnonymousEnumDeclarationImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageArgumentListImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageArgumentsImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageArrayInitializerImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageArrayLiteralImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageArrayMemberInitializationImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageAsmAddExpImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageAsmAndExpImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageAsmBrExpImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageAsmEqualExpImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageAsmExpImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageAsmInstructionImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageAsmLogAndExpImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageAsmLogOrExpImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageAsmMulExpImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageAsmOrExpImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageAsmPrimaryExpImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageAsmRelExpImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageAsmShiftExpImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageAsmStatementImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageAsmTypePrefixImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageAsmUnaExpImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageAsmXorExpImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageAssertExpressionImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageAssignExpressionImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageAssocArrayLiteralImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageAtAttributeImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageAttributeDeclarationImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageAttributeImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageAutoDeclarationImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageBaseClassImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageBaseClassListImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageBlockStatementImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageBodyStatementImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageBreakStatementImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageBuiltinTypeImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageCaseRangeStatementImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageCaseStatementImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageCastExpressionImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageCastQualifierImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageCatchesImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageClassDeclarationImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageCmpExpressionImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageCompileConditionImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageConditionalDeclarationImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageConditionalStatementImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageConstraintImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageContinueStatementImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageDebugConditionImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageDebugSpecificationImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageDeclarationImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageDeclarationOrStatementImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageDeclarationsAndStatementsImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageDefaultStatementImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageDeleteExpressionImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageDeprecatedImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageDestructorImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageDoStatementImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageEnumBodyImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageEqualExpressionImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageExpressionImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageExpressionStatementImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageFinalSwitchStatementImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageFinallyImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageForStatementImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageForeachStatementImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageForeachTypeListImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageFunctionAttributeImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageFunctionBodyImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageFunctionCallExpressionImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageFunctionLiteralExpressionImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageGotoStatementImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageIdentifierChainImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageIdentifierListImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageIdentifierOrTemplateChainImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageIdentifierOrTemplateInstanceImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageIdentityExpressionImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageIfStatementImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageImportBindImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageImportBindingsImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageImportDeclarationImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageImportExpressionImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageInExpressionImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageInStatementImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageIndexExpressionImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageIndexImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageInitializerImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageInterfaceDeclarationImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageInvariantImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageIsExpressionImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageKeyValuePairImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageKeyValuePairsImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageLastCatchImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageLinkageAttributeImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageMemberFunctionAttributeImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageMixinDeclarationImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageMixinExpressionImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageMixinTemplateDeclarationImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageMixinTemplateNameImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageMulExpressionImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageNewAnonClassExpressionImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageNewExpressionImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageNonVoidInitializerImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageOperandsImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageOrExpressionImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageOrOrExpressionImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageOutStatementImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageParametersImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguagePostblitImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguagePowExpressionImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguagePragmaDeclarationImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguagePragmaExpressionImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguagePrimaryExpressionImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageRegisterImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageRelExpressionImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageReturnStatementImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageScopeGuardStatementImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageSharedStaticConstructorImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageSharedStaticDestructorImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageShiftExpressionImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageStatementImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageStatementNoCaseNoDefaultImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageStaticAssertDeclarationImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageStaticAssertStatementImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageStaticConstructorImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageStaticDestructorImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageStaticIfConditionImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageStorageClassImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageStringImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageStructBodyImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageStructInitializerImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageStructMemberInitializerImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageStructMemberInitializersImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageSwitchStatementImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageSymbolImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageSynchronizedStatementImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageTemplateAliasParameterImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageTemplateArgumentImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageTemplateArgumentListImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageTemplateArgumentsImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageTemplateInstanceImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageTemplateMixinExpressionImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageTemplateParameterListImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageTemplateParametersImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageTemplateSingleArgumentImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageTemplateThisParameterImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageTemplateTupleParameterImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageTemplateTypeParameterImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageTemplateValueParameterDefaultImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageTemplateValueParameterImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageTernaryExpressionImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageThrowStatementImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageTraitsExpressionImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageTryStatementImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageTypeImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageTypeSpecializationImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageTypeSuffixImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageType_2Impl;
import io.github.intellij.dlanguage.psi.impl.DLanguageTypeidExpressionImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageTypeofExpressionImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageUnaryExpressionImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageVariableDeclarationImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageVectorImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageVersionConditionImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageWhileStatementImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageWithStatementImpl;
import io.github.intellij.dlanguage.psi.impl.DLanguageXorExpressionImpl;
import io.github.intellij.dlanguage.psi.impl.DlangUnittestImpl;
import io.github.intellij.dlanguage.psi.impl.named.DLanguageAliasInitializerImpl;
import io.github.intellij.dlanguage.psi.impl.named.DLanguageAutoDeclarationPartImpl;
import io.github.intellij.dlanguage.psi.impl.named.DLanguageCatchImpl;
import io.github.intellij.dlanguage.psi.impl.named.DLanguageConstructorImpl;
import io.github.intellij.dlanguage.psi.impl.named.DLanguageDeclaratorImpl;
import io.github.intellij.dlanguage.psi.impl.named.DLanguageEnumMemberImpl;
import io.github.intellij.dlanguage.psi.impl.named.DLanguageEponymousTemplateDeclarationImpl;
import io.github.intellij.dlanguage.psi.impl.named.DLanguageForeachTypeImpl;
import io.github.intellij.dlanguage.psi.impl.named.DLanguageFunctionDeclarationImpl;
import io.github.intellij.dlanguage.psi.impl.named.DLanguageIfConditionImpl;
import io.github.intellij.dlanguage.psi.impl.named.DLanguageLabeledStatementImpl;
import io.github.intellij.dlanguage.psi.impl.named.DLanguageModuleDeclarationImpl;
import io.github.intellij.dlanguage.psi.impl.named.DLanguageNamedImportBindImpl;
import io.github.intellij.dlanguage.psi.impl.named.DLanguageParameterImpl;
import io.github.intellij.dlanguage.psi.impl.named.DLanguageTemplateParameterImpl;
import io.github.intellij.dlanguage.psi.impl.named.DLanguageVersionSpecificationImpl;
import io.github.intellij.dlanguage.psi.impl.named.DlangEnumDeclarationImpl;
import io.github.intellij.dlanguage.psi.impl.named.DlangIdentifierImpl;
import io.github.intellij.dlanguage.psi.impl.named.DlangInterfaceOrClassImpl;
import io.github.intellij.dlanguage.psi.impl.named.DlangSingleImportImpl;
import io.github.intellij.dlanguage.psi.impl.named.DlangStructDeclarationImpl;
import io.github.intellij.dlanguage.psi.impl.named.DlangTemplateDeclarationImpl;
import io.github.intellij.dlanguage.psi.impl.named.DlangUnionDeclarationImpl;

public interface DlangTypes {
    IElementType KEYWORD = new DlangElementType("KEYWORD");
    IElementType OPERATOR = new DlangElementType("OPERATOR");
    IElementType NUMBER = new DlangElementType("NUMBER");
    IElementType STRING = new DlangElementType("STRING");
    IElementType PARENTHESES_RIGHT = new DlangElementType("PARENTHESES_RIGHT");
    IElementType PARENTHESES_LEFT = new DlangElementType("PARENTHESES_LEFT");
    IElementType BRACES_RIGHT = new DlangElementType("BRACES_RIGHT");
    IElementType BRACES_LEFT = new DlangElementType("BRACES_LEFT");
    IElementType BRACKETS_RIGHT = new DlangElementType("BRACKETS_RIGHT");
    IElementType BRACKETS_LEFT = new DlangElementType("BRACKETS_LEFT");
    IElementType COMMA = new DlangElementType("COMMA");
    IElementType SEMICOLON = new DlangElementType("SEMICOLON");
    IElementType DOT = new DlangElementType("DOT");
    IElementType MODULE_DEFINITION = new DlangElementType("MODULE_DEFINITION");
    IElementType FUNCTION_DEFINITION = new DlangElementType("FUNCTION_DEFINITION");

    IElementType DOC_COMMENT = new DlangTokenType("DOC_COMMENT");
    IElementType LINE_DOC = new DlangTokenType("LINE_DOC");


    IElementType IDENTIFIER = DElementTypeFactory.factory("IDENTIFIER");
    IElementType FUNCTION_DECLARATION = DElementTypeFactory.factory("FUNCTION_DECLARATION");
    IElementType INTERFACE_OR_CLASS = DElementTypeFactory.factory("INTERFACE_OR_CLASS");
    IElementType TEMPLATE_DECLARATION = DElementTypeFactory.factory("TEMPLATE_DECLARATION");
    IElementType CONSTRUCTOR = DElementTypeFactory.factory("CONSTRUCTOR");
    IElementType DESTRUCTOR = DElementTypeFactory.factory("DESTRUCTOR");
    IElementType STRUCT_DECLARATION = DElementTypeFactory.factory("STRUCT_DECLARATION");
    IElementType ALIAS_INITIALIZER = DElementTypeFactory.factory("ALIAS_INITIALIZER");
    IElementType MODULE_DECLARATION = DElementTypeFactory.factory("MODULE_DECLARATION");
    IElementType DECLARATOR = DElementTypeFactory.factory("DECLARATOR");
    IElementType LABELED_STATEMENT = DElementTypeFactory.factory("LABELED_STATEMENT");
    IElementType SHARED_STATIC_CONSTRUCTOR = DElementTypeFactory.factory("SHARED_STATIC_CONSTRUCTOR");
    IElementType SHARED_STATIC_DESTRUCTOR = DElementTypeFactory.factory("SHARED_STATIC_DESTRUCTOR");
    IElementType STATIC_CONSTRUCTOR = DElementTypeFactory.factory("STATIC_CONSTRUCTOR");
    IElementType STATIC_DESTRUCTOR = DElementTypeFactory.factory("STATIC_DESTRUCTOR");
    IElementType AUTO_DECLARATION_PART = DElementTypeFactory.factory("AUTO_DECLARATION_PART");
    IElementType ENUM_DECLARATION = DElementTypeFactory.factory("ENUM_DECLARATION");
    IElementType UNION_DECLARATION = DElementTypeFactory.factory("UNION_DECLARATION");
    IElementType SINGLE_IMPORT = DElementTypeFactory.factory("SINGLE_IMPORT");
    IElementType UNITTEST = DElementTypeFactory.factory("UNITTEST");
    IElementType CATCH = DElementTypeFactory.factory("CATCH");
    IElementType IF_CONDITION = DElementTypeFactory.factory("IF_CONDITION");
    IElementType FOREACH_TYPE = DElementTypeFactory.factory("FOREACH_TYPE");
    IElementType PARAMETER = DElementTypeFactory.factory("PARAMETER");
    IElementType TEMPLATE_PARAMETER = DElementTypeFactory.factory("TEMPLATE_PARAMETER");
    IElementType EPONYMOUS_TEMPLATE_DECLARATION = DElementTypeFactory.factory("EPONYMOUS_TEMPLATE_DECLARATION");
    IElementType ENUM_MEMBER = DElementTypeFactory.factory("ENUM_MEMBER");
    IElementType NAMED_IMPORT_BIND = DElementTypeFactory.factory("NAMED_IMPORT_BIND");
    IElementType VERSION_SPECIFICATION = DElementTypeFactory.factory("VERSION_SPECIFICATION");
    IElementType ALIAS_DECLARATION = new DlangElementType("ALIAS_DECLARATION");
    IElementType ALIAS_THIS_DECLARATION = new DlangElementType("ALIAS_THIS_DECLARATION");
    IElementType ALIGN_ATTRIBUTE = new DlangElementType("ALIGN_ATTRIBUTE");
    IElementType AND_AND_EXPRESSION = new DlangElementType("AND_AND_EXPRESSION");
    IElementType AND_EXPRESSION = new DlangElementType("AND_EXPRESSION");
    IElementType ANONYMOUS_ENUM_DECLARATION = new DlangElementType("ANONYMOUS_ENUM_DECLARATION");
    IElementType ARGUMENT_LIST = new DlangElementType("ARGUMENT_LIST");
    IElementType ARGUMENTS = new DlangElementType("ARGUMENTS");
    IElementType ARRAY_INITIALIZER = new DlangElementType("ARRAY_INITIALIZER");
    IElementType ARRAY_LITERAL = new DlangElementType("ARRAY_LITERAL");
    IElementType ARRAY_MEMBER_INITIALIZATION = new DlangElementType("ARRAY_MEMBER_INITIALIZATION");
    IElementType ASM_ADD_EXP = new DlangElementType("ASM_ADD_EXP");
    IElementType ASM_AND_EXP = new DlangElementType("ASM_AND_EXP");
    IElementType ASM_BR_EXP = new DlangElementType("ASM_BR_EXP");
    IElementType ASM_EQUAL_EXP = new DlangElementType("ASM_EQUAL_EXP");
    IElementType ASM_EXP = new DlangElementType("ASM_EXP");
    IElementType ASM_INSTRUCTION = new DlangElementType("ASM_INSTRUCTION");
    IElementType ASM_LOG_AND_EXP = new DlangElementType("ASM_LOG_AND_EXP");
    IElementType ASM_LOG_OR_EXP = new DlangElementType("ASM_LOG_OR_EXP");
    IElementType ASM_MUL_EXP = new DlangElementType("ASM_MUL_EXP");
    IElementType ASM_OR_EXP = new DlangElementType("ASM_OR_EXP");
    IElementType ASM_PRIMARY_EXP = new DlangElementType("ASM_PRIMARY_EXP");
    IElementType ASM_REL_EXP = new DlangElementType("ASM_REL_EXP");
    IElementType ASM_SHIFT_EXP = new DlangElementType("ASM_SHIFT_EXP");
    IElementType ASM_STATEMENT = new DlangElementType("ASM_STATEMENT");
    IElementType ASM_TYPE_PREFIX = new DlangElementType("ASM_TYPE_PREFIX");
    IElementType ASM_UNA_EXP = new DlangElementType("ASM_UNA_EXP");
    IElementType ASM_XOR_EXP = new DlangElementType("ASM_XOR_EXP");
    IElementType ASSERT_EXPRESSION = new DlangElementType("ASSERT_EXPRESSION");
    IElementType ASSIGN_EXPRESSION = new DlangElementType("ASSIGN_EXPRESSION");
    IElementType ASSOC_ARRAY_LITERAL = new DlangElementType("ASSOC_ARRAY_LITERAL");
    IElementType AT_ATTRIBUTE = new DlangElementType("AT_ATTRIBUTE");
    IElementType ATTRIBUTE = new DlangElementType("ATTRIBUTE");
    IElementType ATTRIBUTE_DECLARATION = new DlangElementType("ATTRIBUTE_DECLARATION");
    IElementType AUTO_DECLARATION = new DlangElementType("AUTO_DECLARATION");
    IElementType BASE_CLASS = new DlangElementType("BASE_CLASS");
    IElementType BASE_CLASS_LIST = new DlangElementType("BASE_CLASS_LIST");
    IElementType BLOCK_STATEMENT = new DlangElementType("BLOCK_STATEMENT");
    IElementType BODY_STATEMENT = new DlangElementType("BODY_STATEMENT");
    IElementType BREAK_STATEMENT = new DlangElementType("BREAK_STATEMENT");
    IElementType CASE_RANGE_STATEMENT = new DlangElementType("CASE_RANGE_STATEMENT");
    IElementType CASE_STATEMENT = new DlangElementType("CASE_STATEMENT");
    IElementType CAST_EXPRESSION = new DlangElementType("CAST_EXPRESSION");
    IElementType CAST_QUALIFIER = new DlangElementType("CAST_QUALIFIER");
    IElementType CATCHES = new DlangElementType("CATCHES");
    IElementType CMP_EXPRESSION = new DlangElementType("CMP_EXPRESSION");
    IElementType COMPILE_CONDITION = new DlangElementType("COMPILE_CONDITION");
    IElementType CONDITIONAL_DECLARATION = new DlangElementType("CONDITIONAL_DECLARATION");
    IElementType CONDITIONAL_STATEMENT = new DlangElementType("CONDITIONAL_STATEMENT");
    IElementType CONSTRAINT = new DlangElementType("CONSTRAINT");
    IElementType CONTINUE_STATEMENT = new DlangElementType("CONTINUE_STATEMENT");
    IElementType CLASS_DECLARATION = new DlangElementType("CLASS_DECLARATION");
    IElementType DEBUG_CONDITION = new DlangElementType("DEBUG_CONDITION");
    IElementType DEBUG_SPECIFICATION = new DlangElementType("DEBUG_SPECIFICATION");
    IElementType DECLARATION = new DlangElementType("DECLARATION");
    IElementType DECLARATION_OR_STATEMENT = new DlangElementType("DECLARATION_OR_STATEMENT");
    IElementType DECLARATIONS_AND_STATEMENTS = new DlangElementType("DECLARATIONS_AND_STATEMENTS");
    IElementType DEFAULT_STATEMENT = new DlangElementType("DEFAULT_STATEMENT");
    IElementType DELETE_EXPRESSION = new DlangElementType("DELETE_EXPRESSION");
    IElementType DEPRECATED = new DlangElementType("DEPRECATED");
    IElementType DO_STATEMENT = new DlangElementType("DO_STATEMENT");
    IElementType ENUM_BODY = new DlangElementType("ENUM_BODY");
    IElementType EQUAL_EXPRESSION = new DlangElementType("EQUAL_EXPRESSION");
    IElementType EXPRESSION = new DlangElementType("EXPRESSION");
    IElementType EXPRESSION_STATEMENT = new DlangElementType("EXPRESSION_STATEMENT");
    IElementType FINAL_SWITCH_STATEMENT = new DlangElementType("FINAL_SWITCH_STATEMENT");
    IElementType FINALLY = new DlangElementType("FINALLY");
    IElementType FOR_STATEMENT = new DlangElementType("FOR_STATEMENT");
    IElementType FOREACH_STATEMENT = new DlangElementType("FOREACH_STATEMENT");
    IElementType FOREACH_TYPE_LIST = new DlangElementType("FOREACH_TYPE_LIST");
    IElementType FUNCTION_ATTRIBUTE = new DlangElementType("FUNCTION_ATTRIBUTE");
    IElementType FUNCTION_BODY = new DlangElementType("FUNCTION_BODY");
    IElementType FUNCTION_CALL_EXPRESSION = new DlangElementType("FUNCTION_CALL_EXPRESSION");
    IElementType FUNCTION_LITERAL_EXPRESSION = new DlangElementType("FUNCTION_LITERAL_EXPRESSION");
    IElementType GOTO_STATEMENT = new DlangElementType("GOTO_STATEMENT");
    IElementType IDENTIFIER_CHAIN = new DlangElementType("IDENTIFIER_CHAIN");
    IElementType IDENTIFIER_LIST = new DlangElementType("IDENTIFIER_LIST");
    IElementType IDENTIFIER_OR_TEMPLATE_CHAIN = new DlangElementType("IDENTIFIER_OR_TEMPLATE_CHAIN");
    IElementType IDENTIFIER_OR_TEMPLATE_INSTANCE = new DlangElementType("IDENTIFIER_OR_TEMPLATE_INSTANCE");
    IElementType IDENTITY_EXPRESSION = new DlangElementType("IDENTITY_EXPRESSION");
    IElementType IF_STATEMENT = new DlangElementType("IF_STATEMENT");
    IElementType IMPORT_BIND = new DlangElementType("IMPORT_BIND");
    IElementType IMPORT_BINDINGS = new DlangElementType("IMPORT_BINDINGS");
    IElementType IMPORT_DECLARATION = new DlangElementType("IMPORT_DECLARATION");
    IElementType IMPORT_EXPRESSION = new DlangElementType("IMPORT_EXPRESSION");
    IElementType IN_EXPRESSION = new DlangElementType("IN_EXPRESSION");
    IElementType IN_STATEMENT = new DlangElementType("IN_STATEMENT");
    IElementType INDEX = new DlangElementType("INDEX");
    IElementType INDEX_EXPRESSION = new DlangElementType("INDEX_EXPRESSION");
    IElementType INITIALIZER = new DlangElementType("INITIALIZER");
    IElementType INTERFACE_DECLARATION = new DlangElementType("INTERFACE_DECLARATION");
    IElementType INVARIANT = new DlangElementType("INVARIANT");
    IElementType IS_EXPRESSION = new DlangElementType("IS_EXPRESSION");
    IElementType KEY_VALUE_PAIR = new DlangElementType("KEY_VALUE_PAIR");
    IElementType KEY_VALUE_PAIRS = new DlangElementType("KEY_VALUE_PAIRS");
    IElementType LAST_CATCH = new DlangElementType("LAST_CATCH");
    IElementType LINKAGE_ATTRIBUTE = new DlangElementType("LINKAGE_ATTRIBUTE");
    IElementType MEMBER_FUNCTION_ATTRIBUTE = new DlangElementType("MEMBER_FUNCTION_ATTRIBUTE");
    IElementType MIXIN_DECLARATION = new DlangElementType("MIXIN_DECLARATION");
    IElementType MIXIN_EXPRESSION = new DlangElementType("MIXIN_EXPRESSION");
    IElementType MIXIN_TEMPLATE_DECLARATION = new DlangElementType("MIXIN_TEMPLATE_DECLARATION");
    IElementType MIXIN_TEMPLATE_NAME = new DlangElementType("MIXIN_TEMPLATE_NAME");
    IElementType MUL_EXPRESSION = new DlangElementType("MUL_EXPRESSION");
    IElementType NEW_ANON_CLASS_EXPRESSION = new DlangElementType("NEW_ANON_CLASS_EXPRESSION");
    IElementType NEW_EXPRESSION = new DlangElementType("NEW_EXPRESSION");
    IElementType NON_VOID_INITIALIZER = new DlangElementType("NON_VOID_INITIALIZER");
    IElementType OPERANDS = new DlangElementType("OPERANDS");
    IElementType OR_EXPRESSION = new DlangElementType("OR_EXPRESSION");
    IElementType OR_OR_EXPRESSION = new DlangElementType("OR_OR_EXPRESSION");
    IElementType OUT_STATEMENT = new DlangElementType("OUT_STATEMENT");
    IElementType PARAMETERS = new DlangElementType("PARAMETERS");
    IElementType POSTBLIT = new DlangElementType("POSTBLIT");
    IElementType POW_EXPRESSION = new DlangElementType("POW_EXPRESSION");
    IElementType PRAGMA_DECLARATION = new DlangElementType("PRAGMA_DECLARATION");
    IElementType PRAGMA_EXPRESSION = new DlangElementType("PRAGMA_EXPRESSION");
    IElementType PRIMARY_EXPRESSION = new DlangElementType("PRIMARY_EXPRESSION");
    IElementType REGISTER = new DlangElementType("REGISTER");
    IElementType REL_EXPRESSION = new DlangElementType("REL_EXPRESSION");
    IElementType RETURN_STATEMENT = new DlangElementType("RETURN_STATEMENT");
    IElementType SCOPE_GUARD_STATEMENT = new DlangElementType("SCOPE_GUARD_STATEMENT");
    IElementType SHIFT_EXPRESSION = new DlangElementType("SHIFT_EXPRESSION");
    IElementType STATEMENT = new DlangElementType("STATEMENT");
    IElementType STATEMENT_NO_CASE_NO_DEFAULT = new DlangElementType("STATEMENT_NO_CASE_NO_DEFAULT");
    IElementType STATIC_ASSERT_DECLARATION = new DlangElementType("STATIC_ASSERT_DECLARATION");
    IElementType STATIC_ASSERT_STATEMENT = new DlangElementType("STATIC_ASSERT_STATEMENT");
    IElementType STATIC_IF_CONDITION = new DlangElementType("STATIC_IF_CONDITION");
    IElementType STORAGE_CLASS = new DlangElementType("STORAGE_CLASS");
    IElementType STRUCT_BODY = new DlangElementType("STRUCT_BODY");
    IElementType STRUCT_INITIALIZER = new DlangElementType("STRUCT_INITIALIZER");
    IElementType STRUCT_MEMBER_INITIALIZER = new DlangElementType("STRUCT_MEMBER_INITIALIZER");
    IElementType STRUCT_MEMBER_INITIALIZERS = new DlangElementType("STRUCT_MEMBER_INITIALIZERS");
    IElementType STRING_LIT = new DlangElementType("STRING_LIT");
    IElementType SWITCH_STATEMENT = new DlangElementType("SWITCH_STATEMENT");
    IElementType SYMBOL = new DlangElementType("SYMBOL");
    IElementType SYNCHRONIZED_STATEMENT = new DlangElementType("SYNCHRONIZED_STATEMENT");
    IElementType TEMPLATE_ALIAS_PARAMETER = new DlangElementType("TEMPLATE_ALIAS_PARAMETER");
    IElementType TEMPLATE_ARGUMENT = new DlangElementType("TEMPLATE_ARGUMENT");
    IElementType TEMPLATE_ARGUMENT_LIST = new DlangElementType("TEMPLATE_ARGUMENT_LIST");
    IElementType TEMPLATE_ARGUMENTS = new DlangElementType("TEMPLATE_ARGUMENTS");
    IElementType TEMPLATE_INSTANCE = new DlangElementType("TEMPLATE_INSTANCE");
    IElementType TEMPLATE_MIXIN_EXPRESSION = new DlangElementType("TEMPLATE_MIXIN_EXPRESSION");
    IElementType TEMPLATE_PARAMETER_LIST = new DlangElementType("TEMPLATE_PARAMETER_LIST");
    IElementType TEMPLATE_PARAMETERS = new DlangElementType("TEMPLATE_PARAMETERS");
    IElementType TEMPLATE_SINGLE_ARGUMENT = new DlangElementType("TEMPLATE_SINGLE_ARGUMENT");
    IElementType TEMPLATE_THIS_PARAMETER = new DlangElementType("TEMPLATE_THIS_PARAMETER");
    IElementType TEMPLATE_TUPLE_PARAMETER = new DlangElementType("TEMPLATE_TUPLE_PARAMETER");
    IElementType TEMPLATE_TYPE_PARAMETER = new DlangElementType("TEMPLATE_TYPE_PARAMETER");
    IElementType TEMPLATE_VALUE_PARAMETER = new DlangElementType("TEMPLATE_VALUE_PARAMETER");
    IElementType TEMPLATE_VALUE_PARAMETER_DEFAULT = new DlangElementType("TEMPLATE_VALUE_PARAMETER_DEFAULT");
    IElementType TERNARY_EXPRESSION = new DlangElementType("TERNARY_EXPRESSION");
    IElementType THROW_STATEMENT = new DlangElementType("THROW_STATEMENT");
    IElementType TRAITS_EXPRESSION = new DlangElementType("TRAITS_EXPRESSION");
    IElementType TRY_STATEMENT = new DlangElementType("TRY_STATEMENT");
    IElementType TYPE = new DlangElementType("TYPE");
    IElementType TYPE_2 = new DlangElementType("TYPE_2");
    IElementType TYPE_SPECIALIZATION = new DlangElementType("TYPE_SPECIALIZATION");
    IElementType TYPE_SUFFIX = new DlangElementType("TYPE_SUFFIX");
    IElementType TYPEID_EXPRESSION = new DlangElementType("TYPEID_EXPRESSION");
    IElementType TYPEOF_EXPRESSION = new DlangElementType("TYPEOF_EXPRESSION");
    IElementType UNARY_EXPRESSION = new DlangElementType("UNARY_EXPRESSION");
    IElementType VARIABLE_DECLARATION = new DlangElementType("VARIABLE_DECLARATION");
    IElementType VECTOR = new DlangElementType("VECTOR");
    IElementType VERSION_CONDITION = new DlangElementType("VERSION_CONDITION");
    IElementType WHILE_STATEMENT = new DlangElementType("WHILE_STATEMENT");
    IElementType WITH_STATEMENT = new DlangElementType("WITH_STATEMENT");
    IElementType XOR_EXPRESSION = new DlangElementType("XOR_EXPRESSION");
    IElementType ADD_EXPRESSION = new DlangElementType("ADD_EXPRESSION");
    IElementType BUILTIN_TYPE = new DlangElementType("BUILTIN_TYPE");

    IElementType ALTERNATE_WYSIWYG_STRING = new DlangTokenType("ALTERNATE_WYSIWYG_STRING");
    IElementType BLOCK_COMMENT = new DlangTokenType("BLOCK_COMMENT");
    IElementType CHARACTER_LITERAL = new DlangTokenType("CHARACTER_LITERAL");
    IElementType DELIMITED_STRING = new DlangTokenType("DELIMITED_STRING");
    IElementType DOUBLE_QUOTED_STRING = new DlangTokenType("DOUBLE_QUOTED_STRING");
    IElementType FLOAT_LITERAL = new DlangTokenType("FLOAT_LITERAL");
    IElementType HEX_STRING = new DlangTokenType("HEX_STRING");
    IElementType ID = new DlangTokenType("ID");
    IElementType INTEGER_LITERAL = new DlangTokenType("INTEGER_LITERAL");
    IElementType KW_ABSTRACT = new DlangTokenType("abstract");
    IElementType KW_ALIAS = new DlangTokenType("alias");
    IElementType KW_ALIGN = new DlangTokenType("align");
    IElementType KW_ASM = new DlangTokenType("asm");
    IElementType KW_ASSERT = new DlangTokenType("assert");
    IElementType KW_AUTO = new DlangTokenType("auto");
    IElementType KW_BODY = new DlangTokenType("body");
    IElementType KW_BOOL = new DlangTokenType("bool");
    IElementType KW_BREAK = new DlangTokenType("break");
    IElementType KW_BYTE = new DlangTokenType("byte");
    IElementType KW_CASE = new DlangTokenType("case");
    IElementType KW_CAST = new DlangTokenType("cast");
    IElementType KW_CATCH = new DlangTokenType("catch");
    IElementType KW_CENT = new DlangTokenType("cent");
    IElementType KW_UCENT = new DlangTokenType("ucent");
    IElementType KW_CDOUBLE = new DlangTokenType("cdouble");
    IElementType KW_CFLOAT = new DlangTokenType("cfloat");
    IElementType KW_CHAR = new DlangTokenType("char");
    IElementType KW_CLASS = new DlangTokenType("class");
    IElementType KW_CONST = new DlangTokenType("const");
    IElementType KW_CONTINUE = new DlangTokenType("continue");
    IElementType KW_CREAL = new DlangTokenType("creal");
    IElementType KW_DCHAR = new DlangTokenType("dchar");
    IElementType KW_DEBUG = new DlangTokenType("debug");
    IElementType KW_DEFAULT = new DlangTokenType("default");
    IElementType KW_DELEGATE = new DlangTokenType("delegate");
    IElementType KW_DELETE = new DlangTokenType("delete");
    IElementType KW_DEPRECATED = new DlangTokenType("deprecated");
    IElementType KW_DO = new DlangTokenType("do");
    IElementType KW_DOUBLE = new DlangTokenType("double");
    IElementType KW_ELSE = new DlangTokenType("else");
    IElementType KW_ENUM = new DlangTokenType("enum");
    IElementType KW_EXPORT = new DlangTokenType("export");
    IElementType KW_EXTERN = new DlangTokenType("extern");
    IElementType KW_FALSE = new DlangTokenType("false");
    IElementType KW_FINAL = new DlangTokenType("final");
    IElementType KW_FINALLY = new DlangTokenType("finally");
    IElementType KW_FLOAT = new DlangTokenType("float");
    IElementType KW_FOR = new DlangTokenType("for");
    IElementType KW_FOREACH = new DlangTokenType("foreach");
    IElementType KW_FOREACH_REVERSE = new DlangTokenType("foreach_reverse");
    IElementType KW_FUNCTION = new DlangTokenType("function");
    IElementType KW_GOTO = new DlangTokenType("goto");
    IElementType KW_IDOUBLE = new DlangTokenType("idouble");
    IElementType KW_IF = new DlangTokenType("if");
    IElementType KW_IFLOAT = new DlangTokenType("ifloat");
    IElementType KW_IMMUTABLE = new DlangTokenType("immutable");
    IElementType KW_IMPORT = new DlangTokenType("import");
    IElementType KW_IN = new DlangTokenType("in");
    IElementType KW_INOUT = new DlangTokenType("inout");
    IElementType KW_INT = new DlangTokenType("int");
    IElementType KW_INTERFACE = new DlangTokenType("interface");
    IElementType KW_INVARIANT = new DlangTokenType("invariant");
    IElementType KW_IREAL = new DlangTokenType("ireal");
    IElementType KW_IS = new DlangTokenType("is");
    IElementType KW_LAZY = new DlangTokenType("lazy");
    IElementType KW_LONG = new DlangTokenType("long");
    IElementType KW_MIXIN = new DlangTokenType("mixin");
    IElementType KW_MODULE = new DlangTokenType("module");
    IElementType KW_NEW = new DlangTokenType("new");
    IElementType KW_NOTHROW = new DlangTokenType("nothrow");
    IElementType KW_NULL = new DlangTokenType("null");
    IElementType KW_OUT = new DlangTokenType("out");
    IElementType KW_OVERRIDE = new DlangTokenType("override");
    IElementType KW_PACKAGE = new DlangTokenType("package");
    IElementType KW_PRAGMA = new DlangTokenType("pragma");
    IElementType KW_PRIVATE = new DlangTokenType("private");
    IElementType KW_PROTECTED = new DlangTokenType("protected");
    IElementType KW_PUBLIC = new DlangTokenType("public");
    IElementType KW_PURE = new DlangTokenType("pure");
    IElementType KW_REAL = new DlangTokenType("real");
    IElementType KW_REF = new DlangTokenType("ref");
    IElementType KW_RETURN = new DlangTokenType("return");
    IElementType KW_SCOPE = new DlangTokenType("scope");
    IElementType KW_SHARED = new DlangTokenType("shared");
    IElementType KW_SHORT = new DlangTokenType("short");
    IElementType KW_STATIC = new DlangTokenType("static");
    IElementType KW_STRUCT = new DlangTokenType("struct");
    IElementType KW_SUPER = new DlangTokenType("super");
    IElementType KW_SWITCH = new DlangTokenType("switch");
    IElementType KW_SYNCHRONIZED = new DlangTokenType("synchronized");
    IElementType KW_TEMPLATE = new DlangTokenType("template");
    IElementType KW_THIS = new DlangTokenType("this");
    IElementType KW_THROW = new DlangTokenType("throw");
    IElementType KW_TRUE = new DlangTokenType("true");
    IElementType KW_TRY = new DlangTokenType("try");
    IElementType KW_TYPEID = new DlangTokenType("typeid");
    IElementType KW_TYPEOF = new DlangTokenType("typeof");
    IElementType KW_UBYTE = new DlangTokenType("ubyte");
    IElementType KW_UINT = new DlangTokenType("uint");
    IElementType KW_ULONG = new DlangTokenType("ulong");
    IElementType KW_UNION = new DlangTokenType("union");
    IElementType KW_UNITTEST = new DlangTokenType("unittest");
    IElementType KW_USHORT = new DlangTokenType("ushort");
    IElementType KW_VERSION = new DlangTokenType("version");
    IElementType KW_VOID = new DlangTokenType("void");
    IElementType KW_WCHAR = new DlangTokenType("wchar");
    IElementType KW_WHILE = new DlangTokenType("while");
    IElementType KW_WITH = new DlangTokenType("with");
    IElementType LINE_COMMENT = new DlangTokenType("LINE_COMMENT");
    IElementType NESTING_BLOCK_COMMENT = new DlangTokenType("NESTING_BLOCK_COMMENT");
    IElementType OP_AND = new DlangTokenType("&");
    IElementType OP_AND_EQ = new DlangTokenType("&=");
    IElementType OP_ASTERISK = new DlangTokenType("*");
    IElementType OP_AT = new DlangTokenType("@");
    IElementType OP_BOOL_AND = new DlangTokenType("&&");
    IElementType OP_BOOL_OR = new DlangTokenType("||");
    IElementType OP_BRACES_LEFT = new DlangTokenType("{");
    IElementType OP_BRACES_RIGHT = new DlangTokenType("}");
    IElementType OP_BRACKET_LEFT = new DlangTokenType("[");
    IElementType OP_BRACKET_RIGHT = new DlangTokenType("]");
    IElementType OP_COLON = new DlangTokenType(":");
    IElementType OP_COMMA = new DlangTokenType(",");
    IElementType OP_DDOT = new DlangTokenType("..");
    IElementType OP_DIV = new DlangTokenType("/");
    IElementType OP_DIV_EQ = new DlangTokenType("/=");
    IElementType OP_DOLLAR = new DlangTokenType("$");
    IElementType OP_DOT = new DlangTokenType(".");
    IElementType OP_EQ = new DlangTokenType("=");
    IElementType OP_EQ_EQ = new DlangTokenType("==");
    IElementType OP_GT = new DlangTokenType(">");
    IElementType OP_GT_EQ = new DlangTokenType(">=");
    IElementType OP_LAMBDA_ARROW = new DlangTokenType("=>");
    IElementType OP_LESS = new DlangTokenType("<");
    IElementType OP_LESS_EQ = new DlangTokenType("<=");
    IElementType OP_LESS_GR = new DlangTokenType("<>");
    IElementType OP_LESS_GR_EQ = new DlangTokenType("<>=");
    IElementType OP_MINUS = new DlangTokenType("-");
    IElementType OP_MINUS_EQ = new DlangTokenType("-=");
    IElementType OP_MINUS_MINUS = new DlangTokenType("--");
    IElementType OP_MOD = new DlangTokenType("%");
    IElementType OP_MOD_EQ = new DlangTokenType("%=");
    IElementType OP_MUL_EQ = new DlangTokenType("*=");
    IElementType OP_NOT = new DlangTokenType("!");
    IElementType OP_NOT_EQ = new DlangTokenType("!=");
    IElementType OP_NOT_GR = new DlangTokenType("!>");
    IElementType OP_NOT_GR_EQ = new DlangTokenType("!>=");
    IElementType OP_NOT_LESS = new DlangTokenType("!<");
    IElementType OP_NOT_LESS_EQ = new DlangTokenType("!<=");
    IElementType OP_OR = new DlangTokenType("|");
    IElementType OP_OR_EQ = new DlangTokenType("|=");
    IElementType OP_PAR_LEFT = new DlangTokenType("(");
    IElementType OP_PAR_RIGHT = new DlangTokenType(")");
    IElementType OP_PLUS = new DlangTokenType("+");
    IElementType OP_PLUS_EQ = new DlangTokenType("+=");
    IElementType OP_PLUS_PLUS = new DlangTokenType("++");
    IElementType OP_POW = new DlangTokenType("^^");
    IElementType OP_POW_EQ = new DlangTokenType("^^=");
    IElementType OP_QUEST = new DlangTokenType("?");
    IElementType OP_SCOLON = new DlangTokenType(";");
    IElementType OP_SH_LEFT = new DlangTokenType("<<");
    IElementType OP_SH_LEFT_EQ = new DlangTokenType("<<=");
    IElementType OP_SH_RIGHT = new DlangTokenType(">>");
    IElementType OP_SH_RIGHT_EQ = new DlangTokenType(">>=");
    IElementType OP_TILDA = new DlangTokenType("~");
    IElementType OP_TILDA_EQ = new DlangTokenType("~=");
    IElementType OP_TRIPLEDOT = new DlangTokenType("...");
    IElementType OP_UNORD = new DlangTokenType("!<>");
    IElementType OP_UNORD_EQ = new DlangTokenType("!<>=");
    IElementType OP_USH_RIGHT = new DlangTokenType(">>>");
    IElementType OP_USH_RIGHT_EQ = new DlangTokenType(">>>=");
    IElementType OP_XOR = new DlangTokenType("^");
    IElementType OP_XOR_EQ = new DlangTokenType("^=");
    IElementType SHEBANG = new DlangTokenType("shebang");
    IElementType TOKEN_STRING = new DlangTokenType("TOKEN_STRING");
    IElementType WYSIWYG_STRING = new DlangTokenType("WYSIWYG_STRING");
    IElementType KW___DATE__ = new DlangTokenType("__DATE__");
    IElementType KW___EOF__ = new DlangTokenType("__EOF__");
    IElementType KW___FILE__ = new DlangTokenType("__FILE__");
    IElementType KW___FILE_FULL_PATH__ = new DlangTokenType("__FILE_FULL_PATH__");
    IElementType KW___FUNCTION__ = new DlangTokenType("__FUNCTION__");
    IElementType KW___GSHARED = new DlangTokenType("__gshared");
    IElementType KW___LINE__ = new DlangTokenType("__LINE__");
    IElementType KW___MODULE__ = new DlangTokenType("__MODULE__");
    IElementType KW___PARAMETERS = new DlangTokenType("__parameters");
    IElementType KW___PRETTY_FUNCTION__ = new DlangTokenType("__PRETTY_FUNCTION__");
    IElementType KW___TIME__ = new DlangTokenType("__TIME__");
    IElementType KW___TIMESTAMP__ = new DlangTokenType("__TIMESTAMP__");
    IElementType KW___TRAITS = new DlangTokenType("__traits");
    IElementType KW___VECTOR = new DlangTokenType("__vector");
    IElementType KW___VENDOR__ = new DlangTokenType("__VENDOR__");
    IElementType KW___VERSION__ = new DlangTokenType("__VERSION__");

    IElementType SPECIAL_EMPTY_TOKEN = new DlangTokenType("SPECIAL_EMPTY_TOKEN");

    class Factory {
        public static PsiElement createElement(final ASTNode node) {
            final IElementType type = node.getElementType();
            if (type == CONSTRUCTOR) {
                return new DLanguageConstructorImpl(node);
            } else if (type == DESTRUCTOR) {
                return new DLanguageDestructorImpl(node);
            } else if (type == ENUM_DECLARATION) {
                return new DlangEnumDeclarationImpl(node);
            } else if (type == ENUM_MEMBER) {
                return new DLanguageEnumMemberImpl(node);
            } else if (type == FOREACH_TYPE) {
                return new DLanguageForeachTypeImpl(node);
            } else if (type == IDENTIFIER) {
                return new DlangIdentifierImpl(node);
            } else if (type == LABELED_STATEMENT) {
                return new DLanguageLabeledStatementImpl(node);
            } else if (type == MODULE_DECLARATION) {
                return new DLanguageModuleDeclarationImpl(node);
            } else if (type == PARAMETER) {
                return new DLanguageParameterImpl(node);
            } else if (type == SHARED_STATIC_CONSTRUCTOR) {
                return new DLanguageSharedStaticConstructorImpl(node);
            } else if (type == SHARED_STATIC_DESTRUCTOR) {
                return new DLanguageSharedStaticDestructorImpl(node);
            } else if (type == STATIC_CONSTRUCTOR) {
                return new DLanguageStaticConstructorImpl(node);
            } else if (type == STATIC_DESTRUCTOR) {
                return new DLanguageStaticDestructorImpl(node);
            } else if (type == STRUCT_DECLARATION) {
                return new DlangStructDeclarationImpl(node);
            } else if (type == TEMPLATE_DECLARATION) {
                return new DlangTemplateDeclarationImpl(node);
            } else if (type == TEMPLATE_PARAMETER) {
                return new DLanguageTemplateParameterImpl(node);
            } else if (type == UNION_DECLARATION) {
                return new DlangUnionDeclarationImpl(node);
            } else if (type == ALIAS_DECLARATION) {
                return new DLanguageAliasDeclarationImpl(node);
            } else if (type == ALIAS_INITIALIZER) {
                return new DLanguageAliasInitializerImpl(node);
            } else if (type == ALIAS_THIS_DECLARATION) {
                return new DLanguageAliasThisDeclarationImpl(node);
            } else if (type == ALIGN_ATTRIBUTE) {
                return new DLanguageAlignAttributeImpl(node);
            } else if (type == AND_AND_EXPRESSION) {
                return new DLanguageAndAndExpressionImpl(node);
            } else if (type == AND_EXPRESSION) {
                return new DLanguageAndExpressionImpl(node);
            } else if (type == ANONYMOUS_ENUM_DECLARATION) {
                return new DLanguageAnonymousEnumDeclarationImpl(node);
            } else if (type == ARGUMENT_LIST) {
                return new DLanguageArgumentListImpl(node);
            } else if (type == ARGUMENTS) {
                return new DLanguageArgumentsImpl(node);
            } else if (type == ARRAY_INITIALIZER) {
                return new DLanguageArrayInitializerImpl(node);
            } else if (type == ARRAY_LITERAL) {
                return new DLanguageArrayLiteralImpl(node);
            } else if (type == ARRAY_MEMBER_INITIALIZATION) {
                return new DLanguageArrayMemberInitializationImpl(node);
            } else if (type == ASM_ADD_EXP) {
                return new DLanguageAsmAddExpImpl(node);
            } else if (type == ASM_AND_EXP) {
                return new DLanguageAsmAndExpImpl(node);
            } else if (type == ASM_BR_EXP) {
                return new DLanguageAsmBrExpImpl(node);
            } else if (type == ASM_EQUAL_EXP) {
                return new DLanguageAsmEqualExpImpl(node);
            } else if (type == ASM_EXP) {
                return new DLanguageAsmExpImpl(node);
            } else if (type == ASM_INSTRUCTION) {
                return new DLanguageAsmInstructionImpl(node);
            } else if (type == ASM_LOG_AND_EXP) {
                return new DLanguageAsmLogAndExpImpl(node);
            } else if (type == ASM_LOG_OR_EXP) {
                return new DLanguageAsmLogOrExpImpl(node);
            } else if (type == ASM_MUL_EXP) {
                return new DLanguageAsmMulExpImpl(node);
            } else if (type == ASM_OR_EXP) {
                return new DLanguageAsmOrExpImpl(node);
            } else if (type == ASM_PRIMARY_EXP) {
                return new DLanguageAsmPrimaryExpImpl(node);
            } else if (type == ASM_REL_EXP) {
                return new DLanguageAsmRelExpImpl(node);
            } else if (type == ASM_SHIFT_EXP) {
                return new DLanguageAsmShiftExpImpl(node);
            } else if (type == ASM_STATEMENT) {
                return new DLanguageAsmStatementImpl(node);
            } else if (type == ASM_TYPE_PREFIX) {
                return new DLanguageAsmTypePrefixImpl(node);
            } else if (type == ASM_UNA_EXP) {
                return new DLanguageAsmUnaExpImpl(node);
            } else if (type == ASM_XOR_EXP) {
                return new DLanguageAsmXorExpImpl(node);
            } else if (type == ASSERT_EXPRESSION) {
                return new DLanguageAssertExpressionImpl(node);
            } else if (type == ASSIGN_EXPRESSION) {
                return new DLanguageAssignExpressionImpl(node);
            } else if (type == ASSOC_ARRAY_LITERAL) {
                return new DLanguageAssocArrayLiteralImpl(node);
            } else if (type == AT_ATTRIBUTE) {
                return new DLanguageAtAttributeImpl(node);
            } else if (type == ATTRIBUTE) {
                return new DLanguageAttributeImpl(node);
            } else if (type == ATTRIBUTE_DECLARATION) {
                return new DLanguageAttributeDeclarationImpl(node);
            } else if (type == AUTO_DECLARATION) {
                return new DLanguageAutoDeclarationImpl(node);
            } else if (type == AUTO_DECLARATION_PART) {
                return new DLanguageAutoDeclarationPartImpl(node);
            } else if (type == BASE_CLASS) {
                return new DLanguageBaseClassImpl(node);
            } else if (type == BASE_CLASS_LIST) {
                return new DLanguageBaseClassListImpl(node);
            } else if (type == BLOCK_STATEMENT) {
                return new DLanguageBlockStatementImpl(node);
            } else if (type == BODY_STATEMENT) {
                return new DLanguageBodyStatementImpl(node);
            } else if (type == BREAK_STATEMENT) {
                return new DLanguageBreakStatementImpl(node);
            } else if (type == CASE_RANGE_STATEMENT) {
                return new DLanguageCaseRangeStatementImpl(node);
            } else if (type == CASE_STATEMENT) {
                return new DLanguageCaseStatementImpl(node);
            } else if (type == CAST_EXPRESSION) {
                return new DLanguageCastExpressionImpl(node);
            } else if (type == CAST_QUALIFIER) {
                return new DLanguageCastQualifierImpl(node);
            } else if (type == CATCH) {
                return new DLanguageCatchImpl(node);
            } else if (type == CATCHES) {
                return new DLanguageCatchesImpl(node);
            } else if (type == CMP_EXPRESSION) {
                return new DLanguageCmpExpressionImpl(node);
            } else if (type == COMPILE_CONDITION) {
                return new DLanguageCompileConditionImpl(node);
            } else if (type == CONDITIONAL_DECLARATION) {
                return new DLanguageConditionalDeclarationImpl(node);
            } else if (type == CONDITIONAL_STATEMENT) {
                return new DLanguageConditionalStatementImpl(node);
            } else if (type == CONSTRAINT) {
                return new DLanguageConstraintImpl(node);
            } else if (type == CONTINUE_STATEMENT) {
                return new DLanguageContinueStatementImpl(node);
            } else if (type == DEBUG_CONDITION) {
                return new DLanguageDebugConditionImpl(node);
            } else if (type == DEBUG_SPECIFICATION) {
                return new DLanguageDebugSpecificationImpl(node);
            } else if (type == DECLARATION) {
                return new DLanguageDeclarationImpl(node);
            } else if (type == DECLARATION_OR_STATEMENT) {
                return new DLanguageDeclarationOrStatementImpl(node);
            } else if (type == DECLARATIONS_AND_STATEMENTS) {
                return new DLanguageDeclarationsAndStatementsImpl(node);
            } else if (type == DECLARATOR) {
                return new DLanguageDeclaratorImpl(node);
            } else if (type == DEFAULT_STATEMENT) {
                return new DLanguageDefaultStatementImpl(node);
            } else if (type == DELETE_EXPRESSION) {
                return new DLanguageDeleteExpressionImpl(node);
            } else if (type == DEPRECATED) {
                return new DLanguageDeprecatedImpl(node);
            } else if (type == DO_STATEMENT) {
                return new DLanguageDoStatementImpl(node);
            } else if (type == ENUM_BODY) {
                return new DLanguageEnumBodyImpl(node);
            } else if (type == EPONYMOUS_TEMPLATE_DECLARATION) {
                return new DLanguageEponymousTemplateDeclarationImpl(node);
            } else if (type == EQUAL_EXPRESSION) {
                return new DLanguageEqualExpressionImpl(node);
            } else if (type == EXPRESSION) {
                return new DLanguageExpressionImpl(node);
            } else if (type == EXPRESSION_STATEMENT) {
                return new DLanguageExpressionStatementImpl(node);
            } else if (type == FINAL_SWITCH_STATEMENT) {
                return new DLanguageFinalSwitchStatementImpl(node);
            } else if (type == FINALLY) {
                return new DLanguageFinallyImpl(node);
            } else if (type == FOR_STATEMENT) {
                return new DLanguageForStatementImpl(node);
            } else if (type == FOREACH_STATEMENT) {
                return new DLanguageForeachStatementImpl(node);
            } else if (type == FOREACH_TYPE_LIST) {
                return new DLanguageForeachTypeListImpl(node);
            } else if (type == FUNCTION_ATTRIBUTE) {
                return new DLanguageFunctionAttributeImpl(node);
            } else if (type == FUNCTION_BODY) {
                return new DLanguageFunctionBodyImpl(node);
            } else if (type == FUNCTION_CALL_EXPRESSION) {
                return new DLanguageFunctionCallExpressionImpl(node);
            } else if (type == FUNCTION_DECLARATION) {
                return new DLanguageFunctionDeclarationImpl(node);
            } else if (type == FUNCTION_LITERAL_EXPRESSION) {
                return new DLanguageFunctionLiteralExpressionImpl(node);
            } else if (type == GOTO_STATEMENT) {
                return new DLanguageGotoStatementImpl(node);
            } else if (type == IDENTIFIER_CHAIN) {
                return new DLanguageIdentifierChainImpl(node);
            } else if (type == IDENTIFIER_LIST) {
                return new DLanguageIdentifierListImpl(node);
            } else if (type == IDENTIFIER_OR_TEMPLATE_CHAIN) {
                return new DLanguageIdentifierOrTemplateChainImpl(node);
            } else if (type == IDENTIFIER_OR_TEMPLATE_INSTANCE) {
                return new DLanguageIdentifierOrTemplateInstanceImpl(node);
            } else if (type == IDENTITY_EXPRESSION) {
                return new DLanguageIdentityExpressionImpl(node);
            } else if (type == IF_STATEMENT) {
                return new DLanguageIfStatementImpl(node);
            } else if (type == IMPORT_BIND) {
                return new DLanguageImportBindImpl(node);
            } else if (type == IMPORT_BINDINGS) {
                return new DLanguageImportBindingsImpl(node);
            } else if (type == IMPORT_DECLARATION) {
                return new DLanguageImportDeclarationImpl(node);
            } else if (type == IMPORT_EXPRESSION) {
                return new DLanguageImportExpressionImpl(node);
            } else if (type == IN_EXPRESSION) {
                return new DLanguageInExpressionImpl(node);
            } else if (type == IN_STATEMENT) {
                return new DLanguageInStatementImpl(node);
            } else if (type == INDEX) {
                return new DLanguageIndexImpl(node);
            } else if (type == INDEX_EXPRESSION) {
                return new DLanguageIndexExpressionImpl(node);
            } else if (type == INITIALIZER) {
                return new DLanguageInitializerImpl(node);
            } else if (type == INVARIANT) {
                return new DLanguageInvariantImpl(node);
            } else if (type == IS_EXPRESSION) {
                return new DLanguageIsExpressionImpl(node);
            } else if (type == KEY_VALUE_PAIR) {
                return new DLanguageKeyValuePairImpl(node);
            } else if (type == KEY_VALUE_PAIRS) {
                return new DLanguageKeyValuePairsImpl(node);
            } else if (type == LAST_CATCH) {
                return new DLanguageLastCatchImpl(node);
            } else if (type == LINKAGE_ATTRIBUTE) {
                return new DLanguageLinkageAttributeImpl(node);
            } else if (type == MEMBER_FUNCTION_ATTRIBUTE) {
                return new DLanguageMemberFunctionAttributeImpl(node);
            } else if (type == MIXIN_DECLARATION) {
                return new DLanguageMixinDeclarationImpl(node);
            } else if (type == MIXIN_EXPRESSION) {
                return new DLanguageMixinExpressionImpl(node);
            } else if (type == MIXIN_TEMPLATE_DECLARATION) {
                return new DLanguageMixinTemplateDeclarationImpl(node);
            } else if (type == MIXIN_TEMPLATE_NAME) {
                return new DLanguageMixinTemplateNameImpl(node);
            } else if (type == MUL_EXPRESSION) {
                return new DLanguageMulExpressionImpl(node);
            } else if (type == NEW_ANON_CLASS_EXPRESSION) {
                return new DLanguageNewAnonClassExpressionImpl(node);
            } else if (type == NEW_EXPRESSION) {
                return new DLanguageNewExpressionImpl(node);
            } else if (type == NON_VOID_INITIALIZER) {
                return new DLanguageNonVoidInitializerImpl(node);
            } else if (type == OPERANDS) {
                return new DLanguageOperandsImpl(node);
            } else if (type == OR_EXPRESSION) {
                return new DLanguageOrExpressionImpl(node);
            } else if (type == OR_OR_EXPRESSION) {
                return new DLanguageOrOrExpressionImpl(node);
            } else if (type == OUT_STATEMENT) {
                return new DLanguageOutStatementImpl(node);
            } else if (type == PARAMETERS) {
                return new DLanguageParametersImpl(node);
            } else if (type == POSTBLIT) {
                return new DLanguagePostblitImpl(node);
            } else if (type == POW_EXPRESSION) {
                return new DLanguagePowExpressionImpl(node);
            } else if (type == PRAGMA_DECLARATION) {
                return new DLanguagePragmaDeclarationImpl(node);
            } else if (type == PRAGMA_EXPRESSION) {
                return new DLanguagePragmaExpressionImpl(node);
            } else if (type == PRIMARY_EXPRESSION) {
                return new DLanguagePrimaryExpressionImpl(node);
            } else if (type == REGISTER) {
                return new DLanguageRegisterImpl(node);
            } else if (type == REL_EXPRESSION) {
                return new DLanguageRelExpressionImpl(node);
            } else if (type == RETURN_STATEMENT) {
                return new DLanguageReturnStatementImpl(node);
            } else if (type == SCOPE_GUARD_STATEMENT) {
                return new DLanguageScopeGuardStatementImpl(node);
            } else if (type == SHIFT_EXPRESSION) {
                return new DLanguageShiftExpressionImpl(node);
            } else if (type == SINGLE_IMPORT) {
                return new DlangSingleImportImpl(node);
            } else if (type == STATEMENT) {
                return new DLanguageStatementImpl(node);
            } else if (type == STATEMENT_NO_CASE_NO_DEFAULT) {
                return new DLanguageStatementNoCaseNoDefaultImpl(node);
            } else if (type == STATIC_ASSERT_DECLARATION) {
                return new DLanguageStaticAssertDeclarationImpl(node);
            } else if (type == STATIC_ASSERT_STATEMENT) {
                return new DLanguageStaticAssertStatementImpl(node);
            } else if (type == STATIC_IF_CONDITION) {
                return new DLanguageStaticIfConditionImpl(node);
            } else if (type == STORAGE_CLASS) {
                return new DLanguageStorageClassImpl(node);
            } else if (type == STRUCT_BODY) {
                return new DLanguageStructBodyImpl(node);
            } else if (type == STRUCT_INITIALIZER) {
                return new DLanguageStructInitializerImpl(node);
            } else if (type == STRUCT_MEMBER_INITIALIZER) {
                return new DLanguageStructMemberInitializerImpl(node);
            } else if (type == STRUCT_MEMBER_INITIALIZERS) {
                return new DLanguageStructMemberInitializersImpl(node);
            } else if (type == SWITCH_STATEMENT) {
                return new DLanguageSwitchStatementImpl(node);
            } else if (type == SYMBOL) {
                return new DLanguageSymbolImpl(node);
            } else if (type == SYNCHRONIZED_STATEMENT) {
                return new DLanguageSynchronizedStatementImpl(node);
            } else if (type == TEMPLATE_ALIAS_PARAMETER) {
                return new DLanguageTemplateAliasParameterImpl(node);
            } else if (type == TEMPLATE_ARGUMENT) {
                return new DLanguageTemplateArgumentImpl(node);
            } else if (type == TEMPLATE_ARGUMENT_LIST) {
                return new DLanguageTemplateArgumentListImpl(node);
            } else if (type == TEMPLATE_ARGUMENTS) {
                return new DLanguageTemplateArgumentsImpl(node);
            } else if (type == TEMPLATE_INSTANCE) {
                return new DLanguageTemplateInstanceImpl(node);
            } else if (type == TEMPLATE_MIXIN_EXPRESSION) {
                return new DLanguageTemplateMixinExpressionImpl(node);
            } else if (type == TEMPLATE_PARAMETER_LIST) {
                return new DLanguageTemplateParameterListImpl(node);
            } else if (type == TEMPLATE_PARAMETERS) {
                return new DLanguageTemplateParametersImpl(node);
            } else if (type == TEMPLATE_SINGLE_ARGUMENT) {
                return new DLanguageTemplateSingleArgumentImpl(node);
            } else if (type == TEMPLATE_THIS_PARAMETER) {
                return new DLanguageTemplateThisParameterImpl(node);
            } else if (type == TEMPLATE_TUPLE_PARAMETER) {
                return new DLanguageTemplateTupleParameterImpl(node);
            } else if (type == TEMPLATE_TYPE_PARAMETER) {
                return new DLanguageTemplateTypeParameterImpl(node);
            } else if (type == TEMPLATE_VALUE_PARAMETER) {
                return new DLanguageTemplateValueParameterImpl(node);
            } else if (type == TEMPLATE_VALUE_PARAMETER_DEFAULT) {
                return new DLanguageTemplateValueParameterDefaultImpl(node);
            } else if (type == TERNARY_EXPRESSION) {
                return new DLanguageTernaryExpressionImpl(node);
            } else if (type == THROW_STATEMENT) {
                return new DLanguageThrowStatementImpl(node);
            } else if (type == TRAITS_EXPRESSION) {
                return new DLanguageTraitsExpressionImpl(node);
            } else if (type == TRY_STATEMENT) {
                return new DLanguageTryStatementImpl(node);
            } else if (type == TYPE) {
                return new DLanguageTypeImpl(node);
            } else if (type == TYPE_2) {
                return new DLanguageType_2Impl(node);
            } else if (type == TYPE_SPECIALIZATION) {
                return new DLanguageTypeSpecializationImpl(node);
            } else if (type == TYPE_SUFFIX) {
                return new DLanguageTypeSuffixImpl(node);
            } else if (type == TYPEID_EXPRESSION) {
                return new DLanguageTypeidExpressionImpl(node);
            } else if (type == TYPEOF_EXPRESSION) {
                return new DLanguageTypeofExpressionImpl(node);
            } else if (type == UNARY_EXPRESSION) {
                return new DLanguageUnaryExpressionImpl(node);
            } else if (type == VARIABLE_DECLARATION) {
                return new DLanguageVariableDeclarationImpl(node);
            } else if (type == VECTOR) {
                return new DLanguageVectorImpl(node);
            } else if (type == VERSION_CONDITION) {
                return new DLanguageVersionConditionImpl(node);
            } else if (type == VERSION_SPECIFICATION) {
                return new DLanguageVersionSpecificationImpl(node);
            } else if (type == WHILE_STATEMENT) {
                return new DLanguageWhileStatementImpl(node);
            } else if (type == WITH_STATEMENT) {
                return new DLanguageWithStatementImpl(node);
            } else if (type == XOR_EXPRESSION) {
                return new DLanguageXorExpressionImpl(node);
            } else if (type == ADD_EXPRESSION) {
                return new DLanguageAddExpressionImpl(node);
            } else if (type == CLASS_DECLARATION) {
                return new DLanguageClassDeclarationImpl(node);
            } else if (type == UNITTEST) {
                return new DlangUnittestImpl(node);
            } else if (type == INTERFACE_OR_CLASS) {
                return new DlangInterfaceOrClassImpl(node);
            } else if (type == INTERFACE_DECLARATION) {
                return new DLanguageInterfaceDeclarationImpl(node);
            } else if (type == STRING_LIT) {
                return new DLanguageStringImpl(node);
            } else if (type == IF_CONDITION) {
                return new DLanguageIfConditionImpl(node);
            } else if (type == BUILTIN_TYPE) {
                return new DLanguageBuiltinTypeImpl(node);
            } else if (type == NAMED_IMPORT_BIND) {
                return new DLanguageNamedImportBindImpl(node);
            }


            throw new AssertionError("Unknown element type: " + type);
        }
    }
}
