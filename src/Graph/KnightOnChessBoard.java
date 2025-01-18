package Graph;
/*
Problem Description
Given any source point, (C, D) and destination point,
(E, F) on a chess board of size A x B, we need to find whether Knight can move to the destination or not.
The above figure details the movements for a knight ( 8 possibilities ).
If yes, then what would be the minimum number of steps for the knight
to move to the said point. If knight can not move from the source point to the destination point,
then return -1.

NOTE: A knight cannot go out of the board.

Problem Constraints:
1 <= A, B <= 500

Input Format:
The first argument of input contains an integer A.
The second argument of input contains an integer B.
The third argument of input contains an integer C.
The fourth argument of input contains an integer D.
The fifth argument of input contains an integer E.
The sixth argument of input contains an integer F.

Output Format:
If it is possible to reach the destination point, return the minimum number of moves.
Else return -1.

Example Input:
Input 1:
 int A = 8;
 int B = 8;
 int C = 1;
 int D = 1;
 int E = 8;
 int F = 8;

Input 2:
 int A = 2;
 int B = 4;
 int C = 2;
 int D = 1;
 int E = 4;
 int F = 4;

Example Output:
Output 1: 6
Output 2: -1

Example Explanation:
Explanation 1:
 The size of the chessboard is 8x8, the knight is initially at (1, 1) and the knight wants to reach position (8, 8).
 The minimum number of moves required for this is 6.

Explanation 2:
 It is not possible to move knight to position (4, 4) from (2, 1)
 */

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class KnightOnChessBoard {

    public static void main(String[] args) {
        int A = 8;
        int B = 8;
        int C = 1;
        int D = 1;
        int E = 8;
        int F = 8;

        int res = getNumberOfMovesBfs(A, B, C, D, E, F);
        System.out.println(res);
        // Time O(A * B);
        // Space O(A * B)
    }

    private final static int[] row = {-2, -2, -1, 1, 2, 2, 1, -1};
    private final static int[] col = {-1, 1, 2, 2, 1, -1, -2, -2};

    public static int getNumberOfMovesBfs(int n, int m, int sR, int sC, int dR, int dC) {

        sR--;
        sC--;
        dR--;
        dC--;

        if (sR < 0 || sR >= n || sC < 0 || sC >= m) return -1;

        if (sR == dR && sC == dC) return 0;

        boolean[][] visited = new boolean[n][m];
        Deque<int[]> q = new ArrayDeque<>();
        q.offerLast(new int[]{sR, sC, 0});
        visited[sR][sC] = true;

        while (!q.isEmpty()) {
            int[] current = q.pollFirst();
            int crrSr = current[0];
            int crrSc = current[1];
            int steps = current[2];

            for (int direction = 0; direction <= 7; direction++) {
                int xAxis = crrSr + row[direction];
                int yAxis = crrSc + col[direction];

                if (xAxis >= 0 && xAxis < n && yAxis >= 0 && yAxis < m && !visited[xAxis][yAxis]) {
                    if (xAxis == dR && yAxis == dC) {
                        return steps + 1;
                    }

                    visited[xAxis][yAxis] = true;
                    q.offerLast(new int[]{xAxis, yAxis, steps + 1});
                }
            }
        }

        return -1;
    }

}
