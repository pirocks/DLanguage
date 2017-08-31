package net.masterthought.dlanguage.types;

/**
 * Created by francis on 8/19/2017.
 */
public class DTypesToTextTest extends DTypesToTextTestCase {
    public void testInt() {
        doTestVariable(0, "int");
        doTestVariable(17, "immutable(int)");
    }

    public void testIntPointer() {
        doTestVariable(0, "int*");
        doTestVariable(20, "immutable(int*)");
        doTestVariable(36, "const(const(int)*)");
        doTestVariable(47, "const(int)*");
        doTestVariable(63, "const(const(int)*)");
        doTestVariable(80, "const(const(int)*)");
    }

    public void testIntArray() {
        doTestVariable(0, "int[]");
    }

    public void testIntPointerArray() {
        doTestVariable(0, "int*[]");
    }

    public void testComplexIntPointerArray() {
        doTestVariable(0, "const(const(const(immutable const(int)*)[])[])*");
    }

    public void testAssocArray() {
        doTestVariable(0, "int[char*]");
    }

    public void testObjectDotD() {
//        doTest(0,"TypeInfo_Class");
        doTestBasic(25, "void*");
        doTestBasic(37, "void*[]");
        doTestBasic(54, "uint");
        doTestBasic(74, "int");
        doTestBasic(97, "ulong");
        doTestBasic(117, "immutable(char)[]");
        doTestBasic(150, "immutable(wchar)[]");
        doTestBasic(184, "immutable(dchar)[]");
//        doTest(219,"Object");
        doTestBasic(233, "const(void)[]");
        doTestBasic(250, "char[<len>]");
//        doTest(270,"void");
//        doTest(295,);
    }

    public void testFunction() {
        doTestVariable(0, "int function(char,char)");
        doTestVariable(31, "int function(char,char[])");
        doTestVariable(70, "immutable(int function(char,char[]))");
        doTestVariable(115, "const(int function(char,char[]))");
    }

    public void testDelegate() {
        doTestBasic(0, "int function(char,char) delegate(char[],immutable(int) function())");
    }

    public void testClassArray() {
        doTestVariable(0, "class_[]");
    }
}
