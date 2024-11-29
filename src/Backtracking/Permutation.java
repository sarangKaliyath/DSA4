package Backtracking;

import java.util.ArrayList;
import java.util.Arrays;

/*
Problem Description
Given an integer array A of size N denoting collection of numbers, return all possible permutations.

NOTE:
No two entries in the permutation sequence should be the same.
For the purpose of this problem, assume that all the numbers in the collection are unique.
Return the answer in any order

WARNING: DO NOT USE LIBRARY FUNCTION FOR GENERATING PERMUTATIONS.
Example : next_permutations in C++ / itertools.permutations in python.
If you do, we will disqualify your submission retroactively and give you penalty points.

Problem Constraints
1 <= N <= 9

Input Format
Only argument is an integer array A of size N.

Output Format
Return a 2-D array denoting all possible permutation of the array.

Example Input: A = [1, 2, 3]

Example Output:
[ [1, 2, 3]
  [1, 3, 2]
  [2, 1, 3]
  [2, 3, 1]
  [3, 1, 2]
  [3, 2, 1] ]

Example Explanation
All the possible permutation of array [1, 2, 3].
 */
public class Permutation {

    // on online platforms avoid using static. static variables are shared across all
    // instances and method calls, potentially leading to leftover data from previous runs.
    private static ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
    public static void main(String[] args) {
        ArrayList<Integer> A = new ArrayList<>(Arrays.asList(1, 2, 3));
        generatePermutations(A, 0, new boolean[A.size()], new ArrayList<>());
        System.out.println(ans);
        // Time O(A * A!);
        // Space O(A);
    }

    public static void generatePermutations(ArrayList<Integer> A, int i, boolean[] visiblity, ArrayList<Integer> res) {
        if (res.size() == A.size()) {
            ans.add(new ArrayList<>(res));
            return;
        }

        for (int j = 0; j < A.size(); j++) {
            if (!visiblity[j]) {
                res.add(A.get(j));
                visiblity[j] = true;
                generatePermutations(A, i + 1, visiblity, res);
                visiblity[j] = false;
                res.remove(res.size() - 1);
            }
        }
    }
}
