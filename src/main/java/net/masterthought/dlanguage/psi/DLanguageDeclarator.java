package net.masterthought.dlanguage.psi;

import com.intellij.psi.PsiElement;
import com.intellij.psi.StubBasedPsiElement;
import net.masterthought.dlanguage.psi.interfaces.DNamedElement;
import net.masterthought.dlanguage.psi.interfaces.VariableDeclarationInterface;
import net.masterthought.dlanguage.stubs.DLanguageDeclaratorStub;
import net.masterthought.dlanguage.types.TypeOf;
import org.jetbrains.annotations.Nullable;


public interface DLanguageDeclarator extends PsiElement, DNamedElement, StubBasedPsiElement<DLanguageDeclaratorStub>, TypeOf, VariableDeclarationInterface {
    @Nullable
    DLanguageIdentifier getIdentifier();

    @Nullable
    PsiElement getOP_EQ();

    @Nullable
    DLanguageTemplateParameters getTemplateParameters();

    @Nullable
    DLanguageInitializer getInitializer();
}
