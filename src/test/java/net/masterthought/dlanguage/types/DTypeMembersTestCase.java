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
        super("types/members/", "types/members/");
    }

    protected void doTest(final int offest, final String[] members, final String[] resolvableMembers, final boolean succeed) {
        boolean threw = false;
        try {
            final DLanguageType type = getTypeFromOffset(offest, psiFile);
            assertNotNull(type);
            final DType dType = DTypeUtilsKt.from(type, true, (Mods) null);
            assertNotNull(dType);
            for (final String member : members) {
                assertTrue(dType.getTypeMembersProvider().hasMemberDeclarationOfName(member));
            }
            for (final String resolvableMember : resolvableMembers) {
                assertTrue("member does not exist", dType.getTypeMembersProvider().hasMemberDeclarationOfName(resolvableMember));
                final Set<DNamedElement> elements = dType.getTypeMembersProvider().searchMemberDeclarations(resolvableMember);
                assertTrue("multiple or noelements found", elements.size() == 1);
                if (!(resolvableMember.equals("super") || resolvableMember.equals("this"))) {
                    assertTrue("wrong name", Objects.equals(((DNamedElement) elements.toArray()[0]).getName(), resolvableMember));
                    assertTrue("no members had the correct name", dType.getTypeMembersProvider().getMemberDeclarations().stream().anyMatch(dNamedElement -> dNamedElement.getName().equals(resolvableMember)));
                }
            }
        } catch (final Throwable e) {
            if (succeed) {
                throw e;
            } else
                threw = true;
        }
        if (!succeed) {
            if (!threw)
                assertTrue("should have failed, but didn't", false);

        }
    }
}
