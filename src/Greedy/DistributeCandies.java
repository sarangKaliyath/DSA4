package Greedy;

/*
Problem Description:
N children are standing in a line. Each child is assigned a rating value.
You are giving candies to these children subjected to the following requirements:
    Each child must have at least one candy.
    Children with a higher rating get more candies than their neighbors.
What is the minimum number of candies you must give?

Problem Constraints
1 <= N <= 10^5
-10^9 <= A[i] <= 10^9

Input Format:
The first and only argument is an integer array A representing the rating of children.

Output Format:
Return an integer representing the minimum candies to be given.

Example Input:
Input 1: A = [1, 2]
Input 2: A = [1, 5, 2, 1]

Example Output:
Output 1: 3
Output 2: 7

Example Explanation:
Explanation 1:
 The candidate with 1 rating gets 1 candy and candidate
 with rating 2 cannot get 1 candy as 1 is its neighbor.
 So rating 2 candidate gets 2 candies. In total, 2 + 1 = 3 candies need to be given out.

Explanation 2:
 Candies given = [1, 3, 2, 1]Problem Description

 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class DistributeCandies {
    public static void main(String[] args) {
        ArrayList<Integer> A = new ArrayList<>(Arrays.asList(1, 2));
        int res = find(A, A.size());
        System.out.println(res);
        // Time O(N);
        // Space 0(N);
    }

    public static int find(ArrayList<Integer> A, int n) {
        ArrayList<Integer> res = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            res.add(1);
        }

        for (int i = 1; i < n; i++) {
            if (A.get(i) > A.get(i - 1)) {
                res.set(i, res.get(i - 1) + 1);
            }
        }

        for (int i = n - 2; i >= 0; i--) {
            if (A.get(i) > A.get(i + 1)) {
                int updatedCandyCount = Math.max(res.get(i), res.get(i + 1) + 1);
                res.set(i, updatedCandyCount);
            }
        }

        return res.stream().mapToInt(Integer::intValue).sum();
    }
}
