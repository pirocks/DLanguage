

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



public class DLanguageAsmMulExpImpl extends ASTWrapperPsiElement implements DLanguageAsmMulExp{
       public DLanguageAsmMulExpImpl (ASTNode node){
               super(node);
       }
       public void accept(@NotNull DLanguageVisitor visitor){
           visitor.visitAsmMulExp(this);
       }
       public void accept(@NotNull PsiElementVisitor visitor){
           if(visitor instanceof DLanguageVisitor) accept((DLanguageVisitor)visitor);
           else super.accept(visitor);
       }

            @Nullable
            public DLanguageAsmMulExp getAsmMulExp() {
                return PsiTreeUtil.getChildOfType(this, DLanguageAsmMulExp.class);
            }
            @Nullable
            public DLanguageAsmBrExp getAsmBrExp() {
                return PsiTreeUtil.getChildOfType(this, DLanguageAsmBrExp.class);
            }
            @Nullable
            public PsiElement getOP_MOD() {
                return findChildByType(OP_MOD);
            }
        
            @Nullable
            public PsiElement getOP_DIV() {
                return findChildByType(OP_DIV);
            }
        
            @Nullable
            public PsiElement getOP_ASTERISK() {
                return findChildByType(OP_ASTERISK);
            }
        
}