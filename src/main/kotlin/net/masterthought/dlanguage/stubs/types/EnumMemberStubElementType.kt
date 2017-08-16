package net.masterthought.dlanguage.stubs.types

import com.intellij.psi.stubs.StubElement
import com.intellij.psi.stubs.StubInputStream
import net.masterthought.dlanguage.psi.DLanguageEnumMember
import net.masterthought.dlanguage.psi.impl.named.DLanguageEnumMemberImpl
import net.masterthought.dlanguage.stubs.DLanguageEnumMemberStub
import java.io.IOException

/**
 * Created by francis on 6/13/2017.
 */
class EnumMemberStubElementType(debugName: String) : DNamedStubElementType<DLanguageEnumMemberStub, DLanguageEnumMember>(debugName) {

    override fun createPsi(stub: DLanguageEnumMemberStub): DLanguageEnumMember {
        return DLanguageEnumMemberImpl(stub, this)
    }

    override fun createStub(psi: DLanguageEnumMember, parentStub: StubElement<*>): DLanguageEnumMemberStub {
        return DLanguageEnumMemberStub(parentStub, this, psi.name, psi.attributes)
    }

    @Throws(IOException::class)
    override fun deserialize(dataStream: StubInputStream, parentStub: StubElement<*>): DLanguageEnumMemberStub {
        val namedStubPair = deserializeNamedStub(dataStream, parentStub)
        return DLanguageEnumMemberStub(parentStub, this, namedStubPair.component1(), namedStubPair.component2())
    }
}
