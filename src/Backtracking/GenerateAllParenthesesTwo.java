package Backtracking;

import java.lang.reflect.Array;
import java.util.ArrayList;

/*
Problem Description
Given an integer A pairs of parentheses, write a function to generate all combinations
of well-formed parentheses of length 2*A.

Problem Constraints
1 <= A <= 10

Input Format
First and only argument is integer A.

Output Format:
Return a sorted list of all possible parenthesis.

Example Input:
Input 1: A = 3
Input 2: A = 1

Example Output:
Output 1:
[ "((()))", "(()())", "(())()", "()(())", "()()()" ]

Output 2:
[ "()" ]

Example Explanation
Explanation 1: All parentheses are given in the output list.

Explanation 2: All parentheses are given in the output list.

 */
public class GenerateAllParenthesesTwo {

    // on online platforms avoid using static. static variables are shared across all
    // instances and method calls, potentially leading to leftover data from previous runs.
    private static ArrayList<String> res = new ArrayList<>();

    public static void main(String[] args) {
        int A = 3;
        generateParentheses(new StringBuilder(), A, 0, 0);
        System.out.println(res);
        // O(2^A);
        // Space O(A);
    }

    public static void generateParentheses(StringBuilder sb, int A, int openBraces, int closedBraces) {
        if (openBraces == A && closedBraces == A) {
            res.add(sb.toString());
            return;
        }

        if (openBraces < A) {
            sb.append('(');
            generateParentheses(sb, A, openBraces + 1, closedBraces);
            sb.deleteCharAt(sb.length() - 1);
        }

        if (closedBraces < openBraces) {
            sb.append(')');
            generateParentheses(sb, A, openBraces, closedBraces + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
