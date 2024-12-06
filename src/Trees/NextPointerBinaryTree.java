package Trees;

import java.util.*;

/*
Problem Description:
Given a binary tree,
Populate each next pointer to point to its next right node.
If there is no next right node, the next pointer should be set to NULL.
Initially, all next pointers are set to NULL.
Assume perfect binary tree.

Problem Constraints
1 <= Number of nodes in binary tree <= 100000
0 <= node values <= 10^9

Input Format:
First and only argument is head of the binary tree A.

Output Format:
Return the head of the binary tree after the changes are made.

Example Input:
Input 1:
     1
    /  \
   2    3

Input 2:
        1
       /  \
      2    5
     / \  / \
    3  4  6  7

Example Output:
Output 1:
        1 -> NULL
       /  \
      2 -> 3 -> NULL

Output 2:
         1 -> NULL
       /  \
      2 -> 5 -> NULL
     / \  / \
    3->4->6->7 -> NULL

Example Explanation:
Explanation 1:
Next pointers are set as given in the output.
Explanation 2:
Next pointers are set as given in the output.

 */
public class NextPointerBinaryTree {
    public static void main(String[] args) {
        ArrayList<Integer> A = new ArrayList<>(Arrays.asList(1, 2, 5, 3, 4, 6, 7));
        BuildIntegerTree treeBuilder = new BuildIntegerTree();
        TreeNextNode root = treeBuilder.BuildNextTree(A);
        TreeNextNode res = generate(root);
        System.out.println(res);
        // Time O(N);
        // Space O(N);
    }

    public static TreeNextNode generate(TreeNextNode root) {
        TreeNextNode last = root;
        Deque<TreeNextNode> q = new ArrayDeque<>();
        q.offerLast(root);

        while (!q.isEmpty()) {
            TreeNextNode x = q.pollFirst();
            if (x.left != null) q.offerLast(x.left);
            if (x.right != null) q.offerLast(x.right);
            if (x != last) {
                x.next = q.peekFirst();
            } else {
                if (!q.isEmpty()) last = q.peekLast();
            }
        }

        return root;
    }
}
