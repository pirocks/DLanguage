package net.masterthought.dlanguage.highlighting;

import com.intellij.psi.tree.TokenSet;
import net.masterthought.dlanguage.psi.DLangTypes;

public class DHighlightingTokenSets {


    public static final TokenSet KEYWORD = TokenSet.create(DLangTypes.KEYWORD);
    public static final TokenSet NUMBER = TokenSet.create(DLangTypes.NUMBER);
    public static final TokenSet STRING = TokenSet.create(DLangTypes.STRING);
    public static final TokenSet OPERATOR = TokenSet.create(DLangTypes.OPERATOR);
    public static final TokenSet PARENTHESES = TokenSet.create(DLangTypes.PARENTHESES);
    public static final TokenSet BRACES = TokenSet.create(DLangTypes.BRACES);
    public static final TokenSet BRACKETS = TokenSet.create(DLangTypes.BRACKETS);
    public static final TokenSet COMMA = TokenSet.create(DLangTypes.COMMA);
    public static final TokenSet SEMICOLON = TokenSet.create(DLangTypes.SEMICOLON);
    public static final TokenSet DOT = TokenSet.create(DLangTypes.DOT);
    public static final TokenSet LINE_COMMENT = TokenSet.create(DLangTypes.LINE_COMMENT);
    public static final TokenSet BLOCK_COMMENT = TokenSet.create(DLangTypes.BLOCK_COMMENT);
    public static final TokenSet MODULE_DEFINITION = TokenSet.create(DLangTypes.MODULE_DEFINITION);
    public static final TokenSet FUNCTION_DEFINITION = TokenSet.create(DLangTypes.FUNCTION_DEFINITION);

//    public static final TokenSet WHITESPACES = TokenSet.create(TokenType.WHITE_SPACE);
//
//    public static final TokenSet LINE_COMMENTS = TokenSet.create(DLangTypes.LINE_COMMENT);
//
//    public static final TokenSet BLOCK_COMMENTS = TokenSet.create(DLangTypes.BLOCK_COMMENT,DLangTypes.NESTING_BLOCK_COMMENT);
//
//    public static final TokenSet STRING_LITERALS = TokenSet.create(DLangTypes.DOUBLE_QUOTED_STRING,
//            DLangTypes.KW_CHAR,
//            DLangTypes.KW_DCHAR,
//            DLangTypes.KW_WCHAR,
//            DLangTypes.STRING_LITERAL,
//            DLangTypes.STRING_LITERALS,
//            DLangTypes.HEX_STRING,
//            DLangTypes.CHARACTER_LITERAL,
//            DLangTypes.DELIMITED_STRING,
//            DLangTypes.WYSIWYG_STRING,
//            DLangTypes.ALTERNATE_WYSIWYG_STRING);
//    public static final TokenSet INTEGER_LITERALS = TokenSet.create(DLangTypes.INTEGER_LITERAL);
//    public static final TokenSet FLOAT_LITERALS = TokenSet.create(DLangTypes.FLOAT_LITERAL);
//
//    public static final TokenSet PARENS = TokenSet.create(DLangTypes.OP_PAR_LEFT, DLangTypes.OP_PAR_RIGHT);
//    public static final TokenSet BRACE = TokenSet.create(DLangTypes.OP_BRACES_LEFT, DLangTypes.OP_BRACES_RIGHT);
//    public static final TokenSet BRACKET = TokenSet.create(DLangTypes.OP_BRACKET_LEFT, DLangTypes.OP_BRACKET_RIGHT);
//
//    public static final TokenSet KEYWORD = TokenSet.create(
//            DLangTypes.KW_ABSTRACT,
//            DLangTypes.KW_ALIAS,
//            DLangTypes.KW_ALIGN,
//            DLangTypes.KW_ASM,
//            DLangTypes.KW_ASSERT,
//            DLangTypes.KW_AUTO,
//            DLangTypes.KW_BODY,
//            DLangTypes.KW_BOOL,
//            DLangTypes.KW_BREAK,
//            DLangTypes.KW_BYTE,
//            DLangTypes.KW_CASE,
//            DLangTypes.KW_CAST,
//            DLangTypes.KW_CATCH,
//            DLangTypes.KW_CDOUBLE,
////            DLangTypes.KW_CENT,
//            DLangTypes.KW_CFLOAT,
//            DLangTypes.KW_CHAR,
//            DLangTypes.KW_CLASS,
//            DLangTypes.KW_CONST,
//            DLangTypes.KW_CONTINUE,
//            DLangTypes.KW_CREAL,
//            DLangTypes.KW_DCHAR,
//            DLangTypes.KW_DEBUG,
//            DLangTypes.KW_DEFAULT,
//            DLangTypes.KW_DELEGATE,
//            DLangTypes.KW_DELETE,
//            DLangTypes.KW_DEPRECATED,
//            DLangTypes.KW_DO,
//            DLangTypes.KW_DOUBLE,
//            DLangTypes.KW_ELSE,
//            DLangTypes.KW_ENUM,
//            DLangTypes.KW_EXPORT,
//            DLangTypes.KW_EXTERN,
//            DLangTypes.KW_FALSE,
//            DLangTypes.KW_FINAL,
//            DLangTypes.KW_FINALLY,
//            DLangTypes.KW_FLOAT,
//            DLangTypes.KW_FOR,
//            DLangTypes.KW_FOREACH,
//            DLangTypes.KW_FOREACH_REVERSE,
//            DLangTypes.KW_FUNCTION,
//            DLangTypes.KW_GOTO,
//            DLangTypes.KW_IDOUBLE,
//            DLangTypes.KW_IF,
//            DLangTypes.KW_IFLOAT,
//            DLangTypes.KW_IMMUTABLE,
//            DLangTypes.KW_IMPORT,
//            DLangTypes.KW_IN,
//            DLangTypes.KW_INOUT,
//            DLangTypes.KW_INT,
//            DLangTypes.KW_INTERFACE,
//            DLangTypes.KW_INVARIANT,
//            DLangTypes.KW_IREAL,
//            DLangTypes.KW_IS,
//            DLangTypes.KW_LAZY,
//            DLangTypes.KW_LONG,
////            DLangTypes.KW_MACRO,
//            DLangTypes.KW_MIXIN,
//            DLangTypes.KW_MODULE,
//            DLangTypes.KW_NEW,
//            DLangTypes.KW_NOTHROW,
//            DLangTypes.KW_NULL,
//            DLangTypes.KW_OUT,
//            DLangTypes.KW_OVERRIDE,
//            DLangTypes.KW_PACKAGE,
//            DLangTypes.KW_PRAGMA,
//            DLangTypes.KW_PRIVATE,
//            DLangTypes.KW_PROTECTED,
//            DLangTypes.KW_PUBLIC,
//            DLangTypes.KW_PURE,
//            DLangTypes.KW_REAL,
//            DLangTypes.KW_REF,
//            DLangTypes.KW_RETURN,
//            DLangTypes.KW_SCOPE,
//            DLangTypes.KW_SHARED,
//            DLangTypes.KW_SHORT,
//            DLangTypes.KW_STATIC,
//            DLangTypes.KW_STRUCT,
//            DLangTypes.KW_SUPER,
//            DLangTypes.KW_SWITCH,
//            DLangTypes.KW_SYNCHRONIZED,
//            DLangTypes.KW_TEMPLATE,
//            DLangTypes.KW_THIS,
//            DLangTypes.KW_THROW,
//            DLangTypes.KW_TRUE,
//            DLangTypes.KW_TRY,
////            DLangTypes.KW_TYPEDEF,
//            DLangTypes.KW_TYPEID,
//            DLangTypes.KW_TYPEOF,
//            DLangTypes.KW_UBYTE,
////            DLangTypes.KW_UCENT,
//            DLangTypes.KW_UINT,
//            DLangTypes.KW_ULONG,
//            DLangTypes.KW_UNION,
//            DLangTypes.KW_UNITTEST,
//            DLangTypes.KW_USHORT,
//            DLangTypes.KW_VERSION,
//            DLangTypes.KW_VOID,
////            DLangTypes.KW_VOLATILE,
//            DLangTypes.KW_WCHAR,
//            DLangTypes.KW_WHILE,
//            DLangTypes.KW_WITH,
//            DLangTypes.KW___FILE__,
//            DLangTypes.KW___LINE__,
//            DLangTypes.KW___GSHARED,
//            DLangTypes.KW___TRAITS
////            DLangTypes.KW___VECTOR
//    );
//
//    public static final TokenSet OPERATOR = TokenSet.create(
//            DLangTypes.OP_SCOLON,
//            DLangTypes.OP_COLON,
//            DLangTypes.OP_EQ,
//            DLangTypes.OP_COMMA,
//            DLangTypes.OP_PAR_LEFT,
//            DLangTypes.OP_PAR_RIGHT,
//            DLangTypes.OP_BRACKET_LEFT,
//            DLangTypes.OP_BRACKET_RIGHT,
//            DLangTypes.OP_BRACES_LEFT,
//            DLangTypes.OP_BRACES_RIGHT,
//            DLangTypes.OP_ASTERISK,
//            DLangTypes.OP_DDOT,
//            DLangTypes.OP_TRIPLEDOT,
//            DLangTypes.OP_AT,
//            DLangTypes.OP_PLUS_EQ,
//            DLangTypes.OP_MINUS_EQ,
//            DLangTypes.OP_MUL_EQ,
//            DLangTypes.OP_DIV_EQ,
//            DLangTypes.OP_MOD_EQ,
//            DLangTypes.OP_AND_EQ,
//            DLangTypes.OP_OR_EQ,
//            DLangTypes.OP_XOR_EQ,
//            DLangTypes.OP_TILDA_EQ,
//            DLangTypes.OP_SH_LEFT_EQ,
//            DLangTypes.OP_SH_RIGHT_EQ,
//            DLangTypes.OP_USH_RIGHT_EQ,
//            DLangTypes.OP_POW_EQ,
//            DLangTypes.OP_QUEST,
//            DLangTypes.OP_BOOL_OR,
//            DLangTypes.OP_BOOL_AND,
//            DLangTypes.OP_OR,
//            DLangTypes.OP_XOR,
//            DLangTypes.OP_SH_LEFT,
//            DLangTypes.OP_SH_RIGHT,
//            DLangTypes.OP_USH_RIGHT,
//            DLangTypes.OP_PLUS,
//            DLangTypes.OP_MINUS,
//            DLangTypes.OP_TILDA,
//            DLangTypes.OP_DIV,
//            DLangTypes.OP_MOD,
//            DLangTypes.OP_AND,
//            DLangTypes.OP_PLUS_PLUS,
//            DLangTypes.OP_MINUS_MINUS,
//            DLangTypes.OP_NOT,
//            DLangTypes.OP_POW,
//            DLangTypes.OP_DOLLAR,
//            DLangTypes.OP_EQ_EQ,
//            DLangTypes.OP_NOT_EQ,
//            DLangTypes.OP_LESS,
//            DLangTypes.OP_LESS_EQ,
//            DLangTypes.OP_GT,
//            DLangTypes.OP_GT_EQ,
//            DLangTypes.OP_UNORD,
//            DLangTypes.OP_UNORD_EQ,
//            DLangTypes.LAMBDA
//    );
}
