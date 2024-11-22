package TwoPointers;

import java.util.ArrayList;
import java.util.Arrays;

/*
Problem Description
Given a sorted array of integers (not necessarily distinct) A and an integer B,
find and return how many pair of integers ( A[i], A[j] ) such that i != j have sum equal to B.
Since the number of such pairs can be very large, return number of such pairs modulo (10^9 + 7).

Problem Constraints
1 <= |A| <= 100000
1 <= A[i] <= 10^9
1 <= B <= 10^9

Input Format:
The first argument given is the integer array A.
The second argument given is integer B.

Output Format:
Return the number of pairs for which sum is equal to B modulo (10^9+7).

Example Input:

Input 1:
A = [1, 1, 1]
B = 2

Input 2:
A = [1, 5, 7, 10]
B = 8

Example Output:
Output 1: 3
Output 2: 1

Example Explanation:
Explanation 1:
 The pairs of A[i] and A[j] which sum up to 2 are (0, 1), (0, 2) and (1, 2).
 There are 3 pairs.

Explanation 2:
 There is only one pair, such that i = 0, and j = 2 sums up to 8.

 */
public class PairsWithGivenSum2 {

    public static void main(String[] args) {
        ArrayList<Integer> A = new ArrayList<>(Arrays.asList(1, 5, 7, 10));
        int B = 8;
        int res = find(A, A.size(), B);
        System.out.println(res);
        // Time O(N);
        // Space O(1);
    }

    public static int find(ArrayList<Integer> A, int n, int B) {
        int MOD = (int) Math.pow(10, 9) + 7;
        int i = 0;
        int j = n - 1;
        long count = 0;

        while (i < j) {
            if (A.get(i) + A.get(j) < B) i++;
            else if (A.get(i) + A.get(j) > B) j--;
            else {

                if (A.get(i).equals(A.get(j))) {
                    long range = j - i + 1;
                    count = (count + (range * (range - 1) / 2) % MOD) % MOD;
                    return (int) count;
                } else {
                    long countI = 1;
                    while (i + 1 < j && A.get(i).equals(A.get(i + 1))) {
                        countI++;
                        i++;
                    }

                    long countJ = 1;
                    while (j - 1 > i && A.get(j).equals(A.get(j - 1))) {
                        countJ++;
                        j--;
                    }
                    i++;
                    j--;
                    count = (count + (countI * countJ) % MOD) % MOD;
                }
            }
        }

        return (int) count;
    }
}
