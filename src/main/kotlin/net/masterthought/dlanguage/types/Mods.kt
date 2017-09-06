package net.masterthought.dlanguage.types

import java.lang.IllegalStateException

open class Mods {
    open var MODconst: Boolean
    open var MODimmutable: Boolean
    open var MODshared: Boolean
    open var MODwild: Boolean

    constructor(MODconst: Boolean = false, MODimmutable: Boolean = false, MODshared: Boolean = false, MODwild: Boolean = false, MODmutable: Boolean = false) {
        this.MODconst = MODconst
        this.MODimmutable = MODimmutable
        this.MODshared = MODshared
        this.MODwild = MODwild
        this.MODmutable = MODmutable
    }

    open var MODmutable: Boolean//not really used
    open var MODwildconst: Boolean
        get() = MODconst && MODwild
        set(thing) = throw IllegalStateException(thing.toString())

    fun hasSameMods(to: DType): Boolean {
        return to.mods.MODconst == MODconst && to.mods.MODimmutable == MODimmutable && to.mods.MODmutable == MODmutable && to.mods.MODwild == MODwild && to.mods.MODwildconst == MODwildconst && to.mods.MODshared == MODshared
    }

    fun isConst(): Boolean {
        return MODconst
    }

    fun isImmutable(): Boolean {
        return MODimmutable
    }

    fun isMutable(): Boolean {
        return MODmutable
    }

    fun isShared(): Boolean {
        return MODshared
    }

    fun isSharedConst(): Boolean {
        return (MODshared || MODconst) && (!MODwild) && (!MODimmutable) && (!MODmutable) && (!MODwildconst)
    }

    fun isWild(): Boolean {
        return MODwild
    }

    fun isWildConst(): Boolean {
        return MODwildconst
    }

    fun isSharedWild(): Boolean {
        return (MODshared || MODwild) && (!MODconst) && (!MODimmutable) && (!MODmutable) && (!MODwildconst)
    }

    fun isNaked(): Boolean {
        return MODconst || MODimmutable || MODmutable || MODshared || MODwild || MODwildconst
    }

    fun toText(): String {
        var res = ""
        if (isImmutable()) {
            res += "immutable "
        }
        if (isConst()) {
            res += "const "
        }
        if (isShared()) {
            res += "shared "
        }
        if (isWild()) {
            res += "inout "
        }
        return res.dropLast(1)
    }

    open fun applyTransitiveConst() {
        MODconst = true
    }
}
