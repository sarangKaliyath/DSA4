package Hashing;

/*
Problem Description:
Given two arrays of integers A and B,
Sort A in such a way that the relative order among the elements will be the same as those are in B.
For the elements not present in B, append them at last in sorted order.
Return the array A after sorting from the above method.
NOTE: Elements of B are unique.

Problem Constraints:
1 <= length of the array A <= 100000
1 <= length of the array B <= 100000
-10^9 <= A[i] <= 10^9

Input Format:
The first argument given is the integer array A.
The second argument given is the integer array B.

Output Format:
Return the array A after sorting as described.

Example Input:
Input 1:
A = [1, 2, 3, 4, 5, 4]
B = [5, 4, 2]

Input 2:
A = [5, 17, 100, 11]
B = [1, 100]

Example Output:
Output 1: [5, 4, 4, 2, 1, 3]
Output 2: [100, 5, 11, 17]

Example Explanation:
Explanation 1:
Since 2, 4, 5, 4 of A are present in the array B.  So Maintaining the relative order of B.
Thus, [5, 4, 4, 2] and appending the remaining element (1, 3) in sorted order.
The Final array is [5, 4, 4, 2, 1, 3]

Explanation 2:
Since 100 of A are present in the array B.  So Maintaining the relative order of B.
Thus, [100] and appending the remaining element (5, 11, 17) in sorted order.
The Final array is [100, 5, 11, 17]

 */

import java.util.*;

public class SortArrayInGivenOrder {

    public static void main(String[] args) {
        ArrayList<Integer> A = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 4));
        ArrayList<Integer> B = new ArrayList<>(Arrays.asList(5, 4, 2));

        ArrayList<Integer> res = find(A, B);
        System.out.println(res);
        // Time O(NlogN);
        // Space O(Max(A.size(), B.size())
    }

    public static ArrayList<Integer> find(ArrayList<Integer> A, ArrayList<Integer> B) {
        ArrayList<Integer> res = new ArrayList<>();
        HashMap<Integer, Integer> hm = new HashMap<>();
        for (int el : A) {
            if (hm.containsKey(el)) hm.put(el, hm.get(el) + 1);
            else hm.put(el, 1);
        }

        addToRes(B, hm, res);
        Collections.sort(A);
        addToRes(A, hm, res);

        return res;
    }

    public static void addToRes(ArrayList<Integer> arr, HashMap<Integer, Integer> hm, ArrayList<Integer> res) {
        for (int el : arr) {
            if (hm.containsKey(el)) {
                while (hm.get(el) > 0) {
                    res.add(el);
                    hm.put(el, hm.get(el) - 1);
                }
            }
        }
    }
}
