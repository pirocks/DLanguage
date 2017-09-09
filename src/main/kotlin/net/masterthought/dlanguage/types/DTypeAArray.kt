package net.masterthought.dlanguage.types

class DTypeAArray(val value: DType, val key: DType) : DTypeArray(value, ENUMTY.Taarray) {
    override val typeMembersProvider: TypeMembers
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun toText(): String {
        return value.toText() + "[" + key.toText() + "]"
    }

    override fun implicitlyConvertibleTo(to: DType): Match {
        //printf("TypeAArray::implicitConvTo(to = %s) this = %s\n", to->toChars(), toChars());
        if (equals(to))
            return MATCH.exact
        if (to.ty == ENUMTY.Taarray) {
            val ta = to as DTypeAArray

            if (!MODimplicitConv(next.mods, ta.next.mods))
                return MATCH.nomatch // not const-compatible

            if (!MODimplicitConv(key.mods, ta.key.mods))
                return MATCH.nomatch // not const-compatible

            val m: MATCH = next.constConv(ta.next)
            val mi: MATCH = key.constConv(ta.key)
            if (m != MATCH.nomatch && mi != MATCH.nomatch) {
                if (MODimplicitConv(mods, to.mods))
                    return MATCH.constant
                else
                    return MATCH.nomatch
            }
        }
//        return DType.implicitConvTo(to);
        return Match.nomatch
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
