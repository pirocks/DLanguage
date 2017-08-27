package net.masterthought.dlanguage.types;

import net.masterthought.dlanguage.psi.DLanguageType;
import net.masterthought.dlanguage.psi.interfaces.DNamedElement;

import java.util.Objects;
import java.util.Set;

/**
 * Created by francis on 8/26/2017.
 */
public class DTypeMembersTestCase extends DTypesTestCase {
    /**
     * Sets the expected input and outputs and calls the constructor of the parent.
     */
    protected DTypeMembersTestCase() {
        super("members", "members");
    }

    protected void doTest(final int offest, final String[] members, final String[] resolvableMembers) {
        final DLanguageType type = getTypeFromOffset(offest, psiFile);
        assertNotNull(type);
        final DType dType = DTypeUtilsKt.from(type, true);
        assertNotNull(dType);
        for (final String member : members) {
            assertTrue(dType.getTypeMembersProvider().hasMemberDeclarationOfName(member));
        }
        for (final String resolvableMember : resolvableMembers) {
            assertTrue(dType.getTypeMembersProvider().hasMemberDeclarationOfName(resolvableMember));
            final Set<DNamedElement> elements = dType.getTypeMembersProvider().searchMemberDeclarations(resolvableMember);
            assertTrue("multiple elements found", elements.size() == 1);
            assertTrue("wrong name", Objects.equals(((DNamedElement) elements.toArray()[0]).getName(), resolvableMember));
            assertTrue("no members had the correct name", dType.getTypeMembersProvider().getMemberDeclarations().stream().anyMatch(dNamedElement -> dNamedElement.getName().equals(resolvableMember)));
        }


    }
}
