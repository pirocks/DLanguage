package net.masterthought.dlanguage.types

//
//abstract class TypeBasic : Type() {
//    enum class TypeOf {
//        UINT, INT, CHAR//...todo
//    }
//}
//
///**
// * represents types with some kind of type modifier. Not modifiers like const,immutable etc. Modifiers like int *, int[], int function(), ref int
// */
open class NextMods : Mods {
    override var MODconst: Boolean
    override var MODimmutable: Boolean
    override var MODshared: Boolean
    override var MODwild: Boolean
    val typeNext: DTypeNext

    constructor(typeNext: DTypeNext, MODconst: Boolean = false, MODimmutable: Boolean = false, MODshared: Boolean = false, MODwild: Boolean = false) : super() {
        this.MODconst = MODconst
        if (MODconst) {
            typeNext.next.mods.applyTransitiveConst()
        }
        this.MODimmutable = MODimmutable
        this.MODshared = MODshared
        this.MODwild = MODwild
        this.typeNext = typeNext
    }

    override fun applyTransitiveConst() {
        typeNext.next.mods.applyTransitiveConst()
        MODconst = true
    }

}
