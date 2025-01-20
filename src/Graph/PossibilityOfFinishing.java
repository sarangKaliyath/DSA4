package Graph;

import java.util.ArrayList;
import java.util.Arrays;

/*
Problem Description
There are a total of A courses you have to take,
labeled from 1 to A.
Some courses may have prerequisites,
for example to take course 2 you have to first take course 1,
which is expressed as a pair: [1,2].
So you are given two integer array B and C of same size where
for each i (B[i], C[i]) denotes a pair.
Given the total number of courses and a list of prerequisite pairs,
is it possible for you to finish all courses?
Return 1 if it is possible to finish all the courses,
or 0 if it is not possible to finish all the courses.

Problem Constraints
1 <= A <= 6*10^4
1 <= length(B) = length(C) <= 10^5
1 <= B[i], C[i] <= A

Input Format:
The first argument of input contains an integer A,
representing the number of courses.
The second argument of input contains an integer array, B.
The third argument of input contains an integer array, C.

Output Format:
Return 1 if it is possible to finish all the courses,
or 0 if it is not possible to finish all the courses.

Example Input:
Input 1:
 A = 3
 B = [1, 2]
 C = [2, 3]

Input 2:
 A = 2
 B = [1, 2]
 C = [2, 1]

Example Output:
Output 1: 1
Output 2: 0

Example Explanation:
Explanation 1:
 It is possible to complete the courses in the following order:
    1 -> 2 -> 3

Explanation 2:
 It is not possible to complete all the courses.

 */
public class PossibilityOfFinishing {
    public static void main(String[] args) {
        int A = 3;
        ArrayList<Integer> B = new ArrayList<>(Arrays.asList(1, 2));
        ArrayList<Integer> C = new ArrayList<>(Arrays.asList(2, 3));

        int res = detectCycle(adjacencyList(A, B, C), A);
        System.out.println(res);
        // Time O(Number of Nodes (V) + Number of Edges(E));
        // Space O(Number of Nodes (V) + Number of Edges(E));
    }

    public static ArrayList<ArrayList<Integer>> adjacencyList(int A, ArrayList<Integer> B, ArrayList<Integer> C) {
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

        for (int i = 0; i <= A; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < B.size(); i++) {
            graph.get(B.get(i)).add(C.get(i));
        }

        return graph;
    }

    public static int detectCycle(ArrayList<ArrayList<Integer>> graph, int A) {
        boolean[] visited = new boolean[A + 1];
        boolean[] path = new boolean[A + 1];

        for (int i = 0; i <= A; i++) {
            if (!visited[i] && dfs(graph, i, visited, path)) {
                return 0;
            }
        }

        return 1;
    }

    public static boolean dfs(ArrayList<ArrayList<Integer>> graph, int current, boolean[] visited, boolean[] path) {
        visited[current] = path[current] = true;

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
