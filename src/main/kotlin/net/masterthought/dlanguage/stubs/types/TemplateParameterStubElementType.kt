package net.masterthought.dlanguage.stubs.types

import com.intellij.psi.stubs.StubElement
import com.intellij.psi.stubs.StubInputStream
import net.masterthought.dlanguage.psi.DLanguageTemplateParameter
import net.masterthought.dlanguage.psi.impl.named.DLanguageTemplateParameterImpl
import net.masterthought.dlanguage.stubs.DLanguageTemplateParameterStub
import java.io.IOException

/**
 * Created by francis on 6/13/2017.
 */
class TemplateParameterStubElementType(debugName: String) : DNamedStubElementType<DLanguageTemplateParameterStub, DLanguageTemplateParameter>(debugName) {

    override fun createPsi(stub: DLanguageTemplateParameterStub): DLanguageTemplateParameter {
        return DLanguageTemplateParameterImpl(stub, this)
    }

    override fun createStub(psi: DLanguageTemplateParameter, parentStub: StubElement<*>): DLanguageTemplateParameterStub {
        return DLanguageTemplateParameterStub(parentStub, this, psi.name, psi.attributes)
    }

    @Throws(IOException::class)
    override fun deserialize(dataStream: StubInputStream, parentStub: StubElement<*>): DLanguageTemplateParameterStub {
        val namedStubPair = deserializeNamedStub(dataStream, parentStub)
        return DLanguageTemplateParameterStub(parentStub, this, namedStubPair.component1(), namedStubPair.component2())
    }
}
