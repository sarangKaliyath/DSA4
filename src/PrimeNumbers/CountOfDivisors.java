package PrimeNumbers;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/*
Problem Description
Given an array of integers A, find and return the count of divisors of each element of the array.
NOTE: The order of the resultant array should be the same as the input array.

Problem Constraints
1 <= length of the array <= 100000
1 <= A[i] <= 10^6

Input Format:
The only argument given is the integer array A.

Output Format:
Return the count of divisors of each element of the array in the form of an array.

Example Input:
Input 1: A = [2, 3, 4, 5]
Input 2: A = [8, 9, 10]

Example Output:
Output 1: [2, 2, 3, 2]
Output 1: [4, 3, 4]

Example Explanation:
Explanation 1:
 The number of divisors of 2 : [1, 2], 3 : [1, 3], 4 : [1, 2, 4], 5 : [1, 5]
 So the count will be [2, 2, 3, 2].

Explanation 2:
 The number of divisors of 8 : [1, 2, 4, 8], 9 : [1, 3, 9], 10 : [1, 2, 5, 10]
 So the count will be [4, 3, 4].
 */
public class CountOfDivisors {

    public static void main(String[] args) {
        ArrayList<Integer> A = new ArrayList<>(Arrays.asList(8, 9, 10));
        ArrayList<Integer> res = countDivisors(A);
        System.out.println(res);
        // Time O( end(log end) );
        // Space O(end);
    }

    public static ArrayList<Integer> countDivisors(ArrayList<Integer> A) {
        int start = Collections.min(A);
        int end = Collections.max(A);
        int[] factors = new int[end + 1];
        Arrays.fill(factors, 1);

        for (int i = 2; i <= end; i++) {
            for (int j = i; j <= end; j += i) {
                factors[j]++;
            }
        }

        ArrayList<Integer> res = new ArrayList<>();

        for (int i : A) {
            res.add(factors[i]);
        }
        return res;
    }
}
