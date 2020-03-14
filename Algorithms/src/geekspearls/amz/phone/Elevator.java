package geekspearls.amz.phone;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

/**
 * Elevator has two buttons Up and Down , By pressing up elevator goes up by 'up' floors and by pressing down it goes
 * down by 'down' floors. A building has n floors. Given a starting floor s, Can you explain if it's possible to go to floor e.
 */
public class Elevator {

    /**
     * O(n) we check if we can reach each floor of the total.
     */
    public static boolean canReach(int up, int down, int total, int start, int end) {
        Deque<Integer> queue = new ArrayDeque<>();
        queue.addLast(start);

        Set<Integer> visited = new HashSet<>();
        while (!queue.isEmpty()) {
            Integer cur = queue.removeFirst();
            if (cur == end) {
                return true;
            }
            visited.add(cur);
            // go up
            Integer upFloor = cur + up;
            if (upFloor > 0 && upFloor <= total && !visited.contains(upFloor)) {
                queue.addLast(upFloor);
            }
            // go down
            Integer downFloor = cur - down;
            if (downFloor >= 0 && downFloor < total && !visited.contains(downFloor)) {
                queue.addLast(downFloor);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(canReach(5, 3, 20, 5, 9));
    }
}
