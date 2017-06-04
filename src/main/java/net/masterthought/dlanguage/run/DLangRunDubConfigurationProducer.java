package net.masterthought.dlanguage.run;

import com.intellij.execution.actions.ConfigurationContext;
import com.intellij.execution.actions.RunConfigurationProducer;
import com.intellij.openapi.module.Module;
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

public class DLangRunDubConfigurationProducer extends RunConfigurationProducer<DLangRunDubConfiguration> {


    public DLangRunDubConfigurationProducer() {
        super(new DLangRunDubConfigurationType());
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

            // dont try to run this producer if is a test file
            if (isDunitTestFile(psiFile)) {
                return null;
            } else {
                return virtualFile;
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
    protected boolean setupConfigurationFromContext(DLangRunDubConfiguration configuration, ConfigurationContext context, Ref<PsiElement> sourceElement) {
        final VirtualFile dFile = getRunnableDFileFromContext(context);
        if (dFile != null) {
            Module module = context.getModule();
            if (module != null) {
                configuration.setModule(module);
            }
            configuration.setName("Dub");
            return true;
        }
        return false;
    }

    @Override
    public boolean isConfigurationFromContext(final @NotNull DLangRunDubConfiguration configuration,
                                              final @NotNull ConfigurationContext context) {
        final VirtualFile dFile = getDFileFromContext(context);
        return dFile != null;
    }


}
