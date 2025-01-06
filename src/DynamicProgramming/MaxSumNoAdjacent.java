package DynamicProgramming;

/*
Problem Description
Given a 2 x N grid of integers, A,
your task is to choose numbers from the grid such that sum of these numbers is maximized.
However, you cannot choose two numbers that are adjacent horizontally,
vertically, or diagonally.
Return the maximum possible sum.
Note: You are allowed to choose more than 2 numbers from the grid.

Problem Constraints:
1 <= N <= 20000
1 <= A[i] <= 2000

Input Format:
The first and the only argument of input contains a 2d matrix, A.

Output Format:
Return an integer, representing the maximum possible sum.

Example Input

Input 1:
 A = [
        [1]
        [2]
     ]

Input 2:
 A = [
        [1, 2, 3, 4]
        [2, 3, 4, 5]
     ]

Example Output:
Output 1: 2
Output 2: 8

Example Explanation:
Explanation 1:
 We will choose 2 (From 2nd row 1st column).

Explanation 2:
 We will choose 3 (From 2nd row 2nd column) and 5 (From 2nd row 4th column).
 */

import java.util.ArrayList;
import java.util.Arrays;

public class MaxSumNoAdjacent {
    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> A = new ArrayList<>(Arrays.asList(
                new ArrayList<>(Arrays.asList(1, 2, 3, 4)),
                new ArrayList<>(Arrays.asList(2, 3, 4, 5))
        ));

        // Using Common find Function for all approaches;

        // bruteForce
        int ans = find(A, A.get(0).size());
        // Time O(2^N);
        // Space O(N);

        // with Top down approach
        int res = find(A, A.get(0).size());
        // Time O(N);
        // Space O(N);

        // with Bottom Up approach
        int val = find(A, A.get(0).size());
        System.out.println(val);

    }

    public static int find(ArrayList<ArrayList<Integer>> A, int n) {

        int[] modArray = new int[n];
        for (int i = 0; i < n; i++) {
            modArray[i] = Math.max(A.get(0).get(i), A.get(1).get(i));
        }

        int[] dp = new int[n];
        Arrays.fill(dp, -1);

//        return  bruteForce(modArray, n, 0);
//        return recursive(modArray, n, 0, dp);
        return iterative(modArray, n, dp);
    }

    public static int bruteForce(int[] arr, int n, int i) {
        if (i >= n) return 0;
        int sum1 = arr[i] + bruteForce(arr, n, i + 2);
        int sum2 = bruteForce(arr, n, i + 1);
        return Math.max(sum1, sum2);
    }

    public static int recursive(int[] arr, int n, int i, int[] dp) {
        if (i >= n) return 0;

        if (dp[i] != -1) return dp[i];

        int sum1 = arr[i] + recursive(arr, n, i + 2, dp);
        int sum2 = recursive(arr, n, i + 1, dp);

        dp[i] = Math.max(sum1, sum2);

        return dp[i];
    }

    public static int iterative(int[] arr, int n, int[] dp) {
        if (n == 1) return arr[0];
        if (n == 2) return Math.max(arr[0], arr[1]);

        dp[n - 1] = Math.max(arr[n - 1], 0);
        dp[n - 2] = Math.max(arr[n - 2], arr[n - 1]);

        for (int i = n - 3; i >= 0; i--) {
            dp[i] = Math.max(arr[i] + dp[i + 2], dp[i + 1]);
        }

        return dp[0];
    }

}
