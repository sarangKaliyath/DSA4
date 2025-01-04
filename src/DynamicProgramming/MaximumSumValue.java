package DynamicProgramming;

/*
Problem Description
You are given an array A of N integers and three integers B, C, and D.
You have to find the maximum value of A[i]*B + A[j]*C + A[k]*D, where 1 <= i <= j <= k <= N.

Problem Constraints
1 <= N <= 10^5
-10000 <= A[i], B, C, D <= 10000

Input Format:
First argument is an array A
Second argument is an integer B
Third argument is an integer C
Fourth argument is an integer D

Output Format:
Return an Integer S, i.e maximum value of (A[i] * B + A[j] * C + A[k] * D), where 1 <= i <= j <= k <= N.

Example Input:
Input 1:
 A = [1, 5, -3, 4, -2]
 B = 2
 C = 1
 D = -1

Input 2:
 A = [3, 2, 1]
 B = 1
 C = -10
 D = 3

Example Output:
Output 1: 18
Output 2: -4

Example Explanation:
Explanation 1:
 If you choose i = 2, j = 2, and k = 3 then we will get
 A[2]*B + A[2]*C + A[3]*D = 5*2 + 5*1 + (-3)*(-1) = 10 + 5 + 3 = 18

Explanation 2:
 If you choose i = 1, j = 3, and k = 3 then we will get
 A[1]*B + A[3]*C + A[3]*D = (3*1) + (-10*1) + (3*1) = 3 - 10 + 3 = -4
 */

import java.util.ArrayList;
import java.util.Arrays;

public class MaximumSumValue {

    public static void main(String[] args) {
        ArrayList<Integer> A = new ArrayList<>(Arrays.asList(3, 2, 1));
        int B = 1;
        int C = -10;
        int D = 3;

        int res = bruteForce(A, A.size(), B, C, D);
        System.out.println(res);
        // Time O(N^3);
        // Space O(1);

        int ans = dpBottomUp(A, A.size(), B, C, D);
        System.out.println(ans);
        // Time O(N);
        // Space O(N);
    }

    public static int bruteForce(ArrayList<Integer> A, int n, int B, int C, int D) {
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                for (int k = j; k < n; k++) {
                    int sum = (A.get(i) * B) + (A.get(j) * C) + (A.get(k) * D);
                    max = Math.max(max, sum);
                }
            }
        }

        return max;
    }

    public static int dpBottomUp(ArrayList<Integer> A, int n, int B, int C, int D) {
        int[][] dp = new int[3][n];

        dp[0][0] = A.get(0) * B;
        dp[1][0] = dp[0][0] + (C * A.get(0));
        dp[2][0] = dp[1][0] + (D * A.get(0));

        for (int i = 1; i < n; i++) {
            int val = A.get(i);
            dp[0][i] = Math.max(dp[0][i - 1], B * val);
            dp[1][i] = Math.max(dp[1][i - 1], dp[0][i] + (C * val));
            dp[2][i] = Math.max(dp[2][i - 1], dp[1][i] + (D * val));
        }

        return dp[2][n - 1];
    }

}
