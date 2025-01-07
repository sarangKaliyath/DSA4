package DynamicProgramming;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/*
Problem Description
Given a M x N grid A of integers,
ind a path from top left to bottom right
which minimizes the sum of all numbers along its path.
Return the minimum sum of the path.
NOTE: You can only move either down or right at any point in time.

Problem Constraints:
1 <= M, N <= 2000
-1000 <= A[i][j] <= 1000

Input Format
First and only argument is a 2-D grid A.

Output Format
Return an integer denoting the minimum sum of the path.

Example Input:
Input 1:
 A = [
       [1, 3, 2]
       [4, 3, 1]
       [5, 6, 1]
     ]

Input 2:
 A = [
       [1, -3, 2]
       [2, 5, 10]
       [5, -5, 1]
     ]

Example Output:
Output 1: 8
Output 2: -1

Example Explanation:
Explanation 1:
 The path will be: 1 -> 3 -> 2 -> 1 -> 1.

Input 2:
 The path will be: 1 -> -3 -> 5 -> -5 -> 1.
 */
public class MinSumPath {
    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> A = new ArrayList<>(Arrays.asList(
                new ArrayList<>(Arrays.asList(1, 3, 2)),
                new ArrayList<>(Arrays.asList(4, 3, 1)),
                new ArrayList<>(Arrays.asList(5, 6, 1))
        ));

        int ans = bruteForce(A, 0, 0);
        System.out.println(ans);
        // Time O(2 ^ (N + M));
        // Space O(N + M);

        int[][] dp = new int[A.size()][A.get(0).size()];
        for (int[] row : dp) Arrays.fill(row, -1);
        int val = recursive(A, 0, 0, dp);
        System.out.println(val);
        // Time O(N * M);
        // Space O(N + M);

        int res = iterative(A, A.size(), A.get(0).size());
        System.out.println(res);
        // Time O(N*M);
        // Space O(N*M);
    }

    public static int bruteForce(ArrayList<ArrayList<Integer>> A, int i, int j) {
        int n = A.size();
        int m = A.get(0).size();

        if (i == n - 1 && j == m - 1) return A.get(i).get(j);
        if (i >= n || j >= m) return Integer.MAX_VALUE;

        return Math.min(bruteForce(A, i + 1, j), bruteForce(A, i, j + 1)) + A.get(i).get(j);
    }

    public static int recursive(ArrayList<ArrayList<Integer>> A, int i, int j, int[][] dp) {
        int n = A.size();
        int m = A.get(0).size();

        if (i == n - 1 && j == m - 1) return A.get(i).get(j);
        if (i >= n || j >= m) return Integer.MAX_VALUE;

        if (dp[i][j] != -1) return dp[i][j];

        return dp[i][j] = Math.min(bruteForce(A, i + 1, j), bruteForce(A, i, j + 1)) + A.get(i).get(j);
    }

    public static int iterative(ArrayList<ArrayList<Integer>> A, int n, int m) {
        int[][] dp = new int[n][m];

        dp[n - 1][m - 1] = A.get(n - 1).get(m - 1);

        for (int i = n - 2; i >= 0; i--) {
            dp[i][m - 1] = dp[i + 1][m - 1] + A.get(i).get(m - 1);
        }

        for (int i = m - 2; i >= 0; i--) {
            dp[n - 1][i] = dp[n - 1][i + 1] + A.get(n - 1).get(i + 1);
        }

        for (int i = n - 2; i >= 0; i--) {
            for (int j = m - 2; j >= 0; j--) {
                dp[i][j] = Math.min(dp[i + 1][j], dp[i][j + 1]) + A.get(i).get(j);
            }
        }

        return dp[0][0];
    }
}
