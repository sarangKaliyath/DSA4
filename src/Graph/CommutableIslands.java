package Graph;
/*
Problem Description:
There are A islands and there are M bridges connecting them. Each bridge has some cost attached to it.
We need to find bridges with minimal cost such that all islands are connected.
It is guaranteed that input data will contain at least one possible scenario
in which all islands are connected with each other.

Problem Constraints
1 <= A, M <= 6*10^4
1 <= B[i][0], B[i][1] <= A
1 <= B[i][2] <= 10^3

Input Format
The first argument contains an integer, A, representing the number of islands.
The second argument contains an 2-d integer matrix, B,
of size M x 3 where Island B[i][0] and B[i][1] are connected using a bridge of cost B[i][2].

Output Format:
Return an integer representing the minimal cost required.

Example Input:
Input 1:
 A = 4
 B = [  [1, 2, 1]
        [2, 3, 4]
        [1, 4, 3]
        [4, 3, 2]
        [1, 3, 10]  ]

Input 2:
 A = 4
 B = [  [1, 2, 1]
        [2, 3, 2]
        [3, 4, 4]
        [1, 4, 3]   ]

Example Output:
Output 1: 6
Output 2: 6

Example Explanation:
Explanation 1:
 We can choose bridges (1, 2, 1), (1, 4, 3) and (4, 3, 2), where the total cost incurred will be (1 + 3 + 2) = 6.

Explanation 2:
 We can choose bridges (1, 2, 1), (2, 3, 2) and (1, 4, 3), where the total cost incurred will be (1 + 2 + 3) = 6.

 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class CommutableIslands {
    public static class Edge {
        int node;
        int cost;

        public Edge(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }
    }

    public static void main(String[] args) {
        int A = 4;
        ArrayList<ArrayList<Integer>> B = new ArrayList<>(Arrays.asList(
                new ArrayList<>(Arrays.asList(1, 2, 1)),
                new ArrayList<>(Arrays.asList(2, 3, 4)),
                new ArrayList<>(Arrays.asList(1, 4, 3)),
                new ArrayList<>(Arrays.asList(4, 3, 2)),
                new ArrayList<>(Arrays.asList(1, 3, 10))
        ));

        int res = primsAlgo(A, adjacencyList(A, B));
        System.out.println(res);
        // Time O(E log E); ... no. of edges.
        // Space O(V + E); ... adjacency list and visited array space.
    }

    public static ArrayList<ArrayList<Edge>> adjacencyList(int A, ArrayList<ArrayList<Integer>> B) {
        ArrayList<ArrayList<Edge>> graph = new ArrayList<>();
        for (int i = 0; i <= A; i++) {
            graph.add(new ArrayList<>());
        }

        for (ArrayList<Integer> edges : B) {
            int u = edges.get(0);
            int v = edges.get(1);
            int cost = edges.get(2);

            graph.get(u).add(new Edge(v, cost));
            graph.get(v).add(new Edge(u, cost));
        }

        return graph;
    }

    public static int primsAlgo(int A, ArrayList<ArrayList<Edge>> graph) {
        boolean[] visited = new boolean[A + 1];
        PriorityQueue<Edge> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a.cost, b.cost));
        minHeap.add(new Edge(1, 0));
        int totalCost = 0;

        while (!minHeap.isEmpty()) {
            Edge edge = minHeap.poll();
            if (visited[edge.node]) {
                continue;
            }

            visited[edge.node] = true;

            totalCost += edge.cost;

            for (Edge crrEdge : graph.get(edge.node)) {
                if (!visited[crrEdge.node]) {
                    minHeap.add(crrEdge);
                }
            }
        }

        return totalCost;
    }
}
