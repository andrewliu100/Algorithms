package geekspearls.misc;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class PermutationTest {

    private Permutation permutation;

    @BeforeClass
    public void init() {
        permutation = new Permutation();
    }

    @Test
    public void testPermGen() {
        List<int[]> perms = permutation.permGen(3);
        for (int[] perm : perms) {
            print(perm);
        }
    }

    @Test
    public void testSortedPermGen() {
        List<int[]> perms = permutation.sortedPermGen(4);
        for (int[] perm : perms) {
            print(perm);
        }
    }

    private void print(int[] perm) {
        for (int i : perm) {
            System.out.print(i + ",");
        }
        System.out.println();
    }
}
