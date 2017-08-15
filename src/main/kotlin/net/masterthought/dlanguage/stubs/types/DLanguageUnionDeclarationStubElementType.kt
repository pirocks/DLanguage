package net.masterthought.dlanguage.stubs.types

import com.intellij.psi.stubs.StubElement
import com.intellij.psi.stubs.StubInputStream
import net.masterthought.dlanguage.psi.DLanguageUnionDeclaration
import net.masterthought.dlanguage.psi.impl.named.DLanguageUnionDeclarationImpl
import net.masterthought.dlanguage.stubs.DLanguageUnionDeclarationStub
import java.io.IOException

class DLanguageUnionDeclarationStubElementType(debugName: String) : DNamedStubElementType<DLanguageUnionDeclarationStub, DLanguageUnionDeclaration>(debugName) {

    override fun createPsi(stub: DLanguageUnionDeclarationStub): DLanguageUnionDeclaration {
        return DLanguageUnionDeclarationImpl(stub, this)
    }

    override fun createStub(psi: DLanguageUnionDeclaration, parentStub: StubElement<*>): DLanguageUnionDeclarationStub {
        return DLanguageUnionDeclarationStub(parentStub, this, psi.name, psi.attributes)
    }

    @Throws(IOException::class)
    override fun deserialize(dataStream: StubInputStream, parentStub: StubElement<*>): DLanguageUnionDeclarationStub {
        val namedStubPair = deserializeNamedStub(dataStream, parentStub)
        return DLanguageUnionDeclarationStub(parentStub, this, namedStubPair.component1(), namedStubPair.component2())
    }
}
