package TwoPointers;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/*
Problem Description
Given an array of positive integers A and an integer B,
find and return first continuous subarray which adds to B.
If the answer does not exist return an array with a single integer "-1".
First sub-array means the sub-array for which starting index in minimum.

Problem Constraints
1 <= length of the array <= 100000
1 <= A[i] <= 10^9
1 <= B <= 10^9

Input Format:
The first argument given is the integer array A.
The second argument given is integer B.

Output Format:
Return the first continuous sub-array which adds to B and if the answer does not exist return an array with a single integer "-1".

Example Input:
Input 1:
 A = [1, 2, 3, 4, 5]
 B = 5

Input 2:
 A = [5, 10, 20, 100, 105]
 B = 110

Example Output:
Output 1: [2, 3]
Output 2: [-1]

Example Explanation:
Explanation 1:
 [2, 3] sums up to 5.

Explanation 2:
 No subarray sums up to required number.

 */
public class SubArrayWithGivenSum {

    public static void main(String[] args) {
        ArrayList<Integer> A = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        int B = 9;
        ArrayList<Integer> res = find(A, A.size(), B);
        System.out.println(res);
        // Time O(N);
        // Space O(1);
    }

    public static ArrayList<Integer> find(ArrayList<Integer> A, int n, int B) {
        ArrayList<Integer> res = new ArrayList<>();

        boolean isPresent = false;
        int i = 0;
        int j = i;
        int sum = A.get(i);
        while (j < n) {
            if (sum == B) {
                isPresent = true;
                break;
            }
            if (sum < B) {
                j++;
                if (j >= n) break;
                sum += A.get(j);
            } else {
                sum -= A.get(i);
                i++;
            }
        }

        if (isPresent) {
            while (i <= j) {
                res.add(A.get(i++));
            }
            return res;
        }

        return new ArrayList<>(Arrays.asList(-1));
    }
}
