package geekspearls.amz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * There are 8 prison cells in a row, and each cell is either occupied or vacant.
 *
 * Each day, whether the cell is occupied or vacant changes according to the following rules:
 *
 * If a cell has two adjacent neighbors that are both occupied or both vacant, then the cell becomes occupied.
 * Otherwise, it becomes vacant.
 * (Note that because the prison is a row, the first and the last cells in the row can't have two adjacent neighbors.)
 *
 * We describe the current state of the prison in the following way: cells[i] == 1 if the i-th cell is occupied, else cells[i] == 0.
 *
 * Given the initial state of the prison, return the state of the prison after N days (and N such changes described above.)
 *
 *
 *
 * Example 1:
 *
 * Input: cells = [0,1,0,1,1,0,0,1], N = 7
 * Output: [0,0,1,1,0,0,0,0]
 * Explanation:
 * The following table summarizes the state of the prison on each day:
 * Day 0: [0, 1, 0, 1, 1, 0, 0, 1]
 * Day 1: [0, 1, 1, 0, 0, 0, 0, 0]
 * Day 2: [0, 0, 0, 0, 1, 1, 1, 0]
 * Day 3: [0, 1, 1, 0, 0, 1, 0, 0]
 * Day 4: [0, 0, 0, 0, 0, 1, 0, 0]
 * Day 5: [0, 1, 1, 1, 0, 1, 0, 0]
 * Day 6: [0, 0, 1, 0, 1, 1, 0, 0]
 * Day 7: [0, 0, 1, 1, 0, 0, 0, 0]
 *
 * Example 2:
 *
 * Input: cells = [1,0,0,1,0,0,1,0], N = 1000000000
 * Output: [0,0,1,1,1,1,1,0]
 *
 *
 * Note:
 *
 * cells.length == 8
 * cells[i] is in {0, 1}
 * 1 <= N <= 10^9
 *
 */
public class PrisonCellsAfterNDays {

    /**
     * Let's do a naive simulation, iterating one day at a time. For each day, we will decrement N,
     * the number of days remaining, and transform the state of the prison forward (state -> nextDay(state)).
     *
     * If we reach a state we have seen before, we know how many days ago it occurred, say t.
     * Then, because of this cycle, we can do N %= t. This ensures that our algorithm only needs
     * O(2^n)steps.
     *
     * Time: O(2^n) n is the length of cells
     * Space: O(2^n * n)
     */
    public int[] prisonAfterDays(int[] cells, int N) {
        Map<Integer, Integer> stateToDay = new HashMap<>();
        int state = bitsToInt(cells);

        while (N > 0) {
            if (stateToDay.containsKey(state)) {
                N %= stateToDay.get(state) - N;
            }
            stateToDay.put(state, N);
            if (N > 0) {
                N--;
                state = nextDay(state, cells.length);
            }
        }

        return intToBits(state, cells.length);
    }

    private int nextDay(int state, int numOfBits) {
        int ans = 0;
        // no need for the first and last bits, because they have only one neighbor
        for (int i = 1; i < numOfBits - 1; i++) {
            if ((state >> (i - 1) & 1) == (state >> (i + 1) & 1)) {
                ans ^= (1 << i);
            }
        }
        return ans;
    }

    private int bitsToInt(int[] bits) {
        int result = 0;
        for (int i = 0; i < bits.length; i++) {
            if (bits[i] > 0) {
                result ^= (1<<i);
            }
        }
        return result;
    }

    private int[] intToBits(int n, int numOfBits) {
        int[] bits = new int[numOfBits];
        for (int i = 0; i < numOfBits; i++) {
            bits[i] = (n >> i) & 1;
        }
        return bits;
    }

    public static void main(String[] args) {
        PrisonCellsAfterNDays p = new PrisonCellsAfterNDays();
        System.out.println(IntStream.of(p.prisonAfterDays(new int[]{0,1,0,1,1,0,0,1}, 7)).boxed().collect(Collectors.toList()));
        System.out.println(IntStream.of(p.prisonAfterDays(new int[]{1,0,0,1,0,0,1,0}, 1000000000)).boxed().collect(Collectors.toList()));
    }
}
