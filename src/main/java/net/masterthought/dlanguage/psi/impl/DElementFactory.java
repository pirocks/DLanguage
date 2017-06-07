package net.masterthought.dlanguage.psi.impl;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFileFactory;
import net.masterthought.dlanguage.DLanguage;
import net.masterthought.dlanguage.psi.DLangFile;
import net.masterthought.dlanguage.psi.DLangIdentifier;
import net.masterthought.dlanguage.psi.DLangImportDeclaration;
import net.masterthought.dlanguage.psi.DLangModuleDeclaration;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.intellij.psi.util.PsiTreeUtil.findChildOfType;

/**
 * Performs creation of element types.
 */
public class DElementFactory {

    /**
     * Takes a name and returns a Psi node of that name, or null.
     */
    @Nullable
    public static DLangIdentifier createDLangIdentifierFromText(@NotNull Project project, @NotNull String name) {
        DLangIdentifier e = findChildOfType(createExpressionFromText(project, name + " uniq = " + name), DLangIdentifier.class);
        if (e != null && e.getName().equals(name)) return e;
        return null;
    }

    /**
     * Takes an expression in text and returns a Psi tree of that program.
     */
    @NotNull
    private static PsiElement createExpressionFromText(@NotNull Project project, @NotNull String name) {
        final DLangFile fileFromText = createFileFromText(project, name);
        final PsiElement rhs = fileFromText.getFirstChild().getFirstChild().getLastChild();
        return rhs.getLastChild().getLastChild().getLastChild();
    }

    /**
     * Create a file containing text.
     */
    @NotNull
    private static DLangFile createFileFromText(@NotNull Project project, @NotNull String text) {
        return (DLangFile) PsiFileFactory.getInstance(project).createFileFromText("A.hs", DLanguage.INSTANCE, text);
    }

    public static PsiElement createDLangModuleFromText(Project project, String name) {
        PsiElement e = createExpressionFromText(project, "module " + name + ";").getFirstChild();
        if (e instanceof DLangModuleDeclaration) return e;
        return null;
    }

    public static PsiElement createDLangImportFromText(Project project, String name) {
        PsiElement e = createExpressionFromText(project, "import " + name + ";").getFirstChild();
        if (e instanceof DLangImportDeclaration) return e;
        return null;
    }

}

