package Heaps;

/*
Problem Description:
In the recent expansion into grocery delivery,
Flipkart faces a crucial challenge in effective inventory management.
Each grocery item on the platform carries its own expiration date and profit margin,
represented by two arrays, A and B of size N.
A[i] denotes the time left before expiration date for the ith item,
and B[i] denotes profit margin for the ith item.
To mitigate potential losses due to expiring items, Flipkart is seeking a strategic solution.
The objective is to identify a method to strategically buy certain items,
ensuring they are sold before their expiration date,
thereby maximizing overall profit.
Can you assist Flipkart in developing an innovative approach to optimize their grocery inventory and enhance profitability?
Your task is to find the maximum profit one can earn by buying groceries
considering that you can only buy one grocery item at a time.

NOTE:
    You can assume that it takes 1 minute to buy a grocery item, so you can only buy the ith grocery item when the current time <= A[i] - 1.
    You can start buying from day = 0.
    Return your answer modulo 109 + 7.

Problem Constraints
1 <= N <= 10^5
1 <= A[i] <= 10^9
0 <= B[i] <= 10^9

Input Format:
The first argument is an integer array A represents the deadline for buying the grocery items.
The second argument is an integer array B represents the profit obtained after buying the grocery items.

Output Format:
Return an integer denoting the maximum profit you can earn.

Example Input:
Input 1:
 A = [1, 3, 2, 3, 3]
 B = [5, 6, 1, 3, 9]

Input 2:
 A = [3, 8, 7, 5]
 B = [3, 1, 7, 19]

Example Output:
Output 1: 20
Output 2: 30

Example Explanation:
Explanation 1:
 At time 0, buy item with profit 5.
 At time 1, buy item with profit 6.
 At time 2, buy item with profit 9.
 At time = 3 or after , you can't buy any item, as there is no item with deadline >= 4.
 So, total profit that one can earn is 20.

Explanation 2:
 At time 0, buy item with profit 3.
 At time 1, buy item with profit 1.
 At time 2, buy item with profit 7.
 At time 3, buy item with profit 19.
 We are able to buy all items within their deadline. So, total profit that one can earn is 30.

 */

import java.util.*;

public class FlipKartsChallengeInEffectiveInventoryManagement {

    public static void main(String[] args) {
        ArrayList<Integer> A = new ArrayList<>(Arrays.asList(1, 3, 2, 3, 3));
        ArrayList<Integer> B = new ArrayList<>(Arrays.asList(5, 6, 1, 3, 9));

        int res = bruteForce(A, B, A.size());
        System.out.println(res);

        int ans = optimized(A, B, A.size());
        System.out.println(ans);
    }

    public static class Items {
        int expiryTime;
        int itemValue;

        Items(int expiryTime, int itemValue) {
            this.expiryTime = expiryTime;
            this.itemValue = itemValue;
        }
    }

    public static int optimized(ArrayList<Integer> A, ArrayList<Integer> B, int n) {
        ArrayList<Items> arr = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Items newItem = new Items(A.get(i), B.get(i));
            arr.add(newItem);
        }

        Collections.sort(arr, (a, b) -> a.expiryTime - b.expiryTime);

        PriorityQueue<Items> minH = new PriorityQueue<>((a, b) -> Integer.compare(a.itemValue, b.itemValue));

        int time = 0;
        int profit = 0;

        for (Items item : arr) {
            if (time < item.expiryTime) {
                minH.add(item);
                profit += item.itemValue;
                time++;
            } else if (!minH.isEmpty() && item.itemValue > minH.peek().itemValue) {
                profit -= minH.poll().itemValue;
                minH.add(item);
                profit += item.itemValue;
            }
        }

        return profit;
    }

    public static int bruteForce(ArrayList<Integer> A, ArrayList<Integer> B, int n) {
        int MOD = (int) Math.pow(10, 9) + 7;

        ArrayList<ArrayList<Integer>> res = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            res.add(new ArrayList<Integer>(Arrays.asList(A.get(i), B.get(i))));
        }

        res.sort(new Comparator<ArrayList<Integer>>() {
            @Override
            public int compare(ArrayList<Integer> a, ArrayList<Integer> b) {
                return b.get(1) - a.get(1);
            }
        });

        int maxDeadline = 0;
        for (int i = 0; i < n; i++) {
            maxDeadline = Math.max(maxDeadline, res.get(i).get(0));
        }

        boolean[] cart = new boolean[maxDeadline];

        int profit = 0;

        for (int i = 0; i < n; i++) {
            int expTime = res.get(i).get(0);
            int cost = res.get(i).get(1);

            for (int j = expTime - 1; j >= 0; j--) {
                if (!cart[j]) {
                    cart[j] = true;
                    profit = (profit + cost) % MOD;
                    break;
                }
            }
        }

        return profit;
    }

}
