package Trees;

import java.util.*;
import java.util.Map.Entry;

/*
Problem Description:
Given a binary tree of integers denoted by root A.
Return an array of integers representing the top view of the Binary tree.
The top view of a Binary Tree is a set of nodes visible when the tree is visited from the top.
Return the nodes in any order.

Problem Constraints
1 <= Number of nodes in binary tree <= 100000
0 <= node values <= 10^9

Input Format:
First and only argument is head of the binary tree A.

Output Format:
Return an array, representing the top view of the binary tree.

Example Input:
Input 1:
            1
          /   \
         2    3
        / \  / \
       4   5 6  7
      /
     8

Input 2:
            1
           /  \
          2    3
           \
            4
             \
              5

Example Output:
Output 1: [1, 2, 4, 8, 3, 7]
Output 2: [1, 2, 3]

Example Explanation:
Explanation 1:Top view is described.
Explanation 2: Top view is described.

 */
public class TopViewOfBinaryTree {
    public static void main (String [] args){
        ArrayList<Integer> A = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));
        BuildIntegerTree treeBuilder = new BuildIntegerTree();
        TreeNode root = treeBuilder.BuildIntegerTree(A);
        ArrayList<Integer> res = getTopView(root);
        System.out.println(res);
        // Time O(N);
        // Space O(N);
    }

    public static ArrayList<Integer> getTopView(TreeNode root){
        HashMap<Integer, Integer> hm = new HashMap<>();
        Deque<Entry<TreeNode, Integer>> q = new ArrayDeque<>();
        q.offerLast(new AbstractMap.SimpleEntry<>(root, 0));
        int min = 0 ; int max = 0;

        while(!q.isEmpty()){
            Entry<TreeNode, Integer> x = q.pollFirst();
            TreeNode node = x.getKey();
            Integer level = x.getValue();

            min = Math.min(min, level);
            max = Math.max(max, level);

            hm.putIfAbsent(level, node.val);

            if(node.left != null){
                q.offerLast(new AbstractMap.SimpleEntry<>(node.left, level - 1));
            }
            if(node.right != null){
                q.offerLast(new AbstractMap.SimpleEntry<>(node.right, level + 1));
            }
        }

        ArrayList<Integer> res = new ArrayList<>();
        for (int i = min; i <= max; i++){
            res.add(hm.get(i));
        }

        return  res;
    }
}
