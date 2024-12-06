package Trees;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/*
Problem Description:
Given a binary tree A, invert the binary tree and return it.
Inverting refers to making the left child the right child and vice versa.

Problem Constraints:
1 <= size of tree <= 100000

Input Format:
First and only argument is the head of the tree A.

Output Format:
Return the head of the inverted tree.

Example Input:
Input 1:
     1
   /   \
  2     3

Input 2:
     1
   /   \
  2     3
 / \   / \
4   5 6   7

Example Output:
Output 1:
     1
   /   \
  3     2

Output 2:
     1
   /   \
  3     2
 / \   / \
7   6 5   4

Example Explanation:
Explanation 1:
Tree has been inverted.
Explanation 2:
Tree has been inverted.

 */
public class InvertTheBinaryTree {
    public static  void main (String [] args){
        ArrayList<Integer> A =new ArrayList<>(Arrays.asList(1, 3, 2, 7, 6, 5, 4));
        BuildIntegerTree treeBuilder = new BuildIntegerTree();
        TreeNode root= treeBuilder.BuildIntegerTree(A);
        TreeNode ans = invert(root);
        // Time O(N);
        // Space O(H);
    }

    public static TreeNode invert(TreeNode root){
        if(root == null) return null;

        TreeNode leftNode = invert(root.left);
        TreeNode rightNode = invert(root.right);

        root.left = rightNode;
        root.right = leftNode;

        return root;
    }
}
