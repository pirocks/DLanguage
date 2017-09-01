package net.masterthought.dlanguage.types

//
//
//class TypeReturn : TypeQualified() {
//    override fun implicitlyConvertibleTo(to: Type): Match {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//
//}
//
//
//class TypeTypeof : TypeQualified() {
//    override fun implicitlyConvertibleTo(to: Type): Match {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//
//}
//
//
//class TypeInstance : TypeQualified() {
//    override fun implicitlyConvertibleTo(to: Type): Match {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//}
//
//class TypeIdentifier : TypeQualified() {
//    override fun implicitlyConvertibleTo(to: Type): Match {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//}
//
//abstract class TypeQualified : Type()
//
class FunctionMods(typeNext: DTypeNext, MODconst: Boolean = false, MODimmutable: Boolean = false, MODshared: Boolean = false, MODwild: Boolean = false) : NextMods(typeNext, MODconst, MODimmutable, MODshared, MODwild) {
    override fun applyTransitiveConst() {
        return
    }
}


open class DTypeFunction(open val returnType: DType, open val parameters: List<Pair<DType, String>>, override val ty: TY, val varargs: Int) : DTypeNext(returnType, ty) {

    override val mods: NextMods = FunctionMods(this)

    var isnothrow: Boolean = null             // true: nothrow
    var isnogc: Boolean = null                // true: is @nogc
    var isproperty: Boolean = null            // can be called without parentheses
    var isref: Boolean = null                 // true: returns a reference
    var isreturn: Boolean = null              // true: 'this' is returned by ref
    var isscope: Boolean = null               // true: 'this' is scope


    override val typeMembersProvider: TypeMembers
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun toText(): String {
        val modsString: String = mods.toText()
        val mainString = returnType.toText() + " function" + "(" + java.lang.String.join(",", parameters.map { it.first.toText() }) + ")"
        if (!modsString.equals("")) {
            return modsString + "(" + mainString + ")"
        }
        return mainString
    }

    override fun implicitlyConvertibleTo(to: DType): Match {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}


class DTypeParameter(val stc: DType.DTypeStorageClass, val isDefualt: Boolean, val name: String, val type: DType) {

    companion object{
        fun dim(parameters: List<DTypeParameter>): Int {
            var nargs = 0;

            fun dimDg(n: Int, p: DTypeParameter): Int {
                ++nargs;
                return 0;
            }

            _foreach(parameters, ::dimDg);
            return nargs;
        }

        /***************************************
         * Expands tuples in args in depth first order. Calls
         * dg(void *ctx, size_t argidx, Parameter *arg) for each Parameter.
         * If dg returns !=0, stops and returns that value else returns 0.
         * Use this function to avoid the O(N + N^2/2) complexity of
         * calculating dim and calling N times getNth.
         */
        fun _foreach(parameters: List<DTypeParameter>, dg: (Int, DTypeParameter) -> Int): Int {
            var result = 0;
            for (i in 0..parameters.size) {
                val p: DTypeParameter = (parameters)[i];
                val t: DType = p.type.toBasetype();

                if (t.ty == ENUMTY.Ttuple) {
                    val tu = t as DTypeTuple;
                    result = _foreach(tu.arguments, dg);//todo there are going to be lots of bugs in this function in general
                } else
                    result = dg(i + 1, p);

                if (result != 0)
                    break;
            }
            return result;
        }

        fun getNth( parameters: List<DTypeParameter>, nth: Int) : DTypeParameter?
        {
            var param: DTypeParameter? = null;

            fun getNthParamDg(n: Int,  p: DTypeParameter) : Int
            {
                if (n == nth)
                {
                    param = p;
                    return 1;
                }
                return 0;
            }

            _foreach(parameters, ::getNthParamDg);
            return param
        }

    }
}
