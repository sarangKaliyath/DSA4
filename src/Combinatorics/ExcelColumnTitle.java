package Combinatorics;

/*
Problem Description:
Given a positive integer A, return its corresponding column title as it appears in an Excel sheet.
For example:
    1 -> A
    2 -> B
    3 -> C
    ...
    26 -> Z
    27 -> AA
    28 -> AB

Problem Constraints
1 <= A <= 10^9

Input Format
First and only argument of input contains single integer A

Output Format:
Return a string denoting the corresponding title.

Example Input:
Input 1: A = 3
Input 2: A = 27

Example Output:
Output 1: "C"
Output 2: "AA"

Example Explanation:
Explanation 1: 3 corresponds to C.

Explanation 2:
    1 -> A,
    2 -> B,
    3 -> C,
    ...
    26 -> Z,
    27 -> AA,
    28 -> AB

 */
public class ExcelColumnTitle {

    public  static  void main (String [] args){
        int A = 28;
        String res = find(A);
        System.out.println(res);
        // Time O(log A);
        // Space O(1);
    }

    public static  String find(int A){
        StringBuilder sb = new StringBuilder();
        while( A > 0) {
            A = A - 1;
            sb.append((char) ('A' + (A % 26)));
            A/= 26;
        }
        return  sb.reverse().toString();
    }
}
