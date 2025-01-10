package DynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;

/*
Problem Description:
Given two sequences A and B,
count number of unique ways in sequence A,
to form a subsequence that is identical to the sequence B.
Subsequence : A subsequence of a string is a new string which
is formed from the original string by deleting some (can be none)
of the characters without disturbing the relative positions of the
remaining characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).

Problem Constraints:
1 <= length(A), length(B) <= 700

Input Format:
The first argument of input contains a string A.
The second argument of input contains a string B.

Output Format:
Return an integer representing the answer as described in the problem statement.

Example Input:
Input 1:
 A = "abc"
 B = "abc"

Input 2:
 A = "rabbbit"
 B = "rabbit"

Example Output:
Output 1: 1
Output 2: 3

Example Explanation:
Explanation 1:
 Both the strings are equal.

Explanation 2:
 These are the possible removals of characters:
    => A = "ra_bbit"
    => A = "rab_bit"
    => A = "rabb_it"

 Note: "_" marks the removed character.


 */
public class DistinctSubsequences {

    public static void main(String[] args) {
        String A = "rabbbit";
        String B = "rabbit";

        int res = bruteForce(A, B, 0, 0);
        System.out.println(res);
        // Time O(2^N);
        // Space O(N * M);

        int[][] dp = new int[A.length()][B.length()];
        for (int[] row : dp) Arrays.fill(row, -1);
        int ans = recursive(A, B, 0, 0, dp);
        System.out.println(ans);
        // Time O(N*M);
        // Space O(N*M);

        int val = iterative(A, A.length(), B, B.length());
        System.out.println(val);
        // Time O(N * M);
        // Space O(N * M);
    }

    public static int bruteForce(String A, String B, int i, int j) {
        if (j == B.length()) return 1;

        if (i == A.length()) return 0;

        int reject = bruteForce(A, B, i + 1, j);

        int select = 0;
        if (A.charAt(i) == B.charAt(j)) {
            select = bruteForce(A, B, i + 1, j + 1);
        }

        return reject + select;
    }

    public static int recursive(String A, String B, int i, int j, int[][] dp) {
        if (j == B.length()) return 1;
        if (i == A.length()) return 0;

        if (dp[i][j] != -1) return dp[i][j];

        int reject = recursive(A, B, i + 1, j, dp);

        int select = 0;
        if (A.charAt(i) == B.charAt(j)) {
            select = recursive(A, B, i + 1, j + 1, dp);
        }

        dp[i][j] = select + reject;

        return dp[i][j];
    }

    public static int iterative(String A, int n, String B, int m) {
        int[][] dp = new int[n + 1][m + 1];

        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }

        for (int j = 1; j <= m; j++) {
            dp[0][j] = 0;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (A.charAt(i - 1) == B.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[n][m];
    }
}
