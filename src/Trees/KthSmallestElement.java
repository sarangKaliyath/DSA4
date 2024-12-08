package Trees;

import com.sun.source.tree.Tree;

import java.util.ArrayList;
import java.util.Arrays;

/*
Problem Description
Given a binary search tree represented by root A, write a function to find the Bth smallest element in the tree.

Problem Constraints
1 <= Number of nodes in binary tree <= 100000
0 <= node values <= 10^9

Input Format:
First and only argument is head of the binary tree A.

Output Format:
Return an integer, representing the Bth element.

Example Input:
Input 1:
            2
          /   \
         1    3
B = 2

Input 2:
            3
           /
          2
         /
        1
B = 1

Example Output:
Output 1: 2
Output 2: 1

Example Explanation:
Explanation 1: 2nd element is 2.
Explanation 2: 1st element is 1.

 */
public class KthSmallestElement {

    private static int count = 0;
    private static int ans = Integer.MIN_VALUE;

    public static void main(String[] args) {
        ArrayList<Integer> A = new ArrayList<>(Arrays.asList(3, null, 4));
        int B = 2;

        BuildIntegerTree treeBuilder = new BuildIntegerTree();
        TreeNode root = treeBuilder.BuildIntegerTree(A);
        find(root, B);
        System.out.println(ans);
        // Time O(N);
        // Space O(Height of Tree);
    }

    public static void find(TreeNode root, int B) {
        if (root == null) return;

        find(root.left, B);
        count++;

        if (count == B) {
            ans = root.val;
            return;
        } else find(root.right, B);
    }
}
