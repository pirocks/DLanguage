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

val basicTypeIndex = mutableMapOf<String, BasicDType>()


val tvoid: BasicDType = BasicDType(ENUMTY.Tvoid, "void", 1, "void")
val tbyte: BasicDType = BasicDType(ENUMTY.Tint8, "int8", 1, "byte")
val tubyte: BasicDType = BasicDType(ENUMTY.Tuns8, "uns8", 1, "ubtye")
val tshort: BasicDType = BasicDType(ENUMTY.Tint16, "int16", 2, "short")
val tushort: BasicDType = BasicDType(ENUMTY.Tuns16, "uns16", 2, "ushort")
val tint: BasicDType = BasicDType(ENUMTY.Tint32, "int32", 4, "int")
val tunit: BasicDType = BasicDType(ENUMTY.Tuns32, "uns32", 4, "uint")
val tlong: BasicDType = BasicDType(ENUMTY.Tint64, "int64", 8, "long")
val tulong: BasicDType = BasicDType(ENUMTY.Tuns64, "uns64", 8, "ulong")
val tcent: BasicDType = BasicDType(ENUMTY.Tint128, "int128", 16, "cent")
val tucent: BasicDType = BasicDType(ENUMTY.Tuns128, "uns128", 16, "ucent")
//public val tfloat32: BasicDType = BasicDType("float32",4)
//public val tfloat64: BasicDType = BasicDType("float64",8)
//public val tfloat80: BasicDType = BasicDType("float80",10)
//public val timaginary32: BasicDType = BasicDType("imaginary32",4)
//public val timaginary64: BasicDType = BasicDType("imaginary64",8)
//public val timaginary80: BasicDType = BasicDType("imaginary80",10)
//public val tcomplex32: BasicDType = BasicDType("complex32",8)
//public val tcomplex64: BasicDType = BasicDType("complex64",16)
//public val tcomplex80: BasicDType = BasicDType("complex80",20)
//public val tbool: BasicDType = BasicDType("bool",1)
val tchar: BasicDType = BasicDType(ENUMTY.Tchar, "char", 1, "char")
val twchar: BasicDType = BasicDType(ENUMTY.Twchar, "wchar", 2, "wchar")
val tdchar: BasicDType = BasicDType(ENUMTY.Tdchar, "dchar", 4, "dchar")
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
////todo check transative const
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
abstract class DTypeNext(val next: DType, override val ty: TY) : DType(ty)

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
abstract class DType(open val ty: TY) {
    //shared immutable should not be allowed
    var MODconst: Boolean = false
    var MODimmutable: Boolean = false
    var MODshared: Boolean = false
    var MODwild: Boolean = false
    var MODmutable: Boolean = false//not really used
    var MODwildconst: Boolean = false
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
//    abstract fun constOf(): Type
//
//    abstract fun unSharedOf(): Type
//    abstract fun mutableOf(): Type
//    abstract fun wildOf(): Type
//    abstract fun wildConstOf(): Type
//    abstract fun sharedOf(): Type
//    abstract fun sharedConstOf(): Type
//    abstract fun sharedWildOf(): Type
//    abstract fun sharedWildConstOf(): Type
//    abstract fun immutableOf(): Type
    //initial type handling will  not handle const/mutable etc.
    open fun isintegral(): Boolean {
        return false
    }

    open fun isfloating(): Boolean {
        return false
    }

    open fun isreal(): Boolean {
        return false
    }

    open fun isImaginary(): Boolean {
        return false
    }

    open fun isComplex(): Boolean {
        return false
    }

    open fun isScalar(): Boolean {
        return false
    }

    open fun isUnsigned(): Boolean {
        return false
    }

    open fun isScope(): Boolean {
        return false
    }

    open fun isString(): Boolean {
        return false
    }

    fun isConst(): Boolean {
        return MODconst
    }

    fun isImmutable(): Boolean {
        return MODimmutable
    }

    fun isMutable(): Boolean {
        return MODmutable
    }

    fun isShared(): Boolean {
        return MODshared
    }

    fun isSharedConst(): Boolean {
        return (MODshared || MODconst) && (!MODwild) && (!MODimmutable) && (!MODmutable) && (!MODwildconst)
    }

    fun isWild(): Boolean {
        return MODwild
    }

    fun isWildConst(): Boolean {
        return MODwildconst
    }

    fun isSharedWild(): Boolean {
        return (MODshared || MODwild) && (!MODconst) && (!MODimmutable) && (!MODmutable) && (!MODwildconst)
    }

    fun isNaked(): Boolean {
        return MODconst || MODimmutable || MODmutable || MODshared || MODwild || MODwildconst
    }
}
