package net.masterthought.dlanguage.attributes

import com.intellij.psi.PsiElement
import com.intellij.psi.stubs.StubInputStream
import com.intellij.psi.stubs.StubOutputStream
import net.masterthought.dlanguage.resolve.processors.parameters.DAttributesFinder
import net.masterthought.dlanguage.types.DTypeStorageClass
import net.masterthought.dlanguage.types.LINK
import net.masterthought.dlanguage.types.readLink
import java.io.IOException

/**
 * Created by francis on 8/13/2017.
 */
class DAttributes {
    val isVisible: DAttributesFinder.Visibility
    val isStatic: Boolean
    val isProperty: Boolean
    val isNoGC: Boolean
    val isExtern: Boolean
    val link: LINK
    val isPure: Boolean
    val isNothrow: Boolean
    val isConst: Boolean
    val isImmutable: Boolean
    val isShared: Boolean

    constructor(visibility: DAttributesFinder.Visibility, isStatic: Boolean, isProperty: Boolean, isNoGC: Boolean, isExtern: Boolean, link: LINK, isPure: Boolean, isNothrow: Boolean, isConst: Boolean, isImmutable: Boolean, isShared: Boolean) {
        this.isVisible = visibility
        this.isStatic = isStatic
        this.isProperty = isProperty
        this.isNoGC = isNoGC
        this.isExtern = isExtern
        this.link = link
        this.isPure = isPure
        this.isNothrow = isNothrow
        this.isConst = isConst
        this.isImmutable = isImmutable
        this.isShared = isShared
    }

    constructor(start: PsiElement) {
        val finder = DAttributesFinder(start)
        finder.recurseUp()
        this.isVisible = finder.isVisible()
        this.isStatic = finder.isStatic()
        this.isProperty = finder.isProperty()
        this.isNoGC = finder.isNoGC()
        this.isExtern = finder.isExtern()
        this.isPure = finder.isPure()
        this.isNothrow = finder.isNothrow()
        this.isConst = finder.isConst()
        this.isImmutable = finder.isImmutable()
        this.isShared = finder.isShared()
        this.link = finder.getLink()
    }

    @Throws(IOException::class)
    fun write(stream: StubOutputStream) {
        isVisible.write(stream)
        stream.writeBoolean(isStatic)
        stream.writeBoolean(isProperty)
        stream.writeBoolean(isNoGC)
        stream.writeBoolean(isExtern)
        link.writeLink(stream)
        stream.writeBoolean(isPure)
        stream.writeBoolean(isNothrow)
        stream.writeBoolean(isConst)
        stream.writeBoolean(isImmutable)
        stream.writeBoolean(isShared)
    }

    val stc: DTypeStorageClass
        get() = DTypeStorageClass(STCstatic = isStatic, STCproperty = isProperty, STCnogc = isNoGC, STCextern = isExtern, STCpure = isPure, STCnothrow = isNothrow, STCconst = isConst, STCimmutable = isImmutable, STCshared = isShared)

    companion object {

        @Throws(IOException::class)
        fun read(stream: StubInputStream): DAttributes {
            val visibility = DAttributesFinder.Visibility.read(stream)
            val isStatic = stream.readBoolean()
            val isProperty = stream.readBoolean()
            val isNoGC = stream.readBoolean()
            val isExtern = stream.readBoolean()
            val link = readLink(stream)
            val isPure = stream.readBoolean()
            val isNothrow = stream.readBoolean()
            val isConst = stream.readBoolean()
            val isImmutable = stream.readBoolean()
            val isShared = stream.readBoolean()
            return DAttributes(visibility, isStatic, isProperty, isNoGC, isExtern, link, isPure, isNothrow, isConst, isImmutable, isShared)
        }
    }
}
