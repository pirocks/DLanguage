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

    /*******************************
     * Covariant means that 'this' can substitute for 't',
     * i.e. a pure function is a match for an impure type.
     * Returns:
     *      0       types are distinct
     *      1       this is covariant with t
     *      2       arguments match as far as overloading goes,
     *              but types are not covariant
     *      3       cannot determine covariance because of forward references
     *      *pstc   STCxxxx which would make it covariant todo not implmented
     */
    fun covariant(t: DType): Int {
//        val stc: DTypeStorageClass = DTypeStorageClass();

        var inoutmismatch = false

        val t1: DTypeFunction
        val t2: DTypeFunction

        if (equals(t))
            return 1 // covariant

        if (ty != ENUMTY.Tfunction || t.ty != ENUMTY.Tfunction)
            return 0

        t1 = this as DTypeFunction
        t2 = t as DTypeFunction

        if (t1.varargs != t2.varargs)
            return 0

        if (t1.parameters.isNotEmpty() && t2.parameters.isNotEmpty()) {//check that this is correct
            val dim = DTypeParameter.dim(t1.parameters)
            if (dim != DTypeParameter.dim(t2.parameters))
                return 0

            for (i in 0..dim) {
                val fparam1: DTypeParameter = DTypeParameter.getNth(t1.parameters, i)!!
                val fparam2: DTypeParameter = DTypeParameter.getNth(t2.parameters, i)!!

                if (!fparam1.type.equals(fparam2.type)) {
                    return 0
                }
//                if ((fparam1.storageClass & sc) != (fparam2.storageClass & sc))
//                inoutmismatch = true;
                if (fparam1.stc.STCref == fparam2.stc.STCref && fparam1.stc.STCin == fparam2.stc.STCin && fparam1.stc.STCout == fparam2.stc.STCout && fparam1.stc.STClazy)
                    inoutmismatch = true
                // We can add scope, but not subtract it
                if (!(fparam1.stc.STCscope) && (fparam2.stc.STCscope))
                    inoutmismatch = true
                // We can subtract return, but not add it
                if ((fparam1.stc.STCreturn) && !(fparam2.stc.STCreturn))
                    inoutmismatch = true
            }
        } else if (t1.parameters != t2.parameters) {
            val dim1: Int
            if (t1.parameters == null)
                dim1 = 0
            else
                dim1 = t1.parameters.size
            val dim2: Int
            if (t2.parameters == null)
                dim2 = 0
            else
                dim2 = t2.parameters.size
            if (dim1 > 0 || dim2 > 0)
                return 0
        }

        // The argument lists match
        if (inoutmismatch)
            return 2
        if (t1.linkage != t2.linkage)
            return 2

//        {
        // Return types
        val t1n: DType = t1.next
        val t2n: DType = t2.next

        if (t1n == null || t2n == null) // happens with return type inference
            return 2

        if (t1n.equals(t2n))
            return Lcovariant(t1, t2)
        if (t1n.ty == ENUMTY.Tclass && t2n.ty == ENUMTY.Tclass) {
            /* If same class type, but t2n is const, then it's
             * covariant. Do this test first because it can work on
             * forward references.
             */
            if ((t1n as DTypeClass).interfaceOrClass == (t2n as DTypeClass).interfaceOrClass && MODimplicitConv(t1n.mods, t2n.mods))//todo no way this is correcy
                return Lcovariant(t1, t2)

            // If t1n is forward referenced:
//            val cd = t1n.interfaceOrClass
//            if (cd._scope)
//                cd.semantic(null);
//            return 3 // forward references
        }
        if (t1n.ty == ENUMTY.Tstruct && t2n.ty == ENUMTY.Tstruct) {
            if ((t1n as DTypeStruct).struct == (t2n as DTypeStruct).struct && MODimplicitConv(t1n.mods, t2n.mods))//this can't be correct either
                return Lcovariant(t1, t2)
        } else if (t1n.ty == t2n.ty && t1n.implicitlyConvertibleTo(t2n) != Match.nomatch)
            return Lcovariant(t1, t2)
        else if (t1n.ty == ENUMTY.Tnull && t1n.implicitlyConvertibleTo(t2n) != Match.nomatch && t1n.size() == t2n.size())
            return Lcovariant(t1, t2)
//        }
        return 2
    }

    open fun size(): Int {
        return -1
    }

    fun Lcovariant(t1: DTypeFunction, t2: DTypeFunction): Int {
        if (t1.isref != t2.isref)
            return 2

        // We can subtract 'return' from 'this', but cannot add it
        if (t1.isreturn && !t2.isreturn)
            return 2

        /* Can convert mutable to const
     */
        if (!MODimplicitConv(t2.mods, t1.mods)) {
            return 0
        }

        var notCompletelyCovariant = false
        /* Can convert pure to impure, nothrow to throw, and nogc to gc
     */
        if (t1.purity == PURE.impure && t2.purity != PURE.impure)
            notCompletelyCovariant = true

        if (!t1.isnothrow && t2.isnothrow)
            notCompletelyCovariant = true

        if (!t1.isnogc && t2.isnogc)
            notCompletelyCovariant = true

        /* Can convert safe/trusted to system
     */
//        if (t1.trust <= TRUSTsystem && t2.trust >= TRUSTtrusted) {
//             Should we infer trusted or safe? Go with safe.
//            stc | = STCsafe;
//        }

        if (notCompletelyCovariant) {
            return 2
        }
        return 1
    }

    open fun toBasetype(): DType {
        return this
    }

    /*******************************
     * Determine if converting 'this' to 'to' is an identity operation,
     * a conversion to const operation, or the types aren't the same.
     * Returns:
     * MATCHexact      'this' == 'to'
     * MATCHconst      'to' is const
     * MATCHnomatch    conversion to mutable or invariant
     */
    open fun constConv(to: DType): MATCH {
        if (equals(to))
            return MATCH.exact
        if (ty === to.ty && MODimplicitConv(mods, to.mods))
            return MATCH.constant
        else
            return MATCH.nomatch
    }


}
