package DynamicProgramming;

/*
Problem Description
Given a number A, return number of ways you can draw
A chords in a circle with 2 x A points such that no 2 chords intersect.
Two ways are different if there exists a chord which is present in one way and not in other.
Return the answer modulo 10^9 + 7.

Problem Constraints
1 <= A <= 10^3

Input Format:
The first and the only argument contains the integer A.

Output Format:
Return an integer answering the query as described in the problem statement.

Example Input:
Input 1: A = 1
Input 2: A = 2

Example Output:
Output 1: 1
Output 2: 2

Example Explanation:
Explanation 1:
 If points are numbered 1 to 2 in clockwise direction,
 then different ways to draw chords are: {(1-2)} only. So, we return 1.

Explanation 2:
 If points are numbered 1 to 4 in clockwise direction,
 then different ways to draw chords are:{(1-2), (3-4)} and {(1-4), (2-3)}.
 So, we return 2.

 */

public class IntersectingChordsInACircle {
    public static void main(String[] args) {
        int A = 6;
        int res = find(A);
        System.out.println(res);
        // Time O(A^2);
        // Time O(A);
    }

    public static int find(int n) {
        int MOD = 1000000007;
        long[] catalan = new long[n + 1];
        catalan[0] = 1;
        catalan[1] = 1;

        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                catalan[i] = (catalan[i] + (catalan[j] * catalan[i - j - 1]) % MOD) % MOD;
            }
        }

        return (int) catalan[n];
    }
}
