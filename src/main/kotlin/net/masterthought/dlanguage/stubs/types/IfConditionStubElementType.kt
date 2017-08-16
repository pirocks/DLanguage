package net.masterthought.dlanguage.stubs.types

import com.intellij.lang.ASTNode
import com.intellij.psi.stubs.StubElement
import com.intellij.psi.stubs.StubInputStream
import net.masterthought.dlanguage.psi.DLanguageIfCondition
import net.masterthought.dlanguage.psi.impl.named.DLanguageIfConditionImpl
import net.masterthought.dlanguage.stubs.DLanguageIfConditionStub
import java.io.IOException

/**
 * Created by francis on 6/13/2017.
 */
class IfConditionStubElementType(debugName: String) : DNamedStubElementType<DLanguageIfConditionStub, DLanguageIfCondition>(debugName) {

    override fun createPsi(stub: DLanguageIfConditionStub):DLanguageIfCondition{
        return DLanguageIfConditionImpl(stub, this)
    }

    override fun shouldCreateStub(node: ASTNode?): Boolean {
        return true
    }

    override fun createStub(psi: DLanguageIfCondition, parentStub: StubElement<*>): DLanguageIfConditionStub {
        return DLanguageIfConditionStub(parentStub, this, psi.name, psi.attributes)
    }

    @Throws(IOException::class)
    override fun deserialize(dataStream: StubInputStream, parentStub: StubElement<*>): DLanguageIfConditionStub {
        val namedStubPair = deserializeNamedStub(dataStream, parentStub)
        return DLanguageIfConditionStub(parentStub, this, namedStubPair.component1(), namedStubPair.component2())

    }
}
