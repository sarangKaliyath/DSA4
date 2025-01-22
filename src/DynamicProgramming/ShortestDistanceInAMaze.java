package DynamicProgramming;

import java.util.*;

/*
Problem Description:
Given a matrix of integers A of size N x M describing a maze.
The maze consists of empty locations and walls.
1 represents a wall in a matrix and 0 represents an
empty location in a wall.
There is a ball trapped in a maze. The ball can go through empty
spaces by rolling up, down, left or right, but it won't stop
rolling until hitting a wall (maze boundary is also considered as a wall).
When the ball stops, it could choose the next direction.
Given two array of integers of size B and C of size 2 denoting
the starting and destination position of the ball.
Find the shortest distance for the ball to stop at the destination.
The distance is defined by the number of empty spaces traveled
by the ball from the starting position (excluded) to the destination (included).
If the ball cannot stop at the destination, return -1.

Problem Constraints
2 <= N, M <= 100
0 <= A[i] <= 1
0 <= B[i][0], C[i][0] < N
0 <= B[i][1], C[i][1] < M

Input Format:
The first argument given is the integer matrix A.
The second argument given is an array of integer B.
The third argument if an array of integer C.

Output Format:
Return a single integer, the minimum distance required to reach destination

Example Input:
Input 1:
A = [ [0, 0],
      [0, 0] ]
B = [0, 0]
C = [0, 1]

Input 2:
A = [ [0, 1],
      [1, 0] ]
B = [0, 0]
C = [1, 1]

Example Output:
Output 1: 1
Output 2: -1

Example Explanation:
Explanation 1:
 Go directly from start to destination in distance 1.

Explanation 2:
 It is impossible to reach the destination from (0, 0) to (1, 1) as there are walls at (1, 0) and (0, 1)
 */
public class ShortestDistanceInAMaze {
    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> A = new ArrayList<>(Arrays.asList(
                new ArrayList<>(Arrays.asList(0, 0)),
                new ArrayList<>(Arrays.asList(0, 0))
        ));
        ArrayList<Integer> B = new ArrayList<>(Arrays.asList(0, 0));
        ArrayList<Integer> C = new ArrayList<>(Arrays.asList(0, 1));

        int res = getShortestDistance(A, B.get(0), B.get(1), C.get(0), C.get(1));
        System.out.println(res);
        // Time O(N * M * (N + M));
        // Space O(N * M);
    }

    public static class Element {
        public int row, col, distance;

        public Element(int row, int col, int distance) {
            this.row = row;
            this.col = col;
            this.distance = distance;
        }
    }


    private final static int[] row = {-1, 0, 1, 0};
    private final static int[] col = {0, 1, 0, -1};

    public static int getShortestDistance(ArrayList<ArrayList<Integer>> graph, int sr, int sc, int dr, int dc) {
        int n = graph.size();
        int m = graph.get(0).size();

        Deque<Element> q = new ArrayDeque<>();
        q.offerLast(new Element(sr, sc, 0));

        int[][] dp = new int[n][m];
        for (int[] row : dp) Arrays.fill(row, Integer.MAX_VALUE);
        dp[sr][sc] = 0;

        while (!q.isEmpty()) {
            Element node = q.pollFirst();

            if (node.row == dr && node.col == dc) {
                return node.distance;
            }

            for (int directions = 0; directions <= 3; directions++) {
                int posOnXAxis = node.row + row[directions];
                int posOnYAxis = node.col + col[directions];
                int currentDistance = 0;

                if (isValidMove(graph, posOnXAxis, posOnYAxis)) {
                    while (isValidMove(graph, posOnXAxis, posOnYAxis)) {
                        posOnXAxis += row[directions];
                        posOnYAxis += col[directions];
                        currentDistance++;
                    }

                    posOnXAxis -= row[directions];
                    posOnYAxis -= col[directions];

                    if (node.distance + currentDistance < dp[posOnXAxis][posOnYAxis]) {
                        dp[posOnXAxis][posOnYAxis] = node.distance + currentDistance;
                        q.offerLast(new Element(posOnXAxis, posOnYAxis, dp[posOnXAxis][posOnYAxis]));
                    }
                }
            }
        }

        return -1;
    }

    public static boolean isValidMove(ArrayList<ArrayList<Integer>> graph, int sr, int sc) {
        return sr >= 0 && sr < graph.size() && sc >= 0 && sc < graph.get(0).size() && graph.get(sr).get(sc) == 0;
    }
}
