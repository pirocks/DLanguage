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
typealias TY = ENUMTY;
typealias TypeBasic = BasicDType;
typealias TypeVector = DTypeVector;

val basicTypeIndex = mutableMapOf<String, BasicDType>()

class BasicDType : DType {
    val compilerName: String
    val size: Int
    val actualName: String

    constructor(ty : TY, compilerName: String, size: Int, actualName: String) : super(ty) {
        this.compilerName = compilerName
        this.size = size
        this.actualName = actualName
        basicTypeIndex.put(actualName, this)
    }

    override fun toText(): String {
        return actualName
    }


    override fun implicitlyConvertibleTo(to: DType): Match {
        if (this == to)
            return Match.exact;

        if (ty == to.ty)
        {
            return Match.exact;
            /*if (mod == to.mod)
                return MATCHexact;
            else if (MODimplicitConv(mod, to.mod))
                return MATCHconst;
            else if (!((mod ^ to.mod) & MODshared)) // for wild matching
            return MATCHconst;
            else
            return MATCHconvert;*/
        }

        if (ty == ENUMTY.Tvoid || to.ty == ENUMTY.Tvoid)
            return Match.nomatch;
        if (to.ty == ENUMTY.Tbool)
            return Match.nomatch;

        val tob: TypeBasic;
        if (to.ty == ENUMTY.Tvector && to.deco)
        {
            val tv : TypeVector = to as TypeVector;
            tob = tv.elementType();
        }
        else {
            if(to !is TypeBasic)
                return Match.nomatch
            tob = to
        }
        /**
         * tl'dr version of this is:
         * integers can't be converted to imaginary or complex
         * can't convert to a smaller size type
         * floats can't be implicitly converted to integers
         * complex can't be converted to non-complex
         * real con't be converted to imaginary and vice versa
         */
        //        if (flags & TFLAGSintegral)
//        {
////             Disallow implicit conversion of integers to imaginary or complex
//            if (tob.flags & (TFLAGSimaginary | TFLAGScomplex))
//            return MATCHnomatch;
//
////             If converting from integral to integral
//            if (tob.flags & TFLAGSintegral)
//            {
//                d_uns64 sz = size(Loc());
//                d_uns64 tosz = tob.size(Loc());
//
//                /* Can't convert to smaller size
//                 */
//                if (sz > tosz)
//                    return MATCHnomatch;
//                /* Can't change sign if same size
//                 */
//                if (sz == tosz && (flags ^ tob->flags) & TFLAGSunsigned)
//                    return MATCHnomatch;
//            }
//        }
//        else if (flags & TFLAGSfloating)
//        {
////             Disallow implicit conversion of floating point to integer
//            if (tob.flags & TFLAGSintegral)
//            return MATCHnomatch;
//
//            assert(tob.flags & TFLAGSfloating || to.ty == Tvector);
//
////             Disallow implicit conversion from complex to non-complex
//            if (flags & TFLAGScomplex && !(tob.flags & TFLAGScomplex))
//            return MATCHnomatch;
//
////             Disallow implicit conversion of real or imaginary to complex
//            if (flags & (TFLAGSreal | TFLAGSimaginary) && tob.flags & TFLAGScomplex)
//            return MATCHnomatch;
//
////             Disallow implicit conversion to-from real and imaginary
//            if ((flags & (TFLAGSreal | TFLAGSimaginary)) != (tob.flags & (TFLAGSreal | TFLAGSimaginary)))
//            return MATCHnomatch;
//        }
//        return MATCHconvert;
    }

//    override fun implicitlyConvertibleTo(to: DType): Match {
//        if(to == this)
//            return Match.exact
//        return Match.nomatch
//    }


}


val tvoid: BasicDType = BasicDType("void", 1, "void")
val tbyte: BasicDType = BasicDType("int8", 1, "byte")
val tubyte: BasicDType = BasicDType("uns8", 1, "ubtye")
val tshort: BasicDType = BasicDType("int16", 2, "short")
val tushort: BasicDType = BasicDType("uns16", 2, "ushort")
val tint: BasicDType = BasicDType("int32", 4, "int")
val tunit: BasicDType = BasicDType("uns32", 4, "uint")
val tlong: BasicDType = BasicDType("int64", 8, "long")
val tulong: BasicDType = BasicDType("uns64", 8, "ulong")
val tcent: BasicDType = BasicDType("int128", 16, "cent")
val tucent: BasicDType = BasicDType("uns128", 16, "ucent")
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
val tchar: BasicDType = BasicDType("char", 1, "char")
val twchar: BasicDType = BasicDType("wchar", 2, "wchar")
val tdchar: BasicDType = BasicDType("dchar", 4, "dchar")
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
//
//abstract class ResolvableType {
//    abstract fun resolve(): Type
//    abstract fun actuallyIsType(): Boolean
//    //todo
//}
//
abstract class DType(val ty : TY) {
    //shared immutable should not be allowed
//    var MODconst: Boolean;
//    var MODimmutable: Boolean;
//    var MODshared: Boolean;
//    var MODwild: Boolean;
//    var MODwildconst: Boolean;
//    var MODmutable: Boolean;
//    var vtinfo: TypeInfoDeclaration? = null     // TypeInfo object for this Type


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
        return null;
    }

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
}
//
//class TypeNull : Type() {
//    override fun implicitlyConvertibleTo(to: Type): Match {
//        val match = super.implicitlyConvertibleTo(to)
//        if (match != Match.nomatch)
//            return match
//
//        val tb: Type = to.toBaseType()
//        if (tb is TypeNull || tb is TypePointer || tb is TypeArray /*|| tb is TypeAarray todo*/ || tb is TypeClass || tb is TypeDelegate)
//            return Match.constant
//        return Match.nomatch
//    }
//}
//
//
//class DTypeSlice : DTypeNext(null) {
//    override fun implicitlyConvertibleTo(to: DType): Match {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//}
//
//
//class TypeTuple : Type() {
//    override fun implicitlyConvertibleTo(to: Type): Match {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//}
//
//
class DTypeClass : DType() {
    override fun toText(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun implicitlyConvertibleTo(to: DType): Match {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
//
//
//class TypeEnum : Type() {
//    val sym: EnumDeclaration
//
//    constructor(sym: EnumDeclaration) : super() {
//        this.sym = sym
//    }
//
//
//    override fun implicitlyConvertibleTo(to: Type): Match {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//
//    override fun toBaseType(): Type {
//        if (sym.enumBody?.enumMembers != null && !sym.enumBody!!.enumMembers.isEmpty() && sym.memtype != null)
//            return this
//        val tb = sym.getMemtype().toBaseType()
//        return tb.castMod(mod)         // retain modifier bits from 'this'
//    }
//}
//
//
//class TypeStruct : Type() {
//    override fun implicitlyConvertibleTo(to: Type): Match {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//}
//
//
//class TypeReturn : TypeQualified() {
//    override fun implicitlyConvertibleTo(to: Type): Match {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//
//}
//
//
//class TypeTypeof : TypeQualified() {
//    override fun implicitlyConvertibleTo(to: Type): Match {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//
//}
//
//
//class TypeInstance : TypeQualified() {
//    override fun implicitlyConvertibleTo(to: Type): Match {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//}
//
//class TypeIdentifier : TypeQualified() {
//    override fun implicitlyConvertibleTo(to: Type): Match {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//}
//
//abstract class TypeQualified : Type()
//
class DTypeFunction(val returnType: DType, val parameters: List<Pair<DType, String>>) : DTypeNext(returnType) {
    override fun toText(): String {
        return returnType.toText() + " function" + "(" + java.lang.String.join(",", parameters.map { it.first.toText() }) + ")"
    }

    override fun implicitlyConvertibleTo(to: DType): Match {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

/**
 * in theory just a type function
 */
class DTypeDelegate(val returnType: DType, val parameters: List<Pair<DType, String>>) : DTypeNext(returnType) {
    override fun toText(): String {
        return returnType.toText() + " delegate" + "(" + java.lang.String.join(",", parameters.map { it.first.toText() }) + ")"
    }

    override fun implicitlyConvertibleTo(to: DType): Match {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}


class DTypeReference(val next_: DType) : DTypeNext(next_) {
    override fun toText(): String {
        return "ref " + next.toText()
    }

    override fun implicitlyConvertibleTo(to: DType): Match {



        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}


class DTypePointer(val next_: DType) : DTypeNext(next_) {
    override fun toText(): String {
        return next_.toText() + "*"
    }


    override fun implicitlyConvertibleTo(to: DType): Match {
         /**
         * tl;dr
         * if equals match duh
          * functions can be converted to void* pointers(convert)
          * functions can be converted to function pointers(match const)
          * covariant stuff I don;t understand
          * uses an extra function in comparing pointer to pointer
         */
        /**
        *
         if (equals(to))
            return MATCHexact;

        if (next.ty == Tfunction)
        {
            if (to.ty == Tpointer)
            {
                TypePointer tp = cast(TypePointer)to;
                if (tp.next.ty == Tfunction)
                {
                    if (next.equals(tp.next))
                        return MATCHconst;

                    if (next.covariant(tp.next) == 1)
                    {
                        Type tret = this.next.nextOf();
                        Type toret = tp.next.nextOf();
                        if (tret.ty == Tclass && toret.ty == Tclass)
                        {
                            /* Bugzilla 10219: Check covariant interface return with offset tweaking.
                             * interface I {}
                             * class C : Object, I {}
                             * I function() dg = function C() {}    // should be error
                             */
                            int offset = 0;
                            if (toret.isBaseOf(tret, &offset) && offset != 0)
                                return MATCHnomatch;
                        }
                        return MATCHconvert;
                    }
                }
                else if (tp.next.ty == Tvoid)
                {
                    // Allow conversions to void*
                    return MATCHconvert;
                }
            }
            return MATCHnomatch;
        }
        else if (to.ty == Tpointer)
        {
            TypePointer tp = cast(TypePointer)to;
            assert(tp.next);

            if (!MODimplicitConv(next.mod, tp.next.mod))
                return MATCHnomatch; // not const-compatible

            /* Alloc conversion to void*
             */
            if (next.ty != Tvoid && tp.next.ty == Tvoid)
            {
                return MATCHconvert;
            }

            MATCH m = next.constConv(tp.next);
            if (m > MATCHnomatch)
            {
                if (m == MATCHexact && mod != to.mod)
                    m = MATCHconst;
                return m;
            }
        }
        return MATCHnomatch;
         */

        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}


abstract class DTypeArray(val next_: DType) : DTypeNext(next_) {


    override fun implicitlyConvertibleTo(to: DType): Match {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}


class DTypeDArray(val next__: DType) : DTypeArray(next__) {
    override fun toText(): String {
        return next.toText() + "[]"
    }

    override fun implicitlyConvertibleTo(to: DType): Match {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}


/**
 * note that it is impossible for us to tell the length of a static array in d, because it could be platform/compiler flag dependant. This means that all static arrays are effectively the same type.
 */
class DTypeSArray(val next__: DType) : DTypeArray(next__) {
    override fun toText(): String {
        return next__.toText() + "[" + "<len>" + "]"
    }

    override fun implicitlyConvertibleTo(to: DType): Match {
        /**
         * tl:dr
         * if converting to a dynamic aarry always allow for converdsion if void
         */

        /**
         //printf("TypeSArray::implicitConvTo(to = %s) this = %s\n", to->toChars(), toChars());
        if (to.ty == Tarray)
        {
            TypeDArray ta = cast(TypeDArray)to;
            if (!MODimplicitConv(next.mod, ta.next.mod))
                return MATCHnomatch;

            /* Allow conversion to void[]
             */
            if (ta.next.ty == Tvoid)
            {
                return MATCHconvert;
            }

            MATCH m = next.constConv(ta.next);
            if (m > MATCHnomatch)
            {
                return MATCHconvert;
            }
            return MATCHnomatch;
        }
        if (to.ty == Tsarray)
        {
            if (this == to)
                return MATCHexact;

            TypeSArray tsa = cast(TypeSArray)to;
            if (dim.equals(tsa.dim))
            {
                /* Since static arrays are value types, allow
                 * conversions from const elements to non-const
                 * ones, just like we allow conversion from const int
                 * to int.
                 */
                MATCH m = next.implicitConvTo(tsa.next);
                if (m >= MATCHconst)
                {
                    if (mod != to.mod)
                        m = MATCHconst;
                    return m;
                }
            }
        }
        return MATCHnomatch;
         */
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

class DTypeAArray(val value: DType, val key: DType) : DTypeArray(value) {
    override fun toText(): String {
        return value.toText() + "[" + key.toText() + "]"
    }

    override fun implicitlyConvertibleTo(to: DType): Match {
        /**
         * equals return ...
         * use super of Type, not TypeNext, and do a bunch of constConv
         */

        /**
         * //printf("TypeAArray::implicitConvTo(to = %s) this = %s\n", to->toChars(), toChars());
        if (equals(to))
            return MATCHexact;

        if (to.ty == Taarray)
        {
            TypeAArray ta = cast(TypeAArray)to;

            if (!MODimplicitConv(next.mod, ta.next.mod))
                return MATCHnomatch; // not const-compatible

            if (!MODimplicitConv(index.mod, ta.index.mod))
                return MATCHnomatch; // not const-compatible

            MATCH m = next.constConv(ta.next);
            MATCH mi = index.constConv(ta.index);
            if (m > MATCHnomatch && mi > MATCHnomatch)
            {
                return MODimplicitConv(mod, to.mod) ? MATCHconst : MATCHnomatch;
            }
        }
        return Type.implicitConvTo(to);
         */
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
//
class DTypeVector(val ty_: TY) : DType(ty_) {
    override fun toText(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    val baseType: DType? = null

    override fun implicitlyConvertibleTo(to: DType): Match {
        if (this == to) {//todo override .equals
            return Match.exact
        }
        if (to is TypeVector) {
            return Match.convert
        }
        return Match.nomatch
    }

    fun elementType(): TypeBasic {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
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
abstract class DTypeNext(val next: DType) : DType()
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
