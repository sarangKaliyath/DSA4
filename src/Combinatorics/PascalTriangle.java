package Combinatorics;

import java.util.ArrayList;

/*
Problem Description:
Write a program to print the pascal triangle up to A rows.

Problem Constraints
1 <= A <= 25

Input Format
The first argument is an integer A.

Output Format:
Return a 2D array consisting of A rows whose each row contains A integers.

Example Input:
Input 1: A = 3
Input 2: A = 5

Example Output:
Output 1:
1 0 0
1 1 0
1 2 1
Output 2:
1 0 0 0 0
1 1 0 0 0
1 2 1 0 0
1 3 3 1 0
1 4 6 4 1

Example Explanation:
Explanation 1:
Row 1 = 1 0 0
Row 2 = 1C0 1C1 0 = 1 1 0
Row 3 = 2C0 2C1 2C2 = 1 2 1
 */
public class PascalTriangle {

    public  static  void main (String [] args){

        int A = 5;

        ArrayList<ArrayList<Integer>> res = pascalTriangle(A);
        System.out.println(res);
        // Time O(N^2);
        // Space O(N^2);
    }

    public  static ArrayList<ArrayList<Integer>> pascalTriangle(int n){
        ArrayList<ArrayList<Integer>> nCr = new ArrayList<>();
        for(int i = 0; i < n; i++){
            ArrayList<Integer> row = new ArrayList<>();
            for(int j = 0; j < n; j++){
                row.add(0);
            }
            nCr.add(row);
        }

        for(int i = 0; i < n; i++){

            nCr.get(i).set(0, 1);
            nCr.get(i).set(i, 1);

            for(int j = 1; j < i; j++){
                int val = nCr.get(i - 1).get(j - 1) + nCr.get(i - 1).get(j);
                nCr.get(i).set(j, val);
            }
        }

        return  nCr;
    }
//    public  static  int [][] pascalTriangle(int n){
//        int [][] nCr= new int [n][n];
//        for(int i = 0; i < n; i++){
//            nCr[i][0] = 1;
//            nCr[i][i] = 1;
//            for(int j = 1; j < i; j++){
//                nCr[i][j] = nCr[i - 1][j - 1] + nCr[i - 1][j];
//            }
//        }
//        return  nCr;
//    }
}
