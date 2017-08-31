package net.masterthought.dlanguage.psi.impl.named;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.ResolveState;
import com.intellij.psi.scope.PsiScopeProcessor;
import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.util.PsiTreeUtil;
import net.masterthought.dlanguage.psi.*;
import net.masterthought.dlanguage.psi.impl.DNamedStubbedPsiElementBase;
import net.masterthought.dlanguage.resolve.ScopeProcessorImpl;
import net.masterthought.dlanguage.stubs.DLanguageFunctionDeclarationStub;
import net.masterthought.dlanguage.types.DType;
import net.masterthought.dlanguage.types.DTypeUtilsKt;
import net.masterthought.dlanguage.types.Mods;
import org.apache.commons.lang.NotImplementedException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Created by francis on 7/14/2017.
 */
public class DLanguageFunctionDeclarationImpl extends DNamedStubbedPsiElementBase<DLanguageFunctionDeclarationStub> implements DLanguageFunctionDeclaration {

    public DLanguageFunctionDeclarationImpl(@NotNull final DLanguageFunctionDeclarationStub stub, final IStubElementType nodeType) {
        super(stub, nodeType);
    }

    public DLanguageFunctionDeclarationImpl(final ASTNode node) {
        super(node);
    }

    public void accept(@NotNull final DLanguageVisitor visitor) {
        visitor.visitFunctionDeclaration(this);
    }

    public void accept(@NotNull final PsiElementVisitor visitor) {
        if (visitor instanceof DLanguageVisitor) accept((DLanguageVisitor) visitor);
        else super.accept(visitor);
    }

    @Nullable
    @Override
    public DLanguageIdentifier getNameIdentifier() {
        return getIdentifier();
    }

    @Nullable
    @Override
    public DLanguageType getType() {
        return PsiTreeUtil.getChildOfType(this, DLanguageType.class);
    }

    @Nullable
    @Override
    public DLanguageIdentifier getIdentifier() {
        return PsiTreeUtil.getChildOfType(this, DLanguageIdentifier.class);
    }

    @NotNull
    @Override
    public List<DLanguageStorageClass> getStorageClasses() {
        return PsiTreeUtil.getChildrenOfTypeAsList(this, DLanguageStorageClass.class);
    }

    @Nullable
    @Override
    public DLanguageTemplateParameters getTemplateParameters() {
        return PsiTreeUtil.getChildOfType(this, DLanguageTemplateParameters.class);
    }

    @Nullable
    @Override
    public DLanguageParameters getParameters() {
        return PsiTreeUtil.getChildOfType(this, DLanguageParameters.class);
    }

    @Nullable
    @Override
    public DLanguageConstraint getConstraint() {
        return PsiTreeUtil.getChildOfType(this, DLanguageConstraint.class);
    }

    @Nullable
    @Override
    public DLanguageFunctionBody getFunctionBody() {
        return PsiTreeUtil.getChildOfType(this, DLanguageFunctionBody.class);
    }

    @Override
    public boolean processDeclarations(@NotNull final PsiScopeProcessor processor, @NotNull final ResolveState state, final PsiElement lastParent, @NotNull final PsiElement place) {
        return ScopeProcessorImpl.INSTANCE.processDeclarations(this,processor, state, lastParent, place);
    }

    @Override
    @NotNull
    public DType getReturnType() {
        if (getGreenStub() != null) {
            return getGreenStub().getDType();
        }
        //todo resolve etc. Safe to assume psi loaded?
        if (getType() != null) {
            throw new NotImplementedException();
            //handle auto etc
        }
        return DTypeUtilsKt.from(getType(), false, (Mods) null);
    }

    @Override
    @NotNull
    public DType getTypeOf() {
        return null;
//        return new DTypeFunction(getReturnType(),new ArrayList<Pair<String,DType>>((Pair<String,DType>)getParameters().getParameters().stream().map(param -> new Pair(param.getName(),TypeUtilsKt.from(param.getType(),false))).toArray()/*todo*/));
    }
}
