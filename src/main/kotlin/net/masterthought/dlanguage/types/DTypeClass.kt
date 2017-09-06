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
        var m = constConv(to)
        if (m > MATCH.nomatch)
            return m

//        val cdto = to.isClassHandle()
        val cdto = (to as? DTypeClass)?.interfaceOrClass
        if (cdto != null) {//todo when is this null? with interface declarations?
            //printf("TypeClass::implicitConvTo(to = '%s') %s, isbase = %d %d\n", to->toChars(), toChars(), cdto->isBaseInfoComplete(), sym->isBaseInfoComplete());
            if ((to as DTypeClass).isBaseOf(this).first && MODimplicitConv(mods, to.mods)) {
                //printf("'to' is base\n");
                return MATCH.convert
            }
        }

        m = MATCH.nomatch
//        if (interfaceOrClass.aliasthis && !(att.RECtracing)) {
//            att = (att.RECtracing) as AliasThisRec
//            m = aliasThisOf().implicitlyConvertibleTo(to)
//            att = LINK.d(AliasThisRec)
//            att and RECtracing.inv()
//        }
        //todo handle alias this.
        return m
    }

    //todo this needs testing
    fun isBaseOf(`class`: DTypeClass): Pair<Boolean, Int> {
        var cd: DTypeClass? = `class`
        //printf("ClassDeclaration.isBaseOf(this = '%s', cd = '%s')\n", toChars(), cd.toChars());
        while (cd != null) {
            /* cd.baseClass might not be set if cd is forward referenced.
             */
            if (cd.baseClass() == null && !cd.isInterfaceDeclaration()) {
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
        return interfaceOrClass.isInterface
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
