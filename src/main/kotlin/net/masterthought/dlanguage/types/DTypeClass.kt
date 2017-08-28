package net.masterthought.dlanguage.types

import net.masterthought.dlanguage.utils.InterfaceOrClass

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
class DTypeClass(val interfaceOrClass: InterfaceOrClass) : DType(ENUMTY.Tclass) {


    override val typeMembersProvider: TypeMembers
        get() {
            return ClassTypeMembers(interfaceOrClass)
        }


    override fun toText(): String {
        return interfaceOrClass.name
    }

    override fun implicitlyConvertibleTo(to: DType): Match {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
