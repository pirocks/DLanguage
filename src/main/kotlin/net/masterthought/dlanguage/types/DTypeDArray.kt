package net.masterthought.dlanguage.types

class DTypeDArray(val next__: DType) : DTypeArray(next__, ENUMTY.Tarray) {
    override val typeMembersProvider: TypeMembers
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun toText(): String {
        return next.toText() + "[]"
    }

    override fun implicitlyConvertibleTo(to: DType): Match {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
