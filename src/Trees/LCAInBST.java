package Trees;

import java.util.ArrayList;
import java.util.Arrays;

/*
Problem Description:
Given a Binary Search Tree A. Also given are two nodes B and C. Find the lowest common ancestor of the two nodes.
Note 1: It is guaranteed that the nodes B and C exist.
Note 2: The LCA of B and C in A is the shared ancestor of B and C that is located farthest from the root.

Problem Constraints
1 <= Number of nodes in a binary tree <= 10^5
1 <= B , C <= 105

Input Format:
First argument is a root node of the binary tree, A.
Second argument is integer B.
Third argument is integer C.

Output Format:
Return the LCA of the two nodes

Example Input:
Input 1:
            15
          /    \
        12      20
        / \    /  \
       10  14  16  27
      /
     8

     B = 8
     C = 20

Input 2:
            8
           / \
          6  21
         / \
        1   7

     B = 7
     C = 1

Example Output:
Output 1: 15
Output 2: 6

Example Explanation:
Explanation 1: The LCA of node 8 and 20 is the node 15.
Explanation 2: The LCA of node 7 and 1 is the node 6.
 */
public class LCAInBST {

    public static void main(String[] args) {
        ArrayList<Integer> A = new ArrayList<>(Arrays.asList(15, 12, 20, 10, 14, 16, 27, 8));
        int B = 8;
        int C = 20;
        BuildIntegerTree treeBuilder = new BuildIntegerTree();
        TreeNode root = treeBuilder.BuildIntegerTree(A);
        int res = find(root, B, C);
        System.out.println(res);
        // Time O(N);
        // Space O(1);
    }

    public static int find(TreeNode root, int B, int C) {
        TreeNode current = root;
        while (current != null) {
            if (B < current.val && C < current.val) {
                current = current.left;
            } else if (B > current.val && C > current.val) {
                current = current.right;
            } else {
                return current.val;
            }
        }
        return -1;
    }
}
