package net.masterthought.dlanguage.types

//
class DTypeVector(val ty_: TY) : DType(ty_, Flags()) {
    //todo check that all these flags are correct for vectors,pointers etc.
    override val typeMembersProvider: TypeMembers
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun toText(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    val baseType: DType? = null

    override fun implicitlyConvertibleTo(to: DType): Match {
        if (this == to) {//todo override .equals
            return Match.exact
        }
        if (to is TypeVector) {
            return Match.convert
        }
        return Match.nomatch
    }

    fun elementType(): TypeBasic {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
