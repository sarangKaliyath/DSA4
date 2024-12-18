package TwoPointers;

import java.util.ArrayList;
import java.util.Arrays;

/*
Problem Description
Given two sorted arrays of distinct integers, A and B, and an integer C,
find and return the pair whose sum is closest to C and the pair has one element from each array.
More formally, find A[i] and B[j] such that abs((A[i] + B[j]) - C) has minimum value.
If there are multiple solutions find the one with minimum i and even
if there are multiple values of j for the same i then return the one with minimum j.
Return an array with two elements {A[i], B[j]}.

Problem Constraints
1 <= length of both the arrays <= 10^5
1 <= A[i], B[i] <= 10^9
1 <= C <= 10^9

Input Format
The first argument given is the integer array A.
The second argument given is the integer array B.
The third argument given is integer C.

Output Format
Return an array of size 2 denoting the pair which has sum closest to C.

Example Input

Input 1:
 A = [1, 2, 3, 4, 5]
 B = [2, 4, 6, 8]
 C = 9

Input 2:
 A = [5, 10, 20]
 B = [1, 2, 30]
 C = 13

Example Output:
Output 1: [1, 8]
Output 2: [10, 2]

Example Explanation

Explanation 1:
 There are three pairs: (1, 8), (3, 6), (5, 4), that gives the minimum value.
 Since we have to return the value with minimum i and then with minimum j. We will return [1, 8].

Explanation 2:
 [10, 2] is the only pair such abs(10+2-13) is minimum.
 */
public class ClosestPairFromSortedArrays {
    public static void main(String[] args) {
        ArrayList<Integer> A = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        ArrayList<Integer> B = new ArrayList<>(Arrays.asList(2, 4, 6, 8));
        int C = 9;

        ArrayList<Integer> res = bruteForce(A, B, C);
        System.out.println(res);
        // Time O(N^2);
        // Space O(1);

        ArrayList<Integer> ans = optimized(A, B, C);
        System.out.println(ans);
        // Time O(N);
        // Space O(1);
    }

    public static ArrayList<Integer> optimized(ArrayList<Integer> A, ArrayList<Integer> B, int C) {
        ArrayList<Integer> res = new ArrayList<>();

        int i = 0;
        int j = B.size() - 1;
        int min = Integer.MAX_VALUE;
        int minI = -1;
        int minJ = -1;

        while (i < A.size() && j >= 0) {

            int sum = A.get(i) + B.get(j);
            int diff = Math.abs(sum - C);

            if (diff < min) {
                min = diff;
                minI = i;
                minJ = j;
            } else if (diff == min) {
                if (i < minI || (i == minI && j < minJ)) {
                    minI = i;
                    minJ = j;
                }
            }

            if (sum < C) i++;
            else if (sum > C) j--;
            else {
                minI = i;
                minJ = j;
                min = diff;
                break;
            }

        }
        res.add(A.get(minI));
        res.add(B.get(minJ));
        return res;
    }

    public static ArrayList<Integer> bruteForce(ArrayList<Integer> A, ArrayList<Integer> B, int C) {
        ArrayList<Integer> res = new ArrayList<>();
        int min = Integer.MAX_VALUE;
        int minI = -1;
        int minJ = -1;

        for (int i = 0; i < A.size(); i++) {
            for (int j = 0; j < B.size(); j++) {
                int sum = A.get(i) + B.get(j);

                if (Math.abs(sum - C) < min) {
                    min = Math.abs(sum - C);
                    minI = A.get(i);
                    minJ = B.get(j);
                }
            }
        }

        res.add(minI);
        res.add(minJ);
        return res;
    }
}
