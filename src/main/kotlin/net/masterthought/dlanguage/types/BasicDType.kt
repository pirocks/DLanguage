package net.masterthought.dlanguage.types

class BasicDType : DType {
    override val typeMembersProvider: TypeMembers
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
    val compilerName: String
    val size: Int
    val actualName: String

    constructor(ty: TY, compilerName: String, size: Int, actualName: String) : super(ty) {
        this.compilerName = compilerName
        this.size = size
        this.actualName = actualName
        basicTypeIndex.put(actualName, this)
    }

    override fun toText(): String {
        return actualName
    }


    override fun implicitlyConvertibleTo(to: DType): Match {
        if (this == to)
            return Match.exact

        if (ty == to.ty) {
            return Match.exact
            /*if (mod == to.mod)
                return MATCHexact;
            else if (MODimplicitConv(mod, to.mod))
                return MATCHconst;
            else if (!((mod ^ to.mod) & MODshared)) // for wild matching
            return MATCHconst;
            else
            return MATCHconvert;*/
        }

        if (ty == ENUMTY.Tvoid || to.ty == ENUMTY.Tvoid)
            return Match.nomatch
        if (to.ty == ENUMTY.Tbool)
            return Match.nomatch

        val tob: TypeBasic
        if (to.ty == ENUMTY.Tvector/* && to.deco*/) {
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
        //        if (flags & TFLAGSintegral)
//        {
////             Disallow implicit conversion of integers to imaginary or complex
//            if (tob.flags & (TFLAGSimaginary | TFLAGScomplex))
//            return MATCHnomatch;
//
////             If converting from integral to integral
//            if (tob.flags & TFLAGSintegral)
//            {
//                d_uns64 sz = size(Loc());
//                d_uns64 tosz = tob.size(Loc());
//
//                /* Can't convert to smaller size
//                 */
//                if (sz > tosz)
//                    return MATCHnomatch;
//                /* Can't change sign if same size
//                 */
//                if (sz == tosz && (flags ^ tob->flags) & TFLAGSunsigned)
//                    return MATCHnomatch;
//            }
//        }
//        else if (flags & TFLAGSfloating)
//        {
////             Disallow implicit conversion of floating point to integer
//            if (tob.flags & TFLAGSintegral)
//            return MATCHnomatch;
//
//            assert(tob.flags & TFLAGSfloating || to.ty == Tvector);
//
////             Disallow implicit conversion from complex to non-complex
//            if (flags & TFLAGScomplex && !(tob.flags & TFLAGScomplex))
//            return MATCHnomatch;
//
////             Disallow implicit conversion of real or imaginary to complex
//            if (flags & (TFLAGSreal | TFLAGSimaginary) && tob.flags & TFLAGScomplex)
//            return MATCHnomatch;
//
////             Disallow implicit conversion to-from real and imaginary
//            if ((flags & (TFLAGSreal | TFLAGSimaginary)) != (tob.flags & (TFLAGSreal | TFLAGSimaginary)))
//            return MATCHnomatch;
//        }
//        return MATCHconvert;
        TODO()
    }

//    override fun implicitlyConvertibleTo(to: DType): Match {
//        if(to == this)
//            return Match.exact
//        return Match.nomatch
//    }


}
