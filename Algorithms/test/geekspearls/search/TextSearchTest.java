package geekspearls.search;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class TextSearchTest {

    private TextSearch textSearch;

    @BeforeClass
    public void init() {
        textSearch = new TextSearch();
    }

    @DataProvider(name = "testData")
    public static Object[][] testData() {
        String[][] textAndPattern = {
            {"This is a test text for test", "test"},
            {"aaaaaaaaab", "aab"}
        };

        int[][] foundIdx = {
            {10, 24},
            {7}
        };

        Object[][] testData = {
            {textAndPattern[0], foundIdx[0]},
            {textAndPattern[1], foundIdx[1]}
        };
        return testData;
    }

    @Test(dataProvider = "testData")
    public void testNaiveSearch(String[] textAndPattern, int[] expectedIdx) {
        List<Integer> foundIdx = textSearch.naiveSearch(textAndPattern[0], textAndPattern[1]);
        Assert.assertEquals(foundIdx.size(), expectedIdx.length);
        for (int i = 0; i < foundIdx.size(); i++) {
            Assert.assertEquals(foundIdx.get(i).intValue(), expectedIdx[i]);
        }
    }

    @Test(dataProvider = "testData")
    public void testKMPSearch(String[] textAndPattern, int[] expectedIdx) {
        List<Integer> foundIdx = textSearch.kmpSearch(textAndPattern[0], textAndPattern[1]);
        Assert.assertEquals(foundIdx.size(), expectedIdx.length);
        for (int i = 0; i < foundIdx.size(); i++) {
            Assert.assertEquals(foundIdx.get(i).intValue(), expectedIdx[i]);
        }
    }

}
