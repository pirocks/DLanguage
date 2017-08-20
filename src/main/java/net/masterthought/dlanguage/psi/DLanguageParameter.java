package net.masterthought.dlanguage.psi;

import com.intellij.psi.PsiElement;
import com.intellij.psi.StubBasedPsiElement;
import net.masterthought.dlanguage.psi.interfaces.DNamedElement;
import net.masterthought.dlanguage.stubs.DLanguageParameterStub;
import net.masterthought.dlanguage.types.TypeOf;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;


public interface DLanguageParameter extends PsiElement, DNamedElement, StubBasedPsiElement<DLanguageParameterStub>, TypeOf {
    @NotNull
    List<DLanguageParameterAttribute> getParameterAttributes();

    @Nullable
    DLanguageType getType();

    @Nullable
    DLanguageIdentifier getIdentifier();

    @NotNull
    List<DLanguageTypeSuffix> getTypeSuffixs();

    @Nullable
    PsiElement getOP_TRIPLEDOT();

    @Nullable
    DLanguageAssignExpression getAssignExpression();

    @Nullable
    PsiElement getOP_EQ();

}
