package DynamicProgramming;

/*
Problem Description:
As it is Tushar's Birthday on March 1st,
he decided to throw a party to all his friends at TGI Fridays in Pune.
Given are the eating capacity of each friend,
filling capacity of each dish and cost of each dish.
A friend is satisfied if the sum of the filling capacity
of dishes he ate is equal to his capacity.
Find the minimum cost such that all of Tushar's
friends are satisfied (reached their eating capacity).

NOTE:
Each dish is supposed to be eaten by only one person. Sharing is not allowed.
Each friend can take any dish unlimited number of times.
There always exists a dish with filling capacity 1 so that a solution always exists.

Problem Constraints:
|A| <= 1000
|B| <= 1000
|C| <= 1000

Input Format:
First Argument is vector A, denoting eating capacities
Second Argument is vector B, denoting filling capacities
Third Argument is vector C, denoting cost

Output Format
Return a single integer, the answer to the problem

Example Input:
Input 1:
A = [2, 4, 6]
B = [2, 1, 3]
C = [2, 5, 3]

Input 2:
A = [2]
B = [1]
C = [2]

Example Output:
Output 1: 12
Output 2: 4

Example Explanation:
Explanation 1:
First friend takes dish 1, Second friend takes dish 1 twice and third friend takes dish 3 twice.
So 2 + 2*2 + 3*2 = 12.

Explanation 2:
Only way is to take 2 dishes of cost 2, hence 4.

 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class TusharsBirthdayParty {

    public static class Abilities {
        public int eatingCap;
        public int fillingCap;
        public int cost;

        public Abilities(int eatingCap, int fillingCap, int cost){
            this.eatingCap = eatingCap;
            this.fillingCap = fillingCap;
            this.cost = cost;
        }
    }
    public  static void main (String [] args){
        ArrayList<Integer> A = new ArrayList<>(Arrays.asList(2, 4, 6));
        ArrayList<Integer> B = new ArrayList<>(Arrays.asList(2, 1, 3));
        ArrayList<Integer> C = new ArrayList<>(Arrays.asList(2, 5, 3));
        int n = A.size();

        int res = bruteForce(A, B, C);
        System.out.println(res);
        // Time O(Number of friends * Number of dishes * Max eating capacity of each friend);
        // Space O(Max eating capacity of friend);

        int ans = optimizeRecursive(A, B, C);
        System.out.println(ans);
        // Time O(Max(A) * Number of Dishes available);
        // Space O(Max(A));

        int val = iterative(A, B, C);
        System.out.println(val);
    }

    public static int bruteForce(ArrayList<Integer> A, ArrayList<Integer> B, ArrayList<Integer> C){
        int totalCost = 0;

        for(int capacity : A){
            totalCost += minCostForEachFriend(B, C, capacity);
        }

        return totalCost;
    }

    public static int minCostForEachFriend(ArrayList<Integer> B, ArrayList<Integer> C, int capacity){
        if(capacity <= 0) return 0;

        int minCost = Integer.MAX_VALUE;

        for(int i = 0; i < B.size(); i++){
            if(capacity >= B.get(i)){
                int cost = C.get(i) + minCostForEachFriend(B, C, capacity - B.get(i));
                minCost = Math.min(minCost, cost);
            }
        }

        return minCost;
    }


    public static int optimizeRecursive(ArrayList<Integer> A, ArrayList<Integer> B, ArrayList<Integer> C){

        int maxCap = 0;
        for(int cap : A){
            maxCap = Math.max(cap, maxCap);
        }

        int [] dp = new int [maxCap + 1];
        Arrays.fill(dp, -1);

        int totalCost = 0;

        for(int capacity : A){
            totalCost += findMinOptimizedRecursive(B, C, capacity, dp);
        }

        return totalCost;
    }

    public static int findMinOptimizedRecursive(ArrayList<Integer> B, ArrayList<Integer> C, int capacity, int [] dp){
        if(capacity <= 0) return 0;

        if(dp[capacity] != -1) return  dp[capacity];

        int minCost = Integer.MAX_VALUE;

        for(int i = 0; i < B.size(); i++){
            if(capacity >= B.get(i)){
                int cost = findMinOptimizedRecursive(B, C, capacity - B.get(i), dp) + C.get(i);
                minCost = Math.min(cost, minCost);
            }
        }

        dp[capacity] = minCost;

        return dp[capacity];
    }

    public static int iterative(ArrayList<Integer> A, ArrayList<Integer> B, ArrayList<Integer> C){
        int maxCapacity = Collections.max(A);

        int [] dp = new int[maxCapacity + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for(int i= 0; i < B.size(); i++){
            int fillingCap = B.get(i);
            int cost = C.get(i);

            for(int j = fillingCap; j <= maxCapacity; j++){
                if(dp[j - fillingCap] != Integer.MAX_VALUE){
                    dp[j] = Math.min(dp[j], dp[j - fillingCap] + cost);
                }
            }
        }

        int totalCost = 0;
        for(int capacity : A){
            totalCost += dp[capacity];
        }

        return totalCost;
    }
}
