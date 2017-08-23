package net.masterthought.dlanguage.types

val basicTypeIndex = mutableMapOf<String, BasicDType>()

class BasicDType : DType {
    val compilerName: String
    val size: Int
    val actualName: String

    constructor(compilerName: String, size: Int, actualName: String) : super() {
        this.compilerName = compilerName
        this.size = size
        this.actualName = actualName
//        assert(!basicTypeIndex.containsKey(actualName))
        basicTypeIndex.put(actualName, this)
    }

    override fun toText(): String {
        return actualName
    }


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
abstract class DType {
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

    open fun implicitlyAssignableTo(to: DType): Match {
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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

class DTypeAArray(val value: DType, val key: DType) : DTypeArray(value) {
    override fun toText(): String {
        return value.toText() + "[" + key.toText() + "]"
    }

    override fun implicitlyConvertibleTo(to: DType): Match {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
//
//class TypeVector : Type() {
//    val baseType: Type? = null
//
//    override fun implicitlyConvertibleTo(to: Type): Match {
//        if (this == to) {//todo override .equals
//            return Match.exact
//        }
//        if (to is TypeVector) {
//            return Match.convert
//        }
//        return Match.nomatch
//    }
//}
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
