package DynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;

/*
Problem Description
Given a knapsack weight A and a set of items with certain value B[i] and weight C[i],
we need to calculate maximum amount that could fit in this quantity.
This is different from classical Knapsack problem,
here we are allowed to use unlimited number of instances of an item.

Problem Constraints:
1 <= A <= 1000
1 <= |B| <= 1000
1 <= B[i] <= 1000
1 <= C[i] <= 1000

Input Format:
First argument is the Weight of knapsack A
Second argument is the vector of values B
Third argument is the vector of weights C

Output Format:
Return the maximum value that fills the knapsack completely

Example Input:
Input 1:
A = 10
B = [5]
C = [10]

Input 2:
A = 10
B = [6, 7]
C = [5, 5]

Example Output:
Output 1: 5
Output 2: 14

Example Explanation:
Explanation 1:
Only valid possibility is to take the given item.

Explanation 2:
Take the second item twice.

 */
public class UnboundedKnapsack {

    public static void main(String[] args) {
        int A = 10;
        ArrayList<Integer> B = new ArrayList<>(Arrays.asList(6, 7));
        ArrayList<Integer> C = new ArrayList<>(Arrays.asList(5, 5));
        int n = B.size();

        int res = bruteForce(A, B, C, n);
        System.out.println(res);
        // Time O(2^N * A);
        // Space O(N);


        int[][] dp = new int[n][A + 1];
        for (int[] row : dp) Arrays.fill(row, -1);
        int ans = recursive(A, B, C, 0, dp);
        System.out.println(ans);
        // Time O(N * A);
        // Space O(N * A);

        int val = iterative(A, B, C, n);
        System.out.println(val);
        // Time O(N * A);
        // Space O(A);

    }

    public static int bruteForce(int capacity, ArrayList<Integer> values, ArrayList<Integer> weights, int n) {
        if (capacity == 0) return 0;

        int maxValue = 0;

        for (int i = 0; i < n; i++) {
            if (weights.get(i) <= capacity) {
                int select = values.get(i) + bruteForce(capacity - weights.get(i), values, weights, n);
                maxValue = Math.max(maxValue, select);
            }
        }

        return maxValue;
    }

    public static int recursive(int capacity, ArrayList<Integer> values, ArrayList<Integer> weights, int i, int[][] dp) {
        if (i == values.size() || capacity == 0) return 0;

        if (dp[i][capacity] != -1) return dp[i][capacity];

        int select = 0;
        if (weights.get(i) <= capacity) {
            select = values.get(i) + recursive(capacity - weights.get(i), values, weights, i, dp);
        }

        int reject = recursive(capacity, values, weights, i + 1, dp);

        dp[i][capacity] = Math.max(select, reject);

        return dp[i][capacity];
    }

    public static int iterative(int capacity, ArrayList<Integer> values, ArrayList<Integer> weights, int n) {
        int[] dp = new int[capacity + 1];
        dp[0] = 0;

        for (int i = 1; i <= capacity; i++) {
            for (int j = 0; j < n; j++) {
                if (i >= weights.get(j)) {
                    dp[i] = Math.max(dp[i], values.get(j) + dp[i - weights.get(j)]);
                }
            }
        }

        return dp[capacity];
    }

}
