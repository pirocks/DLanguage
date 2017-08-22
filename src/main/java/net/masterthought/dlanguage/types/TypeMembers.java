package net.masterthought.dlanguage.types;

import net.masterthought.dlanguage.psi.interfaces.DNamedElement;

import java.util.Set;

/**
 * Created by francis on 8/21/2017.
 * Interface for accessing members of a type. Similar yes dotExp(), in the dmd compiler source.
 */
public interface TypeMembers {
    /**
     * @return all member decls, in theory without psi loading beyond stub backed psi
     */
    Set<DNamedElement> getMemberDeclarations();

    /**
     * @return stub backed types
     */
    Set<DType> getMemberTypes();

    Set<DNamedElement> searchMemberDeclarations(String name);

    Set<DType> searchMemberTypes(String name);

    Set<DNamedElement> getMemberDeclarations(String name);

    Set<DType> getMemberTypes(String name);

    boolean hasMemberDeclarations(String name);
}
