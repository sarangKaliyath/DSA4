package Combinatorics;

/*
Problem Description
Given a column title as appears in an Excel sheet, return its corresponding column number.

Problem Constraints
1 <= length of the column title <= 5

Input Format:
The only argument is a string that represents the column title in the excel sheet.

Output Format:
Return a single integer that represents the corresponding column number.

Example Input:
Input 1: AB
Input 2: BB

Example Output:
Output 1: 28
Output 2: 54

Example Explanation

Explanation 1:
 A -> 1
 B -> 2
 C -> 3
 ...
 Z -> 26
 AA -> 27
 AB -> 28

Explanation 2:
 A -> 1
 B -> 2
 C -> 3
 ...
 Z -> 26
 AA -> 27
 AB -> 28
 ...
 AZ -> 52
 BA -> 53
 BB -> 54

 */
public class ExcelColumnNumber {
    public static void main (String [] args){
        String str = "AB";
        int res = find(str);
        System.out.println(res);
        // Time O(length of A);
        // Space O(1);

        int ans = withoutMathPow(str);
        System.out.println(ans);
        // Time O(length of A) - O(time required for Math.pow);
        // Space O(1);
    }

    public static  int find(String str){
        int pos = 0;
        int i = str.length() - 1;
        int val = 0;
        while(i >= 0){
            char el = str.charAt(i);
            val += (el - 'A' + 1) * Math.pow(26, pos++);
            i--;
        }
        return val;
    }

    public static  int withoutMathPow(String str){
        int pos = 0;
        int i = str.length() - 1;
        int val = 0;
        int baseVal = 1;
        while(i >= 0){
            char el = str.charAt(i);
            val += (el - 'A' + 1) * baseVal;
            baseVal *= 26;
            i--;
        }
        return val;
    }
}
