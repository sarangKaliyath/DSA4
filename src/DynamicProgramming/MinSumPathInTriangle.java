package DynamicProgramming;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/*
Problem Description
Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.
Adjacent numbers for jth column of ith row is jth and (j+1)th column of (i + 1)th row

Problem Constraints
|A| <= 1000
A[i] <= 1000

Input Format
First and only argument is the vector of vector A defining the given triangle

Output Format
Return the minimum sum

Example Input:
Input 1:
A = [
         [2],
        [3, 4],
       [6, 5, 7],
      [4, 1, 8, 3]
    ]

Input 2:
 A = [ [1] ]

Example Output:
Output 1: 11
Output 2: 1

Example Explanation:
Explanation 1:
 The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).

Explanation 2:
 Only 2 can be collected.

 */
public class MinSumPathInTriangle {
    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> A = new ArrayList<>(Arrays.asList(
                new ArrayList<>(Arrays.asList(2)),
                new ArrayList<>(Arrays.asList(3, 4)),
                new ArrayList<>(Arrays.asList(6, 5, 7)),
                new ArrayList<>(Arrays.asList(4, 1, 8, 3))
        ));

        int res = bruteForce(A, 0, 0);
        System.out.println(res);
        // Time O(2 ^ N);
        // Space O(N);

        int n = A.size();
        int m = A.get(n - 1).size();
        int[][] dp = new int[n][m];
        for (int[] row : dp) Arrays.fill(row, -1);
        int ans = recursive(A, 0, 0, dp);
        System.out.println(ans);
        // Time O(N^2);
        // Space O(N^2);

        int val = iterative(A, A.size());
        System.out.println(val);
        // Time O(N^2);
        // Space O(N);
    }

    public static int bruteForce(ArrayList<ArrayList<Integer>> A, int i, int j) {
        int n = A.size();
        int m = A.get(0).size();
        if (i == n - 1) return A.get(i).get(j);
        return Math.min(bruteForce(A, i + 1, j), bruteForce(A, i + 1, j + 1)) + A.get(i).get(j);
    }

    public static int recursive(ArrayList<ArrayList<Integer>> A, int i, int j, int[][] dp) {
        if (i == A.size() - 1) return A.get(i).get(j);
        if (j < A.get(i).size() && dp[i][j] != -1) return dp[i][j];
        return dp[i][j] = Math.min(recursive(A, i + 1, j, dp), recursive(A, i + 1, j + 1, dp)) + A.get(i).get(j);
    }

    public static int iterative(ArrayList<ArrayList<Integer>> A, int n){
        int [] dp = new int [n];
        for(int i = 0; i < A.get(n - 1).size(); i++){
            dp[i] = A.get(n - 1).get(i);
        }

        for(int i = n - 2; i  >= 0; i--){
            for(int j = 0; j < A.get(i).size(); j++){
                dp[j] = Math.min(dp[j + 1], dp[j]) + A.get(i).get(j);
            }
        }

        return dp[0];
    }
}
