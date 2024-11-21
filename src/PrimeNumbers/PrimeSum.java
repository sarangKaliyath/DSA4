package PrimeNumbers;

import java.util.ArrayList;
import java.util.Arrays;

/*
Problem Description:
Given an even number A (greater than 2), return two prime numbers whose sum will be equal to the given number.
If there is more than one solution possible, return the lexicographically smaller solution.
If [a, b] is one solution with a <= b, and [c,d] is another solution with c <= d, then
[a, b] < [c, d], If a < c OR a==c AND b < d.
NOTE: A solution will always exist. Read Goldbach's conjecture.

Problem Constraints
4 <= A <= 2*10^7

Input Format:
First and only argument of input is an even number A.

Output Format:
Return an integer array of size 2 containing primes whose sum will be equal to given number.

Example Input: 4

Example Output: [2, 2]
Example Explanation There is only 1 solution for A = 4.

 */
public class PrimeSum {

    public static void main(String[] args) {
        int A = 1048574;
        ArrayList<Integer> res = findSum(A);
        System.out.println(res);
        // Time O( A(log(log A)) );
        // Space O(A);
    }

    public static ArrayList<Integer> findSum(int A) {
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

        for (int i = 2; i <= A / 2; i++) {
            {
                if (isPrime[i] && isPrime[A - 1]) {
                    return new ArrayList<>(Arrays.asList(i, A - 1));
                }
            }
        }
        return new ArrayList<>();
    }
}
