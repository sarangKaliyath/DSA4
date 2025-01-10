package DynamicProgramming;

import java.util.Arrays;

/*
Problem Description:
In Danceland, one person can party either alone or can pair up with another person.
Can you find in how many ways they can party if there are A people in Danceland?
Note: Return your answer modulo 10003, as the answer can be large.

Problem Constraints
1 <= A <= 10^5

Input Format:
Given only argument A of type Integer, number of people in Danceland.

Output Format:
Return an integer denoting the number of ways people of Danceland can party.

Example Input:
Input 1: A = 3
Input 2: A = 5

Example Output:
Output 1: 4
Output 2: 26

Example Explanation

Explanation 1:
 Let suppose three people are A, B, and C. There are only 4 ways to party
 (A, B, C) All party alone
 (AB, C) A and B party together and C party alone
 (AC, B) A and C party together and B party alone
 (BC, A) B and C party together and A
 here 4 % 10003 = 4, so answer is 4.

Explanation 2:
 Number of ways they can party are: 26.
 */
public class LetsParty {

    private static int MOD = 10003;

    public static void main (String [] args){
        int A = 5;

        int res = bruteForce(A);
        System.out.println(res);
        // Time O(2^A);
        // Space O(A);

        int [] dp = new int [A + 1];
        Arrays.fill(dp, -1);
        int ans = recursive(A, dp);
        System.out.println(ans);
        // Time O(A);
        // Space O(A);

        int val = iterative(A);
        System.out.println(val);
        // Time O(A);
        // Space O(1);
    }

    public  static int bruteForce(int n){
        if(n == 0 || n == 1) return 1;

        int reject = (n - 1) * bruteForce(n - 2);
        int select = bruteForce(n - 1);

        return (reject + select) % MOD;
    }

    public static int recursive(int n, int [] dp) {
        if (n == 0 || n == 1) return 1;

        if (dp[n] != -1) return dp[n];

        int reject = (n - 1) * recursive(n - 2, dp);
        int select = recursive(n - 1, dp);
        dp[n] =  (reject + select) % MOD;

        return dp[n];
    }

    public static int iterative(int n){
        if(n == 0 || n == 1) return 1;

        int prev1 = 1;
        int prev2 = 1;
        int curr = 0;

        for(int i = 2; i <= n; i++){
            curr = (prev1 + (i - 1) * prev2) % MOD;
            prev2 = prev1;
            prev1 = curr;
        }

        return curr;
    }
}

