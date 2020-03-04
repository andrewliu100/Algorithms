package geekspearls.amz;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/**
 * Given a 2D grid, each cell is either a zombie 1 or a human 0.
 * Zombies can turn adjacent (up/down/left/right) human beings into zombies every hour.
 * Find out how many hours does it take to infect all humans?
 *
 * Example:
 *
 * Input:
 * [[0, 1, 1, 0, 1],
 *  [0, 1, 0, 1, 0],
 *  [0, 0, 0, 0, 1],
 *  [0, 1, 0, 0, 0]]
 *
 * Output: 2
 *
 * Explanation:
 * At the end of the 1st hour, the status of the grid:
 * [[1, 1, 1, 1, 1],
 *  [1, 1, 1, 1, 1],
 *  [0, 1, 0, 1, 1],
 *  [1, 1, 1, 0, 1]]
 *
 * At the end of the 2nd hour, the status of the grid:
 * [[1, 1, 1, 1, 1],
 *  [1, 1, 1, 1, 1],
 *  [1, 1, 1, 1, 1],
 *  [1, 1, 1, 1, 1]]
 */
public class ZombieInMatrix {

    static class Coordinate {
        int x;
        int y;

        Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int hoursToInfect(List<List<Integer>> grid) {
        int hours = 0;
        int zombieCount = 0;
        int total = 0;

        Deque<Coordinate> queue = new ArrayDeque<>();
        for (int i = 0; i < grid.size(); i++) {
            for (int j = 0; j < grid.get(i).size(); j++) {
                total++;
                if (grid.get(i).get(j) == 1) {
                    queue.addLast(new Coordinate(i, j));
                    zombieCount++;
                }
            }
        }

        while (zombieCount != total && !queue.isEmpty()) {
            int currQueueSize = queue.size();
            while (currQueueSize > 0) {
                Coordinate curr = queue.removeFirst();
                currQueueSize--;
                for (Coordinate next : Arrays.asList(
                                new Coordinate(curr.x - 1, curr.y),
                                new Coordinate(curr.x, curr.y + 1),
                                new Coordinate(curr.x + 1, curr.y),
                                new Coordinate(curr.x, curr.y - 1))) {
                    if (next.x >= 0 && next.x < grid.size() &&
                                    next.y >= 0 && next.y < grid.get(next.x).size() &&
                                    grid.get(next.x).get(next.y) == 0) {
                        zombieCount++;
                        grid.get(next.x).set(next.y, 1);
                        queue.addLast(next);
                    }
                }
            }
            hours++;
        }
        return hours;
    }

    public static void main(String[] args) {
        int[][] grid = {
//                        {1, 0}
                        {0, 1, 1, 0, 1},
                        {0, 1, 0, 1, 0},
                        {0, 0, 0, 0, 1},
                        {0, 1, 0, 0, 0}
        };
        List<List<Integer>> listGrid = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < grid[i].length; j++) {
                row.add(grid[i][j]);
            }
            listGrid.add(row);
        }
        int hours = hoursToInfect(listGrid);
        System.out.println(hours);
    }
}
