package Trees;

import java.util.ArrayList;
import java.util.Arrays;

/*
Problem Description:
Two elements of a Binary Search Tree (BST),
represented by root A are swapped by mistake.
Tell us the 2 values, when swapped, will restore the Binary Search Tree (BST).
A solution using O(n) space is pretty straightforward.
Could you devise a constant space solution?
Note: The 2 values must be returned in ascending order

Problem Constraints
1 <= size of tree <= 100000

Input Format
First and only argument is the head of the tree,A

Output Format:
Return the 2 elements which need to be swapped.

Example Input:
Input 1:
         1
        / \
       2   3

Input 2:
         2
        / \
       3   1

Example Output:
Output 1: [2, 1]
Output 2: [3, 1]

Example Explanation

Explanation 1:

Swapping 1 and 2 will change the BST to be
         2
        / \
       1   3
which is a valid BST

Explanation 2:

Swapping 1 and 3 will change the BST to be
         2
        / \
       1   3
which is a valid BST

 */
public class RecoverBinarySearchTree {
    public static void main (String [] args){
        ArrayList<Integer> A = new ArrayList<>(Arrays.asList(2, 3, 1));
        BuildIntegerTree treeBuilder = new BuildIntegerTree();
        TreeNode root = treeBuilder.BuildIntegerTree(A);
        ArrayList<Integer> res = find(root);
        System.out.println(res);
        // Time O(N);
        // Space O(1);
    }

    public static TreeNode first = null;
    public static TreeNode second = null;
    public static TreeNode prev = null;
    public static ArrayList<Integer> find(TreeNode root){
        morrisInorderTraversal(root);
        rearrange();
        return new ArrayList<>(Arrays.asList(first.val, second.val));
    }

    public static void morrisInorderTraversal(TreeNode root){
        TreeNode current = root;
        while(current != null){
            if(current.left == null){
                isViolation(current);
                current = current.right;
            }
            else {
                TreeNode prev = current.left;
                while(prev.right != null && prev.right != current){
                    prev = prev.right;
                }

                if(prev.right == null){
                    prev.right = current;
                    current = current.left;
                }
                else {
                    prev.right = null;
                    isViolation(current);
                    current = current.right;
                }
            }
        }
    }

    public static void isViolation(TreeNode root){
        if(prev != null && prev.val > root.val){
            if(first == null) first = prev;
            second = root;
        }
        prev = root;
    }

    public static void rearrange(){
        if(first != null && second != null && first.val > second.val){
            TreeNode temp = first;
            first = second;
            second = temp;
        }
    }
}
