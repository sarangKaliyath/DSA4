package DynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;

/*
Problem Description:
Given an array A of positive elements, you have to flip
the sign of some of its elements such that the resultant
sum of the elements of array should be minimum
non-negative(as close to zero as possible).
Return the minimum number of elements whose sign needs
to be flipped such that the resultant sum is minimum non-negative.

Problem Constraints
1 <= length of(A) <= 100
Sum of all the elements will not exceed 10,000.

Input Format:
First and only argument is an integer array A.

Output Format
Return an integer denoting the minimum number of elements whose sign needs to be flipped.

Example Input:
Input 1: A = [15, 10, 6]
Input 2: A = [14, 10, 4]

Example Output:
Output 1: 1
Output 2: 1

Example Explanation:
Explanation 1:
 Here, we will flip the sign of 15 and the resultant sum will be 1.

Explanation 2:
 Here, we will flip the sign of 14 and the resultant sum will be 0.
 Note that flipping the sign of 10 and 4 also gives the resultant sum 0 but flippings there sign are not minimum.
 */
public class FlipArray {

    public static class Result {
        int sum;
        int flips;

        public Result(int sum, int flips) {
            this.sum = sum;
            this.flips = flips;
        }
    }

    public static void main(String[] args) {
        ArrayList<Integer> A = new ArrayList<>(Arrays.asList(15, 10, 6));

        int n = A.size();
        int sum = A.stream().mapToInt(Integer::intValue).sum();

        Result ans = bruteForce(A, n, sum >> 1);
        System.out.println(ans.flips);
        // Time O(2*N);
        // Space O(n);

        Result[][] dp = new Result[n + 1][(sum >> 1) + 1];

        Result res = recursive(A, n, sum >> 1, dp);
        System.out.println(res.flips);
        // Time O(N * halfSum);
        // Space O(N * halfSum);

        int val = iterative(A, n, sum >> 1);
        System.out.println(val);
        // Time O(N * halfSum);
        // Space O(N * halfSum);

        int out = iterativeOptimized(A, n, sum >> 1);
        System.out.println(out);
        // Time O(N * halfSum);
        // Space O(halfSum);
    }


    public static Result bruteForce(ArrayList<Integer> A, int n, int halfSum) {
        if (n == 0 || halfSum == 0) {
            return new Result(0, 0);
        }

        Result keepCurrentElement = bruteForce(A, n - 1, halfSum);

        Result flipCurrentElement = new Result(0, 0);
        if (halfSum - A.get(n - 1) >= 0) {
            Result result = bruteForce(A, n - 1, halfSum - A.get(n - 1));
            flipCurrentElement = new Result(A.get(n - 1) + result.sum, 1 + result.flips);
        }

        return compare(keepCurrentElement, flipCurrentElement);
    }

    public static Result compare(Result a, Result b) {
        if (a.sum > b.sum) return a;
        if (b.sum > a.sum) return b;
        return new Result(a.sum, Math.min(a.flips, b.flips));
    }

    public static Result recursive(ArrayList<Integer> A, int n, int halfSum, Result[][] dp) {
        if (n == 0 || halfSum == 0) {
            return new Result(0, 0);
        }

        if (dp[n][halfSum] != null) return dp[n][halfSum];

        Result keepCurrentElement = recursive(A, n - 1, halfSum, dp);

        Result flipCurrentElement = new Result(0, 0);
        if (halfSum - A.get(n - 1) >= 0) {
            Result result = recursive(A, n - 1, halfSum - A.get(n - 1), dp);
            flipCurrentElement = new Result(A.get(n - 1) + result.sum, 1 + result.flips);
        }

        dp[n][halfSum] = compare(keepCurrentElement, flipCurrentElement);

        return dp[n][halfSum];
    }

    public static int iterative(ArrayList<Integer> A, int n, int halfSum) {

        Result[][] dp = new Result[n + 1][halfSum + 1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= halfSum; j++) {
                dp[i][j] = new Result(0, 0);
            }
        }

        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= halfSum; ++j) {
                Result keepCurrentElement = dp[i - 1][j];

                if (j >= A.get(i - 1)) {
                    Result flipCurrentElement = new Result(
                            A.get(i - 1) + dp[i - 1][j - A.get(i - 1)].sum,
                            1 + dp[i - 1][j - A.get(i - 1)].flips
                    );
                    dp[i][j] = compare(keepCurrentElement, flipCurrentElement);
                } else {
                    dp[i][j] = keepCurrentElement;
                }
            }
        }

        return dp[n][halfSum].flips;
    }

    public static int iterativeOptimized(ArrayList<Integer> A, int n, int halfSum) {
        Result[] dp = new Result[halfSum + 1];
        Arrays.fill(dp, new Result(0, 0));

        for (int i = 1; i <= n; i++) {
            for (int j = halfSum; j >= 1; j--) {
                Result keepCurrentElement = dp[j];

                if (j >= A.get(i - 1)) {
                    Result flipCurrentElement = new Result(
                            A.get(i - 1) + dp[j - A.get(i - 1)].sum,
                            1 + dp[j - A.get(i - 1)].flips
                    );
                    dp[j] = compare(keepCurrentElement, flipCurrentElement);
                }

            }
        }

        return dp[halfSum].flips;
    }
}
