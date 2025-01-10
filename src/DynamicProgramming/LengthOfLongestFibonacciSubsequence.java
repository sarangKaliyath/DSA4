package DynamicProgramming;
/*
Problem Description
Given a strictly increasing array A of positive integers forming a sequence.
A sequence X1, X2, X3, ..., XN is fibonacci like if

N >=3
Xi + Xi+1 = Xi+2 for all i+2 <= N

Find and return the length of the longest Fibonacci-like subsequence of A.
If one does not exist, return 0.
NOTE:
    A subsequence is derived from another sequence A by deleting any number of
    elements (including none) from A, without changing the order of the remaining elements.

Problem Constraints
3 <= length of the array <= 1000
1 <= A[i] <= 109

Input Format:
The only argument given is the integer array A.

Output Format:
Return the length of the longest Fibonacci-like subsequence of A.
If one does not exist, return 0.

Example Input:
Input 1:
 A = [1, 2, 3, 4, 5, 6, 7, 8]

Input 2:
 A = [1, 3, 7, 11, 12, 14, 18]

Example Output:
Output 1: 5
Output 2: 3

Example Explanation:
Explanation 1:
 The longest subsequence that is fibonacci-like: [1, 2, 3, 5, 8].

Explanation 2:
 The longest subsequence that is fibonacci-like: [1, 11, 12], [3, 11, 14] or [7, 11, 18].
 The length will be 3.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class LengthOfLongestFibonacciSubsequence {
    public static void main (String [] args){
        ArrayList<Integer> A = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));
        int n = A.size();

        int res = bruteForce(A, n);
        System.out.println(res);
        // Time O(N^3);
        // Space O(1);

        int ans = iterative(A, n);
        System.out.println(ans);
        // Time O(N^2);
        // Space O(N^2);
    }

    public static int bruteForce (ArrayList<Integer> A, int n){
        int maxLen = 0;

        for(int i = 0; i < n - 1; i++){
            for(int j = i + 1; j < n; j++){

                int a = A.get(i);
                int b = A.get(j);

                int len = 2;
                int nextEl = a + b;

                for(int k = j + 1; k < n ;k++){
                    if(A.get(k) == nextEl){
                        len++;
                        a = b;
                        b = nextEl;
                        nextEl = a + b;
                    }
                }

                if(len >= 3) maxLen = Math.max(maxLen, len);
            }
        }

        return maxLen;
    }

    public static int iterative(ArrayList<Integer> A, int n){
        HashMap<Integer, Integer> indexHm = new HashMap<>();
        for(int i = 0; i < n; i++) indexHm.putIfAbsent(A.get(i), i);

        HashMap<Integer, Integer> longestHm = new HashMap<>();
        int ans = 0;

        for(int i = 0; i < n; i++){
            for(int j = 0; j < i; j++){
                int val = A.get(i) - A.get(j);
                if(val < A.get(j) && indexHm.containsKey(val)){
                    int idx = indexHm.getOrDefault(val, 0);
                    longestHm.putIfAbsent(j * n + i, 0);
                    longestHm.putIfAbsent(idx * n + j, 0);
                    longestHm.replace(j * n + i, longestHm.get(idx * n + j) + 1);
                    ans = Math.max(ans, longestHm.getOrDefault(j * n + i, 0) + 2);
                }
            }
        }
        return ans >= 3 ? ans : 0;
    }
}
