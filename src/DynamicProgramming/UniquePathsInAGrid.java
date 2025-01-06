package DynamicProgramming;

/*
Problem Description
Given a grid of size n * m, lets assume you are starting at (1,1) and your goal is to reach (n, m).
At any instance, if you are on (x, y), you can either go to (x, y + 1) or (x + 1, y).
Now consider if some obstacles are added to the grids.
Return the total number unique paths from (1, 1) to (n, m).
Note:
1. An obstacle is marked as 1 and empty space is marked 0 respectively in the grid.
2. Given Source Point and Destination points are 1-based index.

Problem Constraints
1 <= n, m <= 100
A[i][j] = 0 or 1

Input Format:
First and only argument A is a 2D array of size n * m.

Output Format:
Return an integer denoting the number of unique paths from (1, 1) to (n, m).

Example Input:
Input 1:
 A = [
        [0, 0, 0]
        [0, 1, 0]
        [0, 0, 0]
     ]

Input 2:
 A = [
        [0, 0, 0]
        [1, 1, 1]
        [0, 0, 0]
     ]

Example Output
Output 1: 2
Output 2: 0

Example Explanation

Explanation 1:
 Possible paths to reach (n, m): {(1, 1), (1, 2), (1, 3), (2, 3), (3, 3)} and {(1 ,1), (2, 1), (3, 1), (3, 2), (3, 3)}
 So, the total number of unique paths is 2.

Explanation 2:
 It is not possible to reach (n, m) from (1, 1). So, ans is 0.
 */

import java.util.ArrayList;
import java.util.Arrays;

public class UniquePathsInAGrid {
    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> A = new ArrayList<>(Arrays.asList(
                new ArrayList<>(Arrays.asList(0, 0, 0)),
                new ArrayList<>(Arrays.asList(0, 1, 0)),
                new ArrayList<>(Arrays.asList(0, 0, 0))
        ));

        int ans = bruteForce(A, 0, 0);
        System.out.println(ans);
        // Time O(2^N*M);
        // Space O(N+M);

        int [][] dp = new int[A.size()][A.get(0).size()];

        for(int [] row : dp){
            Arrays.fill(row,-1);
        }
        int val = recursive(A, 0, 0, dp);
        System.out.println(val);
        // Time O(N*M);
        // Space O(N*M);

        int res = iterative(A, A.size(), A.get(0).size());
        System.out.println(res);
        // Time O(N*M);
        // Space O(N*M);
    }

    public static int bruteForce(ArrayList<ArrayList<Integer>> A, int i, int j) {
        int n = A.size();
        int m = A.get(0).size();

        if (i >= n || j >= m || A.get(i).get(j) == 1) return 0;
        if (i == n - 1 && j == m - 1) return 1;
        return bruteForce(A, i + 1, j) + bruteForce(A, i, j + 1);
    }

    public static int recursive(ArrayList<ArrayList<Integer>> A, int i, int j, int [][] dp){
        int n = A.size();
        int m = A.get(0).size();

        if (i >= n || j >= m || A.get(i).get(j) == 1) return 0;

        if(dp[i][j] != -1) return dp[i][j];

        if (i == n - 1 && j == m - 1) return 1;

        dp[i][j] = recursive(A, i + 1, j, dp) + recursive(A, i, j + 1, dp);

        return dp[i][j];
    }

    public static int iterative(ArrayList<ArrayList<Integer>> A, int n, int m) {
        if (A.get(0).get(0) != 0) return 0;

        int[][] dp = new int[n][m];
        dp[0][0] = 1;

        for (int i = 1; i < n; i++) {
            dp[i][0] = A.get(i).get(0) == 0 ? dp[i - 1][0] : 0;
        }

        for (int j = 1; j < m; j++) {
            dp[0][j] = A.get(0).get(j) == 0 ? dp[0][j - 1] : 0;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (A.get(i).get(j) == 0) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                } else {
                    dp[i][j] = 0;
                }
            }
        }

        return dp[n - 1][m - 1];
    }
}
