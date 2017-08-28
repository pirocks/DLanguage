package net.masterthought.dlanguage.types

abstract class DTypeArray(val next_: DType, override val ty: TY) : DTypeNext(next_, ty) {


    override fun implicitlyConvertibleTo(to: DType): Match {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
