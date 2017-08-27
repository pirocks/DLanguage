
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




    public interface DLanguageAssertExpression extends PsiElement {
            @Nullable
            public PsiElement getKW_ASSERT();
        
            @Nullable
            public PsiElement getOP_BRACES_LEFT();
        
            @Nullable
            public PsiElement getOP_BRACES_RIGHT();
        
                @NotNull
                public List<DLanguageAssignExpression> getAssignExpressions();
                @NotNull
                public List<PsiElement> getOP_COMMAs();
            
}