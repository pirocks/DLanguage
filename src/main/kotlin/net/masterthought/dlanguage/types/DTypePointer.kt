package net.masterthought.dlanguage.types

class DTypePointer(val next_: DType) : DTypeNext(next_, ENUMTY.Tpointer) {
    override val typeMembersProvider: TypeMembers
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun toText(): String {
        val modsString: String = mods.toText()
        if (!modsString.equals("")) {
            return modsString + "(" + next_.toText() + "*" + ")"
        }
        return next_.toText() + "*"
    }


    override fun implicitlyConvertibleTo(to: DType): Match {
        /**
         * tl;dr
         * if equals match duh
         * functions can be converted to void* pointers(convert)
         * functions can be converted to function pointers(match const)
         * covariant stuff I don;t understand
         * uses an extra function in comparing pointer to pointer
         */
        /**
         *
        if (equals(to))
        return MATCHexact;

        if (next.ty == Tfunction)
        {
        if (to.ty == Tpointer)
        {
        TypePointer tp = cast(TypePointer)to;
        if (tp.next.ty == Tfunction)
        {
        if (next.equals(tp.next))
        return MATCHconst;

        if (next.covariant(tp.next) == 1)
        {
        Type tret = this.next.nextOf();
        Type toret = tp.next.nextOf();
        if (tret.ty == Tclass && toret.ty == Tclass)
        {
        /* Bugzilla 10219: Check covariant interface return with offset tweaking.
         * interface I {}
         * class C : Object, I {}
         * I function() dg = function C() {}    // should be error
        */
        int offset = 0;
        if (toret.isBaseOf(tret, &offset) && offset != 0)
        return MATCHnomatch;
        }
        return MATCHconvert;
        }
        }
        else if (tp.next.ty == Tvoid)
        {
        // Allow conversions to void*
        return MATCHconvert;
        }
        }
        return MATCHnomatch;
        }
        else if (to.ty == Tpointer)
        {
        TypePointer tp = cast(TypePointer)to;
        assert(tp.next);

        if (!MODimplicitConv(next.mod, tp.next.mod))
        return MATCHnomatch; // not const-compatible

        /* Alloc conversion to void*
        */
        if (next.ty != Tvoid && tp.next.ty == Tvoid)
        {
        return MATCHconvert;
        }

        MATCH m = next.constConv(tp.next);
        if (m > MATCHnomatch)
        {
        if (m == MATCHexact && mod != to.mod)
        m = MATCHconst;
        return m;
        }
        }
        return MATCHnomatch;
         */

        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
