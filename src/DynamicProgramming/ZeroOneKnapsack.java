package DynamicProgramming;

/*
Problem Description
Given two integer arrays A and B of size N each which represent
values and weights associated with N items respectively.
Also given an integer C which represents knapsack capacity.
Find out the maximum value subset of A such that sum of the
weights of this subset is smaller than or equal to C.
NOTE: You cannot break an item, either pick the complete item,
or don’t pick it (0-1 property).

Problem Constraints
1 <= N <= 10^3
1 <= C <= 10^3
1 <= A[i], B[i] <= 10^3

Input Format:
First argument is an integer array A of size N denoting the values on N items.
Second argument is an integer array B of size N denoting the weights on N items.
Third argument is an integer C denoting the knapsack capacity.

Output Format:
Return a single integer denoting the maximum value subset of A
such that sum of the weights of this subset is smaller than or equal to C.

Example Input:
Input 1:
 A = [60, 100, 120]
 B = [10, 20, 30]
 C = 50

Input 2:
 A = [10, 20, 30, 40]
 B = [12, 13, 15, 19]
 C = 10

Example Output:
Output 1: 220
Output 2: 0

Example Explanation:
Explanation 1:
 Taking items with weight 20 and 30 will give us the maximum value i.e 100 + 120 = 220

Explanation 2:
 Knapsack capacity is 10 but each item has weight greater than 10 so no items
 can be considered in the knapsack therefore answer is 0.

 */

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class ZeroOneKnapsack {

    public static class Items {
        public int values;
        public int weights;

        public Items(int values, int weights) {
            this.values = values;
            this.weights = weights;
        }
    }

    public static void main (String [] args){
        ArrayList<Integer> A = new ArrayList<>(Arrays.asList(60, 100, 120));
        ArrayList<Integer> B = new ArrayList<Integer>(Arrays.asList(10, 20, 30));
        int C = 50;
        int n = A.size();
        ArrayList<Items> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            list.add(new Items(A.get(i), B.get(i)));
        }

        int res = bruteForce(list, 0, C);
        System.out.println(res);
        // Time O(2^N);
        // Space O(N)

        int [][] dp = new int[n][C + 1];
        for(int [] row : dp) Arrays.fill(row, -1);
        int ans = recursive(list, 0, C, dp);
        System.out.println(ans);
        // Time O(N * C);
        // Space O(N * C);

        int val = iterative(list, n, C);
        System.out.println(val);
        // Time O(N * C);
        // Space O(N * C);
    }

    public static  int bruteForce(ArrayList<Items> list, int i, int capacity){
        if(i == list.size() || capacity == 0) return 0;

        if(list.get(i).weights > capacity){
            return bruteForce(list, i + 1, capacity);
        }

        int select = bruteForce(list, i + 1, capacity - list.get(i).weights) + list.get(i).values;
        int reject = bruteForce(list, i + 1, capacity);

        return Math.max(select, reject);
    }

    public static int recursive(ArrayList<Items> list, int i, int capacity, int [][] dp){
        if(i == list.size() || capacity == 0) return  0;

        if(list.get(i).weights > capacity){
            dp[i][capacity] =  recursive(list, i + 1, capacity, dp);
            return dp[i][capacity];
        }

        if(dp[i][capacity] != -1) return dp[i][capacity];

        int selected = recursive(list, i + 1, capacity - list.get(i).weights, dp) + list.get(i).values;
        int rejected = recursive(list, i + 1, capacity, dp);

        dp[i][capacity] = Math.max(selected, rejected);

        return dp[i][capacity];
    }

    public  static int iterative(ArrayList<Items> list, int n, int capacity){
        int [][] dp = new int[n][capacity + 1];
        dp[0][0] = 0;

        for(int j = 1; j <= capacity; j++){
            if(j < list.get(0).weights) dp[0][j] = 0;
            else dp[0][j] = list.get(0).values;
        }

        for(int i = 1; i < n; i++){
            for(int j = 1; j <= capacity; j++){
                if(j < list.get(i).weights) dp[i][j] = dp[i -1][j];
                else {
                    dp[i][j] = Math.max(dp[i - 1][j], list.get(i).values + dp[i - 1][j - list.get(i).weights]);
                }
            }
        }

        return dp[n - 1][capacity];
    }
}
