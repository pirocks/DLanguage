package net.masterthought.dlanguage.stubs

import com.intellij.psi.stubs.IStubElementType
import com.intellij.psi.stubs.StubElement
import com.intellij.util.io.StringRef
import net.masterthought.dlanguage.attributes.DAttributes
import net.masterthought.dlanguage.psi.DLanguageEnumMember

/**
 * Created by francis on 6/13/2017.
 */
class DLanguageEnumMemberStub : DNamedStubBase<DLanguageEnumMember> {
    constructor(parent: StubElement<*>?, elementType: IStubElementType<out StubElement<*>, *>?, name: String?, attributes: DAttributes?) : super(parent, elementType, name, attributes)
    constructor(parent: StubElement<*>?, elementType: IStubElementType<out StubElement<*>, *>?, name: StringRef?, attributes: DAttributes?) : super(parent, elementType, name, attributes)
}
