package Graph;

/*
Problem Description:
You are given N towns (1 to N). All towns are connected via unique directed path as mentioned in the input.
Given 2 towns find whether you can reach the first town from the second without repeating any edge.
B C : query to find whether B is reachable from C.
Input contains an integer array A of size N and 2 integers B and C ( 1 <= B, C <= N ).
There exist a directed edge from A[i] to i+1 for every 1 <= i < N. Also,
it's guaranteed that A[i] <= i for every 1 <= i < N.
NOTE: Array A is 0-indexed. A[0] = 1 which you can ignore as it doesn't represent any edge.

Problem Constraints:
1 <= N <= 100000

Input Format
First argument is vector A
Second argument is integer B
Third argument is integer C

Output Format
Return 1 if reachable, 0 otherwise.

Example Input:
Input 1:
 A = [1, 1, 2]
 B = 1
 C = 2

Input 2:
 A = [1, 1, 2]
 B = 2
 C = 1

Example Output:
Output 1: 0
Output 2: 1

Example Explanation:
Explanation 1:
 Tree is 1--> 2--> 3 and hence 1 is not reachable from 2.
Explanation 2:
 Tree is 1--> 2--> 3 and hence 2 is reachable from 1.


 */

import java.util.ArrayList;
import java.util.Arrays;

public class FirstDepthFirstSearch {

    public static void main(String[] args) {
        ArrayList<Integer> A = new ArrayList<>(Arrays.asList(1, 1, 2));
        int B = 2;
        int C = 1;

        // C = source; B = target;
        int res = find(A, C, B);
        System.out.println(res);
        // Time O(Number of Nodes (V) + Number of Edges(E));
        // Space O(Number of Nodes (V) + Number of Edges(E));
    }

    public static int find(ArrayList<Integer> A, int B, int C) {
        if (B == C) return 1;
        boolean[] visited = new boolean[A.size() + 1];
        return dfs(adjacencyList(A), B, C, visited);
    }

    public static ArrayList<ArrayList<Integer>> adjacencyList(ArrayList<Integer> A) {
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= A.size(); i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 1; i < A.size(); i++) {
            int sourceNode = A.get(i);
            int destinationNode = i + 1;
            graph.get(sourceNode).add(destinationNode);
        }

        return graph;
    }

    public static int dfs(ArrayList<ArrayList<Integer>> graph, int current, int target, boolean[] visited) {
        visited[current] = true;
        if (current == target) return 1;

        for (int neighbour : graph.get(current)) {
            if (!visited[neighbour]) {
                if (dfs(graph, neighbour, target, visited) == 1) {
                    return 1;
                }
            }
        }

        return 0;
    }

}
