package DynamicProgramming;

import java.util.Arrays;

/*
Problem Description:
A message containing letters from A-Z is
being encoded to numbers using the following mapping:
'A' -> 1
'B' -> 2
...
'Z' -> 26
Given an encoded message denoted by string A containing digits,
determine the total number of ways to decode it modulo 109 + 7.

Problem Constraints
1 <= length(A) <= 10^5

Input Format
The first and the only argument is a string A.

Output Format
Return an integer, representing the number
of ways to decode the string modulo 109 + 7.

Example Input:
Input 1: A = "12"
Input 2: A = "8"

Example Output:
Output 1: 2
Output 2: 1

Example Explanation:
Explanation 1:
 Given encoded message "12", it could be decoded as "AB" (1, 2) or "L" (12).
 The number of ways decoding "12" is 2.

Explanation 2:
 Given encoded message "8", it could be decoded as only "H" (8).
 The number of ways decoding "8" is 1.
 */
public class WaysToDecode {
    public static void main(String[] args) {
        String A = "12";

        int res = bruteForce(A, 0);
        System.out.println(res);

        int[] dp = new int[A.length()];
        Arrays.fill(dp, -1);
        int ans = recursive(A, 0, dp);
        System.out.println(ans);
        // Time O(N);
        // Space O(N);

        int val = iterative(A, A.length());
        System.out.println(val);
        // Time O(N);
        // Space O(N);

        int out = iterativeOptimized(A, A.length());
        System.out.println(out);
        // Time O(N);
        // Space O(1);
    }

    private static final int MOD = 1_000_000_007;

    public static int bruteForce(String A, int i) {
        if (i == A.length()) return 1;
        if (A.charAt(i) == '0') return 0;

        int ways = (bruteForce(A, i + 1)) % MOD;

        if (i < A.length() - 1) {
            int val = (A.charAt(i) - '0') * 10 + (A.charAt(i + 1) - '0');
            if (val >= 10 && val <= 26) {
                ways = (ways + bruteForce(A, i + 2)) % MOD;
            }
        }

        return ways;
    }

    public static int recursive(String A, int i, int[] dp) {
        if (i == A.length()) return 1;
        if (A.charAt(i) == '0') return 0;

        if (dp[i] != -1) return dp[i];

        int ways = (recursive(A, i + 1, dp)) % MOD;

        if (i < A.length() - 1) {
            int val = (A.charAt(i) - '0') * 10 + (A.charAt(i + 1) - '0');
            if (val >= 10 && val <= 26) {
                ways = (ways + recursive(A, i + 2, dp)) % MOD;
            }
        }

        dp[i] = ways;
        return ways;
    }

    public static int iterative(String A, int n) {
        if (n == 0 || A.charAt(0) == '0') return 0;

        int[] dp = new int[n + 1];
        dp[0] = dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            if (A.charAt(i - 1) != '0') {
                dp[i] = dp[i - 1] % MOD;
            }

            int twoDigitNum = (A.charAt(i - 2) - '0') * 10 + (A.charAt(i - 1) - '0');
            if (twoDigitNum >= 10 && twoDigitNum <= 26) {
                dp[i] = (dp[i] + dp[i - 2]) % MOD;
            }
        }

        return dp[n];
    }

    public static int iterativeOptimized(String A, int n) {
        if (n == 0 || A.charAt(0) == '0') return 0;

        int secondDigit = 1;
        int firstDigit = 1;

        for (int i = 2; i <= n; i++) {
            int count = 0;

            if (A.charAt(i - 1) != '0') {
                count = secondDigit % MOD;
            }

            int twoDigitNum = (A.charAt(i - 2) - '0') * 10 + (A.charAt(i - 1) - '0');
            if (twoDigitNum >= 10 && twoDigitNum <= 26) {
                count = (count + firstDigit) % MOD;
            }

            firstDigit = secondDigit;
            secondDigit = count;
        }

        return secondDigit;
    }

}
