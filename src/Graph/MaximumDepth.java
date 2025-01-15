package Graph;
/*
Problem Description

Given a Tree of A nodes having A-1 edges. Each node is numbered from 1 to A where 1 is the root of the tree.

You are given Q queries. In each query, you will be given two integers L and X. Find the value of such node which lies at level L mod (MaxDepth + 1) and has value greater than or equal to X.

Answer to the query is the smallest possible value or -1, if all the values at the required level are smaller than X.

NOTE:

Level and Depth of the root is considered as 0.
It is guaranteed that each edge will be connecting exactly two different nodes of the tree.
Please read the input format for more clarification.



Problem Constraints

2 <= A, Q(size of array E and F) <= 105

1 <= B[i], C[i] <= A

1 <= D[i], E[i], F[i] <= 106



Input Format:
The first argument is an integer A denoting the number of nodes.
The second and third arguments are the integer arrays B and C where
for each i (0 <= i < A-1), B[i] and C[i] are the nodes connected by an edge.
The fourth argument is an integer array D, where D[i] denotes the value of the (i+1)th node
The fifth and sixth arguments are the integer arrays E and F where for each i (0 <= i < Q),
E[i] denotes L and F[i] denotes X for ith query.

Output Format:
Return an array of integers where the ith element denotes the answer to ith query.

Example Input

Input 1:
 A = 5
 B = [1, 4, 3, 1]
 C = [5, 2, 4, 4]
 D = [7, 38, 27, 37, 1]
 E = [1, 1, 2]
 F = [32, 18, 26]

Input 2:
 A = 3
 B = [1, 2]
 C = [3, 1]
 D = [7, 15, 27]
 E = [1, 10, 1]
 F = [29, 6, 26]

Example Output:
Output 1: [37, 37, 27]
Output 2: [-1, 7, 27]

Example Explanation:

Explanation 1:

      1[7]
     /    \
   5[1]  4[37]
        /    \
       2[38]  3[27]

 Query 1:
    L = 1, X = 32
    Nodes for level 1 are 5, 4
    Value of Node 5 = 1 < 32
    Value of Node 4 = 37 >= 32
    Ans = 37

Explanation 2:

      1[7]
     /    \
   2[15]  3[27]

 Query 1:
    L = 1, X = 6
    Nodes for level 1 are 2, 3 having value 15 and 27 respectively.
    Answer = -1 (Since no node is greater or equal to 29).

 Query 1:
    L = 10 % 2 = 0, X = 6
    Nodes for level 0 is 1 having value 7.
    Answer = 7.

 */

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MaximumDepth {

    public static void main(String[] args) {
        int A = 5;
        ArrayList<Integer> B = new ArrayList<>(Arrays.asList(1, 4, 3, 1));
        ArrayList<Integer> C = new ArrayList<>(Arrays.asList(5, 2, 4, 4));
        ArrayList<Integer> D = new ArrayList<>(Arrays.asList(7, 38, 27, 37, 1));
        ArrayList<Integer> E = new ArrayList<>(Arrays.asList(1, 1, 2));
        ArrayList<Integer> F = new ArrayList<>(Arrays.asList(32, 18, 26));

        ArrayList<Integer> res = find(A, B, C, D, E, F);
        System.out.println(res);
        //  Time O(A + Q log A);
        // Space O(A + Q);
    }

    public static ArrayList<Integer> find(int A, ArrayList<Integer> B, ArrayList<Integer> C, ArrayList<Integer> D, ArrayList<Integer> E, ArrayList<Integer> F) {
        ArrayList<ArrayList<Integer>> graph = adjacencyList(A, B, C);

        ArrayList<ArrayList<Integer>> levels = groupNodeByLevels(graph, D);

        for (ArrayList<Integer> level : levels) {
            Collections.sort(level);
        }

        return processQueries(levels, E, F);
    }

    public static ArrayList<ArrayList<Integer>> adjacencyList(int A, ArrayList<Integer> B, ArrayList<Integer> C) {
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

        for (int i = 0; i <= A; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < B.size(); i++) {
            int nodeB = B.get(i);
            int nodeC = C.get(i);
            graph.get(nodeB).add(nodeC);
            graph.get(nodeC).add(nodeB);
        }

        return graph;
    }

    public static ArrayList<ArrayList<Integer>> groupNodeByLevels(ArrayList<ArrayList<Integer>> graph, ArrayList<Integer> values) {
        ArrayList<ArrayList<Integer>> levels = new ArrayList<>();
        int maxDepth = dfs(graph, 1, 0, values, new boolean[values.size() + 1], levels);

        while (levels.size() > maxDepth + 1) {
            levels.remove(levels.size() - 1);
        }
        return levels;
    }

    public static int dfs(ArrayList<ArrayList<Integer>> graph, int source, int level, ArrayList<Integer> values, boolean[] visited, ArrayList<ArrayList<Integer>> levels) {
        visited[source] = true;
        if (levels.size() <= level) {
            levels.add(new ArrayList<>());
        }
        levels.get(level).add(values.get(source - 1));

        int currentDepth = level;
        for (int neighbor : graph.get(source)) {
            if (!visited[neighbor]) {
                currentDepth = Math.max(currentDepth, dfs(graph, neighbor, level + 1, values, visited, levels));
            }
        }
        return currentDepth;
    }


    public static ArrayList<Integer> processQueries(ArrayList<ArrayList<Integer>> levels, ArrayList<Integer> E, ArrayList<Integer> F) {
        ArrayList<Integer> res = new ArrayList<>();
        int maxDepth = levels.size() - 1;

        for (int i = 0; i < E.size(); i++) {
            int L = E.get(i);
            int X = F.get(i);

            int effectiveLevel = L % (maxDepth + 1);

            if (effectiveLevel >= levels.size() || levels.get(effectiveLevel).isEmpty()) {
                res.add(-1);
                continue;
            }

            ArrayList<Integer> levelNodes = levels.get(effectiveLevel);
            int index = binarySearch(levelNodes, X);

            if (index == -1) res.add(-1);
            else res.add(levelNodes.get(index));
        }

        return res;
    }

    public static int binarySearch(ArrayList<Integer> list, int target) {
        int left = 0;
        int right = list.size() - 1;
        int res = -1;

        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (list.get(mid) >= target) {
                res = mid;
                right = mid - 1;
            } else left = mid + 1;
        }

        return res;
    }
}
