package Graph;
/*
Problem Description:
Given a weighted undirected graph having A nodes and M weighted edges, and a source node C.
You have to find an integer array D of size A such that:
    D[i]: Shortest distance from the C node to node i.
    If node i is not reachable from C then -1.

Note:
    There are no self-loops in the graph.
    There are no multiple edges between two pairs of vertices.
    The graph may or may not be connected.
    Nodes are numbered from 0 to A-1.
    Your solution will run on multiple test cases.
    If you are using global variables, make sure to clear them.

Problem Constraints
1 <= A <= 1e5
0 <= B[i][0],B[i][1] < A
0 <= B[i][2] <= 1e3
0 <= C < A

Input Format:
The first argument is an integer A, representing the number of nodes in the graph.
The second argument is a matrix B of size M x 3, where each row represents
an edge in the graph. The three columns of each row denote the source node B[i][0],
the destination node B[i][1], and the weight of the edge B[i][2].
The third argument is an integer C, representing the source node for
which the shortest distance to all other nodes needs to be found.

Output Format:
Return the integer array D.

Example Input:
Input 1:
A = 6
B = [   [0, 4, 9]
        [3, 4, 6]
        [1, 2, 1]
        [2, 5, 1]
        [2, 4, 5]
        [0, 3, 7]
        [0, 1, 1]
        [4, 5, 7]
        [0, 5, 1] ]
C = 4

Input 2:
A = 5
B = [   [0, 3, 4]
        [2, 3, 3]
        [0, 1, 9]
        [3, 4, 10]
        [1, 3, 8]  ]
C = 4

Example Output:
Output 1:
D = [7, 6, 5, 6, 0, 6]
Output 2:
D = [14, 18, 13, 10, 0]

Example Explanation:
Explanation 1:
 All Paths can be considered from the node C to get shortest path
Explanation 2:
 All Paths can be considered from the node C to get shortest path

 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class Dijkstra {
    public static void main(String[] args) {
        int A = 6;
        ArrayList<ArrayList<Integer>> B = new ArrayList<>(Arrays.asList(
                new ArrayList<>(Arrays.asList(0, 4, 9)),
                new ArrayList<>(Arrays.asList(3, 4, 6)),
                new ArrayList<>(Arrays.asList(1, 2, 1)),
                new ArrayList<>(Arrays.asList(2, 5, 1)),
                new ArrayList<>(Arrays.asList(2, 4, 5)),
                new ArrayList<>(Arrays.asList(0, 3, 7)),
                new ArrayList<>(Arrays.asList(0, 1, 1)),
                new ArrayList<>(Arrays.asList(4, 5, 7)),
                new ArrayList<>(Arrays.asList(0, 5, 1))
        ));
        int C = 4;

        ArrayList<Integer> res = findShortestDistance(adjacencyList(B, A), A, C);
        System.out.println(res);
        // Time O(E logE);
        // Space O(E + V);
    }

    public static class Edges {
        public int node, weight;

        public Edges(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }

    public static ArrayList<ArrayList<Edges>> adjacencyList(ArrayList<ArrayList<Integer>> B, int A) {
        ArrayList<ArrayList<Edges>> graph = new ArrayList<>();
        for (int i = 0; i < A; i++) {
            graph.add(new ArrayList<>());
        }

        for (ArrayList<Integer> row : B) {
            int u = row.get(0);
            int v = row.get(1);
            int weight = row.get(2);

            graph.get(u).add(new Edges(v, weight));
            graph.get(v).add(new Edges(u, weight));
        }

        return graph;
    }

    public static ArrayList<Integer> findShortestDistance(ArrayList<ArrayList<Edges>> graph, int n, int source) {
        ArrayList<Integer> res = new ArrayList<>(Collections.nCopies(n, Integer.MAX_VALUE));
        res.set(source, 0);

        PriorityQueue<Edges> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a.weight, b.weight));
        minHeap.offer(new Edges(source, 0));

        while (!minHeap.isEmpty()) {
            Edges edge = minHeap.poll();
            int node = edge.node;
            int weight = edge.weight;

            if (res.get(node) < weight) {
                continue;
            }

            res.set(node, weight);

            for (Edges neighbouringEdge : graph.get(node)) {
                int totalDistance = neighbouringEdge.weight + weight;
                if (totalDistance < res.get(neighbouringEdge.node)) {
                    minHeap.offer(new Edges(neighbouringEdge.node, totalDistance));
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (res.get(i) == Integer.MAX_VALUE) {
                res.set(i, -1);
            }
        }

        return res;
    }
}
