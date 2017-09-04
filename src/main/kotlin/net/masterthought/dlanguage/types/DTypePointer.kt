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
        if(equals(to))
            return Match.exact
        if (next.ty == ENUMTY.Tfunction)
        {
            if (to.ty == ENUMTY.Tpointer)
            {
                val tp: DTypePointer = to as DTypePointer
                if (tp.next.ty == ENUMTY.Tfunction)
                {
                    if (next.equals(tp.next))
                        return MATCH.constant

                    if (next.covariant(tp.next) == 1)
                    {
                        val tret: DType = (this.next as DTypeNext).next
                        val toret: DType = (tp.next as DTypeNext).next
                        if (tret.ty == ENUMTY.Tclass && toret.ty == ENUMTY.Tclass)
                        {
                            /* Bugzilla 10219: Check covariant interface return with offset tweaking.
                             * interface I {}
                             * class C : Object, I {}
                             * I function() dg = function C() {}    // should be error
                             */
                            val offset = 0
                            if ((toret as DTypeClass).isBaseOf(tret as DTypeClass).first && toret.isBaseOf(tret).second != 0)
                                return MATCH.nomatch
                        }
                        return MATCH.convert
                    }
                }
                else if (tp.next.ty == ENUMTY.Tvoid)
                {
                    // Allow conversions to void*
                    return MATCH.convert
                }
            }
            return MATCH.nomatch
        }
        else if (to.ty == ENUMTY.Tpointer)
        {
            val tp: DTypePointer = to as DTypePointer

            if (!MODimplicitConv(next.mods, tp.next.mods))
                return MATCH.nomatch // not const-compatible

            /* Alloc conversion to void*
             */
            if (next.ty != ENUMTY.Tvoid && tp.next.ty == ENUMTY.Tvoid)
            {
                return MATCH.convert
            }

            var m: MATCH = next.constConv(tp.next)
            if (m > MATCH.nomatch)
            {
                if (m == MATCH.exact && mods != to.mods)
                    m = MATCH.constant
                return m
            }
        }
        return MATCH.nomatch
    }

    override fun constConv(to: DType): MATCH {
        return if (next.ty === ENUMTY.Tfunction) {
            if (next == (to as DTypeNext).next)
                super.constConv(to)//todo this wrong
            else
                MATCH.nomatch
        } else super.constConv(to)
    }
}
