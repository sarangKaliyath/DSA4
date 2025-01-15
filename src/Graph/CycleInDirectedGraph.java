package Graph;
/*
Problem Description
Given a directed graph having A nodes. A matrix B of size M x 2 is given which represents
the M edges such that there is a edge directed from node B[i][0] to node B[i][1].
Find whether the graph contains a cycle or not, return 1 if cycle is present else return 0.
NOTE:
    The cycle must contain at least two nodes.
    There are no self-loops in the graph.
    There are no multiple edges between two nodes.
    The graph may or may not be connected.
    Nodes are numbered from 1 to A.
    Your solution will run on multiple test cases. If you are using global variables make sure to clear them.

Problem Constraints
2 <= A <= 10^5
1 <= M <= min(200000,A*(A-1))
1 <= B[i][0], B[i][1] <= A

Input Format
The first argument given is an integer A representing the number of nodes in the graph.
The second argument given a matrix B of size M x 2 which represents
the M edges such that there is a edge directed from node B[i][0] to node B[i][1].

Output Format:
Return 1 if cycle is present else return 0.

Example Input:
Input 1:
 A = 5
 B = [  [1, 2]
        [4, 1]
        [2, 4]
        [3, 4]
        [5, 2]
        [1, 3] ]

Input 2:
 A = 5
 B = [  [1, 2]
        [2, 3]
        [3, 4]
        [4, 5] ]

Example Output:
Output 1: 1
Output 2: 0

Example Explanation:
Explanation 1:
 The given graph contain cycle 1 -> 3 -> 4 -> 1 or the cycle 1 -> 2 -> 4 -> 1

Explanation 2:
 The given graph doesn't contain any cycle.

 */

import java.util.ArrayList;
import java.util.Arrays;

public class CycleInDirectedGraph {
    public static void main(String[] args) {
        int A = 5;
        ArrayList<ArrayList<Integer>> B = new ArrayList<>(Arrays.asList(
                new ArrayList<>(Arrays.asList(1, 2)),
                new ArrayList<>(Arrays.asList(4, 1)),
                new ArrayList<>(Arrays.asList(2, 4)),
                new ArrayList<>(Arrays.asList(3, 4)),
                new ArrayList<>(Arrays.asList(5, 2)),
                new ArrayList<>(Arrays.asList(1, 3))
        ));

        int res = isCyclePresent(A, adjacencyList(A, B));
        System.out.println(res);
        // Time O(Number of Nodes (V) + Number of Edges(E));
        // Space O(Number of Nodes (V) + Number of Edges(E));
    }

    public static int isCyclePresent(int A, ArrayList<ArrayList<Integer>> graph) {

        boolean[] visited = new boolean[A + 1];
        boolean[] path = new boolean[A + 1];

        for (int i = 1; i <= A; i++) {
            if (!visited[i]) {
                if (dfs(graph, i, visited, path)) {
                    return 1;
                }
            }
        }

        return 0;
    }

    public static ArrayList<ArrayList<Integer>> adjacencyList(int A, ArrayList<ArrayList<Integer>> B) {
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

        for (int i = 0; i <= A; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < B.size(); i++) {
            int sourceNode = B.get(i).get(0);
            int targetNode = B.get(i).get(1);
            graph.get(sourceNode).add(targetNode);
        }

        return graph;
    }

    public static boolean dfs(ArrayList<ArrayList<Integer>> graph, int current, boolean[] visited, boolean[] path) {
        visited[current] = true;
        path[current] = true;

        for (int neighbour : graph.get(current)) {
            if (path[neighbour]) return true;
            if (!visited[neighbour]) {
                if (dfs(graph, neighbour, visited, path)) {
                    return true;
                }
            }
        }

        path[current] = false;
        return false;
    }
}
