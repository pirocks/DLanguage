package net.masterthought.dlanguage.unittest;

import com.intellij.execution.actions.ConfigurationContext;
import com.intellij.execution.actions.RunConfigurationProducer;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.util.Ref;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import net.masterthought.dlanguage.DLangWritingAccessProvider;
import net.masterthought.dlanguage.psi.DLangFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static net.masterthought.dlanguage.utils.DUtil.isDunitTestFile;

public class DUnitTestRunConfigurationProducer extends RunConfigurationProducer<DUnitTestRunConfiguration> {


    public DUnitTestRunConfigurationProducer() {
        super(DUnitTestRunConfigurationType.getInstance());
    }

    @Nullable
    public static VirtualFile getRunnableDFileFromContext(final @NotNull ConfigurationContext context) {
        final PsiElement psiLocation = context.getPsiLocation();
        final PsiFile psiFile = psiLocation == null ? null : psiLocation.getContainingFile();
        final VirtualFile virtualFile = getRealVirtualFile(psiFile);

        if ((psiFile instanceof DLangFile) &&
                virtualFile != null &&
                ProjectRootManager.getInstance(context.getProject()).getFileIndex().isInContent(virtualFile) &&
            !DLangWritingAccessProvider.isInDLangSdkOrDLangPackagesFolder(psiFile.getProject(), virtualFile)) {

            // only run this producer if is test file
            if (isDunitTestFile(psiFile)) {
                return virtualFile;
            } else {
                return null;
            }
        }

        return null;
    }

    @Nullable
    private static VirtualFile getDFileFromContext(final @NotNull ConfigurationContext context) {
        final PsiElement psiLocation = context.getPsiLocation();
        final PsiFile psiFile = psiLocation == null ? null : psiLocation.getContainingFile();
        final VirtualFile virtualFile = getRealVirtualFile(psiFile);
        return psiFile instanceof DLangFile && virtualFile != null ? virtualFile : null;
    }

    public static VirtualFile getRealVirtualFile(PsiFile psiFile) {
        return psiFile != null ? psiFile.getOriginalFile().getVirtualFile() : null;
    }

    @Override
    protected boolean setupConfigurationFromContext(DUnitTestRunConfiguration configuration, ConfigurationContext context, Ref<PsiElement> sourceElement) {
        final VirtualFile dFile = getRunnableDFileFromContext(context);
        if (dFile != null) {
            configuration.setdFilePath(dFile.getPath());
            configuration.setWorkingDir(dFile.getParent().getPath());
            configuration.setGeneratedName();

            sourceElement.set(sourceElement.isNull() ? null : sourceElement.get().getContainingFile());
            return true;
        }
        return false;
    }

    @Override
    public boolean isConfigurationFromContext(final @NotNull DUnitTestRunConfiguration configuration,
                                              final @NotNull ConfigurationContext context) {
        final VirtualFile dartFile = getDFileFromContext(context);
        return dartFile != null && dartFile.getPath().equals(configuration.getdFilePath());
    }


}
