package net.masterthought.dlanguage.stubs.index

import com.intellij.psi.search.GlobalSearchScope
import com.intellij.psi.stubs.*
import net.masterthought.dlanguage.psi.DlangFile
import net.masterthought.dlanguage.psi.DlangSingleImport
import net.masterthought.dlanguage.psi.interfaces.DNamedElement
import net.masterthought.dlanguage.psi.interfaces.HasMembers
import net.masterthought.dlanguage.stubs.DlangIdentifierStub
import net.masterthought.dlanguage.stubs.index.DTopLevelDeclarationIndex.Companion.getTopLevelSymbols

/**
 * Created by francis on 6/17/2017.
 */
class DTopLevelDeclarationsByModule : StringStubIndexExtension<DNamedElement>() {
    override fun getKey(): StubIndexKey<String, DNamedElement> {
        return KEY
    }

    override fun getVersion(): Int {
        return VERSION
    }

    companion object {
        val KEY: StubIndexKey<String, DNamedElement> = StubIndexKey.createIndexKey<String, DNamedElement>("d.globally.accessible.module")
        val VERSION = 2
        fun <S : NamedStubBase<T>, T : DNamedElement> indexTopLevelDeclarationsByModule(stub: S, sink: IndexSink) {
            if (stub !is DlangIdentifierStub && topLevelDeclaration(stub)) {
                val fileName = (stub.psi.containingFile as DlangFile).moduleOrFileName
                sink.occurrence(DTopLevelDeclarationsByModule.KEY, fileName)
            }
        }

        //todo better name/stop repeating type signature?
        fun getSymbolsFromImport(import: DlangSingleImport): MutableSet<DNamedElement> {
            if (import.applicableImportBinds.size == 0) {
                return StubIndex.getElements(KEY, import.importedModuleName, import.project, GlobalSearchScope.everythingScope(import.project), DNamedElement::class.java).toMutableSet()
            }
            val symbols = mutableSetOf<DNamedElement>()
            for (bind in import.applicableImportBinds) {
                for (resolvedBind in getTopLevelSymbols(bind, import.importedModuleName, import.project)) {
                    symbols.add(resolvedBind)
                    if (resolvedBind is HasMembers<*>) {
                        symbols.addAll(resolvedBind.members.map { it.psi as DNamedElement })
                    }
                }
            }
            return symbols
        }
    }
}
