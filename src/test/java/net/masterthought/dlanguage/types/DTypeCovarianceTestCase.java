package net.masterthought.dlanguage.types;

import net.masterthought.dlanguage.psi.DLanguageType;

import java.util.Collections;

/**
 * Created by francis on 9/4/2017.
 */
public class DTypeCovarianceTestCase extends DTypesTestCase {
    /**
     * Sets the expected input and outputs and calls the constructor of the parent.
     */
    protected DTypeCovarianceTestCase() {
        super("types/covariance/", "types/covariance/");
    }

    protected void doTest(final int offseta, final int offsetb, final boolean succeed) {
        final DLanguageType typea = getTypeFromOffset(offseta, psiFile);
        final DLanguageType typeb = getTypeFromOffset(offsetb, psiFile);
        assertNotNull(typea);
        assertNotNull(typeb);
        final DType dTypeA = DTypeUtilsKt.from(typea, true, (Mods) null);
        final DType dTypeB = DTypeUtilsKt.from(typeb, true, (Mods) null);//todo have a way of getting typeof a function/implement typeof
        final DTypeFunction dTypeFunctionA = new DTypeFunction(dTypeA, Collections.emptyList(), 0, new DTypeStorageClass(), LINK.def);
        final DTypeFunction dTypeFunctionB = new DTypeFunction(dTypeB, Collections.emptyList(), 0, new DTypeStorageClass(), LINK.def);
        assertTrueWithSucceed("Match failure", dTypeFunctionA.covariant(dTypeFunctionB) == 1, succeed);

    }
}
