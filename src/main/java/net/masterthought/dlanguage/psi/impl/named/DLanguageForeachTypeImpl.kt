package net.masterthought.dlanguage.psi.impl.named

import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElementVisitor
import com.intellij.psi.stubs.IStubElementType
import com.intellij.psi.util.PsiTreeUtil
import net.masterthought.dlanguage.psi.*
import net.masterthought.dlanguage.psi.impl.DNamedStubbedPsiElementBase
import net.masterthought.dlanguage.stubs.DLanguageForeachTypeStub
import net.masterthought.dlanguage.types.DType
import net.masterthought.dlanguage.types.from

class DLanguageForeachTypeImpl : DNamedStubbedPsiElementBase<DLanguageForeachTypeStub>, DLanguageForeachType {

    constructor(stub: DLanguageForeachTypeStub, type: IStubElementType<*, *>) : super(stub, type)

    constructor(node: ASTNode) : super(node)

    fun accept(visitor: DLanguageVisitor) {
        visitor.visitForeachType(this)
    }

    override fun accept(visitor: PsiElementVisitor) {
        if (visitor is DLanguageVisitor)
            accept(visitor)
        else
            super.accept(visitor)
    }

    override fun getIdentifier(): DLanguageIdentifier? {
        return PsiTreeUtil.getStubChildOfType(this, DLanguageIdentifier::class.java)
    }

    override fun getTypeConstructors(): DLanguageTypeConstructors? {
        return PsiTreeUtil.getChildOfType(this, DLanguageTypeConstructors::class.java)
    }

    override fun getType(): DLanguageType? {
        return PsiTreeUtil.getChildOfType(this, DLanguageType::class.java)
    }

    override fun getNameIdentifier(): DLanguageIdentifier? {
        return identifier
    }

    override fun getTypeOf(): DType {
        if (greenStub != null) {
            return greenStub!!.typeOf
        }
        if (type == null) {
            ((type!!.parent as DLanguageForeachTypeList).parent as DLanguageForeachStatement).expressions.last()
            TODO()
        }
        return from(type!!, true)//todo this resolve available isn't always the case
    }

}
