package DynamicProgramming;
/*
Problem Description
Rishik likes candies a lot. So, he went to a candy-shop to buy candies.
The shopkeeper showed him N packets each containing A[i] candies for cost of C[i] nibbles,
each candy in that packet has a sweetness B[i].
The shopkeeper puts the condition that Rishik can buy as many complete candy-packets as he wants,
but he can't buy a part of the packet.
Rishik has D nibbles, can you tell him the maximum amount of sweetness he can get from candy-packets he will buy?

Problem Constraints
1 <= N <= 700
1 <= A[i] <= 1000
1 <= B[i] <= 1000
1 <= C[i],D <= 1000

Input Format
First argument of input is an integer array A
Second argument of input is an integer array B
Third argument of input is an integer array C
Fourth argument of input is an integer D

Output Format
Return a single integer denoting maximum sweetness of the candies that he can buy

Example Input:
Input 1:
 A = [1, 2, 3]
 B = [2, 2, 10]
 C = [2, 3, 9]
 D = 8

Input 2:
 A = [2]
 B = [5]
 C = [10]
 D = 99

Example Output:
Output 1: 10
Output 2: 90

Example Explanation:
Explanation 1:
 Choose 1 Packet of kind 1 = 1 Candy of 2 Sweetness = 2 Sweetness
 Choose 2 Packet of kind 2 = 2 Candy of 2 Sweetness = 8 Sweetness

Explanation 2:
 Buy 9 Packet of kind 1. 18 Candy each of 5 Sweetness = 90 Sweetness
 */

import java.util.ArrayList;
import java.util.Arrays;

public class BuyingCandies {

    public static class Store {
        public int candies;
        public int sweetness;
        public int nibbles;

        public Store(int candies, int sweetness, int nibbles) {
            this.candies = candies;
            this.sweetness = sweetness;
            this.nibbles = nibbles;
        }
    }

    public static void main(String[] args) {
        ArrayList<Integer> A = new ArrayList<>(Arrays.asList(2, 4));
        ArrayList<Integer> B = new ArrayList<>(Arrays.asList(4, 5));
        ArrayList<Integer> C = new ArrayList<>(Arrays.asList(6, 13));
        int D = 18;
        int n = A.size();

        ArrayList<Store> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(new Store(A.get(i), B.get(i), C.get(i)));
        }

        int ans = bruteForce(list, n - 1, D);
        System.out.println(ans);
        // Time O(2^N);
        // Space O(N);

        int [][] dp = new int [n][D + 1];
        for(int [] row : dp) Arrays.fill(row, -1);
        int val = recursive(list, n - 1, D, dp);
        System.out.println(val);
        // Time O(N * D);
        // Space O(N * D);

        int res = iterative(list, D);
        System.out.println(res);
        // Time O(N * D);
        // Space O(D);
    }

    public static int bruteForce(ArrayList<Store> list, int i, int capacity) {
        if(i < 0 || capacity <= 0) return 0;

        Store item = list.get(i);
        int reject = bruteForce(list, i - 1, capacity);
        int selected = 0;
        if(capacity >= item.nibbles){
            selected = bruteForce(list, i, capacity - item.nibbles) + (item.candies * item.sweetness);
        }

        return Math.max(reject, selected);
    }

    public static int recursive(ArrayList<Store> list, int i, int capacity, int [][] dp){
        if(i < 0 || capacity <= 0) return 0;

        if(dp[i][capacity] != -1) return dp[i][capacity];

        Store item = list.get(i);
        int rejected = recursive(list, i - 1, capacity, dp);

        int selected = 0;
        if(capacity >= item.nibbles){
            selected = recursive(list, i, capacity - item.nibbles, dp) + (item.candies * item.sweetness);
        }

        dp[i][capacity] = Math.max(selected, rejected);

        return dp[i][capacity];
    }

    public static int iterative(ArrayList<Store> list, int capacity){
        int [] dp = new int [capacity + 1];

        for(Store item : list){
            for(int w = item.nibbles; w <= capacity; w++){
                dp[w] = Math.max(dp[w], dp[w - item.nibbles] + item.candies * item.sweetness);
            }
        }

        return dp[capacity];
    }
}
