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
class FunctionMods(typeNext: DTypeNext, MODconst: Boolean = false, MODimmutable: Boolean = false, MODshared: Boolean = false, MODwild: Boolean = false) : NextMods(typeNext, MODconst, MODimmutable, MODshared, MODwild) {
    override fun applyTransitiveConst() {
        return
    }
}


open class DTypeFunction(open val returnType: DType, open val parameters: List<Pair<DType, String>>, override val ty: TY) : DTypeNext(returnType, ty) {

    override val mods: NextMods = FunctionMods(this)

    override val typeMembersProvider: TypeMembers
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun toText(): String {
        val modsString: String = mods.toText()
        val mainString = returnType.toText() + " function" + "(" + java.lang.String.join(",", parameters.map { it.first.toText() }) + ")"
        if (!modsString.equals("")) {
            return modsString + "(" + mainString + ")"
        }
        return mainString
    }

    override fun implicitlyConvertibleTo(to: DType): Match {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
