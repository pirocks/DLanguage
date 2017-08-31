package net.masterthought.dlanguage.types


fun MODimplicitConv(modfrom: Mods, modto: Mods): Boolean {
    if (modfrom == modto)
        return true
    if ((modfrom.isNaked() && modto.isConst()) ||
        (modfrom.isWild() && modfrom.isConst()) ||
        (modfrom.isWild() && modto.isWildConst()) ||
        (modfrom.isWildConst() && modto.isConst()))
        return modfrom.isShared() == modto.isShared()
    if (modfrom.isImmutable() && modto.isConst() ||
        (modfrom.isImmutable() && modto.isWildConst()))
        return true
    return false

}

typealias MATCH = Match//todo


class BasicDType : DType {
    override val typeMembersProvider: TypeMembers
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
    val compilerName: String
    val size: Int
    val actualName: String

    constructor(ty: TY, compilerName: String, size: Int, actualName: String, flags: Flags) : super(ty, flags) {
        this.compilerName = compilerName
        this.size = size
        this.actualName = actualName
        basicTypeIndex.register(actualName, this)
    }

    fun createCopy(): BasicDType {
        return BasicDType(ty, compilerName, size, actualName, flags)
    }

    override fun toText(): String {
        val modsString: String = mods.toText()
        if (!modsString.equals("")) {
            return modsString + "(" + actualName + ")"
        }
        return actualName
    }


    override fun implicitlyConvertibleTo(to: DType): Match {
        if (this == to)
            return Match.exact

        if (ty == to.ty) {
            if (this.mods.hasSameMods(to))
                return Match.exact
            else if (MODimplicitConv(mods, to.mods))
                return Match.constant
            else if (mods.isShared() && !to.mods.isShared()) // for wild matching
                return Match.constant
            else if ((!mods.isShared()) && to.mods.isShared()) // for wild matching
                return Match.constant
            else
                return Match.convert
        }

        if (ty == ENUMTY.Tvoid || to.ty == ENUMTY.Tvoid)
            return Match.nomatch
        if (to.ty == ENUMTY.Tbool)
            return Match.nomatch

        val tob: TypeBasic
        if (to.ty == ENUMTY.Tvector /*&& to.deco todo*/) {
            val tv: TypeVector = to as TypeVector
            tob = tv.elementType()
        } else {
            if (to !is TypeBasic)
                return Match.nomatch
            tob = to
        }
        /**
         * tl'dr version of this is:
         * integers can't be converted to imaginary or complex
         * can't convert to a smaller size type
         * floats can't be implicitly converted to integers
         * complex can't be converted to non-complex
         * real con't be converted to imaginary and vice versa
         */
        if (this.flags.TFLAGSintegral) {
//             Disallow implicit conversion of integers to imaginary or complex
            if (tob.flags.TFLAGSimaginary || tob.flags.TFLAGScomplex)
                return Match.nomatch

//             If converting from integral to integral
            if (tob.flags.TFLAGSintegral) {
                val sz = this.size
                val tosz = tob.size

                /* Can't convert to smaller size
                 */
                if (sz > tosz)
                    return MATCH.nomatch
                /* Can't change sign if same size
                 */
//                if (sz == tosz && (!tob.flags.TFLAGSunsigned) == this.flags.TFLAGSunsigned)
//                    return MATCH.nomatch;
            }
        } else if (this.flags.TFLAGSfloating) {
//             Disallow implicit conversion of floating point to integer
            if (tob.flags.TFLAGSintegral)
                return MATCH.nomatch

//            assert(tob.flags & TFLAGSfloating || to.ty == Tvector);

//             Disallow implicit conversion from complex to non-complex
            if (flags.TFLAGScomplex && !(tob.flags.TFLAGScomplex))
                return MATCH.nomatch

//             Disallow implicit conversion of real or imaginary to complex
            if ((flags.TFLAGSreal || flags.TFLAGSimaginary) && tob.flags.TFLAGScomplex)
                return MATCH.nomatch

//             Disallow implicit conversion to-from real and imaginary
            if ((flags.TFLAGSimaginary && tob.flags.TFLAGSreal) || (flags.TFLAGSreal && tob.flags.TFLAGSimaginary))
                return MATCH.nomatch
        }
        return MATCH.convert
    }

//    override fun implicitlyConvertibleTo(to: DType): Match {
//        if(to == this)
//            return Match.exact
//        return Match.nomatch
//    }


}
