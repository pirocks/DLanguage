package net.masterthought.dlanguage.types

/**
 * Created by francis on 8/31/2017.
 */
class DTypeEnum : DType(ENUMTY.Tenum, Flags()){
    override fun toText(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override val typeMembersProvider: TypeMembers
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun toBaseType(): DType {
        TODO()
    }
}
