package net.masterthought.dlanguage.types;

/**
 * Created by francis on 8/22/2017.
 */
public class DTypesImplicitlyConvertibleTest extends DTypesImplicitlyConvertibleTestCase {
    public void testIntAndInt() {
        doTest(0, 7, true);
    }

    public void testTraits() {
        doTest(170,176,true);
        doTest(225,232,false);
    }
}
