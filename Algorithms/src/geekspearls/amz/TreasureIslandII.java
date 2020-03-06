package geekspearls.amz;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class TreasureIslandII {

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

    private static Deque<Coordinate> getStarts(List<List<Character>> grid) {
        Deque<Coordinate> starts = new ArrayDeque<>();
        for (int i = 0; i < grid.size(); i++) {
            for (int j = 0; j < grid.get(i).size(); j++) {
                if (grid.get(i).get(j) == 'S') {
                    starts.addLast(new Coordinate(i, j, null));
                }
            }
        }
        return starts;
    }

    public static int minSteps(List<List<Character>> grid) {
        Deque<Coordinate> queue =getStarts(grid);
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
                Arrays.asList('S', 'O', 'O', 'S', 'S'),
                Arrays.asList('D', 'O', 'D', 'O', 'D'),
                Arrays.asList('O', 'O', 'O', 'O', 'X'),
                Arrays.asList('O', 'O', 'O', 'O', 'X'),
                Arrays.asList('X', 'D', 'D', 'D', 'O')
        );

        System.out.println(minSteps(grid));
    }
}
