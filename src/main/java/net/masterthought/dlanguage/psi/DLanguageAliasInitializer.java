package net.masterthought.dlanguage.psi;

import com.intellij.codeInsight.template.emmet.actions.GoToEditPointAction;
import com.intellij.psi.PsiElement;
import com.intellij.psi.StubBasedPsiElement;
import net.masterthought.dlanguage.psi.interfaces.DNamedElement;
import net.masterthought.dlanguage.stubs.DLanguageAliasInitializerStub;
import net.masterthought.dlanguage.types.ForwardingType;
import net.masterthought.dlanguage.types.TypeOf;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;


public interface DLanguageAliasInitializer extends PsiElement, DNamedElement, StubBasedPsiElement<DLanguageAliasInitializerStub>,ForwardingType {
    @Nullable
    public DLanguageIdentifier getIdentifier();

    @Nullable
    public PsiElement getOP_EQ();

    @NotNull
    public List<DLanguageStorageClass> getStorageClasss();

    @Nullable
    public DLanguageTemplateParameters getTemplateParameters();

    @Nullable
    public DLanguageType getType();
}
