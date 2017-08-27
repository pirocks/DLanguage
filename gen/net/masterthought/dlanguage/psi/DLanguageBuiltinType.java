
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




    public interface DLanguageBuiltinType extends PsiElement {
            @Nullable
            public PsiElement getKW_BOOL();
        
            @Nullable
            public PsiElement getKW_BYTE();
        
            @Nullable
            public PsiElement getKW_UBYTE();
        
            @Nullable
            public PsiElement getKW_SHORT();
        
            @Nullable
            public PsiElement getKW_USHORT();
        
            @Nullable
            public PsiElement getKW_INT();
        
            @Nullable
            public PsiElement getKW_UINT();
        
            @Nullable
            public PsiElement getKW_LONG();
        
            @Nullable
            public PsiElement getKW_ULONG();
        
            @Nullable
            public PsiElement getKW_CHAR();
        
            @Nullable
            public PsiElement getKW_WCHAR();
        
            @Nullable
            public PsiElement getKW_DCHAR();
        
            @Nullable
            public PsiElement getKW_FLOAT();
        
            @Nullable
            public PsiElement getKW_DOUBLE();
        
            @Nullable
            public PsiElement getKW_REAL();
        
            @Nullable
            public PsiElement getKW_IFLOAT();
        
            @Nullable
            public PsiElement getKW_IDOUBLE();
        
            @Nullable
            public PsiElement getKW_IREAL();
        
            @Nullable
            public PsiElement getKW_CFLOAT();
        
            @Nullable
            public PsiElement getKW_CDOUBLE();
        
            @Nullable
            public PsiElement getKW_CREAL();
        
            @Nullable
            public PsiElement getKW_VOID();
        
}