package MiscellaneousLabProblems;

import java.util.ArrayList;
import java.util.Arrays;

/*
Problem Description:
Given a sorted matrix of integers A of size N x M and an integer B.
Each of the rows and columns of matrix A is sorted in ascending order,
find the Bth smallest element in the matrix.
NOTE: Return The Bth smallest element in the sorted order,
not the Bth distinct element.

Problem Constraints
1 <= N, M <= 500
1 <= A[i] <= 10^9
1 <= B <= N * M

Input Format:
The first argument given is the integer matrix A.
The second argument given is an integer B.

Output Format:
Return the B-th smallest element in the matrix.

Example Input:
Input 1:
 A = [ [9, 11, 15],
       [10, 15, 17] ]
 B = 6

Input 2:
 A = [  [5, 9, 11],
        [9, 11, 13],
        [10, 12, 15],
        [13, 14, 16],
        [16, 20, 21] ]
 B = 12

Example Output:
Output 1: 17
Output 2: 16

Example Explanation:
Explanation 1: 6th smallest element in the sorted matrix is 17.
Explanation 2: 12th smallest element in the sorted matrix is 16.
 */
public class KthSmallestElementInSortedMatrix {

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> A = new ArrayList<>(Arrays.asList(
                new ArrayList<>(Arrays.asList(9, 11, 15)),
                new ArrayList<>(Arrays.asList(10, 15, 17))
        ));
        int B = 6;

        int res = find(A, A.size(), A.get(0).size(), B);
        System.out.println(res);
        // Time O((N+M) * log(search space));
        // Space O(1);
    }


    public static int find(ArrayList<ArrayList<Integer>> A, int n, int m, int B) {
        int left = 0;
        int right = (int) Math.pow(10, 9);

        while (left < right) {
            int mid = left + ((right - left) >> 1);

            if (countElements(A, n, m, mid) < B) left = mid + 1;
            else right = mid;
        }

        return left;
    }

    public static int countElements(ArrayList<ArrayList<Integer>> A, int n, int m, int val) {
        int r = 0;
        int c = m - 1;
        int count = 0;

        while (r < n && c >= 0) {
            if (A.get(r).get(c) <= val) {
                count += c + 1;
                r++;
            } else c--;
        }

        return count;


    }
}
