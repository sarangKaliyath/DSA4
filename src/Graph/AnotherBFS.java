package Graph;
/*
Given a weighted undirected graph having A nodes,
a source node C and destination node D.
Find the shortest distance from C to D
and if it is impossible to reach node D from C then return -1.
You are expected to do it in Time Complexity of O(A + M).

Note:
There are no self-loops in the graph.
No multiple edges between two pair of vertices.
The graph may or may not be connected.
Nodes are Numbered from 0 to A-1.
Your solution will run on multiple testcases.
If you are using global variables make sure to clear them.

Problem Constraints
1 <= A <= 10^5
0 <= B[i][0], B[i][1] < A
1 <= B[i][2] <= 2
0 <= C < A
0 <= D < A

Input Format
The first argument given is an integer A,
representing the number of nodes.
The second argument given is the matrix B,
where B[i][0] and B[i][1] are connected through an edge of weight B[i][2].
The third argument given is an integer C, representing the source node.
The fourth argument is an integer D, representing the destination node.
Note: B[i][2] will be either 1 or 2.

Output Format:
Return the shortest distance from C to D.
If it is impossible to reach node D from C then return -1.

Example Input:
Input 1:
A = 6
B = [   [2, 5, 1]
        [1, 3, 1]
        [0, 5, 2]
        [0, 2, 2]
        [1, 4, 1]
        [0, 1, 1] ]
C = 3
D = 2

Input 2:
A = 2
B = [   [0, 1, 1]
    ]
C = 0
D = 1

Example Output:
Output 1: 4
Output 2: 1

Example Explanation:
Explanation 1:
The path to be followed will be:
    3 -> 1 (Edge weight : 1)
    1 -> 0 (Edge weight : 1)
    0 -> 2 (Edge weight : 2)
Total length of path = 1 + 1 + 2 = 4.

Explanation 2:
 Path will be 0-> 1.

 */

import javax.accessibility.AccessibleRelation;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;

public class AnotherBFS {
    public static void main(String[] args) {
        int A = 6;
        ArrayList<ArrayList<Integer>> B = new ArrayList<>(Arrays.asList(
                new ArrayList<>(Arrays.asList(2, 5, 1)),
                new ArrayList<>(Arrays.asList(1, 3, 1)),
                new ArrayList<>(Arrays.asList(0, 5, 2)),
                new ArrayList<>(Arrays.asList(0, 2, 2)),
                new ArrayList<>(Arrays.asList(1, 4, 1)),
                new ArrayList<>(Arrays.asList(0, 1, 1))
        ));
        int C = 3;
        int D = 2;

        int res = breadthFirstSearch(A, processEdges(A, B), C, D);
        System.out.println(res);
        // Time O(A + M);
        // Space O(A + M);
    }

    public static ArrayList<ArrayList<Integer>> processEdges(int A, ArrayList<ArrayList<Integer>> B) {
        int dummyNode = A;
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

        int weightTwoEdgesCount = 0;

        for (ArrayList<Integer> row : B) {
            int weight = row.get(2);
            if (weight == 2) weightTwoEdgesCount++;
        }

        for (int i = 0; i < A + weightTwoEdgesCount; i++) {
            graph.add(new ArrayList<>());
        }

        for (ArrayList<Integer> row : B) {
            int u = row.get(0);
            int v = row.get(1);
            int weight = row.get(2);

            if (weight == 1) {
                graph.get(u).add(v);
                graph.get(v).add(u);
            } else {
                graph.get(u).add(dummyNode);
                graph.get(dummyNode).add(u);
                graph.get(v).add(dummyNode);
                graph.get(dummyNode).add(v);
                dummyNode++;
            }
        }

        return graph;
    }

    public static int breadthFirstSearch(int A, ArrayList<ArrayList<Integer>> graph, int source, int destination) {
        int n = graph.size();
        boolean[] visited = new boolean[n];
        int[] distance = new int[n];
        Arrays.fill(distance, -1);

        Deque<Integer> q = new ArrayDeque<>();
        q.offerLast(source);
        visited[source] = true;
        distance[source] = 0;

        while (!q.isEmpty()) {
            int node = q.pollFirst();

            if (node == destination) return distance[node];

            for (int neighbours : graph.get(node)) {
                if (!visited[neighbours]) {
                    visited[neighbours] = true;
                    distance[neighbours] = distance[node] + 1;
                    q.offerLast(neighbours);
                }
            }
        }

        return -1;
    }
}
