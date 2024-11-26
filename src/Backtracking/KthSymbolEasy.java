package Backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/*
Problem Description
On the first row, we write a 0. Now in every subsequent row,
we look at the previous row and replace each occurrence of 0 with 01, and each occurrence of 1 with 10.
Given row number A and index B, return the Bth indexed symbol in row A. (The values of B are 0-indexed.).

Problem Constraints
1 <= A <= 20
0 <= B < 2A - 1

Input Format:
First argument is an integer A.
Second argument is an integer B.

Output Format:
Return an integer denoting the Bth indexed symbol in row A.

Example Input:
Input 1:
 A = 3
 B = 0
Input 2:
 A = 4
 B = 4

Example Output:
Output 1: 0
Output 2: 1

Example Explanation

Explanation 1:
 Row 1: 0
 Row 2: 01
 Row 3: 0110

Explanation 2:
 Row 1: 0
 Row 2: 01
 Row 3: 0110
 Row 4: 01101001

 */
public class KthSymbolEasy {
    public static  void main (String [] args){
        int A = 3;
        int B = 0;
        StringBuilder res = new StringBuilder();
        generate(A, B, 0, new StringBuilder().append('0'), res);
        System.out.println(res.charAt(B) - '0');
        // Time O(2^A); .. but getting TLE for this approach.
        // Space O(2^A);

        ArrayList<String> ans = optimized(A);
        System.out.println(Integer.parseInt(ans.get(B)));
        // Time O(2^A);
        // Space O(2^A);
    }

    public static  ArrayList<String> optimized(int A){
        if(A == 1){
            ArrayList<String> res = new ArrayList<>();
            res.add("0");
            return res;
        }

        ArrayList<String> arr = optimized(A - 1);
        ArrayList<String> currentRowList = new ArrayList<>();
        for(int i = 0; i < arr.size(); i++){
            if(arr.get(i).equals("0")){
                currentRowList.add("0");
                currentRowList.add("1");
            }
            else {
                currentRowList.add("1");
                currentRowList.add("0");
            }
        }

        return currentRowList;
    }
    public static void generate(int A, int B, int index, StringBuilder sb, StringBuilder res){
        if(index == A){
            res.append(sb);
            return;
        }

        StringBuilder temp = new StringBuilder();
        for(int i = 0; i < sb.toString().length(); i++){
            if(sb.toString().charAt(i) == '0'){
                temp.append("01");
            }
            else temp.append("10");
        }
        generate(A, B, index + 1, temp, res);
    }
}
