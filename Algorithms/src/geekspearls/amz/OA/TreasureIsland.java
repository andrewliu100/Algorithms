package geekspearls.amz.OA;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/**
 * You have a map that marks the location of a treasure island. Some of the map area has jagged rocks
 * and dangerous reefs. Other areas are safe to sail in. There are other explorers trying to find the
 * treasure. So you must figure out a shortest route to the treasure island.
 *
 * Assume the map area is a two dimensional grid, represented by a matrix of characters. You must start
 * from the top-left corner of the map and can move one block up, down, left or right at a time. The
 * treasure island is marked as X in a block of the matrix. X will not be at the top-left corner.
 * Any block with dangerous rocks or reefs will be marked as D. You must not enter dangerous blocks.
 * You cannot leave the map area. Other areas O are safe to sail in. The top-left corner is always safe.
 * Output the minimum number of steps to get to the treasure.
 *
 * Example:
 *
 * Input:
 * [['O', 'O', 'O', 'O'],
 *  ['D', 'O', 'D', 'O'],
 *  ['O', 'O', 'O', 'O'],
 *  ['X', 'D', 'D', 'O']]
 *
 * Output: 5
 * Explanation: Route is (0, 0), (0, 1), (1, 1), (2, 1), (2, 0), (3, 0) The minimum route takes 5 steps.
 */
public class TreasureIsland {

    static class Coordinate {
        int x;
        int y;
        Coordinate pre;

        Coordinate(int x, int y, Coordinate pre) {
            this.x = x;
            this.y = y;
            this.pre = pre;
        }
    }

    /**
     * BFS. Time: O(m*n) Space: O(m*n)
     */
    public static int minSteps(List<List<Character>> grid) {
        Deque<Coordinate> queue = new ArrayDeque<>();
        Coordinate start = new Coordinate(0, 0, null);
        queue.addLast(start);
        Coordinate treasure = null;
        while (!queue.isEmpty()) {
            Coordinate current = queue.removeFirst();
            if (current.x < 0 || current.x >= grid.size() || current.y <0 || current.y >= grid.get(current.x).size() ||
            grid.get(current.x).get(current.y) == 'D') {
                continue;
            }
            if (grid.get(current.x).get(current.y) == 'X') {
                treasure = current;
                break;
            }
            grid.get(current.x).set(current.y, 'D');
            for (Coordinate next : Arrays.asList(new Coordinate(current.x - 1, current.y, current),
                    new Coordinate(current.x, current.y-1, current),
                    new Coordinate(current.x + 1, current.y, current),
                    new Coordinate(current.x, current.y + 1, current))) {
                queue.addLast(next);
            }
        }

        Coordinate iter = treasure;
        int steps = -1;
        while (iter != null) {
            steps++;
            iter = iter.pre;
        }
        return steps;
    }

    public static void main(String[] args) {
        List<List<Character>> grid = Arrays.asList(
                Arrays.asList('O', 'O', 'O', 'O'),
                Arrays.asList('D', 'O', 'D', 'O'),
                Arrays.asList('O', 'O', 'O', 'O'),
                Arrays.asList('X', 'D', 'D', 'O')
        );

        System.out.println(minSteps(grid));
    }
}
