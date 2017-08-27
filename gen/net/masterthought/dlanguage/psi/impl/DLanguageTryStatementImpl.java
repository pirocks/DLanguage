

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



public class DLanguageTryStatementImpl extends ASTWrapperPsiElement implements DLanguageTryStatement{
       public DLanguageTryStatementImpl (ASTNode node){
               super(node);
       }
       public void accept(@NotNull DLanguageVisitor visitor){
           visitor.visitTryStatement(this);
       }
       public void accept(@NotNull PsiElementVisitor visitor){
           if(visitor instanceof DLanguageVisitor) accept((DLanguageVisitor)visitor);
           else super.accept(visitor);
       }

            @Nullable
            public PsiElement getKW_TRY() {
                return findChildByType(KW_TRY);
            }
        
            @Nullable
            public DLanguageDeclarationOrStatement getDeclarationOrStatement() {
                return PsiTreeUtil.getChildOfType(this, DLanguageDeclarationOrStatement.class);
            }
            @Nullable
            public DLanguageCatches getCatches() {
                return PsiTreeUtil.getChildOfType(this, DLanguageCatches.class);
            }
            @Nullable
            public DLanguageFinally getFinally() {
                return PsiTreeUtil.getChildOfType(this, DLanguageFinally.class);
            }
}