package net.masterthought.dlanguage.types

class DTypeAArray(val value: DType, val key: DType) : DTypeArray(value, ENUMTY.Taarray) {
    override val typeMembersProvider: TypeMembers
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

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
