
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




    public interface DLanguageTypeSuffix extends PsiElement {
                @NotNull
                public List<DLanguageAssignExpression> getAssignExpressions();
            @Nullable
            public PsiElement getOP_TRIPLEDOT();
        
            @Nullable
            public PsiElement getKW_FUNCTION();
        
            @Nullable
            public PsiElement getKW_DELEGATE();
        
            @Nullable
            public PsiElement getOP_ASTERISK();
        
                @NotNull
                public List<DLanguageMemberFunctionAttribute> getMemberFunctionAttributes();
            @Nullable
            public DLanguageParameters getParameters();
            @Nullable
            public PsiElement getOP_BRACKET_LEFT();
        
            @Nullable
            public PsiElement getOP_BRACKET_RIGHT();
        
            @Nullable
            public DLanguageType getType();
}