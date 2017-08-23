package net.masterthought.dlanguage.types;

import net.masterthought.dlanguage.psi.DLanguageType;

/**
 * Created by francis on 8/19/2017.
 */
public abstract class DTypesImplicitlyAssignableTestCase extends DTypesTestCase {
    /**
     * Sets the expected input and outputs and calls the constructor of the parent.
     */
    protected DTypesImplicitlyAssignableTestCase() {
        super("types/assignable/", "types/assignable/");
    }

    protected void doTest(final int offseta, final int offsetb, final boolean succeed) {
        final DLanguageType typea = getTypeFromOffset(offseta, psiFile);
        final DLanguageType typeb = getTypeFromOffset(offsetb, psiFile);
        assertNotNull(typea);
        assertNotNull(typeb);
        assertTrueWithSucceed("Match failure", TypeUtilsKt.from(typea, false).implicitlyAssignableTo(TypeUtilsKt.from(typeb, false)) == Match.exact, succeed);
        assertTrueWithSucceed("Match failure", TypeUtilsKt.from(typea, true).implicitlyAssignableTo(TypeUtilsKt.from(typeb, true)) == Match.exact, succeed);
        assertTrueWithSucceed("Match failure", TypeUtilsKt.from(typea, false).implicitlyAssignableTo(TypeUtilsKt.from(typeb, true)) == Match.exact, succeed);
        assertTrueWithSucceed("Match failure", TypeUtilsKt.from(typea, true).implicitlyAssignableTo(TypeUtilsKt.from(typeb, false)) == Match.exact, succeed);
    }
}
