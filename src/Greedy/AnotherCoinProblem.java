package Greedy;

/*
Problem Description:
The monetary system in DarkLand is really simple and systematic.
The locals-only use coins. The coins come in different values. The values used are:
 1, 5, 25, 125, 625, 3125, 15625, ...
Formally, for each K >= 0 there are coins worth 5K.
Given an integer A denoting the cost of an item,
find and return the smallest number of coins necessary to pay exactly the cost
of the item (assuming you have a sufficient supply of coins of each of the types you will need).

Problem Constraints
1 <= A <= 2×10^9

Input Format
The only argument given is integer A.

Output Format
Return the smallest number of coins necessary to pay exactly the cost of the item.

Example Input:
Input 1:
 A = 47
Input 2:
 A = 9

Example Output:
Output 1: 7
Output 2: 5

Example Explanation:
Explanation 1:
 Representation of 7 coins will be : (1 + 1 + 5 + 5 + 5 + 5 + 25).
Explanation 2:
 Representation of 5 coins will be : (1 + 1 + 1 + 1 + 5).
 */

public class AnotherCoinProblem {

    public static void main(String[] args) {
        int A = 47;

        int res = find(A);
        System.out.println(res);
        // Time O(logA * logA);
        // Space O(1);
    }

    public static int find(int A) {
        if(A % 5 == 0) return  1;
        int count = 0;
        while(A > 0){
            int availableDenomination = findNearestPower(A);
            A -= availableDenomination;
            count++;
        }
        return count;
    }

    public  static  int findNearestPower(int A){
        int ans = 0; int prev = 0;
        int pow = 0;
        while(ans <= A){
            prev = ans;
            ans = (int) Math.pow(5, pow);
            pow++;
        }
        return prev;
    }
}
