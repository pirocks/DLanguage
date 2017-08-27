package net.masterthought.dlanguage.types;

/**
 * Created by francis on 8/19/2017.
 */
public class DTypesToTextTest extends DTypesToTextTestCase {
    public void testInt() {
        doTest(0, "int");
    }

    public void testIntPointer() {
        doTest(0, "int*");
    }

    public void testIntArray() {
        doTest(0, "int[]");
    }

    public void testIntPointerArray() {
        doTest(0, "int*[]");
    }

    public void testComplexIntPointerArray() {
        doTest(0, "int*[][]*");
    }

    public void testAssocArray() {
        doTest(0, "int[char*]");
    }

    public void testObjectDotD() {
//        doTest(0,"TypeInfo_Class");
        doTest(25, "void*");
        doTest(37, "void*[]");
        doTest(54, "uint");
        doTest(74, "int");
        doTest(97, "ulong");
        doTest(117, "char[]");
        doTest(150, "wchar[]");
        doTest(184, "dchar[]");
//        doTest(219,"Object");
        doTest(233, "void[]");
        doTest(250, "char[<len>]");
//        doTest(270,"void");
//        doTest(295,);
    }

    public void testFunction() {
        doTest(0, "int function(char,char)");
        doTest(31, "int function(char,char[])");
    }

    public void testDelegate() {
        doTest(0, "int function(char,char) delegate(char[],int function())");
    }

    public void testArray() {
        doTest(0, "class_[]");
    }
}
