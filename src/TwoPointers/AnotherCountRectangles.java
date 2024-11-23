package TwoPointers;

import java.util.ArrayList;
import java.util.Arrays;

/*
Problem Description
Given a sorted array of distinct integers A and an integer B,
find and return how many rectangles with distinct configurations can be created
using elements of this array as length and breadth whose area is lesser than B.
(Note that a rectangle of 2 x 3 is different from 3 x 2 if we take configuration into view)

Problem Constraints
1 <= |A| <= 100000
1 <= A[i] <= 10^9
1 <= B <= 10^9

Input Format:
The first argument given is the integer array A.
The second argument given is integer B.

Output Format
Return the number of rectangles with distinct configurations with area less than B modulo (10^9 + 7).

Example Input:
Input 1:
 A = [1, 2]
 B = 5

Input 2:
 A = [1, 2]
 B = 1

Example Output:
Output 1: 4
Output 2: 0

Example Explanation:
Explanation 1:
 All 1X1, 2X2, 1X2 and 2X1 have area less than 5.

Explanation 2:
 No Rectangle is valid.

 */
public class AnotherCountRectangles {

    public static void main(String[] args) {
        ArrayList<Integer> A = new ArrayList<>(Arrays.asList(2, 3, 4, 5, 6));
        int B = 12;
        int res = bruteForce(A, A.size(), B);
        System.out.println(res);

        int ans = optimized(A, A.size(), B);
        System.out.println(ans);
    }

    public static int optimized(ArrayList<Integer> A, int n, int B) {
        int MOD = 1000000007;
        long count = 0;
        int i = 0;
        int j = n - 1;

        while (i < n && j >= 0) {
            long ans = (long) A.get(i) * A.get(j);
            if (ans < B) {
                count = (count % MOD + (j + 1) % MOD) % MOD;
                i++;
            } else j--;
        }
        return (int) (count % MOD);
    }

    public static int bruteForce(ArrayList<Integer> A, int n, int B) {
        int count = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int area = A.get(i) * A.get(j);
                if (area < B) count++;
            }
        }
        return count;
    }
}
