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
     * providing the name of the member could speed things up, because stub indexes can be used.
     * @param name
     * @return
     */
    Set<DNamedElement> searchMemberDeclarations(String name);

    /**
     * returns true if member declaration exists. can return true for things like .front() or .dup(), where the declaration does not necessarily exist but the property does.
     *
     * @param name
     * @return
     */
    boolean hasMemberDeclarationOfName(String name);
}
