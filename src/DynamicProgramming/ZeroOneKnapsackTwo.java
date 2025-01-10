package DynamicProgramming;
/*
Problem Description
Given two integer arrays A and B of size N each which represent
values and weights associated with N items respectively.
Also given an integer C which represents knapsack capacity.
Find out the maximum value subset of A such that sum of the weights of this subset is smaller than or equal to C.
NOTE: You cannot break an item, either pick the complete item, or don’t pick it (0-1 property).

Problem Constraints
1 <= N <= 500
1 <= C, B[i] <= 10^6
1 <= A[i] <= 50

Input Format:
First argument is an integer array A of size N denoting the values on N items.
Second argument is an integer array B of size N denoting the weights on N items.
Third argument is an integer C denoting the knapsack capacity.

Output Format:
Return a single integer denoting the maximum value
subset of A such that sum of the weights of this subset is smaller than or equal to C.

Example Input:
Input 1:
 A = [6, 10, 12]
 B = [10, 20, 30]
 C = 50

Input 2:
 A = [1, 3, 2, 4]
 B = [12, 13, 15, 19]
 C = 10

Example Output:
Output 1: 22
Output 2: 0

Example Explanation:
Explanation 1:
 Taking items with weight 20 and 30 will give us th
  maximum value i.e 10 + 12 = 22

Explanation 2:
 Knapsack capacity is 10 but each item has weight greater than 10
 so no items can be considered in the knapsack therefore answer is 0.

 */

import java.util.ArrayList;
import java.util.Arrays;

public class ZeroOneKnapsackTwo {
    public static void main(String[] args) {
        ArrayList<Integer> A = new ArrayList<>(Arrays.asList(6, 10, 12));
        ArrayList<Integer> B = new ArrayList<>(Arrays.asList(10, 20, 30));
        int C = 50;

        int res = bruteForce(A, B, 0, C);
        System.out.println(res);
        // Time O(2^N);
        // Space O(N);

        int[][] dp = new int[A.size()][C + 1];
        for (int[] row : dp) Arrays.fill(row, -1);
        int ans = recursive(A, B, 0, C, dp);
        System.out.println(ans);
        // Time O(N * C);
        // Space O(N * C);

        int val = iterative(A, B, A.size(), C);
        System.out.println(val);
        // Time O(N * C);
        // Space O(N * Total values);

        int test = optimized(A, B, C);
        System.out.println(test);
        // Time O(N * C);
        // Space O(C);
    }

    public static int bruteForce(ArrayList<Integer> values, ArrayList<Integer> weights, int i, int capacity) {
        if (i == values.size() || capacity == 0) return 0;

        int reject = bruteForce(values, weights, i + 1, capacity);

        int select = 0;
        if (weights.get(i) <= capacity) {
            select = bruteForce(values, weights, i + 1, capacity - weights.get(i)) + values.get(i);
        }

        return Math.max(select, reject);
    }

    public static int recursive(ArrayList<Integer> values, ArrayList<Integer> weights, int i, int capacity, int[][] dp) {
        if (i == values.size() || capacity == 0) return 0;

        if (dp[i][capacity] != -1) return dp[i][capacity];

        int reject = recursive(values, weights, i + 1, capacity, dp);

        int select = 0;
        if (weights.get(i) <= capacity) {
            select = recursive(values, weights, i + 1, capacity - weights.get(i), dp) + values.get(i);
        }

        dp[i][capacity] = Math.max(select, reject);

        return dp[i][capacity];
    }

    public static int iterative(ArrayList<Integer> values, ArrayList<Integer> weights, int n, int capacity) {
        int profit = values.stream().mapToInt(Integer::intValue).sum();

        int[][] dp = new int[n + 1][profit + 1];

        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE / 2);
        }

        for (int i = 0; i <= n; i++) {
            dp[i][0] = 0;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= profit; j++) {
                dp[i][j] = dp[i - 1][j];

                if (j >= values.get(i - 1)) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - values.get(i - 1)] + weights.get(i - 1));
                }
            }
        }

        int maxProfit = 0;
        for (int i = 0; i <= profit; i++) {
            if (dp[n][i] <= capacity) {
                maxProfit = i;
            }
        }

        return maxProfit;
    }

    public static int optimized(ArrayList<Integer> values, ArrayList<Integer> weights, int capacity) {
        int[] dp = new int[capacity + 1];

        for (int i = 0; i < values.size(); i++) {
            for (int w = capacity; w >= weights.get(i); w--) {
                dp[w] = Math.max(dp[w], dp[w - weights.get(i)] + values.get(i));
            }
        }

        return dp[capacity];
    }
}
