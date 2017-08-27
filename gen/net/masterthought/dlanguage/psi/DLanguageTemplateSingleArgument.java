
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




    public interface DLanguageTemplateSingleArgument extends PsiElement {
            @Nullable
            public DLanguageIdentifier getIdentifier();
            @Nullable
            public DLanguageType getType();
            @Nullable
            public PsiElement getKW_SUPER();
        
            @Nullable
            public PsiElement getKW_THIS();
        
            @Nullable
            public PsiElement getOP_DOLLAR();
        
            @Nullable
            public PsiElement getKW_TRUE();
        
            @Nullable
            public PsiElement getKW_FALSE();
        
            @Nullable
            public PsiElement getKW___DATE__();
        
            @Nullable
            public PsiElement getKW___EOF__();
        
            @Nullable
            public PsiElement getKW___FILE__();
        
            @Nullable
            public PsiElement getKW___FILE_FULL_PATH__();
        
            @Nullable
            public PsiElement getKW___FUNCTION__();
        
            @Nullable
            public PsiElement getKW___GSHARED();
        
            @Nullable
            public PsiElement getKW___LINE__();
        
            @Nullable
            public PsiElement getKW___MODULE__();
        
            @Nullable
            public PsiElement getKW___PARAMETERS();
        
            @Nullable
            public PsiElement getKW___PRETTY_FUNCTION__();
        
            @Nullable
            public PsiElement getKW___TIME__();
        
            @Nullable
            public PsiElement getKW___TIMESTAMP__();
        
            @Nullable
            public PsiElement getKW___TRAITS();
        
            @Nullable
            public PsiElement getKW___VECTOR();
        
            @Nullable
            public PsiElement getKW___VENDOR__();
        
            @Nullable
            public PsiElement getKW___VERSION__ ();
        
            @Nullable
            public PsiElement getINTEGER_LITERAL();
        
            @Nullable
            public PsiElement getFLOAT_LITERAL();
        
            @Nullable
            public PsiElement getDOUBLE_QUOTED_STRING();
        
            @Nullable
            public PsiElement getCHARACTER_LITERAL();
        
}