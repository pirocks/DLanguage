package net.masterthought.dlanguage.resolve.processors.parameters

import com.intellij.psi.PsiElement
import com.intellij.psi.stubs.StubInputStream
import com.intellij.psi.stubs.StubOutputStream
import net.masterthought.dlanguage.psi.DLanguageAttribute
import net.masterthought.dlanguage.psi.DLanguageFile
import net.masterthought.dlanguage.psi.DLanguageSingleImport
import net.masterthought.dlanguage.psi.DLanguageUnittest
import net.masterthought.dlanguage.psi.interfaces.DNamedElement
import net.masterthought.dlanguage.utils.*

/**
 * Created by francis on 7/17/2017.
 */
class DAttributesFinder {

    val startingPoint: PsiElement

    constructor(startingPoint: PsiElement) {
        this.startingPoint = startingPoint
    }

    enum class Visibility(val value: Int) {
        PUBLIC(0), PRIVATE(1), PROTECTED(2), LOCAL(3);

        fun write(stream: StubOutputStream) {
            stream.writeInt(this.value)
        }

        companion object {
            fun from(findValue: Int): Visibility = Visibility.values().first { it.value == findValue }
            fun read(x: StubInputStream): Visibility {
                return from(x.readInt())
            }
        }
    }

    private var visibility: Visibility? = null
    private var isStatic: Boolean? = null
    private var isProperty: Boolean? = null
    private var isNoGC: Boolean? = null
    private var isExtern: Boolean? = null
    private var isPure: Boolean? = null
    private var isNothrow: Boolean? = null
    private var isConst: Boolean? = null
    private var isImmutable: Boolean? = null

    var defaultsToStatic: Boolean = true
    var defaultVisibility: Visibility = Visibility.PUBLIC
    var defaultsToProperty: Boolean = false
    var defaultsToNoGC: Boolean = false
    var defaultsToExtern: Boolean = false
    var defaultsToLocal: Boolean = false
    var defaultsToPure: Boolean = false
    var defaultsToNothrow: Boolean = false
    var defaultsToConst: Boolean = false
    var defaultsToImmutable: Boolean = false


    fun recurseUp() {
        if (startingPoint is DNamedElement) {
            if (startingPoint is Constructor) {
                recurseUpImpl(startingPoint.kW_THIS!!)
            } else if (startingPoint.nameIdentifier != null) {
                recurseUpImpl(startingPoint.nameIdentifier!!)
            }
        }
        recurseUpImpl(startingPoint)
    }

    private fun recurseUpImpl(startingPoint: PsiElement) {
        var point = startingPoint
        while (true) {
            while (true) {
                if (!execute(point)) {
                    return
                }
                if (point.prevSibling == null) {
                    break
                }
                point = point.prevSibling
            }
            if (point.parent == null) {
                break
            }
            point = point.parent
        }
    }

    fun isParent(parent: PsiElement, child: PsiElement): Boolean {
        if (child == parent)
            return true
        if (child is DLanguageFile)
            return false
        return isParent(parent, child.parent)
    }

    fun execute(element: PsiElement): Boolean {
        if (element is DLanguageSingleImport && isParent(element, startingPoint)) {
            defaultVisibility = Visibility.PRIVATE
            defaultsToStatic = false
        }
        if (element is FunctionDeclaration || element is DLanguageUnittest || element is Parameters || element is TemplateParameters) {
            if (element is FunctionDeclaration) {
                if ((element.functionBody != null && isParent(element.functionBody!!, startingPoint)) || (element.parameters != null && isParent(element.parameters!!, startingPoint)) || (element.templateParameters != null && isParent(element.templateParameters!!, startingPoint))) {
                    visibility = Visibility.LOCAL
                    return false
                }
            }
            return true
        }
        if (element is StructBody) {
            if (isParent(element, startingPoint)) {
                defaultsToStatic = false
                return false
            }
        }
        if (element is Attribute) {
            updateFromAttribute(element)
        }
        if (element is Declaration) {
            if (element.attributeDeclaration != null) {
                for (attribute in element.attributes) {
                    updateFromAttribute(attribute)
                }
            }
        }
        if (element is AttributeDeclaration) {
            updateFromAttribute(element.attribute!!)
        }
        return true
    }

    fun updateFromAttribute(attribute: DLanguageAttribute) {
        if (attribute.textOffset < startingPoint.textOffset) {

            if (attribute.kW_EXPORT != null) {
            } else if (attribute.kW_PACKAGE != null) {
            } else if (attribute.kW_PRIVATE != null) {
                if (visibility == null) {
                    visibility = Visibility.PRIVATE
                }
            } else if (attribute.kW_PROTECTED != null) {
                if (visibility == null) {
                    visibility = Visibility.PROTECTED
                }
            } else if (attribute.kW_PUBLIC != null) {
                if (visibility == null) {
                    visibility = Visibility.PUBLIC
                }
            } else if (attribute.pragmaExpression != null) {
            } else if (attribute.kW_SYNCHRONIZED != null) {
            } else if (attribute.kW_ABSTRACT != null) {
            } else if (attribute.kW_AUTO != null) {
            } else if (attribute.kW_ENUM != null) {
            } else if (attribute.kW_EXTERN != null) {
            } else if (attribute.kW_FINAL != null) {
            } else if (attribute.kW_INOUT != null) {
            } else if (attribute.kW_NOTHROW != null) {
                if (isNothrow == null) {
                    isNothrow = true
                }
            } else if (attribute.kW_OVERRIDE != null) {
            } else if (attribute.kW_PURE != null) {
                if (isPure == null) {
                    isPure = true
                }
            } else if (attribute.kW_REF != null) {
            } else if (attribute.kW___GSHARED != null) {
            } else if (attribute.kW_SCOPE != null) {
            } else if (attribute.kW_STATIC != null) {
                if (isStatic == null) {
                    isStatic = true
                }
            } else if (attribute.alignAttribute != null) {
            } else if (attribute.atAttribute != null) {
                if (attribute.atAttribute?.identifier?.name != null) {
                    if (attribute.atAttribute!!.identifier!!.name == "property") {
                        if (isProperty == null) {
                            isProperty = true
                        }
                    }
                }
            } else if (attribute.linkageAttribute != null) {
            } //else if (attribute.typeConstructor != null) {
//            }
        }
    }

    fun isStatic(): Boolean {
        return isStatic ?: defaultsToStatic
    }

    fun isVisible(): Visibility {
        if (visibility == null)
            return defaultVisibility
        return visibility as Visibility
    }
    fun isProperty(): Boolean {
        return isProperty ?: defaultsToProperty
    }

    fun isNoGC(): Boolean {
        return isNoGC ?: defaultsToNoGC
    }

    fun isExtern(): Boolean {
        return isExtern ?: defaultsToExtern
    }

    fun isLocal(): Boolean {
        if (visibility == null)
            return defaultsToLocal
        return visibility == Visibility.LOCAL
    }

    fun isPure(): Boolean {
        return isPure ?: defaultsToPure
    }

    fun isNothrow(): Boolean {
        return isNothrow ?: defaultsToNothrow
    }

    fun isConst(): Boolean {
        return isConst ?: defaultsToConst
    }

    fun isImmutable(): Boolean {
        return isImmutable ?: defaultsToImmutable
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as DAttributesFinder

        if (startingPoint != other.startingPoint) return false
        if (visibility != other.visibility) return false
        if (isStatic != other.isStatic) return false
        if (isProperty != other.isProperty) return false
        if (isNoGC != other.isNoGC) return false
        if (isExtern != other.isExtern) return false
        if (isPure != other.isPure) return false
        if (isNothrow != other.isNothrow) return false
        if (isConst != other.isConst) return false
        if (isImmutable != other.isImmutable) return false
        if (defaultsToStatic != other.defaultsToStatic) return false
        if (defaultVisibility != other.defaultVisibility) return false
        if (defaultsToProperty != other.defaultsToProperty) return false
        if (defaultsToNoGC != other.defaultsToNoGC) return false
        if (defaultsToExtern != other.defaultsToExtern) return false
        if (defaultsToLocal != other.defaultsToLocal) return false
        if (defaultsToPure != other.defaultsToPure) return false
        if (defaultsToNothrow != other.defaultsToNothrow) return false
        if (defaultsToConst != other.defaultsToConst) return false
        if (defaultsToImmutable != other.defaultsToImmutable) return false

        return true
    }

    override fun hashCode(): Int {
        var result = startingPoint.hashCode()
        result = 31 * result + (visibility?.hashCode() ?: 0)
        result = 31 * result + (isStatic?.hashCode() ?: 0)
        result = 31 * result + (isProperty?.hashCode() ?: 0)
        result = 31 * result + (isNoGC?.hashCode() ?: 0)
        result = 31 * result + (isExtern?.hashCode() ?: 0)
        result = 31 * result + (isPure?.hashCode() ?: 0)
        result = 31 * result + (isNothrow?.hashCode() ?: 0)
        result = 31 * result + (isConst?.hashCode() ?: 0)
        result = 31 * result + (isImmutable?.hashCode() ?: 0)
        result = 31 * result + defaultsToStatic.hashCode()
        result = 31 * result + defaultVisibility.hashCode()
        result = 31 * result + defaultsToProperty.hashCode()
        result = 31 * result + defaultsToNoGC.hashCode()
        result = 31 * result + defaultsToExtern.hashCode()
        result = 31 * result + defaultsToLocal.hashCode()
        result = 31 * result + defaultsToPure.hashCode()
        result = 31 * result + defaultsToNothrow.hashCode()
        result = 31 * result + defaultsToConst.hashCode()
        result = 31 * result + defaultsToImmutable.hashCode()
        return result
    }


}
