package net.masterthought.dlanguage.psi;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

import static net.masterthought.dlanguage.psi.DLanguageTypes.*;

import org.jetbrains.annotations.Nullable;
import com.intellij.psi.util.PsiTreeUtil;

import java.util.List;

import net.masterthought.dlanguage.psi.interfaces.DNamedElement;
import net.masterthought.dlanguage.psi.interfaces.DCompositeElement;
import com.intellij.psi.StubBasedPsiElement;
import net.masterthought.dlanguage.stubs.interfaces.*;
import net.masterthought.dlanguage.stubs.*;
import com.intellij.psi.ResolveState;
import com.intellij.psi.scope.PsiScopeProcessor;
import net.masterthought.dlanguage.resolve.ScopeProcessorImpl;


public interface DLanguageAliasThisDeclaration extends PsiElement {
    @Nullable
    public PsiElement getKW_ALIAS();

    @Nullable
    public DLanguageIdentifier getIdentifier();

    @Nullable
    public PsiElement getKW_THIS();

    @Nullable
    public PsiElement getOP_SCOLON();

}
