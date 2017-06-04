package net.masterthought.dlanguage.psi.references;

import com.intellij.patterns.PlatformPatterns;
import com.intellij.patterns.PsiElementPattern;
import com.intellij.psi.PsiNamedElement;
import com.intellij.psi.PsiReferenceContributor;
import com.intellij.psi.PsiReferenceRegistrar;
import net.masterthought.dlanguage.DLanguage;
import net.masterthought.dlanguage.psi.DLangFuncDeclaration;
import net.masterthought.dlanguage.psi.DLangIdentifier;

public class DReferenceContributor extends PsiReferenceContributor {
    @Override
    public void registerReferenceProviders(PsiReferenceRegistrar registrar) {
        PsiElementPattern.Capture<PsiNamedElement> variableCapture =
            PlatformPatterns.psiElement(PsiNamedElement.class).withParent(DLangIdentifier.class).withParent(DLangFuncDeclaration.class).withLanguage(DLanguage.INSTANCE);
        registrar.registerReferenceProvider(variableCapture,
                new DReferenceProvider());
    }
}

