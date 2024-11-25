package Backtracking;

import java.util.ArrayList;
import java.util.Arrays;

/*
Problem Description
Given a set of distinct integers A,
return all possible subsets that can be formed from the elements of array A.

Problem Constraints
1 ≤ |A| ≤ 16
INT_MIN ≤ A[i] ≤ INT_MAX

Array A is given in increasing order.
All elements of array A are distinct.

Input Format:
First and only argument of input contains a single integer array A.

Output Format:
Return a vector of vectors denoting the answer in any order.

Example Input:
Input 1: A = [1]
Input 2: A = [1, 2, 3]

Example Output:
Output 1:

[
    []
    [1]
]

Output 2:

[
 []
 [1]
 [1, 2]
 [1, 2, 3]
 [1, 3]
 [2]
 [2, 3]
 [3]
]



Example Explanation

Explanation:

You can see that these are all possible subsets.


 */
public class GenerateSubSets {

    public static ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
    public  static  void main (String [] args){
        ArrayList<Integer> A = new ArrayList<>(Arrays.asList(1, 2, 3));
        generateSubSets(A, 0, new ArrayList<>());
        System.out.println(ans);
        // Time O(2^A);
        // Space O(A);
    }

    public static void generateSubSets(ArrayList<Integer> A, int i, ArrayList<Integer> res){
        if(i == A.size()){
            ans.add(new ArrayList<>(res));
            return;
        }

        res.add(A.get(i));
        generateSubSets(A, i + 1, res);

        res.remove(res.size() - 1);
        generateSubSets(A, i + 1, res);
    }
}
