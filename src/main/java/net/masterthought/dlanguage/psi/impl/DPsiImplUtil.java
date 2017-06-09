package net.masterthought.dlanguage.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.navigation.ItemPresentation;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.*;
import com.intellij.psi.scope.PsiScopeProcessor;
import net.masterthought.dlanguage.icons.DLangIcons;
import net.masterthought.dlanguage.psi.*;
import net.masterthought.dlanguage.psi.interfaces.*;
import net.masterthought.dlanguage.psi.interfaces.containers.*;
import net.masterthought.dlanguage.psi.references.DReference;
import net.masterthought.dlanguage.stubs.*;
import net.masterthought.dlanguage.utils.DResolveUtil;
import net.masterthought.dlanguage.utils.DUtil;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.*;

import static com.intellij.psi.util.PsiTreeUtil.*;
import static net.masterthought.dlanguage.psi.interfaces.HasVisibility.Visibility;
import static net.masterthought.dlanguage.utils.DUtil.*;


/**
 * Source of the methods pointed out in DLanguage.bnf.
 * todo: this file is rather long and not getting shorter. Split into multiple files at a later date.
 */
public class DPsiImplUtil {

    // ------------- Identifier ------------------ //
    @NotNull
    public static String getName(@NotNull DLangIdentifier o) {
        DLangIdentifierStub stub = o.getStub();
        if (stub != null) return StringUtil.notNullize(stub.getName());
        return o.getText();
    }

    @Nullable
    public static PsiElement getNameIdentifier(@NotNull DLangIdentifier o) {
        ASTNode keyNode = o.getNode();
        return keyNode != null ? keyNode.getPsi() : null;
    }

    @NotNull
    public static PsiElement setName(@NotNull DLangIdentifier o, @NotNull String newName) {
        PsiElement e = DElementFactory.createDLangIdentifierFromText(o.getProject(), newName);
        o.replace(e);
        return o;
    }

    @NotNull
    public static PsiReference getReference(@NotNull DLangIdentifier o) {
        return new DReference(o, TextRange.from(0, getName(o).length()));
    }

    @Nullable
    //todo theres a standard version of this in PsiTreeUtil
    public static PsiElement findParentOfType(PsiElement element, Class className) {
        if (className.isInstance(element)) {
            return element;
        } else {
            try {
                return findParentOfType(element.getParent(), className);
            } catch (Exception e) {
                return null;
            }
        }

    }

    private static String getParentDeclarationDescription(DLangIdentifier o) {
        PsiNamedElement funcDecl = (PsiNamedElement) findParentOfType(o, DLangFuncDeclaration.class);
        PsiNamedElement classDecl = (PsiNamedElement) findParentOfType(o, DLangClassDeclaration.class);
        String description = "";
        if (funcDecl != null) {
            description = " [Function] (" + funcDecl.getName() + ")";
        }
        if (classDecl != null) {
            description = " [Class] (" + classDecl.getName() + ")";
        }
        //todo complete this
        return description;
    }

    @NotNull
    public static ItemPresentation getPresentation(final DLangIdentifier o) {
        return new ItemPresentation() {
            @NotNull
            @Override
            public String getPresentableText() {
                return o.getName() + getParentDeclarationDescription(o);
            }

            /**
             * This is needed to decipher between files when resolving multiple references.
             */
            @Nullable
            @Override
            public String getLocationString() {
                final PsiFile psiFile = o.getContainingFile();
                return psiFile instanceof DLangFile ? ((DLangFile) psiFile).getModuleOrFileName() : null;
            }

            @Nullable
            @Override
            public Icon getIcon(boolean unused) {
                return DLangIcons.FILE;
            }
        };
    }

    public static void delete(DLangIdentifier identifier) {
        final List<PsiNamedElement> definitionNode = DResolveUtil.INSTANCE.findDefinitionNode(identifier.getProject(), identifier);
        if (definitionNode.size() != 1)
            throw new IllegalStateException();
        definitionNode.get(0).delete();
    }
    // ------------- Identifier ------------------ //

    // ------------- Function Definition ------------------ //

    @NotNull
    public static String getName(@NotNull DLangFuncDeclaration o) {
        DLangFuncDeclStub stub = o.getStub();
        if (stub != null) return StringUtil.notNullize(stub.getName());
        return o.getIdentifier().getText();
    }

    @Nullable
    public static PsiElement getNameIdentifier(@NotNull DLangFuncDeclaration o) {
        ASTNode keyNode = o.getNode();
        return keyNode != null ? keyNode.getPsi() : null;
    }

    @NotNull
    public static PsiElement setName(@NotNull DLangFuncDeclaration o, @NotNull String newName) {
        o.getIdentifier().setName(newName);
        return o;
    }

    @NotNull
    public static PsiReference getReference(@NotNull DLangFuncDeclaration o) {
        return new DReference(o, TextRange.from(0, getName(o).length()));
    }

    @NotNull
    public static ItemPresentation getPresentation(final DLangFuncDeclaration o) {
        return new ItemPresentation() {
            @NotNull
            @Override
            public String getPresentableText() {
                return o.getName();
            }

            /**
             * This is needed to decipher between files when resolving multiple references.
             */
            @Nullable
            @Override
            public String getLocationString() {
                final PsiFile psiFile = o.getContainingFile();
                return psiFile instanceof DLangFile ? ((DLangFile) psiFile).getModuleOrFileName() : null;
            }

            @Nullable
            @Override
            public Icon getIcon(boolean unused) {
                return DLangIcons.FILE;
            }
        };
    }

    @NotNull
    public static List<DLangParameter> getArguments(DLangFuncDeclaration o) {
        if (o.getFuncDeclaratorSuffix().getParameters().getParameterList() == null) {
            return Collections.emptyList();
        }
        return o.getFuncDeclaratorSuffix().getParameters().getParameterList().getParameterList();
    }

    @NotNull
    public static List<DLangTemplateParameter> getTemplateArguments(DLangFuncDeclaration o) {
        if (o.getFuncDeclaratorSuffix().getTemplateParameters() == null || o.getFuncDeclaratorSuffix().getTemplateParameters().getTemplateParameterList() == null) {
            return Collections.emptyList();
        }
        return o.getFuncDeclaratorSuffix().getTemplateParameters().getTemplateParameterList().getTemplateParameterList();
    }

    @NotNull
    public static List<DLangProtectionAttribute> getProtection(DLangFuncDeclaration o) {
        return Collections.singletonList(getChildOfType(o, DLangProtectionAttribute.class));
    }

    public static boolean isSystem(DLangFuncDeclaration o) {
        return false;//todo
    }

    public static boolean isNoGC(DLangFuncDeclaration o) {
        return false;//todo
    }

    public static boolean isTrusted(DLangFuncDeclaration o) {
        return false;//todo
    }

    public static boolean hasCustomProperty(DLangFuncDeclaration o) {
        return false;//todo
    }

    public static boolean isSafe(DLangFuncDeclaration o) {
        return false;//todo
    }

    public static boolean isPropertyFunction(DLangFuncDeclaration o) {
        return false;//todo
    }

    public static DLangUserDefinedAttribute getCustomProperty(DLangFuncDeclaration o) {
        return null;//todo
    }
    // ------------- Function Definition ------------------ //

    // ------------- Class Definition ------------------ //
    @NotNull
    public static String getName(@NotNull DLangClassDeclaration o) {
        DLangClassDeclStub stub = o.getStub();
        if (stub != null) return StringUtil.notNullize(stub.getName());
        if (o.getIdentifier() == null) {
            //noinspection ConstantConditions
            return o.getClassTemplateDeclaration().getIdentifier().getText();
        }
        return o.getIdentifier().getText();
    }

    @Nullable
    public static PsiElement getNameIdentifier(@NotNull DLangClassDeclaration o) {
        ASTNode keyNode = o.getNode();
        return keyNode != null ? keyNode.getPsi() : null;
    }

    @NotNull
    public static PsiElement setName(@NotNull DLangClassDeclaration o, @NotNull String newName) {
        if (o.getIdentifier() != null)
            o.getIdentifier().setName(newName);
        else if (o.getClassTemplateDeclaration() != null)
            o.getClassTemplateDeclaration().getIdentifier().setName(newName);
        return o;
    }

    @NotNull
    public static PsiReference getReference(@NotNull DLangClassDeclaration o) {
        return new DReference(o, TextRange.from(0, getName(o).length()));
    }

    @NotNull
    public static ItemPresentation getPresentation(final DLangClassDeclaration o) {
        return new ItemPresentation() {
            @NotNull
            @Override
            public String getPresentableText() {
                return o.getName();
            }

            /**
             * This is needed to decipher between files when resolving multiple references.
             */
            @Nullable
            @Override
            public String getLocationString() {
                final PsiFile psiFile = o.getContainingFile();
                return psiFile instanceof DLangFile ? ((DLangFile) psiFile).getModuleOrFileName() : null;
            }

            @Nullable
            @Override
            public Icon getIcon(boolean unused) {
                return DLangIcons.FILE;
            }
        };
    }


    public static DLangProtectionAttribute getProtection(@NotNull DLangClassDeclaration o) {
        return getChildOfType(o, DLangProtectionAttribute.class);
    }

    @NotNull
    public static List<CanInherit> whatInheritsFrom(@NotNull DLangClassDeclaration o) {
        final DLangBaseClassList baseClassList = o.getBaseClassList();
        if (baseClassList == null)
            return Collections.emptyList();
        ArrayList<CanInherit> res = new ArrayList<>();
        ArrayList<DLangBasicType> basicTypes = new ArrayList<>();
        if (baseClassList.getSuperClass() == null)
            return Collections.emptyList();
        basicTypes.add(baseClassList.getSuperClass().getBasicType());
        for (DLangInterface interface_ : findChildrenOfType(baseClassList.getInterfaces(), DLangInterface.class)) {
            basicTypes.add(interface_.getBasicType());
        }
        for (DLangBasicType basicType : basicTypes) {
            assert (basicType.getBasicTypeX() == null);
            assert (basicType.getTypeVector() == null);
            assert (basicType.getTypeof() == null);
            final DLangIdentifierList identifierList = basicType.getIdentifierList();
            final ResolveResult[] definitionNodesSimple = ((DReference) getEndOfIdentifierList(identifierList).getReference()).multiResolve(false);
            Set<CanInherit> definitionNodes = new HashSet<>();
            for (ResolveResult node : definitionNodesSimple) {
                if (node.getElement() instanceof CanInherit)
                    definitionNodes.add((CanInherit) node.getElement());
            }
            assert (definitionNodes.size() == 1);
            res.add((CanInherit) definitionNodes.toArray()[0]);

        }

        return res;
    }

    @NotNull
    public static Map<String, DLangIdentifier> getSuperClassNames(@NotNull DLangClassDeclaration o) {
        final DLangBaseClassList baseClassList = o.getBaseClassList();
        if (baseClassList == null)
            return Collections.emptyMap();
        Map<String, DLangIdentifier> res = new HashMap<>();
        ArrayList<DLangBasicType> basicTypes = new ArrayList<>();
        if (baseClassList.getSuperClass() == null)
            return Collections.emptyMap();
        basicTypes.add(baseClassList.getSuperClass().getBasicType());
        for (DLangInterface interface_ : findChildrenOfType(baseClassList.getInterfaces(), DLangInterface.class)) {
            basicTypes.add(interface_.getBasicType());
        }
        for (DLangBasicType basicType : basicTypes) {
            assert (basicType.getBasicTypeX() == null);
            assert (basicType.getTypeVector() == null);
            assert (basicType.getTypeof() == null);
            final DLangIdentifierList identifierList = basicType.getIdentifierList();
            res.put(getEndOfIdentifierList(identifierList).getName(), getEndOfIdentifierList(identifierList));
        }

        return res;
    }

    // ------------- Class Definition ------------------ //

    // ------------- Struct Definition ------------------ //
    @NotNull
    public static String getName(@NotNull DLangStructDeclaration o) {
        DLangStructDeclStub stub = o.getStub();
        if (stub != null) return StringUtil.notNullize(stub.getName());

        if (o.getIdentifier() != null) {
            return o.getIdentifier().getText();
        } else {
            return "anon struct";
        }
    }

    @Nullable
    public static PsiElement getNameIdentifier(@NotNull DLangStructDeclaration o) {
        ASTNode keyNode = o.getNode();
        return keyNode != null ? keyNode.getPsi() : null;
    }

    @NotNull
    public static PsiElement setName(@NotNull DLangStructDeclaration o, @NotNull String newName) {
        if (o.getIdentifier() != null)
            o.getIdentifier().setName(newName);
        return o;
    }

    @NotNull
    public static PsiReference getReference(@NotNull DLangStructDeclaration o) {
        return new DReference(o, TextRange.from(0, getName(o).length()));
    }

    @NotNull
    public static ItemPresentation getPresentation(final DLangStructDeclaration o) {
        return new ItemPresentation() {
            @NotNull
            @Override
            public String getPresentableText() {
                return o.getName();
            }

            /**
             * This is needed to decipher between files when resolving multiple references.
             */
            @Nullable
            @Override
            public String getLocationString() {
                final PsiFile psiFile = o.getContainingFile();
                return psiFile instanceof DLangFile ? ((DLangFile) psiFile).getModuleOrFileName() : null;
            }

            @Nullable
            @Override
            public Icon getIcon(boolean unused) {
                return DLangIcons.FILE;
            }
        };
    }

    public static DLangProtectionAttribute getProtection(DLangStructDeclaration o) {
        return getChildOfType(o, DLangProtectionAttribute.class);
    }


    // ------------- Struct Definition ------------------ //

    // ------------- Enum Definition ------------------ //
    @NotNull
    public static String getName(@NotNull DLangEnumDeclaration o) {
        DLangEnumDeclStub stub = o.getStub();
        if (stub != null) return StringUtil.notNullize(stub.getName());

        if (o.getIdentifier() != null) {
            return o.getIdentifier().getText();
        } else {
            return "anon enum";
        }
    }

    @Nullable
    public static PsiElement getNameIdentifier(@NotNull DLangEnumDeclaration o) {
        ASTNode keyNode = o.getNode();
        return keyNode != null ? keyNode.getPsi() : null;
    }

    @NotNull
    public static PsiElement setName(@NotNull DLangEnumDeclaration o, @NotNull String newName) {
        if (o.getIdentifier() != null) {
            o.getIdentifier().setName(newName);
        } else if (o.getAnonymousEnumDeclaration() != null) {
            throw new IllegalStateException("somehow smeon tried to rename anonymous enum");
        }
        return o;
    }

    @NotNull
    public static PsiReference getReference(@NotNull DLangEnumDeclaration o) {
        return new DReference(o, TextRange.from(0, getName(o).length()));
    }

    @NotNull
    public static ItemPresentation getPresentation(final DLangEnumDeclaration o) {
        return new ItemPresentation() {
            @NotNull
            @Override
            public String getPresentableText() {
                return o.getName();
            }

            /**
             * This is needed to decipher between files when resolving multiple references.
             */
            @Nullable
            @Override
            public String getLocationString() {
                final PsiFile psiFile = o.getContainingFile();
                return psiFile instanceof DLangFile ? ((DLangFile) psiFile).getModuleOrFileName() : null;
            }

            @Nullable
            @Override
            public Icon getIcon(boolean unused) {
                return DLangIcons.FILE;
            }
        };
    }

    public static DLangProtectionAttribute getProtection(DLangEnumDeclaration o) {
        return getChildOfType(o, DLangProtectionAttribute.class);
    }


    // ------------- Enum Definition -------------------- //

    // ------------- Union Definition ------------------ //
    @NotNull
    public static String getName(@NotNull DLangUnionDeclaration o) {
        DLangUnionDeclStub stub = o.getStub();
        if (stub != null) return StringUtil.notNullize(stub.getName());

        if (o.getIdentifier() != null) {
            return o.getIdentifier().getText();
        } else if (o.getUnionTemplateDeclaration() != null) {
            return o.getUnionTemplateDeclaration().getIdentifier().getText();
        } else {
            return "anon union declaration";
        }
    }

    @Nullable
    public static PsiElement getNameIdentifier(@NotNull DLangUnionDeclaration o) {
        ASTNode keyNode = o.getNode();
        return keyNode != null ? keyNode.getPsi() : null;
    }

    @NotNull
    public static PsiElement setName(@NotNull DLangUnionDeclaration o, @NotNull String newName) {
        if (o.getIdentifier() != null) {
            o.getIdentifier().setName(newName);
        } else if (o.getUnionTemplateDeclaration() != null) {
            o.getUnionTemplateDeclaration().getIdentifier().setName(newName);
        }
        return o;
    }

    @NotNull
    public static PsiReference getReference(@NotNull DLangUnionDeclaration o) {
        return new DReference(o, TextRange.from(0, getName(o).length()));
    }

    @NotNull
    public static ItemPresentation getPresentation(final DLangUnionDeclaration o) {
        return new ItemPresentation() {
            @NotNull
            @Override
            public String getPresentableText() {
                return o.getName();
            }

            /**
             * This is needed to decipher between files when resolving multiple references.
             */
            @Nullable
            @Override
            public String getLocationString() {
                final PsiFile psiFile = o.getContainingFile();
                return psiFile instanceof DLangFile ? ((DLangFile) psiFile).getModuleOrFileName() : null;
            }

            @Nullable
            @Override
            public Icon getIcon(boolean unused) {
                return DLangIcons.FILE;
            }
        };
    }

    public static DLangProtectionAttribute getProtection(@NotNull DLangUnionDeclaration o) {
        return getChildOfType(o, DLangProtectionAttribute.class);
    }


    // ------------- Union Definition -------------------- //


    // ------------- Template Definition ------------------ //
    @NotNull
    public static String getName(@NotNull DLangTemplateDeclaration o) {
        DLangTemplateDeclStub stub = o.getStub();
        if (stub != null) return StringUtil.notNullize(stub.getName());
        return o.getIdentifier().getText();
    }

    @Nullable
    public static PsiElement getNameIdentifier(@NotNull DLangTemplateDeclaration o) {
        ASTNode keyNode = o.getNode();
        return keyNode != null ? keyNode.getPsi() : null;
    }

    @NotNull
    public static PsiElement setName(@NotNull DLangTemplateDeclaration o, @NotNull String newName) {
        o.getIdentifier().setName(newName);
        return o;
    }

    @NotNull
    public static PsiReference getReference(@NotNull DLangTemplateDeclaration o) {
        return new DReference(o, TextRange.from(0, getName(o).length()));
    }

    @NotNull
    public static ItemPresentation getPresentation(final DLangTemplateDeclaration o) {
        return new ItemPresentation() {
            @NotNull
            @Override
            public String getPresentableText() {
                return o.getName();
            }

            /**
             * This is needed to decipher between files when resolving multiple references.
             */
            @Nullable
            @Override
            public String getLocationString() {
                final PsiFile psiFile = o.getContainingFile();
                return psiFile instanceof DLangFile ? ((DLangFile) psiFile).getModuleOrFileName() : null;
            }

            @Nullable
            @Override
            public Icon getIcon(boolean unused) {
                return DLangIcons.FILE;
            }
        };
    }
    // ------------- Template Definition ------------------ //

    // ------------- Constructor ------------------ //
    @NotNull
    public static String getName(@NotNull DLangConstructor o) {
        if (DUtil.getParentClassOrStruct(o) == null) {
            throw new IllegalStateException("somehow this constructor is not in a class/struct");
        }
        return DUtil.getParentClassOrStruct(o).getName();
    }

    @Nullable
    public static PsiElement getNameIdentifier(@NotNull DLangConstructor o) {
        ASTNode keyNode = o.getNode();
        return keyNode != null ? keyNode.getPsi() : null;
    }

    @NotNull
    public static PsiElement setName(@NotNull DLangConstructor o, @NotNull String newName) {
        DUtil.getParentClassOrStruct(o).setName(newName);
        return o;
    }

    @NotNull
    public static PsiReference getReference(@NotNull DLangConstructor o) {
        return new DReference(o, TextRange.from(0, getName(o).length()));
    }

    @NotNull
    public static ItemPresentation getPresentation(final DLangConstructor o) {
        return new ItemPresentation() {
            @NotNull
            @Override
            public String getPresentableText() {
                String string = "";
                for (PsiElement psiElement : o.getChildren()) {
                    if (psiElement instanceof DLangParametersImpl)
                        string += psiElement.getText();
                }
                return o.getName() + string;
            }

            /**
             * This is needed to decipher between files when resolving multiple references.
             */
            @Nullable
            @Override
            public String getLocationString() {
                final PsiFile psiFile = o.getContainingFile();
                return psiFile instanceof DLangFile ? ((DLangFile) psiFile).getModuleOrFileName() : null;
            }

            @Nullable
            @Override
            public Icon getIcon(boolean unused) {
                return DLangIcons.FILE;
            }
        };
    }

    @NotNull
    public static List<DLangParameter> getArguments(@NotNull DLangConstructor o) {
        if (o.getConstructorTemplate() == null) {
            if (o.getParameters() == null || o.getParameters().getParameterList() == null) {
                return Collections.emptyList();
            }
            return o.getParameters().getParameterList().getParameterList();
        } else {
            if (o.getConstructorTemplate().getParameters().getParameterList() == null) {
                return Collections.emptyList();
            }
            return o.getConstructorTemplate().getParameters().getParameterList().getParameterList();
        }
    }
    // ------------- Constructor ------------------ //

    // ------------- Destructor ------------------ //
    @NotNull
    public static String getName(@NotNull DLangDestructor o) {
        return "destructors don't really have names";//todo destructors should not be named elements
    }

    @Nullable
    public static PsiElement getNameIdentifier(@NotNull DLangDestructor o) {
        ASTNode keyNode = o.getNode();
        return keyNode != null ? keyNode.getPsi() : null;
    }

    @NotNull
    public static PsiElement setName(@NotNull DLangDestructor o, @NotNull String newName) {
        getParentClassOrStruct(o).setName(newName);
        return o;
    }

    @NotNull
    public static PsiReference getReference(@NotNull DLangDestructor o) {
        return new DReference(o, TextRange.from(0, getName(o).length()));
    }

    @NotNull
    public static ItemPresentation getPresentation(final DLangDestructor o) {
        return new ItemPresentation() {
            @NotNull
            @Override
            public String getPresentableText() {
                String string = "";
                for (PsiElement psiElement : o.getChildren()) {
                    if (psiElement instanceof DLangParametersImpl)
                        string += psiElement.getText();
                }
                return o.getName() + string;
            }

            /**
             * This is needed to decipher between files when resolving multiple references.
             */
            @Nullable
            @Override
            public String getLocationString() {
                final PsiFile psiFile = o.getContainingFile();
                return psiFile instanceof DLangFile ? ((DLangFile) psiFile).getModuleOrFileName() : null;
            }

            @Nullable
            @Override
            public Icon getIcon(boolean unused) {
                return DLangIcons.FILE;
            }
        };
    }
    // ------------- Destructor ------------------ //

    // ------------- Alias Definition ------------------ //
    @NotNull
    public static String getName(@NotNull DLangAliasDeclaration o) {
        DLangAliasDeclStub stub = o.getStub();
        if (stub != null) return StringUtil.notNullize(stub.getName());

        if (o.getIdentifier() != null) {
            return o.getIdentifier().getText();
        }
        return "not found";//todo one alias declaration can have multiple names so there should be some refactoring of Alias declaration being a named element
    }

    @Nullable
    public static PsiElement getNameIdentifier(@NotNull DLangAliasDeclaration o) {
        ASTNode keyNode = o.getNode();
        return keyNode != null ? keyNode.getPsi() : null;
    }

    @NotNull
    public static PsiElement setName(@NotNull DLangAliasDeclaration o, @NotNull String newName) {
        if (o.getIdentifier() != null) {
            o.getIdentifier().setName(newName);
        } else if (o.getAliasDeclarationX() != null) {
            o.getAliasDeclarationX().getAliasDeclarationY().getIdentifier().setName(newName);
        } else if (o.getDeclarator() != null) {
            if (o.getDeclarator().getVarDeclarator() != null) {
                o.getDeclarator().getVarDeclarator().getIdentifier().setName(newName);
            } else if (o.getDeclarator().getAltDeclarator().getIdentifier() != null) {
                o.getDeclarator().getAltDeclarator().getIdentifier().setName(newName);
            } else if (o.getDeclarator().getAltDeclarator().getAltDeclaratorX().getIdentifier() != null) {
                o.getDeclarator().getAltDeclarator().getAltDeclaratorX().getIdentifier().setName(newName);
            } else {
                throw new IllegalStateException("renaming failed");//todo see todo above about refactoring alias declaration named elements
            }
        } else {
            throw new IllegalStateException("renaming failed");
        }
        return o;
    }

    @NotNull
    public static PsiReference getReference(@NotNull DLangAliasDeclaration o) {
        return new DReference(o, TextRange.from(0, getName(o).length()));
    }

    @NotNull
    public static ItemPresentation getPresentation(final DLangAliasDeclaration o) {
        return new ItemPresentation() {
            @NotNull
            @Override
            public String getPresentableText() {
                return o.getName();
            }

            /**
             * This is needed to decipher between files when resolving multiple references.
             */
            @Nullable
            @Override
            public String getLocationString() {
                final PsiFile psiFile = o.getContainingFile();
                return psiFile instanceof DLangFile ? ((DLangFile) psiFile).getModuleOrFileName() : null;
            }

            @Nullable
            @Override
            public Icon getIcon(boolean unused) {
                return DLangIcons.FILE;
            }
        };
    }

    public static boolean actuallyIsDeclaration(DLangAliasDeclaration o) {
        return true;
    }

    public static Type getDeclarationType(DLangAliasDeclaration o) {
        return null;
    }

    // ------------- Alias Definition ------------------ //

    // ------------ Module Declaration ----------------- //

    @NotNull
    public static String getName(@NotNull DLangModuleDeclaration o) {
        DLangModuleDeclStub stub = o.getStub();
        if (stub != null) return StringUtil.notNullize(stub.getName());

        if (o.getModuleFullyQualifiedName() == null) {
            Logger.getLogger(DPsiImplUtil.class).debug("this had no name: " + o.getText());
            return "";
        }

        return o.getModuleFullyQualifiedName().getText();
    }

    @Nullable
    public static PsiElement getNameIdentifier(@NotNull DLangModuleDeclaration o) {
        ASTNode keyNode = o.getNode();
        return keyNode != null ? keyNode.getPsi() : null;
    }

    @NotNull
    public static PsiElement setName(@NotNull DLangModuleDeclaration o, @NotNull String newName) {
        PsiElement e = DElementFactory.createDLangModuleFromText(o.getProject(), newName);
        o.replace(e);
        return o;
    }

    @NotNull
    public static PsiReference getReference(@NotNull DLangModuleDeclaration o) {
        return new DReference(o, TextRange.from(0, getName(o).length()));
    }

    @NotNull
    public static ItemPresentation getPresentation(final DLangModuleDeclaration o) {
        return new ItemPresentation() {
            @NotNull
            @Override
            public String getPresentableText() {
                return o.getName();
            }

            /**
             * This is needed to decipher between files when resolving multiple references.
             */
            @Nullable
            @Override
            public String getLocationString() {
                final PsiFile psiFile = o.getContainingFile();
                return psiFile instanceof DLangFile ? ((DLangFile) psiFile).getModuleOrFileName() : null;
            }

            @Nullable
            @Override
            public Icon getIcon(boolean unused) {
                return DLangIcons.FILE;
            }
        };
    }

    @NotNull
    public static DLangProtectionAttribute getProtection(DLangModuleDeclaration o) {
        return getChildOfType(o, DLangProtectionAttribute.class);
    }

    // ------------ Module Declaration ----------------- //


    // ------------- Interface Definition ------------------ //
    @NotNull
    public static String getName(@NotNull DLangInterfaceDeclaration o) {
        DLangInterfaceDeclStub stub = o.getStub();
        if (stub != null) return StringUtil.notNullize(stub.getName());
        if (o.getInterfaceTemplateDeclaration() != null) {
            return o.getInterfaceTemplateDeclaration().getIdentifier().getText();
        }
        //noinspection ConstantConditions
        return o.getIdentifier().getText();
    }

    @Nullable
    public static PsiElement getNameIdentifier(@NotNull DLangInterfaceDeclaration o) {
        ASTNode keyNode = o.getNode();
        return keyNode != null ? keyNode.getPsi() : null;
    }

    @NotNull
    public static PsiElement setName(@NotNull DLangInterfaceDeclaration o, @NotNull String newName) {
        if (o.getIdentifier() != null) {
            o.getIdentifier().setName(newName);
        } else if (o.getInterfaceTemplateDeclaration() != null) {
            o.getInterfaceTemplateDeclaration().getIdentifier().setName(newName);
        }
        return o;
    }

    @NotNull
    public static PsiReference getReference(@NotNull DLangInterfaceDeclaration o) {
        return new DReference(o, TextRange.from(0, getName(o).length()));
    }

    @NotNull
    public static ItemPresentation getPresentation(final @NotNull DLangInterfaceDeclaration o) {
        return new ItemPresentation() {
            @NotNull
            @Override
            public String getPresentableText() {
                return o.getName();
            }

            /**
             * This is needed to decipher between files when resolving multiple references.
             */
            @Nullable
            @Override
            public String getLocationString() {
                final PsiFile psiFile = o.getContainingFile();
                return psiFile instanceof DLangFile ? ((DLangFile) psiFile).getModuleOrFileName() : null;
            }

            @Nullable
            @Override
            public Icon getIcon(boolean unused) {
                return DLangIcons.FILE;
            }
        };
    }

    @NotNull
    public static List<CanInherit> whatInheritsFrom(@NotNull DLangInterfaceDeclaration o) {
        DLangBaseInterfaceList baseInterfaceList = o.getBaseInterfaceList();
        if (o.getInterfaceTemplateDeclaration() != null)
            baseInterfaceList = o.getInterfaceTemplateDeclaration().getBaseInterfaceList();
        if (baseInterfaceList == null) {
            return Collections.emptyList();
        }
        ArrayList<CanInherit> res = new ArrayList<>();
        ArrayList<DLangBasicType> basicTypes = new ArrayList<>();
        for (DLangInterface interface_ : findChildrenOfType(baseInterfaceList.getInterfaces(), DLangInterface.class)) {
            basicTypes.add(interface_.getBasicType());
        }
        for (DLangBasicType basicType : basicTypes) {
            assert (basicType.getBasicTypeX() == null);
            assert (basicType.getTypeVector() == null);
            assert (basicType.getTypeof() == null);
            final DLangIdentifierList identifierList = basicType.getIdentifierList();
            final ResolveResult[] definitionNodesSimple = ((DReference) getEndOfIdentifierList(identifierList).getReference()).multiResolve(false);
            Set<CanInherit> definitionNodes = new HashSet<>();
            for (ResolveResult node : definitionNodesSimple) {
                if (node.getElement() instanceof CanInherit)
                    definitionNodes.add((CanInherit) node.getElement());
            }
            assert (definitionNodes.size() == 1);
            res.add((CanInherit) definitionNodes.toArray()[0]);

        }

        return res;
    }

    @NotNull
    public static Map<String, DLangIdentifier> getSuperClassNames(@NotNull DLangInterfaceDeclaration o) {
        DLangBaseInterfaceList baseInterfaceList = o.getBaseInterfaceList();
        if (o.getInterfaceTemplateDeclaration() != null)
            baseInterfaceList = o.getInterfaceTemplateDeclaration().getBaseInterfaceList();
        if (baseInterfaceList == null) {
            return Collections.emptyMap();
        }
        Map<String, DLangIdentifier> res = new HashMap<>();
        ArrayList<DLangBasicType> basicTypes = new ArrayList<>();
        for (DLangInterface interface_ : findChildrenOfType(baseInterfaceList.getInterfaces(), DLangInterface.class)) {
            basicTypes.add(interface_.getBasicType());
        }
        for (DLangBasicType basicType : basicTypes) {
            assert (basicType.getBasicTypeX() == null);
            assert (basicType.getTypeVector() == null);
            assert (basicType.getTypeof() == null);
            final DLangIdentifierList identifierList = basicType.getIdentifierList();
            res.put(getEndOfIdentifierList(identifierList).getName(), getEndOfIdentifierList(identifierList));
        }
        return res;
    }

    @NotNull
    public static List<DLangTemplateParameter> getTemplateArguments(@NotNull DLangInterfaceDeclaration o) {
        if (o.getInterfaceTemplateDeclaration() == null)
            return Collections.emptyList();
        return new ArrayList<>(findChildrenOfType(o.getInterfaceTemplateDeclaration().getTemplateParameters(), DLangTemplateParameter.class));
    }

    // ------------- Labeled Statement ------------------ //
    @NotNull
    public static String getName(@NotNull DLangLabeledStatement o) {
        DLangLabeledStatementStub stub = o.getStub();
        if (stub != null) return StringUtil.notNullize(stub.getName());
        return o.getIdentifier().getText();
    }

    @Nullable
    public static PsiElement getNameIdentifier(@NotNull DLangLabeledStatement o) {
        ASTNode keyNode = o.getNode();
        return keyNode != null ? keyNode.getPsi() : null;
    }

    @NotNull
    public static PsiElement setName(@NotNull DLangLabeledStatement o, @NotNull String newName) {
        o.getIdentifier().setName(newName);
        return o;
    }

    @NotNull
    public static PsiReference getReference(@NotNull DLangLabeledStatement o) {
        return new DReference(o, TextRange.from(0, getName(o).length()));
    }

    @NotNull
    public static ItemPresentation getPresentation(final @NotNull DLangLabeledStatement o) {
        return new ItemPresentation() {
            @NotNull
            @Override
            public String getPresentableText() {
                return o.getName();
            }

            /**
             * This is needed to decipher between files when resolving multiple references.
             */
            @Nullable
            @Override
            public String getLocationString() {
                final PsiFile psiFile = o.getContainingFile();
                return psiFile instanceof DLangFile ? ((DLangFile) psiFile).getModuleOrFileName() : null;
            }

            @Nullable
            @Override
            public Icon getIcon(boolean unused) {
                return DLangIcons.FILE;
            }
        };
    }

    // ------------- Labeled Statement ------------------ //

    // ------------- Mixin Declaration ------------------ //
    @NotNull
    public static String getName(@NotNull DLangTemplateMixinDeclaration o) {
        DLangTemplateMixinDeclStub stub = o.getStub();
        if (stub != null) return StringUtil.notNullize(stub.getName());
        return o.getIdentifier().getText();//doesn't have any one name??
    }

    @Nullable
    public static PsiElement getNameIdentifier(@NotNull DLangTemplateMixinDeclaration o) {
        ASTNode keyNode = o.getNode();
        return keyNode != null ? keyNode.getPsi() : null;
    }

    @NotNull
    public static PsiElement setName(@NotNull DLangTemplateMixinDeclaration o, @NotNull String newName) {
        o.getIdentifier().setName(newName);
        return o;
    }

    @NotNull
    public static PsiReference getReference(@NotNull DLangTemplateMixinDeclaration o) {
        return new DReference(o, TextRange.from(0, getName(o).length()));
    }

    @NotNull
    public static ItemPresentation getPresentation(final DLangTemplateMixinDeclaration o) {
        return new ItemPresentation() {
            @NotNull
            @Override
            public String getPresentableText() {
                return o.getName();
            }

            /**
             * This is needed to decipher between files when resolving multiple references.
             */
            @Nullable
            @Override
            public String getLocationString() {
                final PsiFile psiFile = o.getContainingFile();
                return psiFile instanceof DLangFile ? ((DLangFile) psiFile).getModuleOrFileName() : null;
            }

            @Nullable
            @Override
            public Icon getIcon(boolean unused) {
                return DLangIcons.FILE;
            }
        };
    }

    // -------------- Mixin Template Resolving ------------------- //

    @Nullable
    @Deprecated
    public static Mixinable getMixinableDeclaration(@NotNull DLangMixinDeclaration t) {
        if (t.getTemplateInstance() != null) {
            if (t.getTemplateInstance().getIdentifier() == null)
                return null;
            final PsiElement resolve = t.getTemplateInstance().getIdentifier().getReference().resolve();
            if (resolve instanceof Mixinable)
                return (Mixinable) resolve;
            return null;
        }
        return null;
    }

    @Nullable
    @Deprecated
    public static Mixinable getMixinableDeclaration(@NotNull DLangTemplateMixin t) {
        final PsiElement definitionNodes = getEndOfIdentifierList(t.getMixinTemplateName().getQualifiedIdentifierList()).getReference().resolve();
        if (definitionNodes instanceof Mixinable) {
            return (Mixinable) definitionNodes;
        }
        return null;
    }

    @NotNull
    @Deprecated
    public static String getName(@NotNull DLangMixinDeclaration t) {
        if (t.getTemplateInstance() != null) {
            if (t.getTemplateInstance().getIdentifier() == null)
                return null;
            return t.getTemplateInstance().getIdentifier().getName();
        }
        return null;
    }

    @NotNull
    @Deprecated
    public static String getName(@NotNull DLangTemplateMixin t) {
        return getEndOfIdentifierList(t.getMixinTemplateName().getQualifiedIdentifierList()).getName();
    }

    @Nullable
    @Deprecated
    public static String getName(@NotNull DLangMixinExpression t) {
        return findChildOfType(t, DLangIdentifier.class).getName();
    }

    @Nullable
    @Deprecated
    public static String getName(@NotNull DLangMixinStatement t) {
        return findChildOfType(t, DLangIdentifier.class).getName();
    }

    // -------------- Mixin Template Resolving ------------------- //

    // ------------- Var Declaration ------------------ //

    @NotNull
    public static String getName(@NotNull DLangDeclaratorInitializer o) {
        DLangDeclaratorInitializerStub stub = o.getStub();
        if (stub != null) return StringUtil.notNullize(stub.getName());
        return getIdentifier(o).getText();
    }

    @NotNull
    private static DLangIdentifier getIdentifier(DLangDeclaratorInitializer o) {
        if (o.getAltDeclarator() != null) {
            final DLangAltDeclarator altDeclarator = o.getAltDeclarator();
            return getIdentifier(altDeclarator);
        } else /*if (o.getVarDeclarator() != null) */ {
            final DLangVarDeclarator varDeclarator = o.getVarDeclarator();
            assert varDeclarator != null;
            return varDeclarator.getIdentifier();
        }
    }

    private static DLangIdentifier getIdentifier(DLangAltDeclarator altDeclarator) {
        if (altDeclarator.getIdentifier() != null) return altDeclarator.getIdentifier();
        else
            return getIdentifier(altDeclarator.getAltDeclaratorX());
    }

    private static DLangIdentifier getIdentifier(DLangAltDeclaratorX altDeclaratorX) {
        if (altDeclaratorX.getIdentifier() != null)
            return altDeclaratorX.getIdentifier();
        return getIdentifier(altDeclaratorX.getAltDeclarator());
    }

    @Nullable
    public static PsiElement getNameIdentifier(@NotNull DLangDeclaratorInitializer o) {
        ASTNode keyNode = o.getNode();
        return keyNode != null ? keyNode.getPsi() : null;
    }

    @NotNull
    public static PsiElement setName(@NotNull DLangDeclaratorInitializer o, @NotNull String newName) {
        getIdentifier(o).setName(newName);
        return o;
    }

    @NotNull
    public static PsiReference getReference(@NotNull DLangDeclaratorInitializer o) {
        return new DReference(o, TextRange.from(0, getName(o).length()));
    }

    @NotNull
    public static ItemPresentation getPresentation(final DLangDeclaratorInitializer o) {
        return new ItemPresentation() {
            @NotNull
            @Override
            public String getPresentableText() {
                return o.getName();
            }

            /**
             * This is needed to decipher between files when resolving multiple references.
             */
            @Nullable
            @Override
            public String getLocationString() {
                final PsiFile psiFile = o.getContainingFile();
                return psiFile instanceof DLangFile ? ((DLangFile) psiFile).getModuleOrFileName() : null;
            }

            @Nullable
            @Override
            public Icon getIcon(boolean unused) {
                return DLangIcons.FILE;
            }
        };
    }

    public static boolean actuallyIsDeclaration(DLangDeclaratorInitializer o) {
        final DLangVarDeclarations parentVarDeclaration = (DLangVarDeclarations) o.getParent().getParent();
        if (parentVarDeclaration.getStorageClasses() != null)
            return true;
        if (parentVarDeclaration.getBasicType() != null)
            return true;
        if (o.getAltDeclarator() != null) {
            final DLangAltDeclarator altDeclarator = o.getAltDeclarator();
            return false;//todo the majority of the time false is correct however occasionally an alt declarator might actually be a legitimate variable declaration
        }
        if (o.getVarDeclarator() != null) {
            final DLangVarDeclarator varDeclarator = o.getVarDeclarator();
            if (varDeclarator.getBasicType2() != null)
                return true;
        }
        return false;//default to false.
    }

    public static Type getVariableDeclarationType(DLangDeclaratorInitializer o) {
        return new Type(o);

    }

    // ------------- Var Declaration ------------------ //`

    // ------------- Auto Declaration Y ------------------ //
    @NotNull
    public static String getName(@NotNull DLangAutoDeclarationY o) {
        DLangAutoDeclStub stub = o.getStub();
        if (stub != null) return StringUtil.notNullize(stub.getName());
        return o.getIdentifier().getText();
    }

    @Nullable
    public static PsiElement getNameIdentifier(@NotNull DLangAutoDeclarationY o) {
        ASTNode keyNode = o.getNode();
        return keyNode != null ? keyNode.getPsi() : null;
    }

    @NotNull
    public static PsiElement setName(@NotNull DLangAutoDeclarationY o, @NotNull String newName) {
        o.getIdentifier().setName(newName);
        return o;
    }

    @NotNull
    public static PsiReference getReference(@NotNull DLangAutoDeclarationY o) {
        return new DReference(o, TextRange.from(0, getName(o).length()));
    }

    @NotNull
    public static ItemPresentation getPresentation(final DLangAutoDeclarationY o) {
        return new ItemPresentation() {
            @NotNull
            @Override
            public String getPresentableText() {
                return o.getName();
            }

            /**
             * This is needed to decipher between files when resolving multiple references.
             */
            @Nullable
            @Override
            public String getLocationString() {
                final PsiFile psiFile = o.getContainingFile();
                return psiFile instanceof DLangFile ? ((DLangFile) psiFile).getModuleOrFileName() : null;
            }

            @Nullable
            @Override
            public Icon getIcon(boolean unused) {
                return DLangIcons.FILE;
            }
        };
    }

    public static boolean actuallyIsDeclaration(DLangAutoDeclarationY o) {
        final DLangStorageClasses storageClasses = ((DLangAutoDeclaration) o.getParent().getParent()).getStorageClasses();
        if (storageClasses == null)
            return false;
        for (DLangStorageClass dLangStorageClass : storageClasses.getStorageClassList()) {
            if (dLangStorageClass.getKwAuto() != null)
                return true;
        }
        return false;
    }

    public static Type getVariableDeclarationType(DLangAutoDeclarationY o) {
        return null;//todo implement

    }
    // ------------- Auto Declaration Y ------------------ //


    // ------------- Static Constructor ------------------//
    @NotNull
    public static String getName(@NotNull DLangStaticConstructor o) {
        return "this";//todo static constructors don't really have names, therefore they shouldn't be named elements
//        DLangStaticConstructorStub stub = o.getStub();
//        if (stub != null) return StringUtil.notNullize(stub.getName());
//
//        PsiElement parent = o.getParent();
//
//
//        while (!(parent instanceof DLangClassDeclaration)) {
//            parent = parent.getParent();
//        }

//        return ((DLangClassDeclaration)parent).getName() + "constructor";
//        if (o.getIdentifier() != null) {
//            return o.getIdentifier().getText();
//        } else {
//            return "not found";
//        }
    }

    @Nullable
    public static PsiElement getNameIdentifier(@NotNull DLangStaticConstructor o) {
        ASTNode keyNode = o.getNode();
        return keyNode != null ? keyNode.getPsi() : null;
    }

    @NotNull
    public static PsiElement setName(@NotNull DLangStaticConstructor o, @NotNull String newName) {
        throw new UnsupportedOperationException("you should not be renaming static constructors");//todo static constructors don't really have names, therefore they shouldn't be named elements
    }

    @NotNull
    public static PsiReference getReference(@NotNull DLangStaticConstructor o) {
        return new DReference(o, TextRange.from(0, getName(o).length()));
    }

    @NotNull
    public static ItemPresentation getPresentation(final DLangStaticConstructor o) {
        return new ItemPresentation() {
            @NotNull
            @Override
            public String getPresentableText() {
                String string = "";
                for (PsiElement psiElement : o.getChildren()) {
                    if (psiElement instanceof DLangParametersImpl)
                        string += psiElement.getText();
                }
                return o.getName() + string;
            }

            /**
             * This is needed to decipher between files when resolving multiple references.
             */
            @Nullable
            @Override
            public String getLocationString() {
                final PsiFile psiFile = o.getContainingFile();
                return psiFile instanceof DLangFile ? ((DLangFile) psiFile).getModuleOrFileName() : null;
            }

            @Nullable
            @Override
            public Icon getIcon(boolean unused) {
                return DLangIcons.FILE;
            }
        };
    }

    // ------------- Static Constructor ------------------//


    // ---------- Shared Static Constructor ---------------//

    @NotNull
    public static String getName(@NotNull DLangSharedStaticConstructor o) {
        return "this";//todo static constructors don't really have names, therefore they shouldn't be named elements
//        DLangSharedStaticConstructorStub stub = o.getStub();
//        if (stub != null) return StringUtil.notNullize(stub.getName());
//
//        PsiElement parent = o.getParent();
//
//
//        while (!(parent instanceof DLangClassDeclaration)) {
//            parent = parent.getParent();
//        }

//        return ((DLangClassDeclaration)parent).getName() + "constructor";
//        if (o.getIdentifier() != null) {
//            return o.getIdentifier().getText();
//        } else {
//            return "not found";
//        }
    }

    @Nullable
    public static PsiElement getNameIdentifier(@NotNull DLangSharedStaticConstructor o) {
        ASTNode keyNode = o.getNode();
        return keyNode != null ? keyNode.getPsi() : null;
    }

    @NotNull
    public static PsiElement setName(@NotNull DLangSharedStaticConstructor o, @NotNull String newName) {
        throw new UnsupportedOperationException("you should not be renaming static constructors");//todo static constructors don't really have names, therefore they shouldn't be named elements
    }

    @NotNull
    public static PsiReference getReference(@NotNull DLangSharedStaticConstructor o) {
        return new DReference(o, TextRange.from(0, getName(o).length()));
    }

    @NotNull
    public static ItemPresentation getPresentation(final DLangSharedStaticConstructor o) {
        return new ItemPresentation() {
            @NotNull
            @Override
            public String getPresentableText() {
                String string = "";
                for (PsiElement psiElement : o.getChildren()) {
                    if (psiElement instanceof DLangParametersImpl)
                        string += psiElement.getText();
                }
                return o.getName() + string;
            }

            /**
             * This is needed to decipher between files when resolving multiple references.
             */
            @Nullable
            @Override
            public String getLocationString() {
                final PsiFile psiFile = o.getContainingFile();
                return psiFile instanceof DLangFile ? ((DLangFile) psiFile).getModuleOrFileName() : null;
            }

            @Nullable
            @Override
            public Icon getIcon(boolean unused) {
                return DLangIcons.FILE;
            }
        };
    }

    // ---------- Shared Static Constructor ---------------//

    // ------------- Static Destructor ------------------ //
    @NotNull
    public static String getName(@NotNull DLangStaticDestructor o) {
        return "~this";//todo static destructors don't really have names, therefore they shouldn't be named elements
//        DLangStaticDestructorStub stub = o.getStub();
//        if (stub != null) return StringUtil.notNullize(stub.getName());
//
//        PsiElement parent = o.getParent();
//
//
//        while (!(parent instanceof DLangClassDeclaration)) {
//            parent = parent.getParent();
//        }

//        return ((DLangClassDeclaration)parent).getName() + "constructor";
//        if (o.getIdentifier() != null) {
//            return o.getIdentifier().getText();
//        } else {
//            return "not found";
//        }
    }

    @Nullable
    public static PsiElement getNameIdentifier(@NotNull DLangStaticDestructor o) {
        ASTNode keyNode = o.getNode();
        return keyNode != null ? keyNode.getPsi() : null;
    }

    @NotNull
    public static PsiElement setName(@NotNull DLangStaticDestructor o, @NotNull String newName) {
        throw new UnsupportedOperationException("you should not be renaming static destructors");//todo static destructors don't really have names, therefore they shouldn't be named elements
    }

    @NotNull
    public static PsiReference getReference(@NotNull DLangStaticDestructor o) {
        return new DReference(o, TextRange.from(0, getName(o).length()));
    }

    @NotNull
    public static ItemPresentation getPresentation(final DLangStaticDestructor o) {
        return new ItemPresentation() {
            @NotNull
            @Override
            public String getPresentableText() {
                String string = "";
                for (PsiElement psiElement : o.getChildren()) {
                    if (psiElement instanceof DLangParametersImpl)
                        string += psiElement.getText();
                }
                return o.getName() + string;
            }

            /**
             * This is needed to decipher between files when resolving multiple references.
             */
            @Nullable
            @Override
            public String getLocationString() {
                final PsiFile psiFile = o.getContainingFile();
                return psiFile instanceof DLangFile ? ((DLangFile) psiFile).getModuleOrFileName() : null;
            }

            @Nullable
            @Override
            public Icon getIcon(boolean unused) {
                return DLangIcons.FILE;
            }
        };
    }

    @NotNull
    public static String getName(@NotNull DLangSharedStaticDestructor o) {
        return "~this";//todo static constructors don't really have names, therefore they shouldn't be named elements
//        DLangSharedStaticDestructorStub stub = o.getStub();
//        if (stub != null) return StringUtil.notNullize(stub.getName());
//
//        PsiElement parent = o.getParent();
//
//
//        while (!(parent instanceof DLangClassDeclaration)) {
//            parent = parent.getParent();
//        }

//        return ((DLangClassDeclaration)parent).getName() + "constructor";
//        if (o.getIdentifier() != null) {
//            return o.getIdentifier().getText();
//        } else {
//            return "not found";
//        }
    }


    // ------------- Shared Static Destructor ------------- //

    @Nullable
    public static PsiElement getNameIdentifier(@NotNull DLangSharedStaticDestructor o) {
        ASTNode keyNode = o.getNode();
        return keyNode != null ? keyNode.getPsi() : null;
    }

    @NotNull
    public static PsiElement setName(@NotNull DLangSharedStaticDestructor o, @NotNull String newName) {
        throw new UnsupportedOperationException("you should not be renaming static constructors");//todo static destructors don't really have names, therefore they shouldn't be named elements
    }

    @NotNull
    public static PsiReference getReference(@NotNull DLangSharedStaticDestructor o) {
        return new DReference(o, TextRange.from(0, getName(o).length()));
    }

    @NotNull
    public static ItemPresentation getPresentation(final DLangSharedStaticDestructor o) {
        return new ItemPresentation() {
            @NotNull
            @Override
            public String getPresentableText() {
                String string = "";
                for (PsiElement psiElement : o.getChildren()) {
                    if (psiElement instanceof DLangParametersImpl)
                        string += psiElement.getText();
                }
                return o.getName() + string;
            }

            /**
             * This is needed to decipher between files when resolving multiple references.
             */
            @Nullable
            @Override
            public String getLocationString() {
                final PsiFile psiFile = o.getContainingFile();
                return psiFile instanceof DLangFile ? ((DLangFile) psiFile).getModuleOrFileName() : null;
            }

            @Nullable
            @Override
            public Icon getIcon(boolean unused) {
                return DLangIcons.FILE;
            }
        };
    }

    // ------------- Shared Static Destructor ------------- //

    // ------------ Import Declaration ----------------- //

    @NotNull
    public static String getName(@NotNull DLangImport o) {
        DLangImportDeclStub stub = o.getStub();
        if (stub != null) return StringUtil.notNullize(stub.getName());
        return o.getModuleFullyQualifiedName().getText();
    }

    @Nullable
    public static PsiElement getNameIdentifier(@NotNull DLangImport o) {
        ASTNode keyNode = o.getNode();
        return keyNode != null ? keyNode.getPsi() : null;
    }

    @NotNull
    public static PsiElement setName(@NotNull DLangImport o, @NotNull String newName) {
        PsiElement e = DElementFactory.createDLangImportFromText(o.getProject(), newName);
        o.replace(e);
        return o;
    }

    @NotNull
    public static PsiReference getReference(@NotNull DLangImport o) {
        return new DReference(o, TextRange.from(0, getName(o).length()));
    }

    @NotNull
    public static ItemPresentation getPresentation(final DLangImport o) {
        return new ItemPresentation() {
            @NotNull
            @Override
            public String getPresentableText() {
                return o.getName();
            }

            /**
             * This is needed to decipher between files when resolving multiple references.
             */
            @Nullable
            @Override
            public String getLocationString() {
                final PsiFile psiFile = o.getContainingFile();
                return psiFile instanceof DLangFile ? ((DLangFile) psiFile).getModuleOrFileName() : null;
            }

            @Nullable
            @Override
            public Icon getIcon(boolean unused) {
                return DLangIcons.FILE;
            }
        };
    }

    @Contract("null -> null")
    public static DLangProtectionAttribute getProtection(DLangImport o) {
        return getChildOfType(o, DLangProtectionAttribute.class);
    }

    // ------------ Import Declaration ----------------- //

    // ------------ Template Parameter Declaration ----------------- //

    @NotNull
    private static DLangIdentifier getIdentifier(@NotNull DLangTemplateParameter o) {
        final DLangTemplateAliasParameter templateAliasParameter = o.getTemplateAliasParameter();
        final DLangTemplateThisParameter templateThisParameter = o.getTemplateThisParameter();
        final DLangTemplateTypeParameter templateTypeParameter = o.getTemplateTypeParameter();
        if (templateAliasParameter != null) {
            final DLangIdentifier identifier = templateAliasParameter.getIdentifier();
            if (identifier != null)
                return identifier;
            return DUtil.getEndOfIdentifierList(templateAliasParameter.getTypeList().get(0).getBasicType().getIdentifierList());
        }
        if (templateThisParameter != null) {
            return getIdentifier(templateThisParameter.getTemplateTypeParameter());
        }
        assert templateTypeParameter != null;
        return getIdentifier(templateTypeParameter);
    }

    @NotNull
    private static DLangIdentifier getIdentifier(@NotNull DLangTemplateTypeParameter templateTypeParameter) {
        if (templateTypeParameter.getIdentifier() != null) {
            return templateTypeParameter.getIdentifier();
        }
        return DUtil.getEndOfIdentifierList(templateTypeParameter.getTypeList().get(0).getBasicType().getIdentifierList());
    }

    @NotNull
    public static String getName(@NotNull DLangTemplateParameter o) {
        DLangTemplateParameterStub stub = o.getStub();
        if (stub != null) return StringUtil.notNullize(stub.getName());
        return getIdentifier(o).getName();
    }

    @Nullable
    public static PsiElement getNameIdentifier(@NotNull DLangTemplateParameter o) {
        ASTNode keyNode = o.getNode();
        return keyNode != null ? keyNode.getPsi() : null;
    }

    @NotNull
    public static PsiElement setName(@NotNull DLangTemplateParameter o, @NotNull String newName) {
        PsiElement e = DElementFactory.createDLangIdentifierFromText(o.getProject(), newName);
        getIdentifier(o).replace(e);
        return o;
    }

    @NotNull
    public static PsiReference getReference(@NotNull DLangTemplateParameter o) {
        return new DReference(o, TextRange.from(0, getName(o).length()));
    }

    @NotNull
    public static ItemPresentation getPresentation(final DLangTemplateParameter o) {
        return new ItemPresentation() {
            @NotNull
            @Override
            public String getPresentableText() {
                return o.getName();
            }

            /**
             * This is needed to decipher between files when resolving multiple references.
             */
            @Nullable
            @Override
            public String getLocationString() {
                final PsiFile psiFile = o.getContainingFile();
                return psiFile instanceof DLangFile ? ((DLangFile) psiFile).getModuleOrFileName() : null;
            }

            @Nullable
            @Override
            public Icon getIcon(boolean unused) {
                return DLangIcons.FILE;
            }
        };
    }

    @Contract("null -> null")
    public static DLangProtectionAttribute getProtection(DLangTemplateParameter o) {
        return getChildOfType(o, DLangProtectionAttribute.class);
    }

    // ------------ Template Parameter Declaration ----------------- //

    // ------------  Parameter Declaration ----------------- //

    private static DLangIdentifier getIdentifier(@NotNull DLangParameter o) {
        //todo improve this
        if (o.getDeclarator() == null) {
            if (o.getIdentifier() != null) {
                return o.getIdentifier();
            }
            return DUtil.getEndOfIdentifierList(o.getType().getBasicType().getIdentifierList());

        }
        if (o.getDeclarator().getAltDeclarator() != null) {
            return getIdentifier(o.getDeclarator().getAltDeclarator());
        }
        if (o.getDeclarator().getVarDeclarator() != null) {
            return o.getDeclarator().getVarDeclarator().getIdentifier();
        }
        return null;
    }

    @NotNull
    public static String getName(@NotNull DLangParameter o) {
        DLangParameterStub stub = o.getStub();
        if (stub != null) return StringUtil.notNullize(stub.getName());
        return getIdentifier(o).getName();
    }

    @Nullable
    public static PsiElement getNameIdentifier(@NotNull DLangParameter o) {
        ASTNode keyNode = o.getNode();
        return keyNode != null ? keyNode.getPsi() : null;
    }

    @NotNull
    public static PsiElement setName(@NotNull DLangParameter o, @NotNull String newName) {
        PsiElement e = DElementFactory.createDLangIdentifierFromText(o.getProject(), newName);
        getIdentifier(o).replace(e);
        return o;
    }

    @NotNull
    public static PsiReference getReference(@NotNull DLangParameter o) {
        return new DReference(o, TextRange.from(0, getName(o).length()));
    }

    @NotNull
    public static ItemPresentation getPresentation(final DLangParameter o) {
        return new ItemPresentation() {
            @NotNull
            @Override
            public String getPresentableText() {
                return o.getName();
            }

            /**
             * This is needed to decipher between files when resolving multiple references.
             */
            @Nullable
            @Override
            public String getLocationString() {
                final PsiFile psiFile = o.getContainingFile();
                return psiFile instanceof DLangFile ? ((DLangFile) psiFile).getModuleOrFileName() : null;
            }

            @Nullable
            @Override
            public Icon getIcon(boolean unused) {
                return DLangIcons.FILE;
            }
        };
    }

    @Contract("null -> null")
    public static DLangProtectionAttribute getProtection(DLangParameter o) {
        return getChildOfType(o, DLangProtectionAttribute.class);
    }

    // ------------  Parameter Declaration ----------------- //

    // ------------ Foreach Type Declaration ----------------- //

    @NotNull
    private static DLangIdentifier getIdentifier(@NotNull DLangForeachType o) {
        return o.getIdentifier();
    }

    @NotNull
    public static String getName(@NotNull DLangForeachType o) {
        DLangForeachTypeStub stub = o.getStub();
        if (stub != null) return StringUtil.notNullize(stub.getName());
        return getIdentifier(o).getName();
    }

    @Nullable
    public static PsiElement getNameIdentifier(@NotNull DLangForeachType o) {
        ASTNode keyNode = o.getNode();
        return keyNode != null ? keyNode.getPsi() : null;
    }

    @NotNull
    public static PsiElement setName(@NotNull DLangForeachType o, @NotNull String newName) {
        PsiElement e = DElementFactory.createDLangIdentifierFromText(o.getProject(), newName);
        getIdentifier(o).replace(e);
        return o;
    }

    @NotNull
    public static PsiReference getReference(@NotNull DLangForeachType o) {
        return new DReference(o, TextRange.from(0, getName(o).length()));
    }

    @NotNull
    public static ItemPresentation getPresentation(final DLangForeachType o) {
        return new ItemPresentation() {
            @NotNull
            @Override
            public String getPresentableText() {
                return o.getName();
            }

            /**
             * This is needed to decipher between files when resolving multiple references.
             */
            @Nullable
            @Override
            public String getLocationString() {
                final PsiFile psiFile = o.getContainingFile();
                return psiFile instanceof DLangFile ? ((DLangFile) psiFile).getModuleOrFileName() : null;
            }

            @Nullable
            @Override
            public Icon getIcon(boolean unused) {
                return DLangIcons.FILE;
            }
        };
    }

    // ------------ Foreach Type Declaration ----------------- //


    // ------------ Condition Auto Declaration ----------------- //

    @NotNull
    private static DLangIdentifier getIdentifier(@NotNull DLangConditionAutoDeclaration o) {
        return o.getIdentifier();
    }

    @NotNull
    public static String getName(@NotNull DLangConditionAutoDeclaration o) {
        DLangConditionAutoDeclStub stub = o.getStub();
        if (stub != null) return StringUtil.notNullize(stub.getName());
        return getIdentifier(o).getName();
    }

    @Nullable
    public static PsiElement getNameIdentifier(@NotNull DLangConditionAutoDeclaration o) {
        ASTNode keyNode = o.getNode();
        return keyNode != null ? keyNode.getPsi() : null;
    }

    @NotNull
    public static PsiElement setName(@NotNull DLangConditionAutoDeclaration o, @NotNull String newName) {
        PsiElement e = DElementFactory.createDLangIdentifierFromText(o.getProject(), newName);
        getIdentifier(o).replace(e);
        return o;
    }

    @NotNull
    public static PsiReference getReference(@NotNull DLangConditionAutoDeclaration o) {
        return new DReference(o, TextRange.from(0, getName(o).length()));
    }

    @NotNull
    public static ItemPresentation getPresentation(final DLangConditionAutoDeclaration o) {
        return new ItemPresentation() {
            @NotNull
            @Override
            public String getPresentableText() {
                return o.getName();
            }

            /**
             * This is needed to decipher between files when resolving multiple references.
             */
            @Nullable
            @Override
            public String getLocationString() {
                final PsiFile psiFile = o.getContainingFile();
                return psiFile instanceof DLangFile ? ((DLangFile) psiFile).getModuleOrFileName() : null;
            }

            @Nullable
            @Override
            public Icon getIcon(boolean unused) {
                return DLangIcons.FILE;
            }
        };
    }

    // ------------ Condition Auto Declaration ----------------- //

    // ------------ Condition Var Declaration ----------------- //

    @NotNull
    private static DLangIdentifier getIdentifier(@NotNull DLangConditionVarDeclaration o) {
        return o.getIdentifier();
    }

    @NotNull
    public static String getName(@NotNull DLangConditionVarDeclaration o) {
        DLangConditionVarDeclStub stub = o.getStub();
        if (stub != null) return StringUtil.notNullize(stub.getName());
        return getIdentifier(o).getName();
    }

    @Nullable
    public static PsiElement getNameIdentifier(@NotNull DLangConditionVarDeclaration o) {
        ASTNode keyNode = o.getNode();
        return keyNode != null ? keyNode.getPsi() : null;
    }

    @NotNull
    public static PsiElement setName(@NotNull DLangConditionVarDeclaration o, @NotNull String newName) {
        PsiElement e = DElementFactory.createDLangIdentifierFromText(o.getProject(), newName);
        getIdentifier(o).replace(e);
        return o;
    }

    @NotNull
    public static PsiReference getReference(@NotNull DLangConditionVarDeclaration o) {
        return new DReference(o, TextRange.from(0, getName(o).length()));
    }

    @NotNull
    public static ItemPresentation getPresentation(final DLangConditionVarDeclaration o) {
        return new ItemPresentation() {
            @NotNull
            @Override
            public String getPresentableText() {
                return o.getName();
            }

            /**
             * This is needed to decipher between files when resolving multiple references.
             */
            @Nullable
            @Override
            public String getLocationString() {
                final PsiFile psiFile = o.getContainingFile();
                return psiFile instanceof DLangFile ? ((DLangFile) psiFile).getModuleOrFileName() : null;
            }

            @Nullable
            @Override
            public Icon getIcon(boolean unused) {
                return DLangIcons.FILE;
            }
        };
    }

    // ------------ Condition Auto Declaration ----------------- //

    // ------------ Condition Var Declarator ----------------- //

    @NotNull
    private static DLangIdentifier getIdentifier(@NotNull DLangConditionVarDeclarator o) {
        return getIdentifier(o.getDeclarator());
    }

    @NotNull
    private static DLangIdentifier getIdentifier(DLangDeclarator declarator) {
        if (declarator.getVarDeclarator() != null) {
            return declarator.getVarDeclarator().getIdentifier();
        }
        return getIdentifier(declarator.getAltDeclarator());
    }

    @NotNull
    public static String getName(@NotNull DLangConditionVarDeclarator o) {
        DLangConditionVarDeclaratorStub stub = o.getStub();
        if (stub != null) return StringUtil.notNullize(stub.getName());
        return getIdentifier(o).getName();
    }

    @Nullable
    public static PsiElement getNameIdentifier(@NotNull DLangConditionVarDeclarator o) {
        ASTNode keyNode = o.getNode();
        return keyNode != null ? keyNode.getPsi() : null;
    }

    @NotNull
    public static PsiElement setName(@NotNull DLangConditionVarDeclarator o, @NotNull String newName) {
        PsiElement e = DElementFactory.createDLangIdentifierFromText(o.getProject(), newName);
        getIdentifier(o).replace(e);
        return o;
    }

    @NotNull
    public static PsiReference getReference(@NotNull DLangConditionVarDeclarator o) {
        return new DReference(o, TextRange.from(0, getName(o).length()));
    }

    @NotNull
    public static ItemPresentation getPresentation(final DLangConditionVarDeclarator o) {
        return new ItemPresentation() {
            @NotNull
            @Override
            public String getPresentableText() {
                return o.getName();
            }

            /**
             * This is needed to decipher between files when resolving multiple references.
             */
            @Nullable
            @Override
            public String getLocationString() {
                final PsiFile psiFile = o.getContainingFile();
                return psiFile instanceof DLangFile ? ((DLangFile) psiFile).getModuleOrFileName() : null;
            }

            @Nullable
            @Override
            public Icon getIcon(boolean unused) {
                return DLangIcons.FILE;
            }
        };
    }

    // ------------ Condition Auto Declarator ----------------- //

    // ------------ Catch Parameter ----------------- //

    @NotNull
    private static DLangIdentifier getIdentifier(@NotNull DLangCatchParameter o) {
        return o.getIdentifier();
    }

    @NotNull
    public static String getName(@NotNull DLangCatchParameter o) {
        DLangCatchParameterStub stub = o.getStub();
        if (stub != null) return StringUtil.notNullize(stub.getName());
        return getIdentifier(o).getName();
    }

    @Nullable
    public static PsiElement getNameIdentifier(@NotNull DLangCatchParameter o) {
        ASTNode keyNode = o.getNode();
        return keyNode != null ? keyNode.getPsi() : null;
    }

    @NotNull
    public static PsiElement setName(@NotNull DLangCatchParameter o, @NotNull String newName) {
        PsiElement e = DElementFactory.createDLangIdentifierFromText(o.getProject(), newName);
        getIdentifier(o).replace(e);
        return o;
    }

    @NotNull
    public static PsiReference getReference(@NotNull DLangCatchParameter o) {
        return new DReference(o, TextRange.from(0, getName(o).length()));
    }

    @NotNull
    public static ItemPresentation getPresentation(final DLangCatchParameter o) {
        return new ItemPresentation() {
            @NotNull
            @Override
            public String getPresentableText() {
                return o.getName();
            }

            /**
             * This is needed to decipher between files when resolving multiple references.
             */
            @Nullable
            @Override
            public String getLocationString() {
                final PsiFile psiFile = o.getContainingFile();
                return psiFile instanceof DLangFile ? ((DLangFile) psiFile).getModuleOrFileName() : null;
            }

            @Nullable
            @Override
            public Icon getIcon(boolean unused) {
                return DLangIcons.FILE;
            }
        };
    }

    // ------------ Catch Parameter ----------------- //

    // -------------------- Visibility --------------------- //

    public static boolean isSomeVisibility(DLangAliasDeclaration o, Visibility visibility) {
        final DLangAttributeSpecifier attribute = (DLangAttributeSpecifier) o.getParent().getParent().getParent();
        if (attribute.getAttribute().getProtectionAttribute() != null) {
            if (attribute.getAttribute().getProtectionAttribute().getText().equals(visibility))//todo iterate
                return true;
        }
        return isSomeVisibility(o, visibility, AliasContainer.class);

    }

    public static boolean isSomeVisibility(DLangEnumDeclaration o, Visibility visibility) {
        return isSomeVisibility(o, visibility, EnumContainer.class);

    }

    public static boolean isSomeVisibility(DLangModuleDeclaration o, Visibility visibility) {
        if (o.getAttribute() != null)
            if (o.getAttribute().getProtectionAttribute() != null)
                if (protectionToVisibilty(o.getAttribute().getProtectionAttribute()) == (visibility))
                    return true;
        return visibility == protectionToVisibilty("public");
    }

    public static boolean isSomeVisibility(DNamedElement psiElement, Visibility visibility, Class<? extends Container> containerType) {
        //todo fix
        return false;
    }
//        PsiElement parent = psiElement.getParent();
//        while (true) {
//            //default to public
//            if (containerType.isInstance(parent)) {
//                return visibility == public_;
//            }
//            // check that named element isn't explicitly marked some visibilty, eg. private gh();
//            if (parent instanceof DLangDeclDef && ((DLangDeclDef) parent).getAttributeSpecifier() != null) {
//                final DLangAttributeSpecifier attribute = ((DLangDeclDef) parent).getAttributeSpecifier();
//                if (attribute.getAttribute().getProtectionAttribute() != null) {
//                    try {
//                        return protectionToVisibilty(attribute.getAttribute().getProtectionAttribute()) == (visibility);
//                    } catch (IllegalArgumentException e) {
//                        return false;//todo
//                    }
//                }
//            }
//            if (parent instanceof DLangDeclDef && ((DLangDeclDefs) parent).getDeclDef().getAttributeSpecifier() != null) {
//                final DLangAttributeSpecifier attribute = ((DLangDeclDefs) parent).getDeclDef().getAttributeSpecifier();
//                if (attribute.getAttribute().getProtectionAttribute() != null && attribute.getOpColon() != null) {
//                    try {
//                        return protectionToVisibilty(attribute.getAttribute().getProtectionAttribute()) == (visibility);
//                    } catch (IllegalArgumentException e) {
//                        return false;
//                    }
//                }
//            }
//            if(parent instanceof DLangDeclDef)
//                parent = parent.getPrevSibling();
//            else
//                parent = parent.getParent();
////            //check for public: or private: or protected:
////            if (parent instanceof DLangDeclDefs && ((DLangDeclDefs) parent).getDeclDef().getAttributeSpecifier() != null) {
////                final DLangAttributeSpecifier attribute = ((DLangDeclDefs) parent).getDeclDef().getAttributeSpecifier();
////                if (attribute.getAttribute().getProtectionAttribute() != null && attribute.getOpColon() != null) {
////                    try {
////                        return protectionToVisibilty(attribute.getAttribute().getProtectionAttribute()) == (visibility);
////                    } catch (IllegalArgumentException e) {
////                        return false;
////                    }
////                }
////            }
////            parent = parent.getParent();
////        }
//    }

    public static boolean isSomeVisibility(DLangTemplateDeclaration o, Visibility visibility) {
        return isSomeVisibility(o, visibility, TemplateContainer.class);
    }

    public static boolean isSomeVisibility(DLangInterfaceDeclaration o, Visibility visibility) {
        return isSomeVisibility(o, visibility, InterfaceContainer.class);
    }

    public static boolean isSomeVisibility(DLangClassDeclaration o, Visibility visibility) {
        return isSomeVisibility(o, visibility, ClassContainer.class);
    }

    public static boolean isSomeVisibility(DLangStructDeclaration o, Visibility visibility) {
        return isSomeVisibility(o, visibility, StructContainer.class);
    }

    public static boolean isSomeVisibility(DLangConstructor o, Visibility visibility) {
        return isSomeVisibility(o, visibility, ConstructorContainer.class);
    }

    public static boolean isSomeVisibility(DLangFuncDeclaration o, Visibility visibility) {
        return isSomeVisibility(o, visibility, FunctionContainer.class);
    }

    public static boolean isSomeVisibility(DLangStaticConstructor o, Visibility visibility) {
        return isSomeVisibility(o, visibility, ConstructorContainer.class);//todo convert to static constructor container
    }

    public static boolean isSomeVisibility(DLangSharedStaticConstructor o, Visibility visibility) {
        return isSomeVisibility(o, visibility, ConstructorContainer.class);//todo convert to static constructor container
    }

    public static boolean isSomeVisibility(DLangDestructor o, Visibility visibility) {
        return isSomeVisibility(o, visibility, DestructorContainer.class);
    }

    public static boolean isSomeVisibility(DLangStaticDestructor o, Visibility visibility) {
        return isSomeVisibility(o, visibility, DestructorContainer.class);//todo convert to static destructor container
    }

    public static boolean isSomeVisibility(DLangSharedStaticDestructor o, Visibility visibility) {
        return isSomeVisibility(o, visibility, DestructorContainer.class);//todo convert to static destructor container
    }

    public static boolean isSomeVisibility(VariableDeclaration o, Visibility visibility) {
        return isSomeVisibility(o, visibility, GlobalVariableContainer.class);//todo check that this still works correctly for local vars/ do we care if local vars don't have correct visibility?
    }

    public static boolean isSomeVisibility(DLangUnionDeclaration o, Visibility visibility) {
        return isSomeVisibility(o, visibility, UnionContainer.class);//todo check that this still works correctly for local vars/ do we care if local vars don't have correct visibility?
    }

    public static boolean isSomeVisibility(DLangTemplateMixinDeclaration o, Visibility visibility) {
        return isSomeVisibility(o, visibility, TemplateMixinContainer.class);
    }

    public static boolean isSomeVisibility(DLangImport o, Visibility visibility) {
        return isSomeVisibility(o, visibility, ImportContainer.class);
    }

    // -------------------- Visibility --------------------- //

    // -------------------- Scope processing --------------- //

    /**
     * takes the elements declared in the given psi and passes them to the scope processor via the execute method. The scope processor will return false if it has found what it is "looking for". Note that certain declarations processors will not process child block statement/aggregate bodies since those have their own processor.
     * Some of the process declarations methods may return early while others will search throught the entire scope
     *
     * @param element
     * @param processor
     * @param state
     * @param lastParent todo mae use of this do determine if scope statements/decldefs contained inside a element should be processed or not.
     * @param place
     * @return
     */
    public static boolean processDeclarations(DLangFuncDeclaration element, @NotNull PsiScopeProcessor processor,
                                              @NotNull ResolveState state,
                                              PsiElement lastParent,
                                              @NotNull PsiElement place) {
        //todo handle place
        boolean shouldContinue = true;
        for (DLangParameter parameter : element.getArguments()) {
            if (!processor.execute(parameter, state)) {
                shouldContinue = false;
            }
        }
        for (DLangTemplateParameter templateParameter : element.getTemplateArguments()) {
            if (!processor.execute(templateParameter, state)) {
                shouldContinue = false;
            }
        }
        return shouldContinue;
    }

    public static boolean processDeclarations(DLangForeachStatement element, @NotNull PsiScopeProcessor processor,
                                              @NotNull ResolveState state,
                                              PsiElement lastParent,
                                              @NotNull PsiElement place) {
        //todo handle place
        boolean shouldContinue = true;
        for (DLangForeachType foreachType : element.getForeachTypeList().getForeachTypeList()) {
            if (!processor.execute(foreachType, state)) {
                shouldContinue = false;
            }
        }
        return shouldContinue;
    }

    public static boolean processDeclarations(DLangWhileStatement element, @NotNull PsiScopeProcessor processor,
                                              @NotNull ResolveState state,
                                              PsiElement lastParent,
                                              @NotNull PsiElement place) {
        //todo should handle place
        return true;//todo check that while statement's can/can't contain truthy/falsy variable declarations or casts
    }

    public static boolean processDeclarations(DLangForStatement element, @NotNull PsiScopeProcessor processor,
                                              @NotNull ResolveState state,
                                              PsiElement lastParent,
                                              @NotNull PsiElement place) {
        //todo handle place
        if (element.getInitialize() == null || element.getInitialize().getStatement() == null)
            return true;
        return element.getInitialize().getStatement().processDeclarations(processor, state, lastParent, place);
    }

    public static boolean processDeclarations(DLangDoStatement element, @NotNull PsiScopeProcessor processor,
                                              @NotNull ResolveState state,
                                              PsiElement lastParent,
                                              @NotNull PsiElement place) {
        return true;//todo check that while statement's can/can't contain truthy/falsy variable declarations or casts
    }

    public static boolean processDeclarations(DLangIfStatement element, @NotNull PsiScopeProcessor processor,
                                              @NotNull ResolveState state,
                                              PsiElement lastParent,
                                              @NotNull PsiElement place) {
        boolean shouldContinue = true;
        if (element.getIfCondition() != null) {
            final DLangIfCondition condition = element.getIfCondition();
            if (condition.getConditionAutoDeclaration() != null) {
                if (!processor.execute(condition.getConditionAutoDeclaration(), state)) {
                    shouldContinue = false;
                }
            } else if (condition.getConditionVarDeclaration() != null) {
                if (!processor.execute(condition.getConditionVarDeclaration(), state)) {
                    shouldContinue = false;
                }
            } else if (condition.getConditionVarDeclarator() != null) {
                if (!processor.execute(condition.getConditionVarDeclarator(), state)) {
                    shouldContinue = false;
                }
            }
        }
        return shouldContinue;
    }

    public static boolean processDeclarations(DLangBlockStatement element, @NotNull PsiScopeProcessor processor,
                                              @NotNull ResolveState state,
                                              PsiElement lastParent,
                                              @NotNull PsiElement place) {
        //todo handle place
        //todo do statements
        if (element.getStatementList() != null) {
            for (DLangStatement dLangStatement : element.getStatementList().getStatementList()) {
                if (!dLangStatement.processDeclarations(processor, state, lastParent, place)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean processDeclarations(DLangSwitchStatement element, @NotNull PsiScopeProcessor processor,
                                              @NotNull ResolveState state,
                                              PsiElement lastParent,
                                              @NotNull PsiElement place) {
        //todo truthy types in switch statement???//should declarations in the switch scope statementbe processed or do they go out of scope
        return true;
    }

    public static boolean processDeclarations(DLangFinalSwitchStatement element, @NotNull PsiScopeProcessor processor,
                                              @NotNull ResolveState state,
                                              PsiElement lastParent,
                                              @NotNull PsiElement place) {
        return true;//see regular switch statement
    }

    public static boolean processDeclarations(DLangWithStatement element, @NotNull PsiScopeProcessor processor,
                                              @NotNull ResolveState state,
                                              PsiElement lastParent,
                                              @NotNull PsiElement place) {
        return true;//todo I don't actually know how with statements work in D
    }

    public static boolean processDeclarations(DLangSynchronizedStatement element, @NotNull PsiScopeProcessor processor,
                                              @NotNull ResolveState state,
                                              PsiElement lastParent,
                                              @NotNull PsiElement place) {
        return true; //how does scope work in synchronized D statements
    }

    public static boolean processDeclarations(DLangCatch element, @NotNull PsiScopeProcessor processor,
                                              @NotNull ResolveState state,
                                              PsiElement lastParent,
                                              @NotNull PsiElement place) {
        return processor.execute(element.getCatchParameter(), state);
    }

    public static boolean processDeclarations(DLangForeachRangeStatement element, @NotNull PsiScopeProcessor processor,
                                              @NotNull ResolveState state,
                                              PsiElement lastParent,
                                              @NotNull PsiElement place) {
        //see foreach
        return processor.execute(element.getForeachType(), state);
    }

    public static boolean processDeclarations(DLangConditionalStatement element, @NotNull PsiScopeProcessor processor,
                                              @NotNull ResolveState state,
                                              PsiElement lastParent,
                                              @NotNull PsiElement place) {
        //always recurse in since this is a compile time condition
        //handle place and lastParent
        for (DLangStatement dLangStatement : element.getStatementList()) {
            if (!dLangStatement.processDeclarations(processor, state, lastParent, place)) {
                return false;
            }
        }
        for (DLangDeclarationBlock block : element.getDeclarationBlockList()) {
            if (block.processDeclarations(processor, state, lastParent, place)) {
                return false;
            }
        }
        return true;
    }


    public static boolean processDeclarations(DLangDeclarationBlock element, @NotNull PsiScopeProcessor processor,
                                              @NotNull ResolveState state,
                                              PsiElement lastParent,
                                              @NotNull PsiElement place) {
        if (element.getDeclDef() != null) {
            if (!element.getDeclDef().processDeclarations(processor, state, lastParent, place)) {
                return false;
            }
        }
        if (element.getDeclDefs() != null) {
            for (DLangDeclDef def : element.getDeclDefs().getDeclDefList()) {
                if (!def.processDeclarations(processor, state, lastParent, place)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean processDeclarations(DLangDeclDef def, @NotNull PsiScopeProcessor processor,
                                              @NotNull ResolveState state,
                                              PsiElement lastParent,
                                              @NotNull PsiElement place) {
        if (def.getDeclaration() != null) {
            if (!def.getDeclaration().processDeclarations(processor, state, lastParent, place)) {
                return false;
            }
        }
        if (def.getConstructor() != null) {
            //todo make sure names for constructors are handled correctly
            processor.execute(def.getConstructor(), state);
        }
        if (def.getAliasThis() != null) {
            //todo handle alias this
            //processor.execute(def.getAliasThis(), state);
        }
        if (def.getConditionalDeclaration() != null) {
            processor.execute(def.getConditionalDeclaration(), state);
        }
        if (def.getTemplateDeclaration() != null) {
            processor.execute(def.getTemplateDeclaration(), state);
        }
        if (def.getTemplateMixinDeclaration() != null) {
            processor.execute(def.getTemplateMixinDeclaration(), state);
        }
        if (def.getTemplateMixin() != null) {
            //todo handle mixins
            //processor.execute(def.getTemplateMixin(), state);
        }
        if (def.getMixinDeclaration() != null) {
            //todo handle mixins
            //processor.execute(def.getMixinDeclaration(), state);
        }
        if (def.getStaticIfCondition() != null) {
            if (def.getStaticIfCondition() != null) {
                if (def.getStaticIfCondition().getDeclarationBlock() != null) {
                    if (!(def.getStaticIfCondition().getDeclarationBlock().processDeclarations(processor, state, lastParent, place))) {
                        return false;
                    }
                }
            }
        }
        if (def.getStaticElseCondition() != null) {
            if (def.getStaticElseCondition().getDeclarationBlock() != null) {
                if (!(def.getStaticElseCondition().getDeclarationBlock().processDeclarations(processor, state, lastParent, place))) {
                    return false;
                }
            }
        }
        return true;
    }


    public static boolean processDeclarations(DLangConditionalDeclaration element, @NotNull PsiScopeProcessor processor,
                                              @NotNull ResolveState state,
                                              PsiElement lastParent,
                                              @NotNull PsiElement place) {
        for (DLangDeclarationBlock block : element.getDeclarationBlockList()) {
            if (!block.processDeclarations(processor, state, lastParent, place)) {
                return false;
            }
        }
        if (element.getDeclDefs() != null) {
            for (DLangDeclDef def : element.getDeclDefs().getDeclDefList()) {
                if (!def.processDeclarations(processor, state, lastParent, place)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean processDeclarations(DLangClassDeclaration element, @NotNull PsiScopeProcessor processor,
                                              @NotNull ResolveState state,
                                              PsiElement lastParent,
                                              @NotNull PsiElement place) {
        if (element.getClassTemplateDeclaration() != null) {
            if (element.getClassTemplateDeclaration().getTemplateParameters().getTemplateParameterList() != null) {
                for (DLangTemplateParameter templateParameter : element.getClassTemplateDeclaration().getTemplateParameters().getTemplateParameterList().getTemplateParameterList()) {
                    if (!processor.execute(templateParameter, state)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static boolean processDeclarations(DLangTemplateDeclaration element, @NotNull PsiScopeProcessor processor,
                                              @NotNull ResolveState state,
                                              PsiElement lastParent,
                                              @NotNull PsiElement place) {
        if (element.getTemplateParameters() != null) {
            if (element.getTemplateParameters().getTemplateParameterList() != null) {
                for (DLangTemplateParameter templateParameter : element.getTemplateParameters().getTemplateParameterList().getTemplateParameterList()) {
                    if (!processor.execute(templateParameter, state)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static boolean processDeclarations(DLangAggregateBody element, @NotNull PsiScopeProcessor processor,
                                              @NotNull ResolveState state,
                                              PsiElement lastParent,
                                              @NotNull PsiElement place) {
        if (element.getDeclDefs() != null) {
            for (DLangDeclDef declDef : element.getDeclDefs().getDeclDefList()) {
                if (!declDef.processDeclarations(processor, state, lastParent, place)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean processDeclarations(DLangStatement element, @NotNull PsiScopeProcessor processor,
                                              @NotNull ResolveState state,
                                              PsiElement lastParent,
                                              @NotNull PsiElement place) {
        if (element.getBlockStatement() != null) {

        }
        if (element.getNonEmptyStatement() != null) {
            final DLangNonEmptyStatement nonEmptyStatement = element.getNonEmptyStatement();
            if (nonEmptyStatement.getCaseRangeStatement() != null) {

            }
            if (nonEmptyStatement.getCaseStatement() != null) {

            }
            if (nonEmptyStatement.getDefaultStatement() != null) {

            }
            if (nonEmptyStatement.getNonEmptyStatementNoCaseNoDefault() != null) {
                final DLangNonEmptyStatementNoCaseNoDefault statement = nonEmptyStatement.getNonEmptyStatementNoCaseNoDefault();
                if (statement.getLabeledStatement() != null) {
                    processor.execute(statement.getLabeledStatement(), state);//todo labeled statements should be DNamedElements
                    if (statement.getLabeledStatement().getStatement() != null) {
                        statement.getLabeledStatement().getStatement().processDeclarations(processor, state, lastParent, place);
                    }
                }
                if (statement.getDeclarationStatement() != null) {
                    statement.getDeclarationStatement().getDeclaration().processDeclarations()
                }
                if (statement.getBlockStatement() != null) {
                    processor.execute(statement.getBlockStatement(), state);
                }
                if (statement.getIfStatement() != null) {
                    processor.execute(statement.getIfStatement(), state);
                }
                if (statement.getWhileStatement() != null) {
                    processor.execute(statement.getWhileStatement(), state);
                }
                if (statement.getDoStatement() != null) {
                    processor.execute(statement.getDoStatement(), state);
                }
                if (statement.getForStatement() != null) {
                    processor.execute(statement.getForStatement(), state);
                }
                if (statement.getForeachStatement() != null) {
                    processor.execute(statement.getForeachStatement(), state);
                }
                if (statement.getSwitchStatement() != null) {
                    processor.execute(statement.getSwitchStatement(), state);
                }
                if (statement.getFinalSwitchStatement() != null) {
                    processor.execute(statement.getFinalSwitchStatement(), state);
                }
                if (statement.getContinueStatement() != null) {
                    processor.execute(statement.getContinueStatement(), state);
                }
                if (statement.getBreakStatement() != null) {
                    processor.execute(statement.getBreakStatement(), state);
                }
                if (statement.getReturnStatement() != null) {
                    processor.execute(statement.getReturnStatement(), state);
                }
                if (statement.getGotoStatement() != null) {
                    processor.execute(statement.getGotoStatement(), state);
                }
                if (statement.getWithStatement() != null) {
                    processor.execute(statement.getWithStatement(), state);
                }
                if (statement.getSynchronizedStatement() != null) {
                    processor.execute(statement.getSynchronizedStatement(), state);
                }
                if (statement.getTryStatement() != null) {
                    processor.execute(statement.getTryStatement(), state);
                }
                if (statement.getScopeGuardStatement() != null) {
                    processor.execute(statement.getScopeGuardStatement(), state);
                }
                if (statement.getThrowStatement() != null) {
                    processor.execute(statement.getThrowStatement(), state);
                }
                if (statement.getAsmStatement() != null) {
                    processor.execute(statement.getAsmStatement(), state);
                }
                if (statement.getPragmaStatement() != null) {
                    processor.execute(statement.getPragmaStatement(), state);
                }
                if (statement.getMixinStatement() != null) {
                    processor.execute(statement.getMixinStatement(), state);
                }
                if (statement.getForeachRangeStatement() != null) {
                    processor.execute(statement.getForeachRangeStatement(), state);
                }
                if (statement.getConditionalStatement() != null) {
                    processor.execute(statement.getConditionalStatement(), state);
                }
                if (statement.getExpressionStatement() != null) {
                    processor.execute(statement.getExpressionStatement(), state);
                }

            }
        }
    }

    public static boolean processDeclarations(DLangDeclaration element, @NotNull PsiScopeProcessor processor,
                                              @NotNull ResolveState state,
                                              PsiElement lastParent,
                                              @NotNull PsiElement place) {
        if (element.getEnumDeclaration() != null) {
            //todo members
            if (!processor.execute(element.getEnumDeclaration(), state)) {
                return false;
            }
        }
        if (element.getFuncDeclaration() != null) {
            if (!processor.execute(element.getFuncDeclaration(), state)) {
                return false;
            }
        }
        if (element.getVarDeclarations() != null) {
            if (!processor.execute(element.getVarDeclarations(), state)) {
                return false;
            }
        }
        if (element.getAliasDeclaration() != null) {
            if (!processor.execute(element.getAliasDeclaration(), state)) {
                return false;
            }
        }
        if (element.getAggregateDeclaration() != null) {
            //todo
        }
        if (element.getImportDeclaration() != null) {
            if (!processor.execute(element.getImportDeclaration(), state)) {
                return false;
            }
        }
        if (element.getTemplateDeclaration() != null) {
            //todo
        }
    }

    /*public static boolean processDeclarations(DLangStructDeclaration element, @NotNull PsiScopeProcessor processor,
                                              @NotNull ResolveState state,
                                              PsiElement lastParent,
                                              @NotNull PsiElement place) {

    }

    public static boolean processDeclarations(DLangTemplateMixinDeclaration element, @NotNull PsiScopeProcessor processor,
                                              @NotNull ResolveState state,
                                              PsiElement lastParent,
                                              @NotNull PsiElement place) {

    }*/
    // -------------------- Scope processing --------------- //


    // -------------------- Misc --------------------- //
    public static String getFullName(DNamedElement e) {
        if (e == null)
            return "";
        if (e instanceof DLangFile)
            return getFullName(e.getParentContainer()) + "." + e.getName().replace(".d", "");
        return getFullName(e.getParentContainer()) + "." + e.getName();
    }
    // -------------------- Misc --------------------- //


}

