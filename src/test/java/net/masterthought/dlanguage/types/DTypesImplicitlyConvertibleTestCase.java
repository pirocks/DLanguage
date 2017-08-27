package net.masterthought.dlanguage.types;

import net.masterthought.dlanguage.psi.DLanguageType;

/**
 * Created by francis on 8/19/2017.
 */
public abstract class DTypesImplicitlyConvertibleTestCase extends DTypesTestCase {
    /**
     * Sets the expected input and outputs and calls the constructor of the parent.
     */
    protected DTypesImplicitlyConvertibleTestCase() {
        super("types/convertible/", "types/convertible/");
    }

    protected void doTest(final int offseta, final int offsetb, final boolean succeed) {
        final DLanguageType typea = getTypeFromOffset(offseta, psiFile);
        final DLanguageType typeb = getTypeFromOffset(offsetb, psiFile);
        assertNotNull(typea);
        assertNotNull(typeb);
        assertTrueWithSucceed("Match failure", DTypeUtilsKt.from(typea, false).implicitlyConvertibleTo(DTypeUtilsKt.from(typeb, false)) == Match.exact, succeed);
        assertTrueWithSucceed("Match failure", DTypeUtilsKt.from(typea, true).implicitlyConvertibleTo(DTypeUtilsKt.from(typeb, true)) == Match.exact, succeed);
        assertTrueWithSucceed("Match failure", DTypeUtilsKt.from(typea, false).implicitlyConvertibleTo(DTypeUtilsKt.from(typeb, true)) == Match.exact, succeed);
        assertTrueWithSucceed("Match failure", DTypeUtilsKt.from(typea, true).implicitlyConvertibleTo(DTypeUtilsKt.from(typeb, false)) == Match.exact, succeed);
    }

}
