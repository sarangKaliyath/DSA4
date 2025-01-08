package DynamicProgramming;

/*
Problem Description
Given two integer arrays A and B of size N each which represent
values and weights associated with N items respectively.
Also given an integer C which represents knapsack capacity.
Find out the maximum total value that we can fit in the knapsack.
If the maximum total value is ans, then return ⌊ans × 100⌋ , i.e., floor of (ans × 100).
NOTE: You can break an item for maximizing the total value of the knapsack

Problem Constraints
1 <= N <= 10^5
1 <= A[i], B[i] <= 10^3
1 <= C <= 10^3

Input Format:
First argument is an integer array A of size N denoting the values on N items.
Second argument is an integer array B of size N denoting the weights on N items.
Third argument is an integer C denoting the knapsack capacity.

Output Format:
Return a single integer denoting the maximum total value of A
such that sum of the weights of this subset is smaller than or equal to C.

Example Input:
Input 1:
 A = [60, 100, 120]
 B = [10, 20, 30]
 C = 50

Input 2:
 A = [10, 20, 30, 40]
 B = [12, 13, 15, 19]
 C = 10

Example Output:
Output 1: 24000
Output 2: 2105

Example Explanation:
Explanation 1:
Taking the full items with weight 10 and 20 and 2/3 of the item with weight 30 will give us
the maximum value i.e 60 + 100 + 80 = 240. So we return 24000.

Explanation 2:
Taking 10/19 the fourth item gives us the maximum value i.e. 21.0526. So we return 2105.

 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class FractionalKnapsack {

    public static class Items {
        public int values;
        public int weights;

        public Items(int values, int weights) {
            this.values = values;
            this.weights = weights;
        }
    }

    public static void main(String[] args) {

        ArrayList<Integer> A = new ArrayList<>(Arrays.asList(60, 100, 120));
        ArrayList<Integer> B = new ArrayList<Integer>(Arrays.asList(10, 20, 30));
        int C = 50;
        int n = A.size();


        ArrayList<Items> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            list.add(new Items(A.get(i), B.get(i)));
        }

        // 0.0000001 for precision in floating-point calculations, which is smaller by one;
        int res = (int) (find(list, n, C) * 100 + 0.0000001);
        System.out.println(res);
        // Time O(NlogN);
        // Space O(N);
    }


    public static double find(ArrayList<Items> list, int n, int capacity) {
        list.sort((x, y) -> {
            double ratio1 = (double) x.values / x.weights;
            double ratio2 = (double) y.values / y.weights;
            return Double.compare(ratio2, ratio1);
        });

        double ans = 0;

        for (Items item : list) {
            int value = item.values;
            int weight = item.weights;

            if (capacity >= weight) {
                capacity -= weight;
                ans += value;
            } else {
                ans += (double) capacity / weight * value;
                capacity = 0;
            }
        }

        return ans;
    }


}
