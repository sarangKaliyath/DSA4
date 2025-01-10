package DynamicProgramming;

/*
Problem Description
Given a rod of length N units and an array A of size N
denotes prices that contains prices of all pieces of size 1 to N.
Find and return the maximum value that can be
obtained by cutting up the rod and selling the pieces.

Problem Constraints
1 <= N <= 1000
0 <= A[i] <= 10^6

Input Format
First and only argument is an integer array A of size N.

Output Format:
Return an integer denoting the maximum value that can be obtained by cutting up the rod and selling the pieces.

Example Input:
Input 1:
 A = [3, 4, 1, 6, 2]
Input 2:
 A = [1, 5, 2, 5, 6]

Example Output:
Output 1: 15
Output 2: 11

Example Explanation:
Explanation 1:
 Cut the rod of length 5 into 5 rods of length (1, 1, 1, 1, 1) and sell them for (3 + 3 + 3 + 3 + 3) = 15.
Explanation 2:
 Cut the rod of length 5 into 3 rods of length (2, 2, 1) and sell them for (5 + 5 + 1) = 11.
 */

import java.util.ArrayList;
import java.util.Arrays;

public class CuttingARod {

    public static void main(String[] args) {
        ArrayList<Integer> A = new ArrayList<>(Arrays.asList(3, 4, 1, 6, 2));
        int n = A.size();

        int res = bruteForce(A, n);
        System.out.println(res);
        // Time O(2^N);
        // Space O(N);

        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);

        int ans = recursive(A, n, dp);
        System.out.println(ans);
        // Time O(N^2);
        // Space O(N);

        int val = iterative(A, n);
        System.out.println(val);
        // Time O(N^2);
        // Space O(N);
    }

    public static int bruteForce(ArrayList<Integer> A, int i) {
        if (i < 0) return Integer.MIN_VALUE;
        if (i == 0) return 0;

        int maxProfit = Integer.MIN_VALUE;

        for (int cut = 1; cut <= i; cut++) {
            int cutProfit = A.get(cut - 1);
            int balanceProfit = bruteForce(A, i - cut);
            maxProfit = Math.max(maxProfit, cutProfit + balanceProfit);
        }

        return maxProfit;
    }

    public static int recursive(ArrayList<Integer> A, int i, int[] dp) {
        if (i < 0) return Integer.MIN_VALUE;
        if (i == 0) return 0;

        if (dp[i] != -1) return dp[i];

        int maxProfit = Integer.MIN_VALUE;

        for (int cut = 1; cut <= i; cut++) {
            int cutProfit = A.get(cut - 1);
            int balanceProfit = recursive(A, i - cut, dp);
            maxProfit = Math.max(maxProfit, cutProfit + balanceProfit);
        }

        dp[i] = maxProfit;
        return dp[i];
    }

    public static int iterative(ArrayList<Integer> A, int n) {
        int[] dp = new int[n + 1];

        for (int i = 1; i <= n; i++) {

            for (int j = 1; j <= i; j++) {
                dp[i] = Math.max(dp[i], A.get(j - 1) + dp[i - j]);
            }
        }
        return dp[n];
    }


}
