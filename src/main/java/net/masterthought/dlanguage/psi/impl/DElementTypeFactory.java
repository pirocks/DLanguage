package net.masterthought.dlanguage.psi.impl;

import com.intellij.psi.tree.IElementType;
import net.masterthought.dlanguage.stubs.types.*;
import org.jetbrains.annotations.NotNull;

public class DElementTypeFactory {
    private DElementTypeFactory(){}

    public static IElementType factory(@NotNull String name){
        if (name.equals("IDENTIFIER")) return new DLangIdentifierStubElementType(name);
        if (name.equals("FUNC_DECLARATION")) return new DLangFuncDeclarationStubElementType(name);
        if (name.equals("CLASS_DECLARATION")) return new DLangClassDeclarationStubElementType(name);
        if (name.equals("TEMPLATE_DECLARATION")) return new DLangTemplateDeclarationStubElementType(name);
        if (name.equals("CONSTRUCTOR")) return new DLangConstructorStubElementType(name);
        if (name.equals("DESTRUCTOR")) return new DLangDestructorStubElementType(name);
        if (name.equals("STRUCT_DECLARATION")) return new DLangStructDeclarationStubElementType(name);
        if (name.equals("ALIAS_DECLARATION")) return new DLangAliasDeclarationStubElementType(name);
        if (name.equals("MODULE_DECLARATION")) return new DLangModuleDeclarationStubElementType(name);
        if (name.equals("INTERFACE_DECLARATION")) return new DLangInterfaceDeclarationStubElementType(name);
        if (name.equals("DECLARATOR_INITIALIZER")) return new DLangDeclaratorInitializerStubElementType(name);
        if (name.equals("LABELED_STATEMENT")) return new DLangLabeledStatementStubElementType(name);
        if (name.equals("TEMPLATE_MIXIN_DECLARATION"))
            return new DLangTemplateMixinDeclarationStubElementType(name);
        if (name.equals("SHARED_STATIC_CONSTRUCTOR")) return new DLangSharedStaticConstructorStubElementType(name);
        if (name.equals("SHARED_STATIC_DESTRUCTOR")) return new DLangSharedStaticDestructorStubElementType(name);
        if (name.equals("STATIC_CONSTRUCTOR")) return new DLangStaticConstructorStubElementType(name);
        if (name.equals("STATIC_DESTRUCTOR")) return new DLangStaticDestructorStubElementType(name);
        if (name.equals("AUTO_DECLARATION_Y")) return new DLangAutoDeclarationStubElementType(name);
        if (name.equals("ENUM_DECLARATION")) return new DLangEnumDeclarationStubElementType(name);
        if (name.equals("UNION_DECLARATION")) return new DLangUnionDeclarationStubElementType(name);
        if (name.equals("IMPORT")) return new DLangImportDeclarationStubElementType(name);
        if (name.equals("UNIT_TESTING")) return new UnitTestingStubElementType(name);
        throw new RuntimeException("Unknown element type: " + name);
    }
}
