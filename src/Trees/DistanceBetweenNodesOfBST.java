package Trees;

import java.util.ArrayList;
import java.util.Arrays;

/*
Problem Description
Given a binary search tree.
Return the distance between two nodes with given two keys B and C. It may be assumed that both keys exist in BST.
NOTE: Distance between two nodes is number of edges between them.

Problem Constraints
1 <= Number of nodes in a binary tree <= 1000000
0 <= node values <= 10^9

Input Format:
First argument is a root node of the binary tree, A.
Second argument is integer B.
Third argument is integer C.

Output Format:
Return an integer denoting the distance between two nodes with given two keys B and C

Example Input:
Input 1:
         5
       /   \
      2     8
     / \   / \
    1   4 6   11
 B = 2
 C = 11

Input 2:
         6
       /   \
      2     9
     / \   / \
    1   4 7   10
 B = 2
 C = 6

Example Output:
Output 1: 3
Output 2: 1

Example Explanation:
Explanation 1:
 Path between 2 and 11 is: 2 -> 5 -> 8 -> 11. Distance will be 3.
Explanation 2:
 Path between 2 and 6 is: 2 -> 6. Distance will be 1
 */
public class DistanceBetweenNodesOfBST {

    public static void main(String[] args) {
        ArrayList<Integer> A = new ArrayList<>(Arrays.asList(5, 2, 8, 1, 4, 6, 11));
        int B = 2;
        int C = 11;

        BuildIntegerTree treeBuilder = new BuildIntegerTree();
        TreeNode root = treeBuilder.BuildIntegerTree(A);
        int res = find(root, B, C);
        System.out.println(res);
        // Time O(log N);
        // Space O(Height of Tree);
    }

    public static int find(TreeNode root, int B, int C) {
        if (B == C) return 0;

        TreeNode lcaNode = findLCA(root, B, C);

        return findDistanceToVal(lcaNode, B) + findDistanceToVal(lcaNode, C);
    }

    public static TreeNode findLCA(TreeNode root, int B, int C) {
        TreeNode current = root;
        while (current != null) {
            if (B < current.val && C < current.val) {
                current = current.left;
            } else if (B > current.val && C > current.val) {
                current = current.right;
            } else return current;
        }
        return current;
    }

    public static int findDistanceToVal(TreeNode root, int val) {
        if (root == null || root.val == val) return 0;
        if (val < root.val) return findDistanceToVal(root.left, val) + 1;
        else return findDistanceToVal(root.right, val) + 1;
    }
}
