package DynamicProgramming;

/*
Problem Description
Given an integer A, how many structurally unique BST's,
(binary search trees) exist that can store values 1...A?

Problem Constraints:
1 <= A <=18

Input Format:
First and only argument is the integer A

Output Format:
Return a single integer, the answer to the problem

Example Input:
Input 1: 1
Input 2: 2

Example Output:
Output 1: 1
Output 2: 2

Example Explanation:
Explanation 1:
 Only single node tree is possible.

Explanation 2:
 2 trees are possible, one rooted at 1 and other rooted at 2.

 */

public class UniqueBinarySearchTreesTwo {
    public static void main(String[] args) {
        int A = 2;

        int res = find(A);
        System.out.println(res);
        // Time O(A^2);
        // Space O(A);
    }

    public static int find(int n) {
        int[] catalan = new int[n + 1];
        catalan[0] = 1;
        catalan[1] = 1;

        for (int i = 2; i <= n; i++) {
            catalan[i] = 0;
            for (int j = 0; j < i; j++) {
                catalan[i] += catalan[j] * catalan[i - j - 1];
            }
        }

        return catalan[n];
    }
}
