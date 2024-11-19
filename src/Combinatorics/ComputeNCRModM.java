package Combinatorics;
/*
Problem Description
Given three integers A, B, and C, where A represents n, B represents r,
and C represents m, find and return the value of nCr % m where nCr % m = (n!/((n-r)!*r!))% m.
x! means factorial of x i.e. x! = 1 * 2 * 3... * x.

Problem Constraints
1 <= A * B <= 10^6
1 <= B <= A
1 <= C <= 10^6

Input Format
The first argument given is integer A ( = n).
The second argument given is integer B ( = r).
The third argument given is integer C ( = m).

Output Format
Return the value of nCr % m.

Example Input:
Input 1:
 A = 5
 B = 2
 C = 13

Input 2:
 A = 6
 B = 2
 C = 13

Example Output:
Output 1: 10
Output 2: 2

Example Explanation:
Explanation 1:
 The value of 5C2 % 11 is 10.
Explanation 2:
 The value of 6C2 % 13 is 2.
 */

public class ComputeNCRModM {
    public static void main(String[] args) {
        int A = 30;
        int B = 24;
        int C = 56;

        // This approach won't work since values are very large causing overflow;
//        int res = find(A, B, C);
//        System.out.println(res);

        // Using DP
        int res = getNCR(A, B, C);
        System.out.println(res);
        // Time O(A * B);
        // Space O(A * B);
    }

    public static int getNCR(int n, int r, int m){
        int [][] nCr = new int [n + 1][r + 1];

        for(int i = 0; i <= n; i++){
            for(int j = 0; j <= Math.min(i, r); j++){
                if(j == 0 || j == i) nCr[i][j] = 1;
                else {
                    nCr[i][j] = (nCr[i - 1][j - 1] + nCr[i - 1][j]) % m;
                }
            }
        }

        return nCr[n][r];
    }

    public static int find(int n, int r, int m) {
        long prod = (getFactorial(n - r) * getFactorial(r)) % m;
        long val = (getFactorial(n) / prod) % m;
        return  (int) val;
    }

    public static long getFactorial(long x){
        if(x == 0 || x == 1) return 1;
        long res = 1;
        for(int i = 1; i <= x; i++){
            res *= i;
        }
        return  res;
    }
}
