package net.masterthought.dlanguage.types

/**
 * in theory just a type function
 */
class DTypeDelegate(val returnType: DType, val parameters: List<DTypeParameter>) : DTypeNext(returnType, ENUMTY.Tdelegate) {
    override val typeMembersProvider: TypeMembers
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun toText(): String {
        return returnType.toText() + " delegate" + "(" + java.lang.String.join(",", parameters.map { it.type.toText() }) + ")"
    }

}
