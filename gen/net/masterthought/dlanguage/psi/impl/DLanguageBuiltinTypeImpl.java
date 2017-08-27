

package net.masterthought.dlanguage.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import net.masterthought.dlanguage.psi.*;
import java.util.List;
import static net.masterthought.dlanguage.psi.DLanguageTypes.*;
import com.intellij.psi.ResolveState;
import com.intellij.psi.scope.PsiScopeProcessor;
import net.masterthought.dlanguage.resolve.ScopeProcessorImpl;



public class DLanguageBuiltinTypeImpl extends ASTWrapperPsiElement implements DLanguageBuiltinType{
       public DLanguageBuiltinTypeImpl (ASTNode node){
               super(node);
       }
       public void accept(@NotNull DLanguageVisitor visitor){
           visitor.visitBuiltinType(this);
       }
       public void accept(@NotNull PsiElementVisitor visitor){
           if(visitor instanceof DLanguageVisitor) accept((DLanguageVisitor)visitor);
           else super.accept(visitor);
       }

            @Nullable
            public PsiElement getKW_BOOL() {
                return findChildByType(KW_BOOL);
            }
        
            @Nullable
            public PsiElement getKW_BYTE() {
                return findChildByType(KW_BYTE);
            }
        
            @Nullable
            public PsiElement getKW_UBYTE() {
                return findChildByType(KW_UBYTE);
            }
        
            @Nullable
            public PsiElement getKW_SHORT() {
                return findChildByType(KW_SHORT);
            }
        
            @Nullable
            public PsiElement getKW_USHORT() {
                return findChildByType(KW_USHORT);
            }
        
            @Nullable
            public PsiElement getKW_INT() {
                return findChildByType(KW_INT);
            }
        
            @Nullable
            public PsiElement getKW_UINT() {
                return findChildByType(KW_UINT);
            }
        
            @Nullable
            public PsiElement getKW_LONG() {
                return findChildByType(KW_LONG);
            }
        
            @Nullable
            public PsiElement getKW_ULONG() {
                return findChildByType(KW_ULONG);
            }
        
            @Nullable
            public PsiElement getKW_CHAR() {
                return findChildByType(KW_CHAR);
            }
        
            @Nullable
            public PsiElement getKW_WCHAR() {
                return findChildByType(KW_WCHAR);
            }
        
            @Nullable
            public PsiElement getKW_DCHAR() {
                return findChildByType(KW_DCHAR);
            }
        
            @Nullable
            public PsiElement getKW_FLOAT() {
                return findChildByType(KW_FLOAT);
            }
        
            @Nullable
            public PsiElement getKW_DOUBLE() {
                return findChildByType(KW_DOUBLE);
            }
        
            @Nullable
            public PsiElement getKW_REAL() {
                return findChildByType(KW_REAL);
            }
        
            @Nullable
            public PsiElement getKW_IFLOAT() {
                return findChildByType(KW_IFLOAT);
            }
        
            @Nullable
            public PsiElement getKW_IDOUBLE() {
                return findChildByType(KW_IDOUBLE);
            }
        
            @Nullable
            public PsiElement getKW_IREAL() {
                return findChildByType(KW_IREAL);
            }
        
            @Nullable
            public PsiElement getKW_CFLOAT() {
                return findChildByType(KW_CFLOAT);
            }
        
            @Nullable
            public PsiElement getKW_CDOUBLE() {
                return findChildByType(KW_CDOUBLE);
            }
        
            @Nullable
            public PsiElement getKW_CREAL() {
                return findChildByType(KW_CREAL);
            }
        
            @Nullable
            public PsiElement getKW_VOID() {
                return findChildByType(KW_VOID);
            }
        
}