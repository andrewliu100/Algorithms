package geekspearls.amz.OA;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

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
