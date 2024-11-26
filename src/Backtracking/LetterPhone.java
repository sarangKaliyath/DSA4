package Backtracking;

import java.util.*;

/*
Problem Description
Given a digit string A, return all possible letter combinations that the number could represent.
A mapping of digit to letters (just like on the telephone buttons) is given below.
The digit 0 maps to 0 itself. The digit 1 maps to 1 itself.
NOTE: Make sure the returned strings are lexicographically sorted.

Problem Constraints
1 <= |A| <= 10

Input Format:
The only argument is a digit string A.

Output Format:
Return a string array denoting the possible letter combinations.

Example Input:
Input 1: A = "23"
Input 2: A = "012"

Example Output:
Output 1:
 ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"]
Output 2:
 ["01a", "01b", "01c"]

Example Explanation:
Explanation 1:
 There are 9 possible letter combinations.

Explanation 2:
 Only 3 possible letter combinations.
 */
public class LetterPhone {
    private static ArrayList<String> list = new ArrayList<>(Arrays.asList("0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuvw", "xyz"));
    private static ArrayList<String> res = new ArrayList<>();

    public static void main(String[] args) {
        String A = "23";
        generate(A, 0, new StringBuilder());
        System.out.println(res);
        // Time O(4^N); ... N = length of string A;
        // Space O(4^N N);
    }

    public static void generate(String A, int index, StringBuilder sb) {

        if (sb.length() == A.length()) {
            res.add(sb.toString());
            return;
        }

        int chIndex = A.charAt(index) - '0';

        for (int i = 0; i < list.get(chIndex).length(); i++) {
            sb.append(list.get(chIndex).charAt(i));
            generate(A, index + 1, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
