package net.masterthought.dlanguage.types;

/**
 * Created by francis on 8/26/2017.
 */
public class DTypeMembersTest extends DTypeMembersTestCase {
    public void testClassDeclaration() {
        doTest(0, new String[]{"sizeof", "alignof", "mangleof", "tupleof", "classinfo", "vptr", "monitor", "outer"}, new String[]{"toHash", "toString", "this", "super", "foo", "bar", "opCmp", "opEquals"});
    }
}
