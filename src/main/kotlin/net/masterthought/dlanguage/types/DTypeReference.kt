package net.masterthought.dlanguage.types

class DTypeReference(val next_: DType, override val ty: TY) : DTypeNext(next_, ty) {
    override val typeMembersProvider: TypeMembers
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun toText(): String {
        return "ref " + next.toText()
    }

    override fun implicitlyConvertibleTo(to: DType): Match {


        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
