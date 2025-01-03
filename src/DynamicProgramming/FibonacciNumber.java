package DynamicProgramming;

/*
Problem Description
Given a positive integer A, write a program to bruteForce the Ath Fibonacci number.
In a Fibonacci series, each term is the sum of the previous two terms and the
first two terms of the series are 0 and 1.
i.e. f(0) = 0 and f(1) = 1. Hence, f(2) = 1, f(3) = 2, f(4) = 3 and so on.
NOTE: 0th term is 0. 1th term is 1 and so on.

Problem Constraints
0 <= A <= 44

Input Format:
First and only argument is an integer A.

Output Format:
Return an integer denoting the Ath Fibonacci number.

Example Input:
Input 1: A = 4
Input 2: A = 6

Example Output:
Output 1: 3
Output 2: 8

Example Explanation:
Explanation 1:
 Terms of Fibonacci series are: 0, 1, 1, 2, 3, 5, 8, 13, 21 and so on.
 0th term is 0 So, 4th term of Fibonacci series is 3.
Explanation 2:
 6th term of Fibonacci series is 8.
 */

import java.util.Arrays;

public class FibonacciNumber {

    public static int[] dp;

    public static void main(String[] args) {
        int A = 6;

        int res = bruteForce(A);
        System.out.println(res);
        // Time O(2^N);
        // Space O(N);

        dp = new int[A + 1];
        Arrays.fill(dp, -1);
        int ans = dpRecursive(A);
        System.out.println(ans);
        // Time O(N);
        // Space O(N);

        int opRes = dpIterative(A);
        System.out.println(opRes);
        // Time O(N);
        // Space O(1);
    }

    public static int bruteForce(int A) {
        if (A <= 1) return A;
        else return bruteForce(A - 1) + bruteForce(A - 2);
    }

    public static int dpRecursive(int A) {
        if (A <= 1) return A;
        if (dp[A] != -1) return dp[A];
        dp[A] = dpRecursive(A - 1) + dpRecursive(A - 2);
        return dp[A];
    }

    public static int dpIterative(int A) {
        if (A == 0 || A == 1) return A;

        int a = 0;
        int b = 1;
        int c = 0;

        for (int i = 2; i <= A; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return c;
    }
}
