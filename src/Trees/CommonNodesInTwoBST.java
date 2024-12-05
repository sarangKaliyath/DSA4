package Trees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

/*
Problem Description
Given two BST's A and B, return the (sum of all common nodes in both A and B) % (109 +7) .
In case there is no common node, return 0.
NOTE: Try to do it one pass through the trees.

Problem Constraints
1 <= Number of nodes in the tree A and B <= 10^5
1 <= Node values <= 10^6

Input Format:
First argument represents the root of BST A.
Second argument represents the root of BST B.

Output Format
Return an integer denoting the (sum of all common nodes in both BST's A and B) % (109 +7) .

Example Input:
Input 1:
 Tree A:
    5
   / \
  2   8
   \   \
    3   15
        /
        9
 Tree B:
    7
   / \
  1  10
   \   \
    2  15
       /
      11

Input 2:
  Tree A:
    7
   / \
  1   10
   \   \
    2   15
        /
       11
 Tree B:
    7
   / \
  1  10
   \   \
    2  15
       /
      11

Example Output:
Output 1: 17
Output 2: 46

Example Explanation:
Explanation 1:
 Common Nodes are : 2, 15
 So answer is 2 + 15 = 17
Explanation 2:
 Common Nodes are : 7, 2, 1, 10, 15, 11
 So answer is 7 + 2 + 1 + 10 + 15 + 11 = 46

 */
public class CommonNodesInTwoBST {

    public static void main(String[] args) {
        ArrayList<Integer> A = new ArrayList<>(Arrays.asList(7, 1, 10, null, 2, null, 15, null, null, 11));
        ArrayList<Integer> B = new ArrayList<>(Arrays.asList(7, 1, 10, null, 2, null, 15, null, null, 11));

        BuildIntegerTree treeBuilder = new BuildIntegerTree();
        TreeNode rootA = treeBuilder.BuildIntegerTree(A);
        TreeNode rootB = treeBuilder.BuildIntegerTree(B);

        int res = findSum(rootA, rootB);
        System.out.println(res);
        // Time O(N + M):
        // Space O(H1 + H2);
    }

    public static int findSum(TreeNode rootA, TreeNode rootB) {
        if (rootA == null || rootB == null) return 0;

        int MOD = (int) Math.pow(10, 9) + 7;
        int sum = 0;

        Stack<TreeNode> stackA = new Stack<>();
        Stack<TreeNode> stackB = new Stack<>();
        TreeNode currentA = rootA;
        TreeNode currentB = rootB;

        while ((currentA != null || !stackA.isEmpty()) && (currentB != null || !stackB.isEmpty())) {
            while (currentA != null) {
                stackA.push(currentA);
                currentA = currentA.left;
            }

            while (currentB != null) {
                stackB.push(currentB);
                currentB = currentB.left;
            }

            TreeNode nodeA = stackA.peek();
            TreeNode nodeB = stackB.peek();

            if (nodeA.val == nodeB.val) {
                sum = (sum + nodeA.val) % MOD;
                stackA.pop();
                stackB.pop();
                currentA = nodeA.right;
                currentB = nodeB.right;
            } else if (nodeA.val < nodeB.val) {
                stackA.pop();
                currentA = nodeA.right;
            } else {
                stackB.pop();
                currentB = nodeB.right;
            }

        }
        return sum;
    }

}
