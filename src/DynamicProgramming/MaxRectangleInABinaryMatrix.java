package DynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

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
