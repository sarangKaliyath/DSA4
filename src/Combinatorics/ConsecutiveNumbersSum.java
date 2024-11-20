package Combinatorics;

/*
Problem Description:
Given a positive integer A.
Return the number of ways it can be written as a sum of consecutive positive integers.

Problem Constraints
1 <= A <= 10^9

Input Format:
The first and the only argument of input contains an integer, A.

Output Format:
Return an integer, representing the answer as described in the problem statement.

Example Input:
Input 1: A = 5
Input 2: A = 15

Example Output:
Output 1: 2
Output 2: 4

Example Explanation:
Explanation 1:
 The 2 ways are:
 => [5]
 => [2, 3]

Explanation 2:
 The 4 ways are:
 => [15]
 => [7, 8]
 => [4, 5, 6]
 => [1, 2, 3, 4, 5]
 */
public class ConsecutiveNumbersSum {
    public static void main(String[] args) {
        int A = 15;

        int res = bruteForce(A);
        System.out.println(res);
        // Time O(N^2);
        // Space O(1);

        int ans = optimized(A);
        System.out.println(ans);
        // Time O(Square root of A);
        // Space O(1);
    }

    public static int bruteForce(int A) {
        int count = 1;

        for (int i = 1; i <= A; i++) {
            int sum = 0;
            for (int j = i; j <= A; j++) {
                if (sum > A) break;
                else if (sum == A) count++;
                sum += j;
            }
        }

        return count;
    }

    public static int optimized(int A) {
        int count = 0;
        for (int k = 1; k * (k - 1) / 2 < A; k++) {
            int rem = A - k * (k - 1) / 2;
            if (rem % k == 0) count++;
        }
        return count;
    }
}
