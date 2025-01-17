package Graph;
/*
Problem Description:
Given a matrix of integers A of size N x M consisting of 0, 1 or 2.
Each cell can have three values:
The value 0 representing an empty cell.
The value 1 representing a fresh orange.
The value 2 representing a rotten orange.
Every minute, any fresh orange that is adjacent (Left, Right, Top, or Bottom)
to a rotten orange becomes rotten. Return the minimum number of minutes that must
elapse until no cell has a fresh orange. If this is impossible, return -1 instead.
Note: Your solution will run on multiple test cases. If you are using global variables, make sure to clear them.

Problem Constraints:
1 <= N, M <= 1000
0 <= A[i][j] <= 2

Input Format:
The first argument given is the integer matrix A.

Output Format:
Return the minimum number of minutes that must elapse until no cell has a fresh orange.
If this is impossible, return -1 instead.

Example Input:

Input 1:
A = [   [2, 1, 1]
        [1, 1, 0]
        [0, 1, 1]   ]

Input 2:

A = [   [2, 1, 1]
        [0, 1, 1]
        [1, 0, 1]   ]

Example Output:
Output 1: 4
Output 2: -1

Example Explanation:

Explanation 1:
Minute 0: [ [2, 1, 1]
            [1, 1, 0]
            [0, 1, 1] ]
Minute 1: [ [2, 2, 1]
            [2, 1, 0]
            [0, 1, 1] ]
Minute 2: [ [2, 2, 2]
            [2, 2, 0]
            [0, 1, 1] ]
Minute 3: [ [2, 2, 2]
            [2, 2, 0]
            [0, 2, 1] ]
Minute 4: [ [2, 2, 2]
            [2, 2, 0]
            [0, 2, 2] ]
At Minute 4, all the oranges are rotten.

Explanation 2:
The fresh orange at 2nd row and 0th column cannot be rotten, So return -1.

 */

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;

public class RottenOranges {

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> A = new ArrayList<>(Arrays.asList(
                new ArrayList<>(Arrays.asList(2, 1, 1)),
                new ArrayList<>(Arrays.asList(1, 1, 0)),
                new ArrayList<>(Arrays.asList(0, 1, 1))
        ));

        int res = findMinTime(A, A.size(), A.get(0).size());
        System.out.println(res);
        // Time O(N * M);
        // Space O(N * M);
    }

    public static int findMinTime(ArrayList<ArrayList<Integer>> A, int n, int m) {
        Deque<ArrayList<Integer>> q = new ArrayDeque<>();
        int freshOranges = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (A.get(i).get(j) == 2) {
                    q.offerLast(new ArrayList<>(Arrays.asList(i, j, 0)));
                } else if (A.get(i).get(j) == 1) {
                    freshOranges++;
                }
            }
        }

        if (freshOranges == 0) return 0;

        int[] row = {-1, 0, 1, 0};
        int[] col = {0, 1, 0, -1};

        while (!q.isEmpty()) {
            ArrayList<Integer> cell = q.pollFirst();
            int i = cell.get(0);
            int j = cell.get(1);
            int time = cell.get(2);

            for (int direction = 0; direction <= 3; direction++) {
                int xAxis = i + row[direction];
                int yAxis = j + col[direction];

                if (xAxis >= 0 && xAxis < n && yAxis >= 0 && yAxis < m) {
                    if (A.get(xAxis).get(yAxis) == 1) {
                        A.get(xAxis).set(yAxis, 2);
                        q.offerLast(new ArrayList<>(Arrays.asList(xAxis, yAxis, time + 1)));
                        freshOranges--;
                        if (freshOranges == 0) return time + 1;
                    }
                }

            }
        }

        return -1;
    }
}
