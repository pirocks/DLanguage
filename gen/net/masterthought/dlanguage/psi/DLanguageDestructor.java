
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




    public interface DLanguageDestructor extends PsiElement , DCompositeElement, StubBasedPsiElement<DLanguageDestructorStub>{
            @Nullable
            public DLanguageFunctionBody getFunctionBody();
            @Nullable
            public PsiElement getOP_SCOLON();
        
            @Nullable
            public PsiElement getOP_PAR_RIGHT();
        
            @Nullable
            public PsiElement getOP_PAR_LEFT();
        
            @Nullable
            public PsiElement getKW_THIS();
        
            @Nullable
            public PsiElement getOP_TILDA();
        
                @NotNull
                public List<DLanguageMemberFunctionAttribute> getMemberFunctionAttributes();
}