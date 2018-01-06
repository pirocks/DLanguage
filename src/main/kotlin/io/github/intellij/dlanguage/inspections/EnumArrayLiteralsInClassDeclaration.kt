package io.github.intellij.dlanguage.inspections

import com.intellij.codeInspection.LocalInspectionTool
import com.intellij.codeInspection.ProblemsHolder
import com.intellij.psi.util.PsiTreeUtil
import io.github.intellij.dlanguage.DlangBundle
import io.github.intellij.dlanguage.psi.DlangVisitor
import io.github.intellij.dlanguage.psi.impl.named.DlangInterfaceOrClassImpl
import io.github.intellij.dlanguage.psi.impl.named.DlangStructDeclarationImpl
import io.github.intellij.dlanguage.psi.impl.named.DlangUnionDeclarationImpl
import io.github.intellij.dlanguage.psi.interfaces.DNamedElement
import io.github.intellij.dlanguage.utils.AutoDeclarationPart

/**
 * Created by francis on 1/5/2018.
 */
class EnumArrayLiteralsInClassDeclaration : LocalInspectionTool() {
    override fun getDescriptionFileName(): String = "FunctionShouldBeConst.html"
    override fun getDisplayName(): String = "Function Should be Const"
    override fun buildVisitor(holder: ProblemsHolder, isOnTheFly: Boolean): DlangVisitor = EnumArrayLiteralsInClassDeclarationVisitor(holder)
    override fun getGroupDisplayName(): String = DlangBundle.message("d.inspections.groupname")
}

class EnumArrayLiteralsInClassDeclarationVisitor(holder: ProblemsHolder) : DlangVisitor() {
    override fun visitInterfaceOrClass(o: DlangInterfaceOrClassImpl) {
        checkForEnumLiterals(o)
    }

    override fun visitUnionDeclaration(o: DlangUnionDeclarationImpl) {
        checkForEnumLiterals(o)
    }

    override fun visitStructDeclaration(o: DlangStructDeclarationImpl) {
        checkForEnumLiterals(o)
    }

    fun checkForEnumLiterals(o: DNamedElement) {
        for (decl in PsiTreeUtil.findChildrenOfType(o, AutoDeclarationPart::class.java)) {
            TODO()
        }
    }
}
