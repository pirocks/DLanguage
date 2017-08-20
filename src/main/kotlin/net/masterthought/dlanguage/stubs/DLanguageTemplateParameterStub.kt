package net.masterthought.dlanguage.stubs

import com.intellij.psi.stubs.IStubElementType
import com.intellij.psi.stubs.StubElement
import com.intellij.util.io.StringRef
import net.masterthought.dlanguage.attributes.DAttributes
import net.masterthought.dlanguage.psi.DLanguageTemplateParameter

/**
 * Created by francis on 6/13/2017.
 */
open class DLanguageTemplateParameterStub : DNamedStubBase<DLanguageTemplateParameter> {
    enum class TemplateParameterType {
        TYPE, THIS, VALUE, ALIAS, SEQUENCE
    }

    val type: TemplateParameterType?


    /*protected*/ constructor(parent: StubElement<*>?, elementType: IStubElementType<out StubElement<*>, *>?, name: String?, attributes: DAttributes?, templateParameter: TemplateParameterType?) : super(parent, elementType, name, attributes) {
        this.type = templateParameter
    }

    /*protected*/ constructor(parent: StubElement<*>?, elementType: IStubElementType<out StubElement<*>, *>?, name: StringRef?, attributes: DAttributes?, templateParameter: TemplateParameterType?) : super(parent, elementType, name, attributes) {
        this.type = templateParameter
    }

//    ####TemplateTypeParameter
//    ####Template This Parameters
//    ####Template Value Parameters
//    ####Template Alias Parameters
//    ####Template Sequence Parameters
}


class DLanguageTemplateTypeParameterStub : DLanguageTemplateParameterStub {
//    val bind : DTypeNext

    constructor(parent: StubElement<*>?, elementType: IStubElementType<out StubElement<*>, *>?, name: String?, attributes: DAttributes?, templateParameter: TemplateParameterType) : super(parent, elementType, name, attributes, templateParameter)
    constructor(parent: StubElement<*>?, elementType: IStubElementType<out StubElement<*>, *>?, name: StringRef?, attributes: DAttributes?, templateParameter: TemplateParameterType) : super(parent, elementType, name, attributes, templateParameter)
}
