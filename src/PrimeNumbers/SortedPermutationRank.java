package PrimeNumbers;

/*
Problem Description
Given a string A. Find the rank of the string amongst its permutations sorted lexicographically.
Assume that no characters are repeated.
Note: The answer might not fit in an integer, so return your answer % 1000003

Problem Constraints
1 <= |A| <= 1000

Input Format:
First argument is a string A.

Output Format:
Return an integer denoting the rank of the given string.

Example Input:
Input 1: A = "acb"
Input 2: A = "a"

Example Output:
Output 1: 2
Output 2: 1

Example Explanation:

Explanation 1:
Given A = "acb".
The order permutations with letters 'a', 'c', and 'b' :
abc
acb
bac
bca
cab
cba
So, the rank of A is 2.

Explanation 2:
Given A = "a".
Rank is clearly 1.
 */
public class SortedPermutationRank {

    public static void main(String[] args) {
        String A = "cab";
        int MOD = 1000003;
        int res = find(A, A.length(), MOD);
        System.out.println(res);
        // Time O(N^2);
        // Space O(N);
    }

    public static int find(String str, int n, int MOD) {
        int[] factorials = new int[n];
        factorials[0] = 1;

        for (int i = 1; i < n; i++) {
            factorials[i] = (factorials[i - 1] * i) % MOD;
        }

        int res = 1;
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = i + 1; j < n; j++) {
                if (str.charAt(j) < str.charAt(i)) count++;
            }
            res = (res + (count * factorials[n - i - 1]) % MOD) % MOD;
        }
        return res;
    }
}
