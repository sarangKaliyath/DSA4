package Graph;
/*
Problem Description:
Given a 2-D board A of size N x M containing 'X' and 'O', capture all regions surrounded by 'X'.
A region is captured by flipping all 'O's into 'X's in that surrounded region.

Problem Constraints
1 <= N, M <= 1000

Input Format:
First and only argument is a N x M character matrix A.

Output Format:
Return nothing. Make changes to the the input only as matrix is passed by reference.

Example Input:
Input 1:
 A = [
       [X, X, X, X],
       [X, O, O, X],
       [X, X, O, X],
       [X, O, X, X]
     ]

Input 2:
 A = [
       [X, O, O],
       [X, O, X],
       [O, O, O]
     ]

Example Output:
Output 1:
 After running your function, the board should be:
 A = [
       [X, X, X, X],
       [X, X, X, X],
       [X, X, X, X],
       [X, O, X, X]
     ]

Output 2:
 After running your function, the board should be:
 A = [
       [X, O, O],
       [X, O, X],
       [O, O, O]
     ]

Example Explanation:
Explanation 1:
 O in (4,2) is not surrounded by X from below.
Explanation 2:
 No O's are surrounded.
 */

import java.util.ArrayList;
import java.util.Arrays;

public class CaptureRegionsOnBoard {
    public static void main(String[] args) {
        ArrayList<ArrayList<Character>> A = new ArrayList<>(Arrays.asList(
                new ArrayList<>(Arrays.asList('X', 'X', 'X', 'X')),
                new ArrayList<>(Arrays.asList('X', 'O', 'O', 'X')),
                new ArrayList<>(Arrays.asList('X', 'X', 'O', 'X')),
                new ArrayList<>(Arrays.asList('X', 'O', 'X', 'X'))
        ));

        find(A, A.size(), A.get(0).size());
        System.out.println(A);
        // Time O(N * M);
        // Space O(N * M);
    }

    private static int[] row = {-1, 0, 1, 0};
    private static int[] col = {0, 1, 0, -1};

    public static void find(ArrayList<ArrayList<Character>> graph, int n, int m) {
        captureBoundaryO(graph, n, m);
        flipValues(graph, n, m);
    }

    public static void captureBoundaryO(ArrayList<ArrayList<Character>> graph, int n, int m) {
        for (int i = 0; i < n; i++) {
            if (graph.get(i).get(0) == 'O') dfs(graph, i, 0);
            if (graph.get(i).get(m - 1) == 'O') dfs(graph, i, m - 1);
        }

        for (int j = 0; j < m; j++) {
            if (graph.get(0).get(j) == 'O') dfs(graph, 0, j);
            if (graph.get(n - 1).get(j) == 'O') dfs(graph, n - 1, j);
        }

    }

    public static void dfs(ArrayList<ArrayList<Character>> graph, int i, int j) {
        if (i < 0 || i >= graph.size() || j < 0 || j >= graph.get(0).size() || graph.get(i).get(j) != 'O') {
            return;
        }

        graph.get(i).set(j, 'B');
        for (int direction = 0; direction <= 3; direction++) {
            int xAxis = i + row[direction];
            int yAxis = j + col[direction];

            dfs(graph, xAxis, yAxis);
        }
    }

    public static void flipValues(ArrayList<ArrayList<Character>> graph, int n, int m) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (graph.get(i).get(j) == 'B') graph.get(i).set(j, 'O');
                else if (graph.get(i).get(j) == 'O') graph.get(i).set(j, 'X');
            }
        }
    }
}
