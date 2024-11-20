package PrimeNumbers;

import java.util.ArrayList;
import java.util.Arrays;

/*
Problem Description
Given an integer A. Find the list of all prime numbers in the range [1, A].

Problem Constraints
1 <= A <= 10^6

Input Format:
Only argument A is an integer.

Output Format:
Return a sorted array of prime number in the range [1, A].

Example Input:
Input 1: A = 7
Input 2: A = 12

Example Output:
Output 1: [2, 3, 5, 7]
Output 2: [2, 3, 5, 7, 11]

Example Explanation:
For Input 1:
The prime numbers from 1 to 7 are 2, 3, 5 and 7.
For Input 2:
The prime numbers from 1 to 12 are 2, 3, 5, 7 and 11.

 */
public class FindAllPrimes {

    public static  void main(String [] args){
        int A = 7;
        ArrayList<Integer> res = find(A);
        System.out.println(res);
        // Time O( A(logA) ); ..... exact time is O( A(log(log A)) );
        // Space O(A);
    }

    public static ArrayList<Integer> find(int A){

        boolean [] sieve = new boolean [A + 1];
        Arrays.fill(sieve, true);
        sieve[0] = false;
        sieve[1] = false;

        for(int i = 2; i * i <= A; i++){
            if(sieve[i]){
                for(int j = i * i; j <= A; j += i){
                    sieve[j] = false;
                }
            }
        }

        ArrayList<Integer> res = new ArrayList<>();
        for(int i = 2; i <= A; i++){
            if(sieve[i]) res.add(i);
        }

        return  res;
    }
}
