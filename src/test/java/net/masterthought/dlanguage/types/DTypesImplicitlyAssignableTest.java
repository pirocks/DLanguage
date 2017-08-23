package net.masterthought.dlanguage.types;

/**
 * Created by francis on 8/22/2017.
 */
public class DTypesImplicitlyAssignableTest extends DTypesImplicitlyAssignableTestCase {
    public void testIntAndInt() {
        doTest(0, 7, true);
    }

}
