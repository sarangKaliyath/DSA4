package Hashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/*
Problem Description
Misha likes finding all Sub-arrays of an Array.
Now she gives you an array A of N elements and told you to find the number of sub-arrays of A,
that have unique elements.
Since the number of sub-arrays could be large, return value % 10^9 +7.

Problem Constraints
1 <= N <= 10^5
1 <= A[i] <= 10^6

Input Format:
The only argument given is an Array A, having N integers.

Output Format:
Return the number of sub-arrays of A, that have unique elements.

Example Input:
Input 1:
 A = [1, 1, 3]

Input 2:
 A = [2, 1, 2]

Example Output:
Output 1: 4
Output 1: 5

Example Explanation:
Explanation 1:
 Sub-arrays of A that have unique elements only:
 [1], [1], [1, 3], [3]

Explanation 2:
 Sub-arrays of A that have unique elements only:
 [2], [1], [2, 1], [1, 2], [2]
 */
public class CountSubArrays {
    public static void main(String[] args) {
        ArrayList<Integer> A = new ArrayList<>(Arrays.asList(2, 1, 2));
        int res = find(A, A.size(), (int) Math.pow(10, 9) + 7);
        System.out.println(res);
    }

    public static int find(ArrayList<Integer> A, int n, int MOD) {
        int i = 0;
        int j = 0;
        int count = 0;
        HashMap<Integer, Integer> hm = new HashMap<>();

        while (j < n) {
            int el = A.get(j);
            if (hm.containsKey(el)) {
                i = Math.max(i, hm.get(el) + 1);
            }
            count = (count + (j - i + 1)) % MOD;
            hm.put(el, j);
            j++;
        }
        return count;
    }
}
