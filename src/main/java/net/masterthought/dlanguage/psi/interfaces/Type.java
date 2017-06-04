package net.masterthought.dlanguage.psi.interfaces;

import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import net.masterthought.dlanguage.psi.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by francis on 3/12/2017.
 */
public class Type {
    List<DLangType> types = new ArrayList<>(); // should normally be only one element
    List<DLangBasicType2> basicTypes2 = new ArrayList<>();
    List<DLangStorageClasses> storageClasses = new ArrayList<>();
    List<DLangBasicType> basicTypes = new ArrayList<>();

    public Type(List<DLangType> types, List<DLangBasicType2> basicTypes2, List<DLangStorageClasses> storageClasses, List<DLangBasicType> basicTypes) {
        this.types = types;
        this.basicTypes2 = basicTypes2;
        this.storageClasses = storageClasses;
        this.basicTypes = basicTypes;
    }

    /**
     * todo completely broken for complicated declarations
     *
     * @param initializer
     */
    public Type(DLangDeclaratorInitializer initializer) {
        types = PsiTreeUtil.getChildrenOfTypeAsList(initializer.getParent().getParent(), DLangType.class);
        basicTypes2 = PsiTreeUtil.getChildrenOfTypeAsList(initializer.getParent().getParent(), DLangBasicType2.class);
        storageClasses = PsiTreeUtil.getChildrenOfTypeAsList(initializer.getParent().getParent(), DLangStorageClasses.class);
        basicTypes = PsiTreeUtil.getChildrenOfTypeAsList(initializer.getParent().getParent(), DLangBasicType.class);
//        final DLangAltDeclarator altDeclarator = initializer.getAltDeclarator();
//        final DLangVarDeclarator varDeclarator = initializer.getVarDeclarator();
//        if(altDeclarator != null){
//            final DLangAltDeclaratorX altDeclaratorX = altDeclarator.getAltDeclaratorX();
//            final DLangAltDeclaratorSuffixes altDeclaratorSuffixes = altDeclarator.getAltDeclaratorSuffixes();
//            final DLangAltFuncDeclaratorSuffix altFuncDeclaratorSuffix = altDeclarator.getAltFuncDeclaratorSuffix();
//            final DLangBasicType2 basicType2 = altDeclarator.getBasicType2();
//            if(altDeclaratorX != null){
//                if(altDeclaratorX.getAltDeclarator() != null)
//                    throw new NotImplementedException();//todo
//                final DLangBasicType2 basicType21 = altDeclaratorX.getBasicType2();
//            }
//            if(altDeclaratorSuffixes != null){
//                altDeclaratorSuffixes.
//            }
//        }
//        else if (varDeclarator != null){
//
//        }
    }

    /**
     * if the type is one identifier eg. Type foo = null;, then return that identifier, instead return null
     *
     * @return
     */
    public DLangIdentifier isOneIdentifier() {
        List<DLangIdentifier> identifiers = new ArrayList<>();
        for (PsiElement element : types) {
            identifiers.addAll(PsiTreeUtil.findChildrenOfType(element, DLangIdentifier.class));
        }
        for (PsiElement element : basicTypes2) {
            identifiers.addAll(PsiTreeUtil.findChildrenOfType(element, DLangIdentifier.class));
        }
        for (PsiElement element : storageClasses) {
            identifiers.addAll(PsiTreeUtil.findChildrenOfType(element, DLangIdentifier.class));
        }
        for (PsiElement element : basicTypes) {
            identifiers.addAll(PsiTreeUtil.findChildrenOfType(element, DLangIdentifier.class));
        }
        if (identifiers.size() == 1)
            return identifiers.get(0);
        return null;
    }
}
