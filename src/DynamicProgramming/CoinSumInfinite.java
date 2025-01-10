package DynamicProgramming;

/*
Problem Description
You are given a set of coins A.
In how many ways can you make sum B assuming you have infinite amount of each coin in the set.
NOTE:
Coins in set A will be unique. Expected space complexity of this problem is O(B).
The answer can overflow. So, return the answer % (106 + 7).

Problem Constraints
1 <= A <= 500
1 <= A[i] <= 1000
1 <= B <= 50000

Input Format:
First argument is an integer array A representing the set.
Second argument is an integer B.

Output Format:
Return an integer denoting the number of ways.

Example Input:
Input 1:
 A = [1, 2, 3]
 B = 4

Input 2:
 A = [10]
 B = 10



Example Output:
Output 1: 4
Output 2: 1

Example Explanation:
Explanation 1:
 The 4 possible ways are:
 {1, 1, 1, 1}
 {1, 1, 2}
 {2, 2}
 {1, 3}

Explanation 2:
 There is only 1 way to make sum 10.
 */

import java.util.ArrayList;
import java.util.Arrays;

public class CoinSumInfinite {
    public static void main(String[] args) {
        ArrayList<Integer> A = new ArrayList<>(Arrays.asList(1, 2, 3));
        int B = 4;
        int n = A.size();

        int res = bruteForce(A, 0, B);
        System.out.println(res);
        // Time O(2^N);
        // Space O(B);

        int[][] dp = new int[A.size()][B + 1];
        for (int[] row : dp) Arrays.fill(row, -1);
        int ans = recursive(A, 0, B, dp);
        System.out.println(ans);
        // Time O(N * B);
        // Space O(N * B);

        int val = find(A, B);
        System.out.println(val);
        // Time O(N * B);
        // Space O(B);
    }

    public static int bruteForce(ArrayList<Integer> A, int i, int capacity) {
        if (i == A.size() || capacity <= 0) return 0;

        int reject = bruteForce(A, i + 1, capacity);

        int select = 0;
        if (capacity >= A.get(i)) {
            select = bruteForce(A, i, capacity - A.get(i)) + 1;
        }

        return Math.max(reject, select);
    }

    public static int recursive(ArrayList<Integer> A, int i, int capacity, int[][] dp) {
        if (capacity == 0) return 1;
        if (i == A.size() || capacity < 0) return 0;

        if (dp[i][capacity] != -1) return dp[i][capacity];

        int reject = recursive(A, i + 1, capacity, dp);

        int select = 0;
        if (capacity >= A.get(i)) {
            select = recursive(A, i, capacity - A.get(i), dp);
        }

        dp[i][capacity] = (reject + select) % 1000007;
        return dp[i][capacity];
    }

    public static int find(ArrayList<Integer> A, int B) {
        int[] dp = new int[B + 1];
        dp[0] = 1;

        for (Integer coins : A) {
            for (int j = coins; j <= B; j++) {
                if (coins <= j) {
                    dp[j] = (dp[j] + dp[j - coins]) % 1000007;
                }
            }
        }

        return dp[B];
    }
}
