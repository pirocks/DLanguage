package net.masterthought.dlanguage.types

import net.masterthought.dlanguage.psi.DLanguageParameterAttribute

/************************************
 * Apply modifiers to existing type.
 */
//    fun castMod(MODconst: Boolean? = null, MODimmutable: Boolean? = null, MODshared: Boolean? = null, MODwild: Boolean? = null, MODwildconst: Boolean? = null, MODmutable: Boolean? = null): Type {
//        val t: Type = this
//
//        return t
//    }
//
//    /**
//     * this family of functions change the type, and do not create a copy
//     */
//    abstract fun constOf(): DType
//    abstract fun unSharedOf(): DType
//    abstract fun mutableOf(): DType
//    abstract fun wildOf(): DType
//    abstract fun wildConstOf(): DType
//    abstract fun sharedOf(): DType
//    abstract fun sharedConstOf(): DType
//    abstract fun sharedWildOf(): DType
//    abstract fun sharedWildConstOf(): DType
//    abstract fun immutableOf(): DType
//initial type handling will  not handle const/mutable etc.

class DTypeStorageClass(
    val STCundefined: Boolean = false, val STCstatic: Boolean = false, val STCextern: Boolean = false, var STCconst: Boolean = false, var STCfinal: Boolean = false, val STCabstract: Boolean = false, val STCparameter: Boolean = false, val STCfield: Boolean = false, val STCoverride: Boolean = false, var STCauto: Boolean = false, val STCsynchronized: Boolean = false, val STCdeprecated: Boolean = false, var STCin: Boolean = false, var STCout: Boolean = false, var STClazy: Boolean = false, val STCforeach: Boolean = false, val STCvariadic: Boolean = false, val STCctorinit: Boolean = false, val STCtemplateparameter: Boolean = false, var STCscope: Boolean = false, var STCimmutable: Boolean = false, var STCref: Boolean = false, val STCinit: Boolean = false, val STCmanifest: Boolean = false, val STCnodtor: Boolean = false, var STCnothrow: Boolean = false, var STCpure: Boolean = false, val STCtls: Boolean = false, val STCalias: Boolean = false, var STCshared: Boolean = false, val STCgshared: Boolean = false, var STCwild: Boolean = false, var STCproperty: Boolean = false, var STCsafe: Boolean = false, var STCtrusted: Boolean = false, var STCsystem: Boolean = false, val STCctfe: Boolean = false, val STCdisable: Boolean = false, val STCresult: Boolean = false, val STCnodefaultctor: Boolean = false, val STCtemp: Boolean = false, val STCrvalue: Boolean = false, var STCnogc: Boolean = false, val STCvolatile: Boolean = false, val STCreturn: Boolean = false, val STCautoref: Boolean = false, val STCinference: Boolean = false, val STCexptemp: Boolean = false
) {
    val STC_TYPECTOR
        get() = STCconst || STCimmutable || STCshared || STCwild
    val STC_FUNCATTR
        get() = STCref || STCnothrow || STCnogc || STCpure || STCproperty || STCsafe || STCtrusted || STCsystem

    constructor(attribs: List<DLanguageParameterAttribute>) : this() {
        for (attrib in attribs) {
            val typeConstructor = attrib.typeConstructor
            when (true) {
                attrib.kW_AUTO != null -> {
                    STCauto = true
                }
                attrib.kW_FINAL != null -> {
                    STCfinal = true
                }
                attrib.kW_IN != null -> {
                    STCin = true
                }
                attrib.kW_LAZY != null -> {
                    STClazy = true
                }
                attrib.kW_OUT != null -> {
                    STCout = true
                }
                attrib.kW_REF != null -> {
                    STCref = true
                }
                attrib.kW_SCOPE != null -> {
                    STCscope = true
                }
                typeConstructor?.kW_CONST != null -> {
                    STCconst = true
                }
                typeConstructor?.kW_IMMUTABLE != null -> {
                    STCimmutable = true
                }
                typeConstructor?.kW_INOUT != null -> {
                    STCwild = true
                }
                typeConstructor?.kW_SCOPE != null -> {
                    STCscope = true
                }
                typeConstructor?.kW_SHARED != null -> {
                    STCshared = true
                }
            }
        }
    }


}

