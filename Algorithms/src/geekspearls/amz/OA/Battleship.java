package geekspearls.amz.OA;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/discuss/interview-question/479360/
 *
 * The positions of ships are given as a string S, containing pairs of positions describing respectively the
 * top-left and bottom-right corner cells of each ship. Ships' descriptions are separated with commas.
 * The positions of hits are given as a string T, containing positions describing the map cells that were
 * hit: for the map in the example shown above, S = "1B 2C,2D 4D" and T = "2B 2D 3D 4D 4A". Ships in
 * S and hits in T may appear in any order.
 * Write a function:
 *
 * class Solution { public String solution(int N, String S, String T); }
 * that, given the size of the map N and two strings S, T that describe the positions of ships and hits
 * respectively, returns a string with two numbers: the count of sunken ships and the count of ships that
 * have been hit but not sunk, separated with a comma.
 *
 * For instance, given N = 4, S = "1B 2C,2D 4D" and T = "2B 2D 3D 4D 4A", your function should return
 * "1,1", as explained above.
 *
 * Given N = 3, S = "1A 1B,2C 2C" and T = "1B", your function should return "0,1", because one ship
 * was hit but not sunk.
 *
 * Given N = 12, S = "1A 2A,12A 12A" and T = "12A", your function should return "1,0", because one
 * ship was hit and sunk.
 *
 * Assume that:
 *
 * N is an integer within the range [1..26];
 * string S contains the descriptions of rectangular ships of area not greater than 4 cells;
 * there can be at most one ship located on any map cell (ships do not overlap);
 * each map cell can appear in string T at most once;
 * string S and string T contains only valid positions given in specified format.
 * In your solution, focus on correctness. The performance of your solution will not be the focus of the
 * assessment.
 */
public class Battleship {

    class Coordinate {
        int row;
        char col;

        Coordinate(int row, char col) {
            this.row = row;
            this.col = col;
        }
    }

    class Ship {
        Coordinate topLeft;
        Coordinate bottomRight;
        int hitCount = 0;
        List<String> hitPlace = new ArrayList<>();

        Ship(String shipStr) {
            String[] coordinateStr = shipStr.split(" ");
            this.topLeft = new Coordinate(Integer.valueOf(coordinateStr[0].substring(0, 1)), coordinateStr[0].charAt(1));
            this.bottomRight = new Coordinate(Integer.valueOf(coordinateStr[1].substring(0, 1)), coordinateStr[1].charAt(1));
        }

        void checkHit(String[] hits) {
            for (String hit : hits) {
                int x = Integer.valueOf(hit.substring(0, 1));
                char y = hit.charAt(1);
                if (topLeft.row <= x && x <= bottomRight.row && topLeft.col <= y && y <= bottomRight.col) {
                    // this is a hit
                    hitCount += 1;
                    hitPlace.add(hit);
                }
            }
        }

        boolean isHit() {
            return hitCount > 0 && !isSunk();
        }

        boolean isSunk() {
            return hitCount == ((bottomRight.row - topLeft.row + 1) * (bottomRight.col - topLeft.col + 1));
        }
    }

    /**
     * for each ship, check the hit positions.
     *
     * A ship is hit if it is not sunk and at least one hit position is inside it.
     * A ship is sunk if the number of hits equals the number of ship area.
     *
     * To check hit: hit.x is between ship's top-left.x and bottom-right.x, and hit.y is between ship's top-left.y and bottom-right.y
     * The number of ship area = (bottomRight.x - topLeft.x + 1) * (bottomRight.y - topLeft.y + 1)
     *
     * Time: O(S*H) S is the number of ships, H is the number of hits
     * Space: O(S)
     */
    public String battleship(String ships, String hits) {
        String[] shipList = ships.split(",");
        List<Ship> shipObjs = new ArrayList<>();
        for (String shipStr : shipList) {
            Ship ship = new Ship(shipStr);
            shipObjs.add(ship);
            ship.checkHit(hits.split(" "));
        }
        int hitCount = 0;
        int sunkCount = 0;
        for (Ship ship : shipObjs) {
            if (ship.isHit()) {
                hitCount++;
            }
            if (ship.isSunk()) {
                sunkCount++;
            }
        }
        return hitCount + "," + sunkCount;
    }

    public static void main(String[] args) {
        Battleship battleship = new Battleship();
        int N1 = 4;
        String S1 = "1B 2C,2D 4D", T1 = "2B 2D 3D 4D 4A";
        System.out.println(battleship.battleship(S1, T1));
        int N2 = 3;
        String S2 = "1A 1B,2C 2C", T2 = "1B";
        System.out.println(battleship.battleship(S2, T2));
        int N3 = 12;
        String S3 = "1A 2A,12A 12A", T3 = "12A";
        System.out.println(battleship.battleship(S3, T3));
    }
}
