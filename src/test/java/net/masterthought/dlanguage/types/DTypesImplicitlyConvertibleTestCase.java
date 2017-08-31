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
        final Match resNoResolve = DTypeUtilsKt.from(typea, false, (Mods) null).implicitlyConvertibleTo(DTypeUtilsKt.from(typeb, false, (Mods) null));
        assertTrueWithSucceed("Match failure", resNoResolve == Match.exact || resNoResolve == Match.convert || resNoResolve == Match.constant, succeed);
        final Match resWithResolve = DTypeUtilsKt.from(typea, true, (Mods) null).implicitlyConvertibleTo(DTypeUtilsKt.from(typeb, true, (Mods) null));
        assertTrueWithSucceed("Match failure", resWithResolve == Match.exact || resWithResolve == Match.convert || resWithResolve == Match.constant, succeed);
        final Match resSomeResolve = DTypeUtilsKt.from(typea, false, (Mods) null).implicitlyConvertibleTo(DTypeUtilsKt.from(typeb, true, (Mods) null));
        assertTrueWithSucceed("Match failure", resSomeResolve == Match.exact || resSomeResolve == Match.convert || resSomeResolve == Match.constant, succeed);
        final Match resSomeResolve2 = DTypeUtilsKt.from(typea, true, (Mods) null).implicitlyConvertibleTo(DTypeUtilsKt.from(typeb, false, (Mods) null));
        assertTrueWithSucceed("Match failure", resSomeResolve2 == Match.exact || resSomeResolve2 == Match.convert || resSomeResolve2 == Match.constant, succeed);
    }

}
