package net.masterthought.dlanguage.inspections

import com.intellij.codeInspection.LocalInspectionTool
import com.intellij.codeInspection.ProblemsHolder
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiElementVisitor
import com.intellij.psi.tree.IElementType
import net.masterthought.dlanguage.DLanguageBundle
import net.masterthought.dlanguage.psi.DLanguageTypes.*
import net.masterthought.dlanguage.psi.DLanguageVisitor
import net.masterthought.dlanguage.psi.impl.named.DLanguageFunctionDeclarationImpl

/**
 * Created by francis on 8/16/2017.
 */
class ExitPointsMissing : LocalInspectionTool() {
    class ExitPointsVisitor(val holder: ProblemsHolder) : DLanguageVisitor() {
        override fun visitFunctionDeclaration(o: DLanguageFunctionDeclarationImpl) {
            if (o.type?.type_2?.builtinType?.kW_VOID != null || o.functionBody == null) {
                return
            }
            for (storageClass in o.storageClasses) {
                if (storageClass.kW_AUTO != null)
                    return
            }
            if (o.storageClasses.size == 1 && o.type == null) {
                if (o.storageClasses.first().kW_STATIC != null) {
                    return
                }
                if (o.storageClasses.first().atAttribute?.identifier != null) {
                    return
                }
            }
            if (DPsiTreeUtil.getChildrenOfType(o.functionBody!!, setOf<IElementType>(RETURN_STATEMENT, THROW_STATEMENT, ASSERT_EXPRESSION, MIXIN_EXPRESSION, MIXIN_DECLARATION), setOf<IElementType>(INTERFACE_OR_CLASS, UNITTEST, TEMPLATE_DECLARATION, UNION_DECLARATION, FUNCTION_LITERAL_EXPRESSION, ENUM_DECLARATION, STRUCT_DECLARATION, ASSOC_ARRAY_LITERAL, ARRAY_LITERAL, VARIABLE_DECLARATION)) == null)
                holder.registerProblem(o.functionBody!!, "No function exit point")

        }
    }

    override fun buildVisitor(holder: ProblemsHolder, isOnTheFly: Boolean): PsiElementVisitor = ExitPointsVisitor(holder)

    override fun getDisplayName(): String = DLanguageBundle.message("d.inspections.symbol.function.exit.missing.displayname")

    override fun getGroupDisplayName(): String = DLanguageBundle.message("d.inspections.symbol.function.exit.missing.groupname")
}

object DPsiTreeUtil {
    fun getChildrenOfType(root: PsiElement, types: Set<IElementType>, excluded: Set<IElementType>): PsiElement? {
        if (types.contains(root.node.elementType)) {
            return root
        }
        for (child in root.children) {
            if (excluded.contains(child.node.elementType)) {
                continue
            }
            val res = getChildrenOfType(child, types, excluded)
            if (res != null) {
                return res
            }
        }
        return null
    }

}
