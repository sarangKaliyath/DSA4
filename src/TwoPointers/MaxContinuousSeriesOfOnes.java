package TwoPointers;

import java.util.ArrayList;
import java.util.Arrays;

/*
Problem Description
Given a binary array A, find the maximum sequence of continuous 1's that can be formed by replacing at-most B zeroes.
For this problem, return the indices of maximum continuous series of 1s in order.
If there are multiple possible solutions, return the sequence which has the minimum start index.

Problem Constraints
0 <= B <= 10^5
1 <= size(A) <= 10^5
0 <= A[i] <= 1

Input Format
First argument is an binary array A.
Second argument is an integer B.

Output Format
Return an array of integers denoting the indices(0-based) of 1's in the maximum continuous series.

Example Input

Input 1:
 A = [1, 1, 0, 1, 1, 0, 0, 1, 1, 1]
 B = 1

Input 2:
 A = [1, 0, 0, 0, 1, 0, 1]
 B = 2

Example Output:
Output 1: [0, 1, 2, 3, 4]
Output 2: [3, 4, 5, 6]

Example Explanation:
Explanation 1:
 Flipping 0 present at index 2 gives us the longest continuous series of 1's i.e subarray [0:4].

Explanation 2:
 Flipping 0 present at index 3 and index 5 gives us the longest continuous series of 1's i.e subarray [3:6].
 */
public class MaxContinuousSeriesOfOnes {
    public static void main(String[] args) {
        ArrayList<Integer> A = new ArrayList<Integer>(Arrays.asList(0, 1, 1, 1));
        int B = 0;

        ArrayList<Integer> res = bruteForce(A, A.size(), B);
        System.out.println(res);
        // Time O(N);
        // Space O(1);

        ArrayList<Integer> ans = optimized(A, A.size(), B);
        System.out.println(ans);
        // Time O(N);
        // Space O(1);

    }

    public static ArrayList<Integer> optimized(ArrayList<Integer> A, int n, int B) {
        int i = 0;
        int j = 0;
        int count = 0;
        int maxCount = 0;
        int maxI = 0;
        int maxJ = n - 1;
        int limit = B;

        while (j < n) {
            if (A.get(j) == 0) limit--;
            if (limit < 0) {
                if (A.get(i) == 0) {
                    limit++;
                }
                count--;
                i++;
            }

            j++;
            count++;

            if (count > maxCount) {
                maxCount = count;
                maxI = i;
                maxJ = j;
            }
        }

        ArrayList<Integer> res = new ArrayList<>();
        for (int k = maxI; k < maxJ; k++) {
            res.add(k);
        }
        return res;
    }

    public static ArrayList<Integer> bruteForce(ArrayList<Integer> A, int n, int B) {
        int limit = B;
        int maxCount = 0;
        int maxI = 0;
        int maxJ = n - 1;
        for (int i = 0; i < n; i++) {
            int count = 0;
            int ind = 0;
            for (int j = i; j < n; j++) {
                if (A.get(j) == 0) {
                    limit--;
                }
                if (limit >= 0) {
                    count++;
                    ind = j;
                } else {
                    limit = B;
                    break;
                }
            }
            if (count > maxCount) {
                maxI = i;
                maxJ = ind;
                maxCount = count;
            }
        }

        ArrayList<Integer> res = new ArrayList<>();
        for (int i = maxI; i <= maxJ; i++) {
            res.add(i);
        }
        return res;
    }
}
