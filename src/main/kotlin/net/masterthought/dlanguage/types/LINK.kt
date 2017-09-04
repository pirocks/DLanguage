package net.masterthought.dlanguage.types

import com.intellij.psi.stubs.StubInputStream
import com.intellij.psi.stubs.StubOutputStream

enum class LINK {
    def, // default
    d,
    c,
    cpp,
    windows,
    pascal,
    objc;

    fun writeLink(stream: StubOutputStream) {
        stream.writeInt(ordinal)
    }

}

fun readLink(stream: StubInputStream): LINK {
    return LINK.values()[stream.readInt()]
}
