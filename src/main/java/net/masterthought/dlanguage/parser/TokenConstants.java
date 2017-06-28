package net.masterthought.dlanguage.parser;

import net.masterthought.dlanguage.psi.DLanguageTypes;

/**
 * Created by francis on 6/27/2017.
 */
class TokenConstants {
    public static final short TOKconst = DLanguageTypes.KW_CONST.getIndex();
    public static final short TOKimmutable = DLanguageTypes.KW_IMMUTABLE.getIndex();
    public static final short TOKshared = DLanguageTypes.KW_SHARED.getIndex();
    public static final short TOKwild = DLanguageTypes.KW_INOUT.getIndex();
    public static final short TOKfinal = DLanguageTypes.KW_FINAL.getIndex();
    public static final short TOKauto = DLanguageTypes.KW_AUTO.getIndex();
    public static final short TOKscope = DLanguageTypes.KW_SCOPE.getIndex();
    public static final short TOKoverride = DLanguageTypes.KW_OVERRIDE.getIndex();
    public static final short TOKabstract = DLanguageTypes.KW_ABSTRACT.getIndex();
    public static final short TOKsynchronized = DLanguageTypes.KW_SYNCHRONIZED.getIndex();
    public static final short TOKdeprecated = DLanguageTypes.KW_DEPRECATED.getIndex();
    public static final short TOKnothrow = DLanguageTypes.KW_NOTHROW.getIndex();
    public static final short TOKpure = DLanguageTypes.KW_PURE.getIndex();
    public static final short TOKref = DLanguageTypes.KW_REF.getIndex();
    public static final short TOKgshared = DLanguageTypes.KW___GSHARED.getIndex();
    public static final short TOKreturn = DLanguageTypes.KW_RETURN.getIndex();
    public static final short TOKat = DLanguageTypes.OP_AT.getIndex();
    public static final short TOKlparen = DLanguageTypes.OP_PAR_LEFT.getIndex();
    public static final short TOKrparen = DLanguageTypes.OP_PAR_RIGHT.getIndex();
    public static final short TOKeof = -1;//todo
    public static final short TOKidentifier = DLanguageTypes.ID.getIndex();
    public static final short TOKnot = DLanguageTypes.OP_NOT.getIndex();
    public static final short TOKmodule = DLanguageTypes.KW_MODULE.getIndex();

}
