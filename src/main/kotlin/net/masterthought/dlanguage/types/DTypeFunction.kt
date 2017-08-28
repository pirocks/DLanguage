package net.masterthought.dlanguage.types

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
open class DTypeFunction(open val returnType: DType, open val parameters: List<Pair<DType, String>>, override val ty: TY) : DTypeNext(returnType, ty) {
    override val typeMembersProvider: TypeMembers
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun toText(): String {
        return returnType.toText() + " function" + "(" + java.lang.String.join(",", parameters.map { it.first.toText() }) + ")"
    }

    override fun implicitlyConvertibleTo(to: DType): Match {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
