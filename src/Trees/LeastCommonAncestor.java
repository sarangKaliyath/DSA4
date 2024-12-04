package Trees;

import com.sun.source.tree.Tree;

import java.util.ArrayList;
import java.util.Arrays;

/*
Problem Description:
Find the lowest common ancestor in an unordered binary tree A, given two values, B and C, in the tree.
Lowest common ancestor: the lowest common ancestor (LCA) of two nodes and w
in a tree or directed acyclic graph (DAG) is the lowest (i.e., deepest) node that has both v and w as descendants.

Problem Constraints
1 <= size of tree <= 100000
1 <= B, C <= 10^9

Input Format:
First argument is head of tree A.
Second argument is integer B.
Third argument is integer C.

Output Format:
Return the LCA.

Example Input:
Input 1:
      1
     /  \
    2    3
B = 2
C = 3

Input 2:
      1
     /  \
    2    3
   / \
  4   5
B = 4
C = 5

Example Output:
Output 1: 1
Output 2: 2

Example Explanation:
Explanation 1: LCA is 1.
Explanation 2: LCA is 2.

 */
public class LeastCommonAncestor {

    public static void main(String[] args) {
        ArrayList<Integer> A = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        int B = 41;
        int C = 5;
        BuildIntegerTree treeBuilder = new BuildIntegerTree();
        TreeNode root = treeBuilder.BuildIntegerTree(A);
        boolean isPresent = findIfValIsPresent(root, B) && findIfValIsPresent(root, C);
        if (isPresent) {
            TreeNode res = find(root, B, C);
            System.out.println(res.val);
        } else System.out.println(-1);
        // Time O(N);
        // Space O(Height of tree);
    }

    public static TreeNode find(TreeNode root, int B, int C) {
        if (root == null || root.val == B || root.val == C) return root;

        TreeNode leftLCA = find(root.left, B, C);
        TreeNode rightLCA = find(root.right, B, C);

        if (leftLCA != null && rightLCA != null) return root;
        else if (leftLCA != null) return leftLCA;
        else return rightLCA;
    }

    public static boolean findIfValIsPresent(TreeNode root, int x) {
        if (root == null) return false;
        if (root.val == x) return true;
        return findIfValIsPresent(root.left, x) || findIfValIsPresent(root.right, x);
    }
}
