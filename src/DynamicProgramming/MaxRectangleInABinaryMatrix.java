package DynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
/*
Problem Description
Given a 2-D binary matrix A of size N x M filled with 0's and 1's,
find the largest rectangle containing only ones and return its area.

Problem Constraints:
1 <= N, M <= 100

Input Format:
The first argument is a 2-D binary array A.

Output Format:
Return an integer denoting the area of the largest rectangle containing only ones.

Example Input:
Input 1:
 A = [
       [1, 1, 1]
       [0, 1, 1]
       [1, 0, 0]
     ]

Input 2:
 A = [
       [0, 1, 0]
       [1, 1, 1]
     ]

Example Output:
Output 1: 4
Output 2: 3

Example Explanation:
Explanation 1:
 As the max area rectangle is created by the 2x2 rectangle created by (0, 1), (0, 2), (1, 1) and (1, 2).

Explanation 2:
 As the max area rectangle is created by the 1x3 rectangle created by (1, 0), (1, 1) and (1, 2).
 */
public class MaxRectangleInABinaryMatrix {
    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> A = new ArrayList<>(Arrays.asList(
                new ArrayList<>(Arrays.asList(1, 1, 1)),
                new ArrayList<>(Arrays.asList(0, 1, 1)),
                new ArrayList<>(Arrays.asList(1, 0, 0))
        ));


        int res = findMaxArea(A, A.size(), A.get(0).size());
        System.out.println(res);
        // Time O(N*M);
        // Space O(M);
    }

    public static int findMaxArea(ArrayList<ArrayList<Integer>> A, int n, int m) {
        if (n == 0) return 0;

        int[] dp = new int[m];
        int maxArea = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int val = A.get(i).get(j);
                dp[j] = val == 0 ? 0 : dp[j] + 1;
            }

            maxArea = Math.max(maxArea, rectangleArea(dp, m));
        }

        return maxArea;
    }

    public static int rectangleArea(int[] dp, int n) {
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;

        for (int i = 0; i <= n; i++) {
            int val = (i == n) ? 0 : dp[i];
            while (!stack.isEmpty() && val < dp[stack.peek()]) {
                int height = dp[stack.pop()];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, height * width);
            }
            stack.push(i);
        }

        return maxArea;
    }
}
