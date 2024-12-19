package Heaps;

import java.util.Arrays;

/*
Problem Description
Max Heap is a special kind of complete binary tree in which,
for every node, the value present in that node is greater than the value present in its children nodes.
Find the number of distinct Max Heap that can be made from A distinct integers.
In short, you have to ensure the following properties for the max heap :
    Heap has to be a complete binary tree
    ( A complete binary tree is a binary tree in which every level,
        except possibly the last, is completely filled, and all nodes are as far left as possible.)
    Every node is greater than all its children.
NOTE: Return your answer modulo 10^9 + 7.

Problem Constraints
1 <= A <= 100

Input Format:
The first and only argument is an integer A.

Output Format:
Return an integer denoting the number of distinct Max Heap.

Example Input:
Input 1: A = 4
Input 2: A = 10

Example Output:
Output 1: 3
Output 2: 3360

Example Explanation:
Explanation 1:
 Let us take 1, 2, 3, 4 as our 4 distinct integers
 Following are the 3 possible max heaps from these 4 numbers :
      4           4                     4
    /  \         / \                   / \
   3    2   ,   2   3      and        3   1
  /            /                     /
 1            1                     2

Explanation 2:
 Number of distinct heaps possible with 10 distinct integers = 3360.

 */
public class WaysToFormMaxHeap {
    public static void main(String[] args) {
        int A = 10;
        int res = find(A);
        System.out.println(res);
        // Time O(A);
        // Space O(A);
    }

    private static int MOD = 1000000007;
    private static long[] factorial;
    private static long[][] ncr;
    private static long[] dp;

    public static int find(int A) {
        if (A <= 1) return 1;
        generateFactorials(A);
        generateNCR(A);
        dp = new long[A + 1];
        Arrays.fill(dp, -1);
        return (int) findWays(A);
    }

    public static void generateFactorials(int A) {
        factorial = new long[A + 1];
        factorial[0] = 1;
        for (int i = 1; i <= A; i++) {
            factorial[i] = factorial[i - 1] * i % MOD;
        }
    }

    public static void generateNCR(int A) {
        ncr = new long[A + 1][A + 1];
        for (int i = 0; i <= A; i++) {
            ncr[i][0] = ncr[i][i] = 1;
            for (int j = 1; j < i; j++) {
                ncr[i][j] = (ncr[i - 1][j - 1] + ncr[i - 1][j]) % MOD;
            }
        }
    }

    public static long findWays(int n) {
        if (n <= 1) return 1;
        if (dp[n] != -1) return dp[n];

        int heightOfTree = (int) (Math.log(n) / Math.log(2));

        int maxNodesLastLevel = 1 << (heightOfTree);
        int totalNodesExceptLastLevel = (1 << heightOfTree) - 1;

        int leftSubtreeNodes = (totalNodesExceptLastLevel - 1) / 2 + Math.min(maxNodesLastLevel / 2, n - totalNodesExceptLastLevel);

        int rightSubTreeNodes = n - 1 - leftSubtreeNodes;

        long leftCount = findWays(leftSubtreeNodes);
        long rightCount = findWays(rightSubTreeNodes);

        long totalCount = ncr[n - 1][leftSubtreeNodes] * leftCount % MOD * rightCount % MOD;
        dp[n] = totalCount;
        return dp[n];
    }
}
