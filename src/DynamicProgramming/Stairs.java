package DynamicProgramming;

/*
Problem Description
You are climbing a staircase and it takes A steps to reach the top.
Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
Return the number of distinct ways modulo 1000000007

Problem Constraints
1 <= A <= 10^5

Input Format:
The first and the only argument contains an integer A, the number of steps.

Output Format:
Return an integer, representing the number of ways to reach the top.

Example Input:
Input 1: A = 2
Input 2: A = 3

Example Output:
Output 1: 2
Output 2: 3

Example Explanation:

Explanation 1:
 Distinct ways to reach top: [1, 1], [2].

Explanation 2:
 Distinct ways to reach top: [1 1 1], [1 2], [2 1].
 */

import java.util.Arrays;

public class Stairs {
    public static int[] dp;
    public static int MOD = 1000000007;

    public static void main(String[] args) {
        int A = 3;

        int res = bruteForce(A);
        System.out.println(res);
        // Time O(2^A);
        // Space O(A);

        dp = new int[A + 1];
        Arrays.fill(dp, -1);
        int ans = dpRecursive(A);
        System.out.println(ans);
        // Time O(A);
        // Space O(A);
    }

    public static int bruteForce(int A) {
        if (A <= 2) return A;
        return bruteForce(A - 1) + bruteForce(A - 2);
    }


    public static int dpRecursive(int A) {
        if (A <= 2) return A;
        if (dp[A] != -1) return dp[A];
        dp[A] = (dpRecursive(A - 1) + dpRecursive(A - 2)) % MOD;
        return dp[A];
    }
}
