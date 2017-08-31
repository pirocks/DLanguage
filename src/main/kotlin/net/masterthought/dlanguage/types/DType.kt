package net.masterthought.dlanguage.types

enum class ENUMTY
{
    Tarray,     // slice array, aka T[]
    Tsarray,    // static array, aka T[dimension]
    Taarray,    // associative array, aka T[type]
    Tpointer,
    Treference,
    Tfunction,
    Tident,
    Tclass,
    Tstruct,
    Tenum,

    Tdelegate,
    Tnone,
    Tvoid,
    Tint8,
    Tuns8,
    Tint16,
    Tuns16,
    Tint32,
    Tuns32,
    Tint64,

    Tuns64,
    Tfloat32,
    Tfloat64,
    Tfloat80,
    Timaginary32,
    Timaginary64,
    Timaginary80,
    Tcomplex32,
    Tcomplex64,
    Tcomplex80,

    Tbool,
    Tchar,
    Twchar,
    Tdchar,
    Terror,
    Tinstance,
    Ttypeof,
    Ttuple,
    Tslice,
    Treturn,

    Tnull,
    Tvector,
    Tint128,
    Tuns128,
    TMAX,
}

//these make copy pasting from compiler easier. when done these should be inlined.
typealias TY = ENUMTY
typealias TypeBasic = BasicDType
typealias TypeVector = DTypeVector

val basicTypeIndex: BasicTypeIndex = BasicTypeIndex()

class BasicTypeIndex {
    private val basicTypeIndex = mutableMapOf<String, BasicDType>()

    fun getFromIndex(name: String): BasicDType? {
        return basicTypeIndex.get(name)?.createCopy()
    }

    fun register(name: String, type: BasicDType) {
        basicTypeIndex.putIfAbsent(name, type)
    }
}


val tvoid: BasicDType = BasicDType(ENUMTY.Tvoid, "void", 1, "void", DType.Flags())
val tbyte: BasicDType = BasicDType(ENUMTY.Tint8, "int8", 1, "byte", DType.Flags(TFLAGSintegral = true))
val tubyte: BasicDType = BasicDType(ENUMTY.Tuns8, "uns8", 1, "ubtye", DType.Flags(TFLAGSintegral = true, TFLAGSunsigned = true))
val tshort: BasicDType = BasicDType(ENUMTY.Tint16, "int16", 2, "short", DType.Flags(TFLAGSintegral = true))
val tushort: BasicDType = BasicDType(ENUMTY.Tuns16, "uns16", 2, "ushort", DType.Flags(TFLAGSintegral = true, TFLAGSunsigned = true))
val tint: BasicDType = BasicDType(ENUMTY.Tint32, "int32", 4, "int", DType.Flags(TFLAGSintegral = true))
val tunit: BasicDType = BasicDType(ENUMTY.Tuns32, "uns32", 4, "uint", DType.Flags(TFLAGSintegral = true, TFLAGSunsigned = true))
val tlong: BasicDType = BasicDType(ENUMTY.Tint64, "int64", 8, "long", DType.Flags(TFLAGSintegral = true))
val tulong: BasicDType = BasicDType(ENUMTY.Tuns64, "uns64", 8, "ulong", DType.Flags(TFLAGSintegral = true, TFLAGSunsigned = true))
val tcent: BasicDType = BasicDType(ENUMTY.Tint128, "int128", 16, "cent", DType.Flags(TFLAGSintegral = true))
val tucent: BasicDType = BasicDType(ENUMTY.Tuns128, "uns128", 16, "ucent", DType.Flags(TFLAGSintegral = true, TFLAGSunsigned = true))
val tfloat32: BasicDType = BasicDType(ENUMTY.Tfloat32, "float32", 4, "float", DType.Flags(TFLAGSfloating = true, TFLAGSreal = true))
val tfloat64: BasicDType = BasicDType(ENUMTY.Tfloat64, "float64", 8, "double", DType.Flags(TFLAGSfloating = true, TFLAGSreal = true))
val tfloat80: BasicDType = BasicDType(ENUMTY.Tfloat80, "float80", 16, "real", DType.Flags(TFLAGSfloating = true, TFLAGSreal = true))
val timaginary32: BasicDType = BasicDType(ENUMTY.Timaginary32, "imaginary32", 4, "ifloat", DType.Flags(TFLAGSfloating = true, TFLAGSimaginary = true))
val timaginary64: BasicDType = BasicDType(ENUMTY.Timaginary64, "imaginary64", 8, "idouble", DType.Flags(TFLAGSfloating = true, TFLAGSimaginary = true))
val timaginary80: BasicDType = BasicDType(ENUMTY.Timaginary80, "imaginary80", 16, "ireal", DType.Flags(TFLAGSfloating = true, TFLAGSimaginary = true))
val tcomplex32: BasicDType = BasicDType(ENUMTY.Tcomplex32, "complex32", 8, "cfloat", DType.Flags(TFLAGSfloating = true, TFLAGScomplex = true))
val tcomplex64: BasicDType = BasicDType(ENUMTY.Tcomplex64, "complex64", 16, "cdouble", DType.Flags(TFLAGSfloating = true, TFLAGScomplex = true))
val tcomplex80: BasicDType = BasicDType(ENUMTY.Tcomplex80, "complex80", 32, "creal", DType.Flags(TFLAGSfloating = true, TFLAGScomplex = true))
val tbool: BasicDType = BasicDType(ENUMTY.Tbool, "bool", 1, "bool", DType.Flags(true))
val tchar: BasicDType = BasicDType(ENUMTY.Tchar, "char", 1, "char", DType.Flags(TFLAGSintegral = true, TFLAGSunsigned = true))
val twchar: BasicDType = BasicDType(ENUMTY.Twchar, "wchar", 2, "wchar", DType.Flags(TFLAGSintegral = true, TFLAGSunsigned = true))
val tdchar: BasicDType = BasicDType(ENUMTY.Tdchar, "dchar", 4, "dchar", DType.Flags(TFLAGSintegral = true, TFLAGSunsigned = true))
//
//
//// Some special types
//val tshiftcnt: Type? = null
//val tvoidptr: Type? = null    // void*
//val tstring: Type? = null     // immutable(char)[]
//val twstring: Type? = null    // immutable(wchar)[]
//val tdstring: Type? = null    // immutable(dchar)[]
//val tvalist: Type? = null     // va_list alias
//val terror: Type? = null      // for error recovery
//val tnull: Type? = null       // for null type
//
//val tsize_t: Type? = null     // matches size_t alias
//val tptrdiff_t: Type? = null  // matches ptrdiff_t alias
//val thash_t: Type? = null     // matches hash_t alias
//
//
//val dtypeinfo: ClassDeclaration? = null
//val typeinfoclass: ClassDeclaration? = null
//val typeinfointerface: ClassDeclaration? = null
//val typeinfostruct: ClassDeclaration? = null
//val typeinfopointer: ClassDeclaration? = null
//val typeinfoarray: ClassDeclaration? = null
//val typeinfostaticarray: ClassDeclaration? = null
//val typeinfoassociativearray: ClassDeclaration? = null
//val typeinfovector: ClassDeclaration? = null
//val typeinfoenum: ClassDeclaration? = null
//val typeinfofunction: ClassDeclaration? = null
//val typeinfodelegate: ClassDeclaration? = null
//val typeinfotypelist: ClassDeclaration? = null
//val typeinfoconst: ClassDeclaration? = null
//val typeinfoinvariant: ClassDeclaration? = null
//val typeinfoshared: ClassDeclaration? = null
//val typeinfowild: ClassDeclaration? = null
//
//val rtinfo: TemplateDeclaration? = null
//
////Type[TMAX] basic;
////StringTable stringtable;
//todo check transative const
//
enum class Match {
    nomatch, // no match
    convert, // match with conversions
    constant, // match with conversion to const
    exact      // exact match

}


//
//abstract class TypeBasic : Type() {
//    enum class TypeOf {
//        UINT, INT, CHAR//...todo
//    }
//}
//
///**
// * represents types with some kind of type modifier. Not modifiers like const,immutable etc. Modifiers like int *, int[], int function(), ref int
// */
open class NextMods : Mods {
    override var MODconst: Boolean
    override var MODimmutable: Boolean
    override var MODshared: Boolean
    override var MODwild: Boolean
    val typeNext: DTypeNext

    constructor(typeNext: DTypeNext, MODconst: Boolean = false, MODimmutable: Boolean = false, MODshared: Boolean = false, MODwild: Boolean = false) : super() {
        this.MODconst = MODconst
        if (MODconst) {
            typeNext.next.mods.applyTransitiveConst()
        }
        this.MODimmutable = MODimmutable
        this.MODshared = MODshared
        this.MODwild = MODwild
        this.typeNext = typeNext
    }

    override fun applyTransitiveConst() {
        typeNext.next.mods.applyTransitiveConst()
        MODconst = true
    }

}

abstract class DTypeNext(val next: DType, override val ty: TY) : DType(ty, Flags()) {
    override val mods: NextMods = NextMods(this)

}

//
//
//class TypeError : Type() {
//    override fun implicitlyConvertibleTo(): Boolean {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//
//}
//
//
//class TypeInfoDeclaration {
////todo
//}
//
//
//
//
//abstract class ResolvableType {
//    abstract fun resolve(): Type
//    abstract fun actuallyIsType(): Boolean
//    //todo
//}
//
abstract class DType(open val ty: TY, val flags: Flags) {

    class Flags(val TFLAGSintegral: Boolean = false,
                val TFLAGSfloating: Boolean = false,
                val TFLAGSunsigned: Boolean = false,
                val TFLAGSreal: Boolean = false,
                val TFLAGSimaginary: Boolean = false,
                val TFLAGScomplex: Boolean = false) {

        fun isIntegral(): Boolean {
            return TFLAGSintegral
        }

        fun isFloating(): Boolean {
            return TFLAGSfloating
        }

        fun isUnsigned(): Boolean {
            return TFLAGSunsigned
        }

        fun isReal(): Boolean {
            return TFLAGSreal
        }

        fun isImaginary(): Boolean {
            return TFLAGSimaginary
        }

        fun isComplex(): Boolean {
            return TFLAGScomplex
        }

    }


    open val mods: Mods = Mods()
    //shared immutable should not be allowed
//    var vtinfo: TypeInfoDeclaration? = null     // TypeInfo object for this Type

    /**
     * cached values
     */
    var cto: DType? = null       // MODconst                 ? naked version of this type : const version
    var ito: DType? = null       // MODimmutable             ? naked version of this type : immutable version
    var sto: DType? = null       // MODshared                ? naked version of this type : shared mutable version
    var scto: DType? = null      // MODshared | MODconst     ? naked version of this type : shared const version
    var wto: DType? = null       // MODwild                  ? naked version of this type : wild version
    var wcto: DType? = null      // MODwildconst             ? naked version of this type : wild const version
    var swto: DType? = null      // MODshared | MODwild      ? naked version of this type : shared wild version
    var swcto: DType? = null     // MODshared | MODwildconst ? naked version of this type : shared wild const version

    var pto: DType? = null       // merged pointer to this type
    var rto: DType? = null       // reference to this type
    var arrayof: DType? = null   // array of this type


    open fun implicitlyConvertibleTo(to: DType): Match {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    open fun isAssignable(to: DType): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    open fun toBaseType(): DType {
        return this
    }

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }

    abstract fun toText(): String
    open fun isTypeBasic(): TypeBasic? {
        return null
    }

    abstract val typeMembersProvider: TypeMembers

    /************************************
     * Apply modifiers to existing type.
     */
//    fun castMod(MODconst: Boolean? = null, MODimmutable: Boolean? = null, MODshared: Boolean? = null, MODwild: Boolean? = null, MODwildconst: Boolean? = null, MODmutable: Boolean? = null): Type {
//        val t: Type = this
//
//        return t
//    }
//
//    /**
//     * this family of functions change the type, and do not create a copy
//     */
//    abstract fun constOf(): DType
//    abstract fun unSharedOf(): DType
//    abstract fun mutableOf(): DType
//    abstract fun wildOf(): DType
//    abstract fun wildConstOf(): DType
//    abstract fun sharedOf(): DType
//    abstract fun sharedConstOf(): DType
//    abstract fun sharedWildOf(): DType
//    abstract fun sharedWildConstOf(): DType
//    abstract fun immutableOf(): DType
    //initial type handling will  not handle const/mutable etc.
}
