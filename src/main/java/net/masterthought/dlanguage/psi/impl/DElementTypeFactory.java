package net.masterthought.dlanguage.psi.impl;

import com.intellij.psi.tree.IElementType;
import net.masterthought.dlanguage.stubs.types.*;
import org.jetbrains.annotations.NotNull;

public class DElementTypeFactory {
    private DElementTypeFactory(){}

    public static IElementType factory(@NotNull String name){
        if (name.equals("IDENTIFIER")) return new DLangIdentifierStubElementType(name);
        if (name.equals("FUNC_DECLARATION")) return new DLangFuncDeclStubElementType(name);
        if (name.equals("CLASS_DECLARATION")) return new DLangClassDeclStubElementType(name);
        if (name.equals("TEMPLATE_DECLARATION")) return new DLangTemplateDeclStubElementType(name);
        if (name.equals("CONSTRUCTOR")) return new DLangConstructorStubElementType(name);
        if (name.equals("DESTRUCTOR")) return new DLangDestructorStubElementType(name);
        if (name.equals("STRUCT_DECLARATION")) return new DLangStructDeclStubElementType(name);
        if (name.equals("ALIAS_DECLARATION")) return new DLangAliasStubElementType(name);
        if (name.equals("MODULE_DECLARATION")) return new DLangModuleDeclStubElementType(name);
        if (name.equals("INTERFACE_DECLARATION")) return new DLangInterfaceDeclStubElementType(name);
        if (name.equals("DECLARATOR_INITIALIZER")) return new DLangDeclaratorInitializerStubElementType(name);
        if (name.equals("LABELED_STATEMENT")) return new DLangLabeledStatementStubElementType(name);
        if (name.equals("TEMPLATE_MIXIN_DECLARATION")) return new DLangTemplateMixinDeclStubElementType(name);
        if (name.equals("SHARED_STATIC_CONSTRUCTOR")) return new DLangSharedStaticConstructorStubElementType(name);
        if (name.equals("SHARED_STATIC_DESTRUCTOR")) return new DLangSharedStaticDestructorStubElementType(name);
        if (name.equals("STATIC_CONSTRUCTOR")) return new DLangStaticConstructorStubElementType(name);
        if (name.equals("STATIC_DESTRUCTOR")) return new DLangStaticDestructorStubElementType(name);
        if (name.equals("AUTO_DECLARATION_Y")) return new DLangAutoDeclStubElementType(name);
        if (name.equals("ENUM_DECLARATION")) return new DLangEnumDeclStubElementType(name);
        if (name.equals("UNION_DECLARATION")) return new DLangUnionDeclStubElementType(name);
        if (name.equals("IMPORT")) return new DLangImportDeclStubElementType(name);
        if (name.equals("UNIT_TESTING")) return new UnitTestingStubElementType(name);
        if (name.equals("TEMPLATE_PARAMETER")) return new DLangTemplateParameterStubElementType(name);
        if (name.equals("PARAMETER")) return new DLangParameterStubElementType(name);
        if (name.equals("FOREACH_TYPE")) return new DLangForeachTypeStubElementType(name);
        if (name.equals("CONDITION_VAR_DECLARATOR")) return new DLangConditionVarDeclaratorStubElementType(name);
        if (name.equals("CONDITION_AUTO_DECLARATION")) return new DLangConditionAutoDeclStubElementType(name);
        if (name.equals("CONDITION_VAR_DECLARATION")) return new DLangConditionVarDeclStubElementType(name);
        if (name.equals("CATCH_PARAMETER")) return new DLangCatchParameterStubElementType(name);
        throw new RuntimeException("Unknown element type: " + name);
    }
}
