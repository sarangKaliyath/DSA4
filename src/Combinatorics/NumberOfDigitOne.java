package Combinatorics;

/*
Problem Description
Given an integer A, find and return the total number of digits 1 appearing in all non-negative integers less than or equal to A.

Problem Constraints
0 <= A <= 10^9

Input Format
The only argument given is the integer A.

Output Format:
Return the total number of digits 1 appearing in all non-negative integers less than or equal to A.

Example Input:
Input 1: A = 10
Input 2: A = 11

Example Output:
Output 1: 2
Output 2: 4

Example Explanation:
Explanation 1:
Digit 1 appears in 1 and 10 only and appears one time in each. So the answer is 2.
Explanation 2:
Digit 1 appears in 1(1 time) , 10(1 time) and 11(2 times) only. So the answer is 4.
 */
public class NumberOfDigitOne {
    public  static  void main (String [] args){
        int A = 19;

        int brut = bruteForce(A);
        System.out.println(brut);
        // Time O(A log₁₀A);
        // Space O(1);

        int res = optimized(A);
        System.out.println(res);
        // Time O(log₁₀(A));
        // Space O(1);
    }

    public static int bruteForce(int A){
        int count = 0;
        for(int i = 0; i <= A; i++){
            int el = i;
            while(el > 0){
                if(el % 10 == 1) count++;
                el/=10;
            }
        }
        return  count;
    }
    public static int optimized(int A){
        int res = 0;
        for(int i = 1; i <= A; i*= 10){
            res += (A / (i * 10)) * i;
            res += Math.min(Math.max( A % (10 * i) - i + 1, 0), i);
        }
        return  res;
    }
}
