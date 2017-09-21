package io.github.intellij.dlanguage.psi;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.util.Key;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.ResolveState;
import com.intellij.psi.scope.PsiScopeProcessor;
import com.intellij.psi.stubs.StubElement;
import io.github.intellij.dlanguage.DLanguage;
import io.github.intellij.dlanguage.DlangFileType;
import io.github.intellij.dlanguage.psi.interfaces.DNamedElement;
import io.github.intellij.dlanguage.resolve.ScopeProcessorImplUtil;
import io.github.intellij.dlanguage.stubs.DlangFileStub;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class DlangFile extends PsiFileBase {

    public DlangFile(@NotNull final FileViewProvider viewProvider) {
        super(viewProvider, DLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public FileType getFileType() {
        return DlangFileType.INSTANCE;
    }

    @Override
    public String toString() {
        return "D Language File";
    }

    @Override
    public Icon getIcon(final int flags) {
        return super.getIcon(flags);
    }

    /**
     * Returns the module name defined in the file or null if it doesn't exist.
     */
    @Nullable
    public String getModuleName() {
        final DLanguageModuleDeclaration module = findChildByClass(DLanguageModuleDeclaration.class);
        if (module == null) {
            return null;
        }
        return module.getText().replaceAll(";", "").replaceAll("^module\\s+", "");
    }

    /**
     * Returns the module name if it exists, otherwise returns the file name.
     */
    @NotNull
    public String getModuleOrFileName() {
        final String moduleName = getModuleName();
        return moduleName == null ? getName() : moduleName;
    }

    /**
     * Generates a stub for the current file, particularly so we can index names.
     */
    @Nullable
    @Override
    public DlangFileStub getStub() {
        final StubElement stub = super.getStub();
        if (stub == null) return null;
        return (DlangFileStub) stub;
    }

    @Override
    public boolean processDeclarations(@NotNull final PsiScopeProcessor processor, @NotNull final ResolveState state, final PsiElement lastParent, @NotNull final PsiElement place) {
        boolean toContinue = true;
        for (final PsiElement element : getChildren()) {
            if (element instanceof DLanguageDeclaration) {
                if (!ScopeProcessorImplUtil.INSTANCE.processDeclaration((DLanguageDeclaration) element, processor, state, lastParent, place)) {
                    toContinue = false;
                }
            }
            if (element instanceof DLanguageModuleDeclaration) {
                if (!processor.execute(element, state)) {
                    toContinue = false;
                }
            }
        }
        return toContinue;
    }

    public DLanguageFunctionDeclaration getMainFunction() {
        final DLanguageFunctionDeclaration[] res = new DLanguageFunctionDeclaration[1];
        final PsiScopeProcessor mainFunctionProcessor = new PsiScopeProcessor() {

            @Override
            public boolean execute(@NotNull final PsiElement element, @NotNull final ResolveState state) {
                if (element instanceof DLanguageFunctionDeclaration) {
                    if (((DNamedElement) element).getName().equals("main")) {
                        res[0] = (DLanguageFunctionDeclaration) element;
                        return false;
                    }
                }
                return true;
            }

            @Nullable
            @Override
            public <T> T getHint(@NotNull final Key<T> hintKey) {
                return null;
            }

            @Override
            public void handleEvent(@NotNull final Event event, @Nullable final Object associated) {

            }
        };
        this.processDeclarations(mainFunctionProcessor, ResolveState.initial(), null, this);
        return res[0];

    }
}
