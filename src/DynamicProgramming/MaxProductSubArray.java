package DynamicProgramming;

/*
Problem Description
Given an integer array A of size N.
Find the contiguous sub-array within the given array (containing at least one number) which has the largest product.
Return an integer corresponding to the maximum product possible.
NOTE: Answer will fit in 32-bit integer value.

Problem Constraints
1 <= N <= 5 * 10^5
-100 <= A[i] <= 100

Input Format:
First and only argument is an integer array A.

Output Format:
Return an integer corresponding to the maximum product possible.

Example Input:
Input 1:
 A = [4, 2, -5, 1]
Input 2:
 A = [-3, 0, -5, 0]

Example Output:
Output 1: 8
Output 2: 0

Example Explanation:
Explanation 1:
 We can choose the sub-array [4, 2] such that the maximum product is 8.
Explanation 2:
 0 will be the maximum product possible.
 */

import java.util.ArrayList;
import java.util.Arrays;

public class MaxProductSubArray {

    public static void main(String[] args) {
        ArrayList<Integer> A = new ArrayList<>(Arrays.asList(4, 2, -5, 1));

        int res = find(A, A.size());
        System.out.println(res);
        // Time O(N);
        // Space O(N);

        int ans = optimized(A, A.size());
        System.out.println(ans);
        // Time O(N);
        // Space O(1);
    }

    public static int find(ArrayList<Integer> A, int n) {
        int[] maxDp = new int[n];
        int[] minDp = new int[n];

        maxDp[0] = A.get(0);
        minDp[0] = A.get(0);
        int res = A.get(0);

        for (int i = 1; i < n; i++) {
            int currVal = A.get(i);

            maxDp[i] = Math.max(currVal, Math.max(currVal * maxDp[i - 1], currVal * minDp[i - 1]));
            minDp[i] = Math.min(currVal, Math.min(currVal * maxDp[i - 1], currVal * minDp[i - 1]));

            res = Math.max(res, Math.max(maxDp[i], minDp[i]));
        }

        return res;
    }

    public static int optimized(ArrayList<Integer> A, int n) {
        int currMax = A.get(0);
        int currMin = A.get(0);
        int res = A.get(0);

        for (int i = 1; i < n; i++) {
            int currVal = A.get(i);

            int tempMax = currMax;

            currMax = Math.max(currVal, Math.max(currVal * currMax, currVal * currMin));
            currMin = Math.min(currVal, Math.min(currVal * tempMax, currVal * currMin));

            res = Math.max(res, Math.max(currMax, currMin));
        }

        return res;
    }
}
