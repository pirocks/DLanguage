package net.masterthought.dlanguage.types

import net.masterthought.dlanguage.psi.DLanguageStructDeclaration

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
class DTypeStruct(val struct: DLanguageStructDeclaration) : DType(ENUMTY.Tstruct, Flags()) {

    override fun toText(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override val typeMembersProvider: TypeMembers
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun implicitlyConvertibleTo(to: DType): Match {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
