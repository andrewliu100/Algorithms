/**
 * COPYRIGHT (C) 2015 Andrew Liu. All Rights Reserved.
 * <p>
 * Algorithms geekspearls.dp.LcsTest
 *
 * @author Andrew Liu
 * @since 2015 30/08/2015 1:21 PM
 */
package geekspearls.dp;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @author Andrew
 */
public class LcsTest {

    private Lcs lcs;

    @BeforeClass
    public void init() {
        lcs = new Lcs();
    }

    @DataProvider(name = "Strings")
    public static Object[][] Strings() {
        return new Object[][] {
                {new String[]{"xyxxzxyzxy", "zxzyyzxxyxxz"}, 6},
                {new String[]{"ABCDGH", "AEDFHR"}, 3},
                {new String[]{"AGGTAB", "GXTXAYB"}, 4}
        };
    }

    @Test(dataProvider = "Strings")
    public void testLcs(String[] strings, Integer result) {
        Assert.assertEquals(lcs.lcs(strings[0], strings[1]), result.intValue());
    }

    //@Test
    public void debugLcs() {
        lcs.lcs("xyxxzxyzxy", "zxzyyzxxyxxz");
        lcs.printArray();
    }
}
