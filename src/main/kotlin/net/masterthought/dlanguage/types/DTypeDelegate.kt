package net.masterthought.dlanguage.types

/**
 * in theory just a type function
 */
class DTypeDelegate(override val returnType: DType, override val parameters: List<Pair<DType, String>>) : DTypeFunction(returnType, parameters, ENUMTY.Tdelegate) {
    override fun toText(): String {
        return returnType.toText() + " delegate" + "(" + java.lang.String.join(",", parameters.map { it.first.toText() }) + ")"
    }

}
