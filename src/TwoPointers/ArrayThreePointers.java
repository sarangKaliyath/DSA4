package TwoPointers;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

/*
Problem Description:
You are given 3 sorted arrays A, B and C.
Find i, j, k such that : max(abs(A[i] - B[j]), abs(B[j] - C[k]), abs(C[k] - A[i])) is minimized.
Return the minimum max(abs(A[i] - B[j]), abs(B[j] - C[k]), abs(C[k] - A[i])).

Problem Constraints
1 <= len(A), len(B), len(c) <= 10^6
0 <= A[i], B[i], C[i] <= 10^7

Input Format:
First argument is an integer array A.
Second argument is an integer array B.
Third argument is an integer array C.

Output Format:
Return a single integer denoting the minimum max(abs(A[i] - B[j]), abs(B[j] - C[k]), abs(C[k] - A[i])).

Example Input

Input 1:
 A = [1, 4, 10]
 B = [2, 15, 20]
 C = [10, 12]

Input 2:
 A = [3, 5, 6]
 B = [2]
 C = [3, 4]

Example Output:
Output 1: 5
Output 2: 1

Example Explanation:
Explanation 1:
 With 10 from A, 15 from B and 10 from C.

Explanation 2:
 With 3 from A, 2 from B and 3 from C.
 */
public class ArrayThreePointers {

    public static void main (String [] args){
        List<Integer> A= new ArrayList<>(Arrays.asList(1, 4, 10));
        List<Integer> B= new ArrayList<>(Arrays.asList(2, 15, 20));
        List<Integer> C= new ArrayList<>(Arrays.asList(10, 12));

        int res = bruteForce(A, B, C);
        System.out.println(res);
        // Time O(N^3);
        // Space O(1);

        int ans = optimized(A, B, C);
        System.out.println(ans);
        // Time O(N);
        // Space O(1);
    }

    public static int optimized(List<Integer> A, List<Integer> B, List<Integer> C) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        int i = 0;
        int j = 0;
        int k = 0;

        while (i < A.size() && j < B.size() && k < C.size()) {
            int a = A.get(i);
            int b = B.get(j);
            int c = C.get(k);
            int ab = Math.abs(a - b);
            int bc = Math.abs(b - c);
            int ac = Math.abs(a - c);

            int val = Math.max(Math.max(ab, bc), ac);
            min = Math.min(min, val);

            if (a <= b && a <= c) i++;
            else if (b <= a && b <= c) j++;
            else k++;
        }
        return min;
    }

    public  static int bruteForce(List<Integer> A, List<Integer> B, List<Integer> C){
        int min = Integer.MAX_VALUE;
        int ans = Integer.MIN_VALUE;

        for(int i = 0; i < A.size(); i++){
            for(int j = 0; j < B.size(); j++){
                for(int k = 0; k < C.size(); k++){
                    int ab = Math.abs(A.get(i) - B.get(j));
                    int bc = Math.abs(B.get(j) - C.get(k));
                    int ac = Math.abs(A.get(i) - C.get(k));

                    int val = Math.max(Math.max(ab, bc), ac);

                    if(val < min){
                        min = val;
                    }
                }
            }
        }

        return  min;
    }
}
