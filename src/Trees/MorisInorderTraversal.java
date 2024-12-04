package Trees;

import java.util.ArrayList;
import java.util.Arrays;

/*
Problem Description:
Given a binary tree, return the inorder traversal of its nodes' values.
NOTE: Using recursion and stack are not allowed.

Problem Constraints
1 <= number of nodes <= 10^5

Input Format:
First and only argument is root node of the binary tree, A.

Output Format:
Return an integer array denoting the inorder traversal of the given binary tree.

Example Input:
Input 1:
   1
    \
     2
    /
   3

Input 2:
   1
  / \
 6   2
    /
   3

Example Output:
Output 1: [1, 3, 2]
Output 2: [6, 1, 3, 2]

Example Explanation:
Explanation 1:
 The Inorder Traversal of the given tree is [1, 3, 2].
Explanation 2:
 The Inorder Traversal of the given tree is [6, 1, 3, 2].
 */
public class MorisInorderTraversal {

    public static void main(String[] args) {
        ArrayList<Integer> A = new ArrayList<>(Arrays.asList(1, 6, 2, null, null, 3, null));
        BuildIntegerTree treeBuilder = new BuildIntegerTree();
        TreeNode root = treeBuilder.BuildIntegerTree(A);

        ArrayList<Integer> res = morrisInOrderTraversal(root, new ArrayList<Integer>());
        System.out.println(res);
        // Time O(N);
        // Space O(1);

    }

    public static ArrayList<Integer> morrisInOrderTraversal(TreeNode root, ArrayList<Integer> res) {
        TreeNode current = root;

        while (current != null) {
            if (current.left == null) {
                res.add(current.val);
                current = current.right;
            } else {
                TreeNode prev = current.left;
                while (prev.right != null && prev.right != current) {
                    prev = prev.right;
                }

                if (prev.right == null) {
                    prev.right = current;
                    current = current.left;
                } else {
                    prev.right = null;
                    res.add(current.val);
                    current = current.right;
                }
            }
        }
        return res;
    }
}
