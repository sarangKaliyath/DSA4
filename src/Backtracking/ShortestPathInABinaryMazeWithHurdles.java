package Backtracking;

import java.util.ArrayList;
import java.util.Arrays;

/*
Problem Description
Given an MxN matrix where each element can either be 0 or 1.
We need to find the length of the shortest path between a given source cell to a destination cell.
A cell with value 0 denotes that it's a hurdle. The path can only be created out of the cells with values 1.
If NO path exists, then print -1.
    The matrix A is given as input of size M x N.
    The coordinates of Source cell are given by B, C.
    The coordinates of Destination cell are given by D, E.

Problem Constraints
    1 <= N , M <= 8
    0 <= A[i][j] <= 1
    0 <= B, C < N
    0 <= D, E < M
    A[B][C] == A[D][E] == 1

Input Format:
    First Argument is a 2-D Integer Array denotes the matrix A, of size MxN.
    Second Argument is an Integer B, denoting the Source Row Index
    Third Argument is an Integer C, denoting the Source Column Index
    Fourth Argument is an Integer D, denoting the Destination Row Index
    Fifth Argument is an Integer E, denoting the Destination Column Index

Output Format
Output a single integer denoting the length of the minimum distance from Source to destination

Example Input
Input 1 :
A = [[1, 1, 0, 0],
     [0, 1, 1, 0],
     [0, 0, 1, 1],
     [0, 0, 0, 1]]
B = 0, C = 0
D = 3, E = 3

Input 2 :
A = [[1, 1, 1],
     [1, 0, 1],
     [1, 1, 1]]
B = 0, C = 0
D = 0, E = 2

Input 3 :
A = [[1, 0, 1],
     [1, 0, 1],
     [1, 0, 1]]
B = 0, C = 0
D = 0, E = 2

Example Output
Output 1 : 6
Output 2 : 2
Output 3 : -1

Example Explanation
For Input 1:
The shortest path is (0,0) -> (0,1) -> (1,1) -> (1,2) -> (2,2) -> (2,3) -> (3,3)
For Input 2 :
The shortest path is (0,0) -> (0,1) -> (0,2)
For Input 3 :
No path exists, so we return -1
 */
public class ShortestPathInABinaryMazeWithHurdles {
    private static int[] row = {-1, 1, 0, 0};
    private static int[] col = {0, 0, 1, -1};
    // row and col are direction array
    private static int ans = Integer.MAX_VALUE;
    private static boolean isPossible = false;

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> A = new ArrayList<>(Arrays.asList(
                new ArrayList<>(Arrays.asList(1, 1, 0, 0)),
                new ArrayList<>(Arrays.asList(0, 1, 1, 0)),
                new ArrayList<>(Arrays.asList(0, 0, 1, 1)),
                new ArrayList<>(Arrays.asList(0, 0, 0, 1))
        ));
        int B = 0, C = 0, D = 3, E = 3;
        explorePath(A, B, C, D, E, new boolean[A.size()][A.get(0).size()], 0);
        System.out.println(isPossible ? ans : -1);
        // Time O(4^(N*M));
        // Space O(N * M);
    }

    public static void explorePath(ArrayList<ArrayList<Integer>> maze, int startRow, int startCol, int endRow, int endCol, boolean[][] visited, int distanceTraveled) {
        if (startRow == endRow && startCol == endCol) {
            ans = Math.min(ans, distanceTraveled);
            isPossible = true;
            return;
        }

        for (int direction = 0; direction < 4; direction++) {
            int nRow = startRow + row[direction];
            int nCol = startCol + col[direction];

            if (nRow >= 0 && nRow < maze.size() && nCol >= 0 && nCol < maze.get(0).size()) {

                if (maze.get(nRow).get(nCol) == 1 && !visited[nRow][nCol]) {
                    visited[nRow][nCol] = true;
                    explorePath(maze, nRow, nCol, endRow, endCol, visited, distanceTraveled + 1);
                    visited[nRow][nCol] = false;
                }

            }
        }
    }
}
