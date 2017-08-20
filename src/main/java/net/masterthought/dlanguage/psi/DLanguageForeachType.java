package net.masterthought.dlanguage.psi;

import com.intellij.psi.PsiElement;
import com.intellij.psi.StubBasedPsiElement;
import net.masterthought.dlanguage.psi.interfaces.DNamedElement;
import net.masterthought.dlanguage.stubs.DLanguageForeachTypeStub;
import net.masterthought.dlanguage.types.TypeOf;
import org.jetbrains.annotations.Nullable;


public interface DLanguageForeachType extends PsiElement, DNamedElement, StubBasedPsiElement<DLanguageForeachTypeStub>, TypeOf {
    @Nullable
    DLanguageType getType();

    @Nullable
    DLanguageIdentifier getIdentifier();

    @Nullable
    DLanguageTypeConstructors getTypeConstructors();
}
