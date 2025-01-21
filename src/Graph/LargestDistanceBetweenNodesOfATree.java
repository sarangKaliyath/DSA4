package Graph;
/*
Problem Description:
Find largest distance Given an arbitrary unweighted rooted
tree which consists of N (2 <= N <= 40000) nodes.
The goal of the problem is to find the largest distance between
two nodes in a tree. Distance between two nodes is a number of edges
on a path between the nodes (there will be a unique path
between any pair of nodes since it is a tree).
The nodes will be numbered 0 through N - 1.
The tree is given as an array A, there is an edge between nodes A[i] and i (0 <= i < N).
Exactly one of the i's will have A[i] equal to -1, it will be root node.

Problem Constraints
2 <= |A| <= 40000

Input Format:
First and only argument is vector A

Output Format:
Return the length of the longest path

Example Input:
Input 1:
A = [-1, 0]

Input 2:
A = [-1, 0, 0]

Example Output:
Output 1: 1
Output 2: 2

Example Explanation:
Explanation 1: Path is 0 -> 1.
Explanation 2: Path is 1 -> 0 -> 2.

 */

import java.util.ArrayList;
import java.util.Arrays;

public class LargestDistanceBetweenNodesOfATree {
    public static void main(String[] args) {
        ArrayList<Integer> A = new ArrayList<>(Arrays.asList(-1, 0, 0));

        int res = findLargestDistance(A);
        System.out.println(res);
        // Time O(V + E);
        // Space O(V);
    }

    private static int maxDistance = 0;
    private static int farthestNode = 0;

    public static int findLargestDistance(ArrayList<Integer> A) {
        int source = 0;
        ArrayList<ArrayList<Integer>> graph = adjacencyList(A, source);


        dfs(graph, 0, 0, new boolean[A.size()]);
        dfs(graph, farthestNode, 0, new boolean[A.size()]);
        return maxDistance;
    }

    public static ArrayList<ArrayList<Integer>> adjacencyList(ArrayList<Integer> A, int source) {
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < A.size(); i++) graph.add(new ArrayList<>());

        for (int i = 0; i < A.size(); i++) {
            if (A.get(i) != -1) {
                int u = A.get(i);
                graph.get(u).add(i);
                graph.get(i).add(u);
            } else source = i;
        }

        return graph;
    }

    public static void dfs(ArrayList<ArrayList<Integer>> graph, int source, int depth, boolean[] visited) {
        visited[source] = true;

        if (depth > maxDistance) {
            maxDistance = depth;
            farthestNode = source;
        }

        for (int neighbour : graph.get(source)) {
            if (!visited[neighbour]) {
                dfs(graph, neighbour, depth + 1, visited);
            }
        }
    }
}
