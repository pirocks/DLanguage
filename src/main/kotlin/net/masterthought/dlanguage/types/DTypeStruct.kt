package net.masterthought.dlanguage.types

import net.masterthought.dlanguage.psi.DLanguageStructDeclaration

//
//
//class TypeEnum : Type() {
//    val sym: EnumDeclaration
//
//    constructor(sym: EnumDeclaration) : super() {
//        this.sym = sym
//    }
//
//
//    override fun implicitlyConvertibleTo(to: Type): Match {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//
//    override fun toBaseType(): Type {
//        if (sym.enumBody?.enumMembers != null && !sym.enumBody!!.enumMembers.isEmpty() && sym.memtype != null)
//            return this
//        val tb = sym.getMemtype().toBaseType()
//        return tb.castMod(mod)         // retain modifier bits from 'this'
//    }
//}
//
//
class DTypeStruct(val struct: DLanguageStructDeclaration) : DType(ENUMTY.Tstruct, Flags()) {

    override fun toText(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override val typeMembersProvider: TypeMembers
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun implicitlyConvertibleTo(to: DType): Match {
        var m: MATCH

        //printf("TypeStruct::implicitConvTo(%s => %s)\n", toChars(), to.toChars());
        if (ty == to.ty && struct == (to as DTypeStruct).struct) {
            m = MATCH.exact // exact match
            if (mods != to.mods) {
                m = MATCH.constant
                if (MODimplicitConv(mods, to.mods)) {
                } else {
                    /* Check all the fields. If they can all be converted,
                     * allow the conversion.
                     */
                    var offset: Int = ~0 // dead-store to prevent spurious warning
                    for (v in struct.fields) {
                        if (v.offset == offset) {
                            if (m > MATCH.nomatch)
                                continue
                        } else {
                            if (m == MATCH.nomatch)
                                return m
                        }

                        // 'from' type
                        val tvf: DType = v.typeOf.addMod(mods)

                        // 'to' type
                        val tv: DType = v.typeOf.addMod(to.mods)

                        //printf("\t%s => %s, match = %d\n", v.type.toChars(), tv.toChars(), mf);
                        // field match
                        val mf = tvf.implicitlyConvertibleTo(tv)

                        if (mf <= MATCH.nomatch)
                            return mf
                        if (mf < m) // if field match is worse
                            m = mf
                        offset = v.offset
                    }
                }
            }
        }
//        else if (struct.aliasthis && !(att & RECtracing))
//        {
//            att = (AliasThisRec)(att | RECtracing);
//            m = aliasthisOf().implicitConvTo(to);
//            att = (AliasThisRec)(att & ~RECtracing);
//        }
//        else//todo alias this
        m = MATCH.nomatch // no match
        return m
    }
}
