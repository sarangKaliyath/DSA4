package DynamicProgramming;

import javax.swing.plaf.synth.SynthUI;
import java.util.Arrays;

/*
Problem Description
Find out the number of A digit positive numbers,
whose digits on being added equals to a given number B.
Note that a valid number starts from digits 1-9 except the number 0 itself.
i.e. leading zeroes are not allowed.
Since the answer can be large, output answer modulo 1000000007

Problem Constraints
1 <= A <= 1000
1 <= B <= 10000

Input Format:
First argument is the integer A
Second argument is the integer B

Output Format:
Return a single integer, the answer to the problem

Example Input:
Input 1:
 A = 2
 B = 4

Input 2:
 A = 1
 B = 3

Example Output:
Output 1: 4
Output 2: 1

Example Explanation:
Explanation 1:
 Valid numbers are {22, 31, 13, 40}
 Hence output 4.

Explanation 2:
 Only valid number is 3

 */
public class NDigitNumbers {

    public static void main(String[] args) {
        int A = 4;
        int B = 4;

        int ans = bruteForce(A, B);
        System.out.println(ans);
        // Time O(A * 10^A);
        // Space O(1);

        int out = recursive(A, B);
        System.out.println(out);
        // Time O(A * B);
        // Space O(A * B);

        int res = iterative(A, B);
        System.out.println(res);
        // Time O(A*B);
        // Space O(A*B);
    }


    public static int bruteForce(int digits, int sum) {
        int MOD = 1000000007;
        int start = (int) Math.pow(10, digits - 1);
        int end = (int) Math.pow(10, digits);
        long count = 0;

        for (int i = start; i < end; i++) {
            if (digitSum(i) == sum) {
                count = (count + 1) % MOD;
            }
        }

        return (int) count;
    }

    public static long digitSum(int digits) {
        long sum = 0;
        while (digits > 0) {
            sum += digits % 10;
            digits /= 10;
        }
        return sum;
    }

    private static int MOD = 1000000007;

    public static int recursive(int A, int B) {
        int[][] dp = new int[A + 1][B + 1];

        for (int[] row : dp) Arrays.fill(row, -1);

        int res = 0;

        for (int digits = 1; digits <= 9; digits++) {
            if (B - digits >= 0) {
                res = (res + countNDigitNumbers(A - 1, B - digits, dp)) % MOD;
            }
        }

        return res;
    }

    public static int countNDigitNumbers(int digitsLeft, int sumLeft, int[][] dp) {

        if (sumLeft < 0) return 0;
        if (digitsLeft == 0 && sumLeft == 0) return 1;
        if (digitsLeft == 0) return 0;

        if (dp[digitsLeft][sumLeft] != -1) {
            return dp[digitsLeft][sumLeft];
        }

        int res = 0;

        for (int digit = 0; digit <= 9; digit++) {
            res = (res + countNDigitNumbers(digitsLeft - 1, sumLeft - digit, dp)) % MOD;
        }

        dp[digitsLeft][sumLeft] = res;

        return dp[digitsLeft][sumLeft];
    }

    public static int iterative(int A, int B) {
        int MOD = 1000000007;
        int[][] dp = new int[A + 1][B + 1];

        for (int col = 1; col <= 9; col++) {
            if (col <= B) {
                dp[1][col] = 1;
            }
        }

        for (int i = 2; i <= A; i++) {
            for (int j = 0; j <= B; j++) {
                for (int k = 0; k <= 9; k++) {
                    if (j >= k) {
                        dp[i][j] = (dp[i][j] + dp[i - 1][j - k]) % MOD;
                    }
                }
            }
        }

        return dp[A][B];
    }
}
