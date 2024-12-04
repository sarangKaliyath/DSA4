package Trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BuildIntegerTree {

    public TreeNode BuildIntegerTree(ArrayList<Integer> A) {
        if (A == null || A.size() == 0) return null;

        // Create the root node
        TreeNode root = new TreeNode(A.get(0));

        // Use a queue to keep track of the nodes
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int i = 1; // Pointer to traverse the array

        // Build the tree level by level
        while (i < A.size() && !queue.isEmpty()) {
            TreeNode currentNode = queue.poll();

            // Left child
            if (i < A.size() && A.get(i) != null) {
                currentNode.left = new TreeNode(A.get(i));
                queue.offer(currentNode.left);
            }
            i++;

            // Right child
            if (i < A.size() && A.get(i) != null) {
                currentNode.right = new TreeNode(A.get(i));
                queue.offer(currentNode.right);
            }
            i++;
        }
        return root;
    }
}
