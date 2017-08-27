package net.masterthought.dlanguage.types;

import org.junit.Ignore;

/**
 * Created by francis on 8/22/2017.
 */
@Ignore
public class DTypesImplicitlyAssignableTest extends DTypesImplicitlyAssignableTestCase {
    public void testIntAndInt() {
        doTest(0, 7, true);
    }

    public void testTraits(){
        doTest(29,35,true);
        doTest(71,76,false);
//        doTest(113,128,true);
        doTest(167,175,false);
    }

}
