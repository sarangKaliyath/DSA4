package TwoPointers;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

/*
Problem Description:
Given an one-dimensional integer array A of size N and an integer B.
Count all distinct pairs with difference equal to B.
Here a pair is defined as an integer pair (x, y),
where x and y are both numbers in the array and their absolute difference is B.

Problem Constraints:
1 <= N <= 10^4
0 <= A[i], B <= 10^5

Input Format:
First argument is a one-dimensional integer array A of size N.
Second argument is an integer B.

Output Format:
Return an integer denoting the count of all distinct pairs with difference equal to B.

Example Input:
Input 1:
 A = [1, 5, 3, 4, 2]
 B = 3

Input 2:
 A = [8, 12, 16, 4, 0, 20]
 B = 4

Input 3:
 A = [1, 1, 1, 2, 2]
 B = 0

Example Output:
Output 1: 2
Output 2: 5
Output 3: 2

Example Explanation:
Explanation 1:
 There are 2 unique pairs with difference 3, the pairs are {1, 4} and {5, 2}

Explanation 2:
 There are 5 unique pairs with difference 4, the pairs are {0, 4}, {4, 8}, {8, 12}, {12, 16} and {16, 20}

Explanation 3:
 There are 2 unique pairs with difference 0, the pairs are {1, 1} and {2, 2}.

 */
public class PairWithGivenDifference {
    public static void main(String[] args) {
        ArrayList<Integer> A = new ArrayList<>(Arrays.asList(1, 1, 1, 2, 2));
        int B = 0;
        int res = find(A, A.size(), B);
        System.out.println(res);
        // Time O(NlogN);
        // Space O(1);
    }

    public static int find(ArrayList<Integer> A, int n, int B) {
        Collections.sort(A);
        int i = 0;
        int j = 1;
        int count = 0;

        while (i < n && j < n) {
            if (i == j) {
                j++;
                continue;
            }

            int diff = A.get(j) - A.get(i);
            if (diff == B) {
                count++;
                i++;
                j++;
                while (i < n && A.get(i).equals(A.get(i - 1))) i++;
                while (j < n && A.get(j).equals(A.get(j - 1))) j++;
            } else if (diff < B) j++;
            else i++;
        }
        return count;
    }
}
