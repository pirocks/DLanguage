package net.masterthought.dlanguage.types;

/**
 * Created by francis on 9/4/2017.
 */
public class DTypeCovarianceTest extends DTypeCovarianceTestCase {
    public void testBasicTest() {
        //sorry about all the magic constants. In short they refer to number of characters till next type.
        doTest(1093 + 1, 1214 + 1, false);//#1
        doTest(1698 + 1, 1819 + 1, false);//#2
        doTest(2303 + 1, 2424 + 1, false);//#3
        int i = 2424 + 484;
        doTest(i + 1, i + 1 + 121, false);//#4
        i += 484;
        i += 121;
        doTest(i + 1, i + 1 + 121, true);//#5
        i += 484;
        i += 121;
        doTest(i + 1, i + 1 + 121, false);//#6
        i += 484;
        i += 121;
        doTest(i + 1, i + 1 + 121, true);//#7
        i += 484;
        i += 121;
        doTest(i + 1, i + 1 + 121, false);//#8
        i += 484;
        i += 121;
        doTest(i + 1, i + 1 + 121, false);//#9
        i += 484;
        i += 121;
        doTest(i + 1, i + 1 + 121, false);//#10
        i += 484;
        i += 121;
        doTest(i + 1, i + 1 + 121, true);//#11
        i += 484;
        i += 121;
        doTest(i + 1, i + 1 + 121, false);//#12
        i += 484;
        i += 121;
        doTest(i + 1, i + 1 + 121, true);//#13
        i += 484;
        i += 121;
        doTest(i + 1, i + 1 + 121, false);//#14
        i += 484;
        i += 121;
        doTest(i + 1, i + 1 + 121, false);//#15
        i += 484;
        i += 121;
        doTest(i + 1, i + 1 + 121, false);//#16
        i += 484;
        i += 121;
        doTest(i + 1, i + 1 + 121, true);//#17
        i += 484;
        i += 121;
        doTest(i + 1, i + 1 + 121, false);//#18
        i += 484;
        i += 121;
        doTest(i + 1, i + 1 + 121, true);//#19
        i += 484;
        i += 121;
        doTest(i + 1, i + 1 + 121, true);//#20
        i += 484;
        i += 121;
        doTest(i + 1, i + 1 + 121, false);//#21
        i += 484;
        i += 121;
        doTest(i + 1, i + 1 + 121, false);//#22
        i += 484;
        i += 121;
        doTest(i + 1, i + 1 + 121, true);//#23
        i += 484;
        i += 121;
        doTest(i + 1, i + 1 + 121, false);//#24
        i += 484;
        i += 121;
        doTest(i + 1, i + 1 + 121, true);//#25
        i += 484;
        i += 121;
        doTest(i + 1, i + 1 + 121, false);//#26
        i += 484;
        i += 121;
        doTest(i + 1, i + 1 + 121, true);//#27
        i += 484;
        i += 121;
        doTest(i + 1, i + 1 + 121, false);//#28
        i += 484;
        i += 121;
        doTest(i + 1, i + 1 + 121, true);//#29
        i += 484;
        i += 121;
        doTest(i + 1, i + 1 + 121, true);//#30
        i += 484;
        i += 121;
        doTest(i + 1, i + 1 + 121, false);//#31
        i += 484;
        i += 121;
        doTest(i + 1, i + 1 + 121, false);//#32
        i += 484;
        i += 121;
        doTest(i + 1, i + 1 + 121, false);//#33
        i += 484;
        i += 121;
        doTest(i + 1, i + 1 + 121, false);//#34
        i += 484;
        i += 121;
        doTest(i + 1, i + 1 + 121, false);//#35
        i += 484;
        i += 121;
        doTest(i + 1, i + 1 + 121, false);//#36
        i += 484;
        i += 121;
        doTest(i + 1, i + 1 + 121, false);//#37
        i += 484;
        i += 121;
        doTest(i + 1, i + 1 + 121, false);//#38
        i += 484;
        i += 121;
        doTest(i + 1, i + 1 + 121, false);//#39
        i += 484;
        i += 121;
        doTest(i + 1, i + 1 + 121, false);//#40
        i += 484;
        i += 121;
        doTest(i + 1, i + 1 + 121, false);//#41
        i += 484;
        i += 121;
    }
}
