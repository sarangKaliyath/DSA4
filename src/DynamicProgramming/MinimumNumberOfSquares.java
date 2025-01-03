package DynamicProgramming;
/*
Problem Description
Given an integer A. Return minimum count of numbers,
sum of whose squares is equal to A.

Problem Constraints
1 <= A <= 10^5

Input Format:
First and only argument is an integer A.

Output Format:
Return an integer denoting the minimum count.

Example Input:
Input 1: A = 6
Input 2: A = 5

Example Output:
Output 1: 3
Output 2: 2

Example Explanation:
Explanation 1:
 Possible combinations are : (1^2 + 1^2 + 1^2 + 1^2 + 1^2 + 1^2) and (1^2 + 1^2 + 2^2).
 Minimum count of numbers, sum of whose squares is 6 is 3.

Explanation 2:
 We can represent 5 using only 2 numbers i.e. 1^2 + 2^2 = 5
 */

import java.util.Arrays;

public class MinimumNumberOfSquares {
    public static int ans;

    public static int dpAns;
    public static int[] dp;

    public static int[] dpArr;

    public static void main(String[] args) {
        int A = 5;

        int res = bruteForce(A);
        System.out.println(res);
        // Time O(√N ^ N);
        // Space O(N);

        dp = new int[A + 1];
        Arrays.fill(dp, -1);
        int opRes = dpRecursive(A);
        System.out.println(opRes);
        // Time O(N * √N);
        // Space O(N);

        dpArr = new int[A + 1];
        Arrays.fill(dpArr, Integer.MAX_VALUE);
        int bottomUpRes = iterativeDp(A);
        System.out.println(bottomUpRes);
        // Time O(N * √N);
        // Space O(N);
    }

    public static int bruteForce(int A) {
        if (A == 0) return 0;
        ans = A;

        for (int i = 1; i * i <= A; i++) {
            ans = Math.min(ans, 1 + bruteForce(A - i * i));
        }

        return ans;
    }

    public static int dpRecursive(int A) {
        if (A == 0) return 0;
        if (dp[A] != -1) return dp[A];

        dpAns = A;

        for (int i = 1; i * i <= A; i++) {
            dpAns = Math.min(dpAns, 1 + dpRecursive(A - i * i));
        }

        dp[A] = dpAns;
        return dpAns;
    }

    public static int iterativeDp(int A) {
        dpArr[0] = 0;
        for (int i = 1; i <= A; i++) {
            dp[i] = i;
            for (int j = 1; j * j <= i; j++) {
                dpArr[i] = Math.min(dpArr[i], 1 + dpArr[i - (j * j)]);
            }
        }
        return dpArr[A];
    }
}
