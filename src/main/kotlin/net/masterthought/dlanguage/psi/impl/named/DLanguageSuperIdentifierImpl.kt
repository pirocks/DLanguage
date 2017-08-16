package net.masterthought.dlanguage.psi.impl.named

import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.lang.ASTNode
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiPolyVariantReference
import com.intellij.psi.PsiReference
import com.intellij.psi.PsiReferenceBase
import com.intellij.psi.stubs.IStubElementType
import net.masterthought.dlanguage.psi.DLanguageClassDeclaration
import net.masterthought.dlanguage.psi.DLanguageIdentifier
import net.masterthought.dlanguage.psi.DLanguageInterfaceOrClass
import net.masterthought.dlanguage.psi.DLanguageSuperIdentifier
import net.masterthought.dlanguage.psi.impl.DNamedStubbedPsiElementBase
import net.masterthought.dlanguage.psi.interfaces.DNamedElement
import net.masterthought.dlanguage.stubs.DLanguageSuperIdentifierStub
import net.masterthought.dlanguage.utils.DUtil

/**
 * Created by francis on 8/14/2017.
 */
class DLanguageSuperIdentifierImpl : DNamedStubbedPsiElementBase<DLanguageSuperIdentifierStub>, DLanguageSuperIdentifier {
    internal val superLength = 5

    constructor(stub: DLanguageSuperIdentifierStub, nodeType: IStubElementType<*, *>) : super(stub, nodeType)

    constructor(node: ASTNode) : super(node)

    override fun getNameIdentifier(): DLanguageIdentifier? {
        val greenStub = greenStub
        if (greenStub != null)
            return (DUtil.getParentClassOrStructOrTemplateOrInterfaceOrUnionStub(greenStub)!!.psi as DNamedElement).nameIdentifier
        if (DUtil.getParentClassOrStructOrTemplateOrInterfaceOrUnion(this) == null)
            return null
        return DUtil.getParentClassOrStructOrTemplateOrInterfaceOrUnion(this).nameIdentifier//this is a freeky function name todo
    }

    override fun getReference(): PsiReference {
        return DSuperReference(this, TextRange.from(0, superLength))
    }

    private inner class DSuperReference
    /**
     * @param element        PSI element
     * *
     * @param rangeInElement range relatively to the element's start offset
     * todo correctly handle resolving constructors
     */
    (element: DLanguageSuperIdentifier, rangeInElement: TextRange) : PsiReferenceBase<DLanguageSuperIdentifier>(element, rangeInElement), PsiReference {

        /**
         * Returns the element which is the target of the reference.

         * @return the target element, or null if it was not possible to resolve the reference to a valid target.
         * *
         * @see PsiPolyVariantReference.multiResolve
         */
        override fun resolve(): PsiElement? {
            val parentClass = DUtil.getParentClassOrStructOrTemplateOrInterfaceOrUnion(element)
            if (parentClass is DLanguageInterfaceOrClass && parentClass.getParent() is DLanguageClassDeclaration) {
                return parentClass.baseClassList!!.baseClasss.first().type_2?.symbol?.identifierOrTemplateChain?.identifierOrTemplateInstances?.last()?.identifier?.reference?.resolve()
            }
            return null
        }

        /**
         * Returns the array of String, [PsiElement] and/or [LookupElement]
         * instances representing all identifiers that are visible at the location of the reference. The contents
         * of the returned array is used to build the lookup list for basic code completion. (The list
         * of visible identifiers may not be filtered by the completion prefix string - the
         * filtering is performed later by IDEA core.)

         * @return the array of available identifiers.
         */
        override fun getVariants(): Array<Any> {
            return emptyArray()
        }
    }
}
