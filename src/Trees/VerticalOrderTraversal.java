package Trees;

import java.util.*;
import java.util.Map.Entry;

/*
Problem Description:
Given a binary tree, return a 2-D array with vertical order traversal of it. Go through the example and image for more details.
NOTE: If 2 Tree Nodes shares the same vertical level then the one with lesser depth will come first.

Problem Constraints
0 <= number of nodes <= 10^5

Input Format:
First and only argument is a pointer to the root node of binary tree, A.

Output Format:
Return a 2D array denoting the vertical order traversal of tree as shown.

Example Input:
Input 1:
      6
    /   \
   3     7
  / \     \
 2   5     9

Input 2:
      1
    /   \
   3     7
  /       \
 2         9

Example Output:
Output 1:
 [
    [2],
    [3],
    [6, 5],
    [7],
    [9]
 ]

Output 2:
 [
    [2],
    [3],
    [1],
    [7],
    [9]
 ]

Example Explanation:
Explanation 1:
 First row represents vertical line 1 and so on.
 */
public class VerticalOrderTraversal {
    public static void main(String[] args) {
        ArrayList<Integer> A = new ArrayList<>(Arrays.asList(6, 3, 7, 2, 5, null, 9));
        BuildIntegerTree treeBuilder = new BuildIntegerTree();
        TreeNode root = treeBuilder.BuildIntegerTree(A);
        ArrayList<ArrayList<Integer>> res = traverse(root);
        System.out.println(res);
        // Time O(N);
        // Space O(N);
    }

    public static ArrayList<ArrayList<Integer>> traverse(TreeNode root) {
        HashMap<Integer, List<Integer>> hm = new HashMap<>();
        Deque<Entry<TreeNode, Integer>> q = new ArrayDeque<>();
        q.offer(new AbstractMap.SimpleEntry<>(root, 0));
        int minLevel = 0;
        int maxLevel = 0;

        while (!q.isEmpty()) {
            Entry<TreeNode, Integer> x = q.pollFirst();

            TreeNode node = x.getKey();
            int level = x.getValue();

            minLevel = Math.min(minLevel, level);
            maxLevel = Math.max(maxLevel, level);

            hm.putIfAbsent(level, new ArrayList<>());
            hm.get(level).add(node.val);


            if (node.left != null) {
                q.offerLast(new AbstractMap.SimpleEntry<>(node.left, level - 1));
            }
            if (node.right != null) {
                q.offerLast(new AbstractMap.SimpleEntry<>(node.right, level + 1));
            }
        }

        ArrayList<ArrayList<Integer>> res = new ArrayList<>();

        for(int i = minLevel; i <= maxLevel; i++){
            res.add(new ArrayList<>(hm.get(i)));
        }

        return res;
    }
}
