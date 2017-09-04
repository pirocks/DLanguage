package net.masterthought.dlanguage.types

abstract class DTypeNext(val next: DType, override val ty: TY) : DType(ty, Flags()) {
    override val mods: NextMods = NextMods(this)

    override fun constConv(to: DType): MATCH {
        if (equals(to))
            return Match.exact

        if (!(ty === to.ty && MODimplicitConv(mods, to.mods)))
            return Match.nomatch

        val tn = (to as DTypeNext).next
        if (!(next.ty === tn.ty))
            return Match.nomatch

        var m: MATCH
        if (to.mods.isConst())
        // whole tail const conversion
        {
            // Recursive shared level check
            m = next.constConv(tn)
            if (m === Match.exact)
                m = Match.constant
        } else {
            //printf("\tnext => %s, to->next => %s\n", next->toChars(), tn->toChars());
            m = if (next.equals(tn)) Match.constant else Match.nomatch
        }
        return m
    }
}
