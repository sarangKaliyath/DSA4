package Graph;
/*
Problem Description
Given character matrix A of dimensions N×M consisting of O's and X's,
where O = white, X = black.
Return the number of black shapes.
A black shape consists of one or more adjacent X's (diagonals not included)

Problem Constraints:
1 <= N, M <= 1000
A[i][j] = 'X' or 'O'

Input Format:
The First and only argument is character matrix A.

Output Format:
Return a single integer denoting number of black shapes.

Example Input:
Input 1: A = [ [X, X, X], [X, X, X], [X, X, X] ]
Input 2: A = [ [X, O], [O, X] ]

Example Output:
Output 1: 1
Output 2: 2

Example Explanation:
Explanation 1: All X's belong to single shapes
Explanation 2: Both X's belong to different shapes

 */

import java.util.ArrayList;
import java.util.Arrays;

public class BlackShapes {
    public static void main(String[] args) {
        ArrayList<String> A = new ArrayList<>(Arrays.asList(
                "XO",
                "OX"
        ));

        int res = getBlackShapeCount(adjacencyList(A, A.size()), A.size(), A.get(0).length());
        System.out.println(res);
        // Time O(N * M);
        // Space O(N * M);
    }

    private final static int[] row = {-1, 0, 1, 0};
    public final static int[] col = {0, 1, 0, -1};

    public static int getBlackShapeCount(ArrayList<ArrayList<Character>> graph, int n, int m) {
        if (graph == null || graph.isEmpty()) return 0;

        int count = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (graph.get(i).get(j) == 'X') {
                    count++;
                    dfs(graph, i, j);
                }
            }
        }

        return count;
    }

    public static void dfs(ArrayList<ArrayList<Character>> graph, int i, int j) {
        if (i < 0 || i >= graph.size() || j < 0 || j >= graph.get(0).size() || graph.get(i).get(j) != 'X') {
            return;
        }

        graph.get(i).set(j, 'O');

        for (int direction = 0; direction <= 3; direction++) {
            int xAxis = i + row[direction];
            int yAxis = j + col[direction];
            dfs(graph, xAxis, yAxis);
        }
    }

    public static ArrayList<ArrayList<Character>> adjacencyList(ArrayList<String> A, int n) {
        ArrayList<ArrayList<Character>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < A.get(i).length(); j++) {
                graph.get(i).add(A.get(i).charAt(j));
            }
        }

        return graph;
    }
}
