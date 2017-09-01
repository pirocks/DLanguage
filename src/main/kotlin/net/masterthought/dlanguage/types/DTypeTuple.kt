package net.masterthought.dlanguage.types

/**
 * Created by francis on 8/31/2017.
 */
class DTypeTuple(val arguments: List<DTypeParameter>) : DType(ENUMTY.Ttuple, Flags()){
    override fun toText(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override val typeMembersProvider: TypeMembers
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

}
