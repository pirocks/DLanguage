package net.masterthought.dlanguage.refactoring;

import com.intellij.lang.refactoring.RefactoringSupportProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import net.masterthought.dlanguage.psi.DLangIdentifier;
import net.masterthought.dlanguage.psi.interfaces.DNamedElement;
import net.masterthought.dlanguage.utils.DResolveUtil;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Created by francis on 4/18/2017.
 */
public class DRefactoringSupportProvider extends RefactoringSupportProvider {
    @Override
    public boolean isSafeDeleteAvailable(@NotNull PsiElement element) {
        if (!(element instanceof DLangIdentifier))
            return false;
        final List<PsiNamedElement> resolve = DResolveUtil.findDefinitionNode(element.getProject(), ((DNamedElement) element).getName(), (PsiNamedElement) element);
        return resolve.size() == 1;
    }
}
