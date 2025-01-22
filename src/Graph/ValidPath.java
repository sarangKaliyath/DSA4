package Graph;
/*
Problem Description
There is a rectangle with left bottom as (0, 0) and right up as (x, y).
There are N circles such that their centers are inside the rectangle.
Radius of each circle is R. Now we need to find out if it is possible
that we can move from (0, 0) to (x, y) without touching any circle.
Note : We can move from any cell to any of its 8 adjacent
neighbours, and we cannot move outside the boundary of the
rectangle at any point of time.

Problem Constraints
0 <= x , y, R <= 100
1 <= N <= 1000
Center of each circle would lie within the grid

Input Format:
1st argument given is an Integer x , denoted by A in input.
2nd argument given is an Integer y, denoted by B in input.
3rd argument given is an Integer N, number of circles, denoted by C in input.
4th argument given is an Integer R, radius of each circle, denoted by D in input.
5th argument given is an Array A of size N, denoted by E in input, where A[i] = x cordinate of ith circle
6th argument given is an Array B of size N, denoted by F in input, where B[i] = y cordinate of ith circle

Output Format:
Return YES or NO depending on weather it is possible to reach cell (x,y) or not starting from (0,0).

Example Input:
Input 1:
 x = 2
 y = 3
 N = 1
 R = 1
 A = [2]
 B = [3]

Input 2:
 x = 3
 y = 3
 N = 1
 R = 1
 A = [0]
 B = [3]

Example Output:
Output 1: NO
Output 2: YES

Example Explanation:
Explanation 1:
 There is NO valid path in this case

Explanation 2:
 There is many valid paths in this case.
 One of the path is (0, 0) -> (1, 0) -> (2, 0) -> (3, 0) -> (3, 1) -> (3, 2) -> (3, 3).

 */

import java.util.ArrayList;
import java.util.Arrays;

public class ValidPath {
    public static void main(String[] args) {
        int A = 3;
        int B = 3;
        int C = 1;
        int D = 1;
        ArrayList<Integer> E = new ArrayList<>(Arrays.asList(0));
        ArrayList<Integer> F = new ArrayList<>(Arrays.asList(3));

        String res = isPossible(A, B, C, D, E, F);
        System.out.println(res);
        // Time O(A * B * C);
        // Space O(A * B);
    }

    public static String isPossible(int A, int B, int C, int D, ArrayList<Integer> E, ArrayList<Integer> F) {
        int[][] graph = getGraph(A, B, E, F, D);
        if (graph[0][0] == 1 || graph[A][B] == 1) return "NO";
        return dfs(graph, 0, 0, A, B, new boolean[A + 1][B + 1]) ? "YES" : "NO";
    }


    public static int[][] getGraph(int n, int m, ArrayList<Integer> E, ArrayList<Integer> F, int radius) {
        int[][] graph = new int[n + 1][m + 1];
        for (int[] row : graph) Arrays.fill(row, 0);

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k < E.size(); k++) {

                    int xPos = E.get(k);
                    int yPos = F.get(k);

                    boolean isInsideCircle = Math.pow(xPos - i, 2) + Math.pow(yPos - j, 2) <= (radius * radius);

                    if (isInsideCircle) {
                        graph[i][j] = 1;
                        break;
                    }

                }
            }
        }

        return graph;
    }

    private final static int[] row = {-1, -1, -1, 0, 1, 1, 1, 0};
    private final static int[] col = {-1, 0, 1, 1, 1, 0, -1, -1};

    public static boolean dfs(int[][] graph, int sr, int sc, int dr, int dc, boolean[][] visited) {
        if (sr == dr && sc == dc) return true;

        if (sr < 0 || sr >= graph.length || sc < 0 || sc >= graph[0].length || graph[sr][sc] != 0 || visited[sr][sc]) {
            return false;
        }

        visited[sr][sc] = true;

        for (int direction = 0; direction <= 7; direction++) {
            int xAxis = sr + row[direction];
            int yAxis = sc + col[direction];
            if (dfs(graph, xAxis, yAxis, dr, dc, visited)) {
                return true;
            }
        }

        return false;
    }
}
