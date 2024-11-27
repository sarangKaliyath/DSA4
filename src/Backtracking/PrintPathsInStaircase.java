package Backtracking;

import java.util.ArrayList;

/*
Problem Description
You are climbing a staircase, and it takes A steps to reach the top.
Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
You need to return all the distinct ways to climb to the top in lexicographical order.

Problem Constraints
2 <= A <= 25

Input Format:
The first and the only argument contains an integer A, the number of steps.

Output Format:
Return a 2-D Integer Array, which denotes all the unique valid paths to reach the top.


Example Input:
Input 1: A = 2
Input 2: A = 3

Example Output:
Output 1: [ [1, 1], [2] ]
Output 2: [ [1, 1, 1], [1, 2], [2, 1] ]

Example Explanation:
Explanation 1:
 Distinct ways to reach top: 1 + 1, 2.
Explanation 2:
 Distinct ways to reach top: 1 + 1 + 1, 1 + 2, 2 + 1.
 */
public class PrintPathsInStaircase {
    public static ArrayList<ArrayList<Integer>> ans = new ArrayList<>();

    public static void main(String[] args) {
        int A = 3;
        generate(A, new ArrayList<Integer>());
        System.out.println(ans);
        // Time O(2^A);
        // Space O(A);
    }

    public static void generate(int stepsToClimb, ArrayList<Integer> res) {
        if (stepsToClimb == 0) {
            ans.add(new ArrayList<>(res));
            return;
        }

        if (stepsToClimb >= 1) {
            res.add(1);
            generate(stepsToClimb - 1, res);
            res.remove(res.size() - 1);
        }

        if (stepsToClimb >= 2) {
            res.add(2);
            generate(stepsToClimb - 2, res);
            res.remove(res.size() - 1);
        }
    }
}
