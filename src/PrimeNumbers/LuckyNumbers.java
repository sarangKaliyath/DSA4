package PrimeNumbers;

import java.util.Arrays;

/*
Problem Description
A lucky number is a number that has exactly 2 distinct prime divisors.
You are given a number A, and you need to determine the count of lucky numbers between the range 1 to A (both inclusive).

Problem Constraints
1 <= A <= 50000

Input Format:
The first and only argument is an integer A.

Output Format:
Return an integer i.e the count of lucky numbers between 1 and A, both inclusive.

Example Input:
Input 1: A = 8
Input 2: A = 12

Example Output:
Output 1: 1
Output 2: 3

Example Explanation:
Explanation 1:
 Between [1, 8] there is only 1 lucky number i.e 6.
 6 has 2 distinct prime factors i.e 2 and 3.

Explanation 2:
 Between [1, 12] there are 3 lucky number: 6, 10 and 12.
 */
public class LuckyNumbers {
    public static void main(String[] args) {
        int A = 12;
        int res = findLuckyNumber(A);
        System.out.println(res);
        // Time O(N^2);
        // Space O(A);

        int ans = optimized(A);
        System.out.println(ans);
        // Time O(A logA);
        // Space O(A);
    }

    public static int optimized(int A) {
        int res = 0;
        if (A <= 2) return res;

        int[] prime = new int[50001];
        prime[0] = 1;
        prime[1] = 1;

        for (int i = 2; i <= A; i++) {
            if (prime[i] == 0) {
                for (int j = i; j <= A; j += i) {
                    if (i != j) prime[j]++;
                }
            }
        }

        for (int i = 2; i <= A; i++) {
            if (prime[i] == 2) res++;
        }

        return res;
    }

    public static int findLuckyNumber(int A) {
        int res = 0;
        if (A <= 2) return res;

        boolean[] isPrime = new boolean[A + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;

        for (int i = 2; i * i <= A; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= A; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        for (int i = 2; i <= A; i++) {
            int count = 0;
            for (int j = 2; j <= i; j++) {
                if (isPrime[j] && i % j == 0) count++;
            }
            if (count == 2) res++;
        }

        return res;
    }

}
