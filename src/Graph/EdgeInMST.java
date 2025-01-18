package Graph;
/*
Problem Description
Given a undirected weighted graph with A nodes labelled from 1 to A with M edges given
in a form of 2D-matrix B of size M * 3 where B[i][0] and B[i][1] denotes the
two nodes connected by an edge of weight B[i][2].
For each edge check whether it belongs to any of the possible minimum
spanning tree or not , return 1 if it belongs else return 0.
Return a one-dimensional binary array of size M denoting answer for each edge.
NOTE:
    The graph may be disconnected in that case consider mst for each component.
    No self-loops and no multiple edges present.
    Answers in output array must be in order with the input array B output[i] must denote the answer of edge B[i][0] to B[i][1].

Problem Constraints
1 <= A, M <= 3*10^5
1 <= B[i][0], B[i][1] <= A
1 <= B[i][1] <= 10^3

Input Format:
The first argument given is an integer A representing the number of nodes in the graph.
The second argument given is an matrix B of size M x 3
which represents the M edges such that there is a edge
between node B[i][0] and node B[i][1] with weight B[i][2].

Output Format
Return a one-dimensional binary array of size M denoting answer for each edge.

Example Input:
Input 1:
 A = 3
 B = [ [1, 2, 2]
       [1, 3, 2]
       [2, 3, 3]
     ]

Example Output:
Output 1: [1, 1, 0]

Example Explanation:
Explanation 1:
 Edge (1, 2) with weight 2 is included in the MST           1
                                                          /   \
                                                         2     3
 Edge (1, 3) with weight 2 is included in the same MST mentioned above.
 Edge (2,3) with weight 3 cannot be included in any of the mst possible.
 So we will return [1, 1, 0]

 */

import java.util.*;

public class EdgeInMST {
    public static void main(String[] args) {
        int A = 3;
        ArrayList<ArrayList<Integer>> B = new ArrayList<>(Arrays.asList(
                new ArrayList<>(Arrays.asList(1, 2, 2)),
                new ArrayList<>(Arrays.asList(1, 3, 2)),
                new ArrayList<>(Arrays.asList(2, 3, 3))
        ));

        ArrayList<Integer> res = edgeInMST(A, getList(B));
        System.out.println(res);
        // Time,
        //      Sorting Edges, O(E log E), ... E is the number of Edges;
        //      DSU Operations, O(E∝(A)), where ∝, is inverse Ackermann function;
        //      Total time = O(E log E + E∝(A)) ;
        //      O(E Log E);

        // Space O(A + E);
    }

    public static class Edge {
        int u, v, weight, index;

        Edge(int u, int v, int weight, int index) {
            this.u = u;
            this.v = v;
            this.weight = weight;
            this.index = index;
        }
    }

    public static class DisjointSetUnion {
        int[] parent, rank;

        public DisjointSetUnion(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }

        int find(int x) {
            if (parent[x] != x) parent[x] = find(parent[x]);
            return parent[x];
        }

        boolean union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) return false;

            if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
            return true;
        }
    }


    public static ArrayList<Edge> getList(ArrayList<ArrayList<Integer>> B) {
        ArrayList<Edge> list = new ArrayList<>();

        for (int i = 0; i < B.size(); i++) {
            int u = B.get(i).get(0) - 1;
            int v = B.get(i).get(1) - 1;
            int weight = B.get(i).get(2);
            list.add(new Edge(u, v, weight, i));
        }

        list.sort((a, b) -> Integer.compare(a.weight, b.weight));
        return list;
    }

    public static ArrayList<Integer> edgeInMST(int A, ArrayList<Edge> list) {
        DisjointSetUnion dsu = new DisjointSetUnion(A);
        ArrayList<Integer> res = new ArrayList<>(Collections.nCopies(list.size(), 0));

        int i = 0;

        while (i < list.size()) {
            int weight = list.get(i).weight;

            ArrayList<Edge> sameWeightEdges = new ArrayList<>();

            while (i < list.size() && list.get(i).weight == weight) {
                sameWeightEdges.add(list.get(i));
                i++;
            }

            for (Edge edge : sameWeightEdges) {
                if (dsu.find(edge.u) != dsu.find(edge.v)) {
                    res.set(edge.index, 1);
                }
            }

            for (Edge edge : sameWeightEdges) {
                dsu.union(edge.u, edge.v);
            }
        }

        return res;
    }

}
