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
class DTypeClass(val interfaceOrClass: InterfaceOrClass) : DType(ENUMTY.Tclass, Flags()) {


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

    fun isBaseOf(`class`: DTypeClass): Pair<Boolean, Int> {
        var cd: DTypeClass? = `class`
        //printf("ClassDeclaration.isBaseOf(this = '%s', cd = '%s')\n", toChars(), cd.toChars());
        while (cd != null) {
            /* cd.baseClass might not be set if cd is forward referenced.
             */
            if (cd.baseClass() != null && !cd.isInterfaceDeclaration()) {
                throw UnableToDeduceTypeException()
//                    cd.error("base class is forward referenced by %s", toChars());
            }

            if (this == cd.baseClass())
                return Pair(true, 0)

            cd = cd.baseClass()
        }
        return Pair(false, 0)
    }

    fun isInterfaceDeclaration(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun baseClass(): DTypeClass? {
        val superType = interfaceOrClass.baseClassList?.baseClasss?.firstOrNull()?.type
        if (superType != null) {
            return from(superType, true, Mods()) as DTypeClass//todo cache etc...
        } else {
            return null
        }
    }
}
