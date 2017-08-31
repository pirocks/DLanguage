package net.masterthought.dlanguage.types

import com.intellij.psi.util.PsiTreeUtil
import net.masterthought.dlanguage.attributes.DAttributes
import net.masterthought.dlanguage.psi.DLanguageAttribute
import net.masterthought.dlanguage.psi.references.DReference
import net.masterthought.dlanguage.utils.*


/**
 * Created by francis on 7/29/2017.
 */

fun from(type: Type, resolveAvailable: Boolean = false, dAttributes: DAttributes): DType {
    return from(type, resolveAvailable, Mods(MODconst = dAttributes.isConst, MODimmutable = dAttributes.isImmutable, MODshared = dAttributes.isShared))//todo inout?
}

fun from(type: Type, resolveAvailable: Boolean = false, additionalMods: Mods? = null): DType {//idk about resolve available thing
    if (PsiTreeUtil.hasErrorElements(type)) {
        throw UnableToDeduceTypeException()
    }
    val type_2 = type.type_2
    val attribute = type.attribute//todo shouldn't this be a list?
    val typeSuffixs = type.typeSuffixs
    val dType = from(type_2!!, typeSuffixs, resolveAvailable)
    if (additionalMods != null) {
        if (additionalMods.MODconst) {
            dType.mods.MODconst = true
            dType.mods.applyTransitiveConst()
        }
        if (additionalMods.MODimmutable) {
            dType.mods.MODimmutable = true
        }
        if (additionalMods.MODshared) {
            dType.mods.MODshared = true
        }
        if (additionalMods.MODwild) {
            dType.mods.MODwild = true
        }
        if (additionalMods.MODmutable) {
            dType.mods.MODmutable = true
        }
    }
    if (attribute != null) {
        applyAttributesToType(attribute, dType)
    }
    return dType
}

private fun applyAttributesToType(attribute: DLanguageAttribute, dType: DType) {
    if (attribute.kW_INOUT != null) {
        dType.mods.MODwild = true
    }
    if (attribute.kW_CONST != null) {
        dType.mods.MODconst = true
    }
    if (attribute.kW_IMMUTABLE != null) {
        dType.mods.MODimmutable = true
    }
    if (attribute.kW_SHARED != null) {
        dType.mods.MODshared = true
    }
}

fun from(type_2: Type_2, typeSuffixs: List<TypeSuffix>, resolveAvailable: Boolean = false): DType {
    val res: DType
    if (typeSuffixs.isEmpty()) {
        res = from(type_2, resolveAvailable)
    } else {
        val inPointer = typeSuffixs.last().oP_ASTERISK != null
        val inBracket = typeSuffixs.last().oP_BRACKET_LEFT != null
        val inDelegate = typeSuffixs.last().kW_DELEGATE != null
        val inFunction = typeSuffixs.last().kW_FUNCTION != null
        val shorter = typeSuffixs.subList(0, typeSuffixs.size - 1)
        if (inPointer) {
            res = DTypePointer(from(type_2, shorter, resolveAvailable))
        } else if (inBracket) {
            val dynamicLength = typeSuffixs.last().assignExpressions.isEmpty()
            val assocArray = typeSuffixs.last().type != null
            if (assocArray) {
                res = DTypeAArray(from(type_2, shorter, resolveAvailable), from(typeSuffixs.last().type!!, resolveAvailable))
            } else if (dynamicLength) {
                res = DTypeDArray(from(type_2, shorter, resolveAvailable))
            } else
                res = DTypeSArray(from(type_2, shorter, resolveAvailable))
        } else if (inDelegate) {
            res = DTypeDelegate(from(type_2, shorter, resolveAvailable), typeSuffixs.last().parameters?.parameters?.map { Pair(it.typeOf, it.name) }!!)
        } else if (inFunction) {
            res = DTypeFunction(from(type_2, shorter, resolveAvailable), typeSuffixs.last().parameters?.parameters?.map { Pair(it.typeOf, it.name) }!!, ENUMTY.Tfunction)
        } else if (typeSuffixs.isEmpty())
            res = from(type_2, shorter, resolveAvailable)
        else
            TODO()
    }
    if (typeSuffixs.isEmpty()) {//only add modifiers to resultant type if there are no type suffixs in the way. todo move ito upper if statement, and generally clean this up.
        if (type_2.kW_CONST != null) {
            res.mods.MODconst = true
            res.mods.applyTransitiveConst()//todo move this into a setter
        }
        if (type_2.kW_IMMUTABLE != null) {
            res.mods.MODimmutable = true
        }
        if (type_2.kW_INOUT != null) {
            res.mods.MODwild = true
        }
        if (type_2.kW_SHARED != null) {
            res.mods.MODshared = true
        }
    }
    return res
}

fun from(type_2: Type_2, resolveAvailable: Boolean = false): DType {
    val isBuiltinType = type_2.builtinType != null
    val isSymbol = type_2.symbol != null
    val isTypeOfSymbol = type_2.typeofExpression != null
    if (isBuiltinType) {
        if (type_2.builtinType?.text == null || basicTypeIndex.getFromIndex(type_2.builtinType?.text!!) == null)
            TODO()
        return basicTypeIndex.getFromIndex(type_2.builtinType?.text!!)!!
    }
    if (isSymbol) {
        if (type_2.symbol?.identifierOrTemplateChain?.identifierOrTemplateInstances?.lastOrNull()?.identifier == null)
            TODO()
        else {
            val resolve = (type_2.symbol!!.identifierOrTemplateChain!!.identifierOrTemplateInstances.lastOrNull()!!.identifier!!.reference as DReference).resolve()//todo make identifier/template lists a reference because this is ridiculous
            if (resolve === null)
                throw UnableToDeduceTypeException()
            val resolveWrapper = resolve.parent
            if (resolve is InterfaceOrClass) {
                return DTypeClass(resolve)
            }
            if (resolve is StructDeclaration) {
                return DTypeStruct()
            }
            if (resolve is ForwardingType) {
                return resolve.forwardedType
            }
            if (resolve is TypeOf) {
                return resolve.typeOf//todo? is this correct
            }
            TODO()
        }

    }
    if (isTypeOfSymbol) {
        TODO()
    }
    return from(type_2.type!!, resolveAvailable)

}

class UnableToDeduceTypeException : Throwable()
