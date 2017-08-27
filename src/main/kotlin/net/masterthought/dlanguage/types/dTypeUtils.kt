package net.masterthought.dlanguage.types

import com.intellij.psi.util.PsiTreeUtil
import net.masterthought.dlanguage.psi.references.DReference
import net.masterthought.dlanguage.utils.*


/**
 * Created by francis on 7/29/2017.
 */
fun from(type: Type, resolveAvailable: Boolean = false): DType {//idk about resolve available thing
    if (PsiTreeUtil.hasErrorElements(type)) {
        throw UnableToDeduceTypeException()
    }
    val type_2 = type.type_2
    val attribute = type.attribute
    val typeSuffixs = type.typeSuffixs
    return from(type_2!!, typeSuffixs, resolveAvailable)

}

fun from(type_2: Type_2, typeSuffixs: List<TypeSuffix>, resolveAvailable: Boolean = false): DType {
    if (typeSuffixs.isEmpty()) {
        return from(type_2, resolveAvailable)
    }
    val inPointer = typeSuffixs.last().oP_ASTERISK != null
    val inBracket = typeSuffixs.last().oP_BRACKET_LEFT != null
    val inDelegate = typeSuffixs.last().kW_DELEGATE != null
    val inFunction = typeSuffixs.last().kW_FUNCTION != null
    val shorter = typeSuffixs.subList(0, typeSuffixs.size - 1)
    if (inPointer) {
        return DTypePointer(from(type_2, shorter, resolveAvailable))
    }
    if (inBracket) {
        val dynamicLength = typeSuffixs.last().assignExpressions.isEmpty()
        val assocArray = typeSuffixs.last().type != null
        if (assocArray) {
            return DTypeAArray(from(type_2, shorter, resolveAvailable), from(typeSuffixs.last().type!!))
        }
        if (dynamicLength) {
            return DTypeDArray(from(type_2, shorter, resolveAvailable))
        }
        return DTypeSArray(from(type_2, shorter, resolveAvailable))
    }
    if (inDelegate) {
        return DTypeDelegate(from(type_2, shorter, resolveAvailable), typeSuffixs.last().parameters?.parameters?.map { Pair(it.typeOf, it.name) }!!)
    }
    if (inFunction) {
        return DTypeFunction(from(type_2, shorter, resolveAvailable), typeSuffixs.last().parameters?.parameters?.map { Pair(it.typeOf, it.name) }!!,ENUMTY.Tfunction)
    }
    if (typeSuffixs.isEmpty())
        return from(type_2, shorter, resolveAvailable)
    TODO()
}

fun from(type_2: Type_2, resolveAvailable: Boolean = false): DType {
    val isBuiltinType = type_2.builtinType != null
    val isSymbol = type_2.symbol != null
    val isTypeOfSymbol = type_2.typeofExpression != null
    if (isBuiltinType) {
        if (type_2.builtinType?.text == null || basicTypeIndex.get(type_2.builtinType?.text!!) == null)
            TODO()
        return basicTypeIndex.get(type_2.builtinType?.text!!)!!
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
                return resolve.typeOf
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
