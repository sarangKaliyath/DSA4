package MiscellaneousLabProblems;
/*
Problem Description:
You are given an array of non-negative integers A,
where each element in the array represents the maximum
number of steps you can jump forward from that position.
A jump means moving from the current index to another
index further in the array within the allowed range of
steps defined by the value at the current index.
For instance, if A[i] = 3, you can jump to any of the
next 3 positions from index i (i.e., i+1, i+2, or i+3),
provided these positions are within the bounds of the array.
Your task is to determine the minimum number of jumps
required to reach the last index of the array, starting from the first index.
If it's not possible to reach the last index, return -1.

Problem Constraints
1 ≤ ∣A∣ ≤ 105
0 ≤ A[i] ≤105

Input Format:
A single argument: an Integer array A of size N

Output Format:
Return an integer representing the minimum number of jumps needed to reach the last index of the array.
If it's not possible to reach the last index, return -1.

Example Input:
Input 1: A = [2, 3, 1, 1, 4]
Input 2: A = [0, 1, 1, 4]

Example Output:
Output 1: 2
Output 2: -1

Example Explanation:
Explanation 1:
The first element A[0] = 2 means you can jump to index 1 or index 2.
From index 1 (A[1] = 3), you can jump to index 2, index 3, or index 4.
The optimal strategy is:
Jump from index 0 to index 1.
From index 1, jump directly to index 4 (the last index).
Therefore, the minimum number of jumps required is 2.

Explanation 2:
Since the first index has a value of 0, meaning you cannot move forward from there.

 */

import java.util.ArrayList;
import java.util.Arrays;

public class JumpGame2 {
    public static void main(String[] args) {
        ArrayList<Integer> A = new ArrayList<>(Arrays.asList(2, 3, 1, 1, 4));
        int res = getMinJumps(A);
        System.out.println(res);
    }

    public static int getMinJumps(ArrayList<Integer> A) {
        int left = 0;
        int right = 0;
        int jumpCount = 0;

        while (right < A.size() - 1) {

            int max = Integer.MIN_VALUE;

            for (int i = left; i <= right; i++) {
                max = Math.max(max, A.get(i) + i);
            }

            if (max == right) return -1;

            jumpCount++;
            right = max;
            left++;

        }

        return jumpCount;
    }

}
