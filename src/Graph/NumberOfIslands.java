package Graph;

/*
Problem Description
Given a matrix of integers A of size N x M consisting of 0 and 1.
A group of connected 1's forms an island.
From a cell (i, j) such that A[i][j] = 1
you can visit any cell that shares a corner with (i, j) and
value in that cell is 1.
More formally, from any cell (i, j) if A[i][j] = 1 you can visit:

(i-1, j) if (i-1, j) is inside the matrix and A[i-1][j] = 1.
(i, j-1) if (i, j-1) is inside the matrix and A[i][j-1] = 1.
(i+1, j) if (i+1, j) is inside the matrix and A[i+1][j] = 1.
(i, j+1) if (i, j+1) is inside the matrix and A[i][j+1] = 1.
(i-1, j-1) if (i-1, j-1) is inside the matrix and A[i-1][j-1] = 1.
(i+1, j+1) if (i+1, j+1) is inside the matrix and A[i+1][j+1] = 1.
(i-1, j+1) if (i-1, j+1) is inside the matrix and A[i-1][j+1] = 1.
(i+1, j-1) if (i+1, j-1) is inside the matrix and A[i+1][j-1] = 1.

Return the number of islands.
NOTE: Rows are numbered from top to bottom and columns are numbered from left to right.

Problem Constraints:
1 <= N, M <= 100
0 <= A[i] <= 1

Input Format:
The only argument given is the integer matrix A.

Output Format:
Return the number of islands.

Example Input:
Input 1:
 A = [
       [0, 1, 0]
       [0, 0, 1]
       [1, 0, 0]
     ]

Input 2:
 A = [
       [1, 1, 0, 0, 0]
       [0, 1, 0, 0, 0]
       [1, 0, 0, 1, 1]
       [0, 0, 0, 0, 0]
       [1, 0, 1, 0, 1]
     ]

Example Output:
Output 1: 2
Output 2: 5

Example Explanation:
Explanation 1:
 The 1's at position A[0][1] and A[1][2] forms one island.
 Other is formed by A[2][0].

Explanation 2:
 There 5 island in total.
 */

import java.util.ArrayList;
import java.util.Arrays;

public class NumberOfIslands {
    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> A = new ArrayList<>(Arrays.asList(
                new ArrayList<>(Arrays.asList(1, 1, 0, 0, 0)),
                new ArrayList<>(Arrays.asList(0, 1, 0, 0, 0)),
                new ArrayList<>(Arrays.asList(1, 0, 0, 1, 1)),
                new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0)),
                new ArrayList<>(Arrays.asList(1, 0, 1, 0, 1))
        ));

        int res = countIslands(A);
        System.out.println(res);
        // Time O(N * M);
        // Space O(N * M);
    }

    public static int countIslands(ArrayList<ArrayList<Integer>> graph) {
        int noOfIslands = 0;

        for (int i = 0; i < graph.size(); i++) {
            for (int j = 0; j < graph.get(0).size(); j++) {
                if (graph.get(i).get(j) == 1) {
                    dfs(graph, i, j);
                    noOfIslands++;
                }
            }
        }

        for (ArrayList<Integer> row : graph) {
            for (int j = 0; j < row.size(); j++) {
                if (row.get(j) == -1) row.set(j, 1);
            }
        }

        return noOfIslands;
    }

    private final static int[] row = {-1, -1, 0, 1, 1, 1, -1, 0};
    private final static int[] col = {0, 1, 1, 1, 0, -1, -1, -1};

    public static void dfs(ArrayList<ArrayList<Integer>> graph, int sr, int sc) {
        if (sr < 0 || sr >= graph.size() || sc < 0 || sc >= graph.get(0).size() || graph.get(sr).get(sc) != 1) {
            return;
        }

        graph.get(sr).set(sc, -1);

        for (int direction = 0; direction <= 7; direction++) {
            int xAxis = sr + row[direction];
            int yAxis = sc + col[direction];
            dfs(graph, xAxis, yAxis);
        }
    }
}
