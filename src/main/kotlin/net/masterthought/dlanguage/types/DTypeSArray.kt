package net.masterthought.dlanguage.types

/**
 * note that it is impossible for us to tell the length of a static array in d, because it could be platform/compiler flag dependant. This means that all static arrays are effectively the same type.
 */
class DTypeSArray(val next__: DType) : DTypeArray(next__, ENUMTY.Tsarray) {
    override val typeMembersProvider: TypeMembers
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

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
