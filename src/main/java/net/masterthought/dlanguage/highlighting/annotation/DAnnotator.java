package net.masterthought.dlanguage.highlighting.annotation;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.openapi.editor.colors.EditorColorsManager;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.psi.PsiElement;
import net.masterthought.dlanguage.psi.DLangVisitor;
import org.jetbrains.annotations.NotNull;

/**
* Annotator that:
* adds extra syntax highlighting
*/
public class DAnnotator implements Annotator {

    private static void setHighlighting(@NotNull PsiElement element, @NotNull AnnotationHolder holder,
                                        @NotNull TextAttributesKey key) {
        holder.createInfoAnnotation(element, null).setEnforcedTextAttributes(
            EditorColorsManager.getInstance().getGlobalScheme().getAttributes(key));
    }

    @Override
    public void annotate(@NotNull final PsiElement element, @NotNull final AnnotationHolder holder) {
        element.accept(new DLangVisitor() {
//            @Override
//            public void visitModuleFullyQualifiedName(@NotNull DLangModuleFullyQualifiedName o) {
//                super.visitModuleFullyQualifiedName(o);
//                if(o.getIdentifier().getText().startsWith("std")) {
//                    setHighlighting(o, holder, DHighlighter.STD_IMPORT);
//                }
//            }
//
//            @Override
//            public void visitModuleDeclaration(@NotNull DLangModuleDeclaration o) {
//                super.visitModuleDeclaration(o);
//                setHighlighting(o.getModuleFullyQualifiedName(), holder, DHighlighter.MODULE_DEFINITION);
//            }
//
//            @Override
//            public void visitFuncDeclaration(@NotNull DLangFuncDeclaration o) {
//                super.visitFuncDeclaration(o);
//                setHighlighting(o.getIdentifier(), holder, DHighlighter.FUNCTION_DEFINITION);
//            }
//
//            @Override
//            public void visitVarDeclarations(@NotNull DLangVarDeclarations o) {
//                super.visitVarDeclarations(o);
//                if(o.getBasicType() != null) {
//                    setHighlighting(o.getBasicType(), holder, DHighlighter.BASIC_TYPE);
//                }
//            }
//
//            @Override
//            public void visitUserDefinedAttribute(@NotNull DLangUserDefinedAttribute o) {
//                super.visitUserDefinedAttribute(o);
//                    setHighlighting(o, holder, DHighlighter.USER_DEFINED_ATTRIBUTE);
//            }
//
//            @Override
//            public void visitAggregateDeclaration(@NotNull DLangAggregateDeclaration o) {
//                super.visitAggregateDeclaration(o);
//                if(o.getClassDeclaration() != null){
//                    if(o.getClassDeclaration().getIdentifier() != null) {
//                        setHighlighting(o.getClassDeclaration().getIdentifier(), holder, DHighlighter.AGGREGATE_DEFINITION);
//                    }
//                }
//                if(o.getStructDeclaration() != null){
//                    if(o.getStructDeclaration().getIdentifier() != null) {
//                        setHighlighting(o.getStructDeclaration().getIdentifier(), holder, DHighlighter.AGGREGATE_DEFINITION);
//                    }
//                }
//                if(o.getUnionDeclaration() != null){
//                    if(o.getUnionDeclaration().getIdentifier() != null) {
//                        setHighlighting(o.getUnionDeclaration().getIdentifier(), holder, DHighlighter.AGGREGATE_DEFINITION);
//                    }
//                }
//                if(o.getInterfaceDeclaration() != null){
//                    if(o.getInterfaceDeclaration().getIdentifier() != null) {
//                        setHighlighting(o.getInterfaceDeclaration().getIdentifier(), holder, DHighlighter.AGGREGATE_DEFINITION);
//                    }
//                }
//            }
//
//            @Override
//            public void visitBasicType2(@NotNull DLangBasicType2 o) {
//                super.visitBasicType2(o);
//                    setHighlighting(o, holder, DHighlighter.BASIC_TYPE);
//            }
//
//            @Override
//            public void visitNewExpressionWithArgs(@NotNull DLangNewExpressionWithArgs o) {
//                super.visitNewExpressionWithArgs(o);
//                try {
//                    // try catch this
//                    DLangIdentifier identifier = o.getType().getBasicType().getIdentifierList().getIdentifier();
//                    setHighlighting(identifier, holder, DHighlighter.AGGREGATE_DEFINITION);
//                } catch(Exception e){
//                   // do nothing
//                }
//            }

        });
    }
}

