package DynamicProgramming;

/*
Problem Description
You are trying to send signals to aliens using a linear array of A laser lights.
You don't know much about how the aliens are going to perceive the signals,
but what you know is that if two consecutive lights are on then the aliens might
take it as a sign of danger and destroy the earth.
Find and return the total number of ways in which you can send a signal without
compromising the safety of the earth. Return the ans % 109 + 7.

Problem Constraints
1 <= A <= 10^5

Input Format:
The only argument given is integer A.

Output Format:
Return the ans%(109+7).

Example Input:
Input 1: A = 2
Input 2: A = 3

Example Output:
Output 1: 3
Output 2: 5

Example Explanation:
Explanation 1:
 OFF OFF
 OFF ON
 ON OFF
All lights off is also a valid signal which probably means 'bye'

Explanation 2:
 OFF OFF OFF
 OFF OFF ON
 OFF ON OFF
 ON OFF OFF
 ON OFF ON

 */

import java.util.Arrays;

public class WaysToSendTheSignal {

    public static void main (String [] args){
        int A = 26;

        int res = bruteForce(A, 0, 0);
        System.out.println(res);
        // Time O(2^A);
        // Space O(A);

        int [][] dp = new int[A + 1][2];
        for(int [] row : dp){
            row[0] = -1;
            row[1] = -1;
        }
        int ans = recursive(A, 0, 0, dp);
        System.out.println(ans);
        // Time O(A);
        // Space O(A);

        int val = iterative(A);
        System.out.println(val);
        // Time O(A);
        // Space O(A);
    }

    public static int MOD = (int) Math.pow(10, 9) + 7;

    public static int bruteForce(int n, int i, int prevState){
        if(i == n) return 1;

        int res = 0;

        if(prevState == 0){
            res += bruteForce(n,i + 1, 0) + bruteForce(n,i + 1, 1);

        }
        else res += bruteForce(n,i + 1 , 0);

        return res % MOD;
    }

    public static int recursive(int n, int i, int prevState, int [][] dp){
        if(i == n) return 1;

        if(dp[i][prevState] != -1) return dp[i][prevState];

        int res = 0;

        if(prevState == 0){
            res += recursive(n,i + 1, 0, dp) + recursive(n,i + 1, 1, dp);

        }
        else res += recursive(n,i + 1 , 0, dp);

        dp[i][prevState] = res % MOD;

        return dp[i][prevState];
    }

    public static int iterative(int n){
        int [][] dp = new int[n + 1][2];
        dp[0][0] = 1;
        dp[0][1] = 0;

        for(int i = 1; i <= n; i++){
            dp[i][0] = (dp[i - 1][0] + dp[i - 1][1]) % MOD;
            dp[i][1] = dp[i - 1][0] % MOD;
        }

        return (dp[n][0] + dp[n][1]) % MOD;
    }
}
