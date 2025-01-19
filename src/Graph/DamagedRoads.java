package Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

/*
Problem Description:
You are the Prime Minister of a country and once you went for a world tour.
After 5 years, when you returned to your country, you were shocked to see
the condition of the roads between the cities. So, you plan to repair them,
but you cannot afford to spend a lot of money.
The country can be represented as a (N+1) x (M+1) grid,
where Country(i, j) is a city.
The cost of repairing a road between (i, j) and (i + 1, j) is A[i].
The cost of repairing a road between (i, j) and (i, j + 1) is B[j].
Return the minimum cost of repairing the roads such that
all cities can be visited from city indexed (0, 0).
As the cost can be large, return the cost modulo 10^9+7.

Problem Constraints
1 <= N, M <= 10^5
1 <= A[i], B[i] <= 10^3

Input Format:
The first argument will be an integer array, A, of size N.
The second argument will be an integer array, B, of size M.

Output Format:
Return an integer representing the minimum possible cost.

Example Input:
Input 1:
 A = [1, 1, 1]
 B = [1, 1, 2]

Input 2:
 A = [1, 2, 3]
 B = [4, 5, 6]

Example Output:
Output 1: 16
Output 2: 39

Example Explanation:
Explanation 1:
 The minimum cost will be 16 if we repair the roads in the following way:
 Repair the roads from the all cities in row 0 to row 1 i.e. (0, j) to (1, j) (0 <= j <= 3),
 so that the cost will be 4 (A[0] * 4).
 Repair the roads from the all cities in row 1 to row 2 i.e. (1, j) to (2, j) (0 <= j <= 3),
 so that the cost will be 4 (A[1] * 4).
 Repair the roads from the all cities in row 2 to row 3 i.e. (2, j) to (3, j) (0 <= j <= 3),
 so that the cost will be 4 (A[2] * 4).
 Repair the roads (0, 0) to (0, 1), (0, 1) to (0, 2), (0, 2) to (0, 3),
 so that the cost will be B[0] + B[1] + B[2] = 4.
 Total cost will be 16.

Explanation 2:
 The minimum possible cost will be 39.
 */
public class DamagedRoads {
    public static void main(String[] args) {
        ArrayList<Integer> A = new ArrayList<>(Arrays.asList(1, 2, 3));
        ArrayList<Integer> B = new ArrayList<>(Arrays.asList(4, 5, 6));

        int res = minCostToRepairRoads(A, A.size(), B, B.size());
        System.out.println(res);
        // Time O((N + M) log(N + M));
        // Space O(N + M);
    }

    public static class Road {
        public int type;
        public int cost;

        public Road(int type, int cost) {
            this.type = type;
            this.cost = cost;
        }
    }

    public static int minCostToRepairRoads(ArrayList<Integer> A, int n, ArrayList<Integer> B, int m) {
        int MOD = 1_000_000_007;
        int verticalRoad = 0;
        int horizontalRoad = 1;

        PriorityQueue<Road> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a.cost, b.cost));

        for (int cost : A) {
            minHeap.offer(new Road(verticalRoad, cost));
        }

        for (int cost : B) {
            minHeap.offer(new Road(horizontalRoad, cost));
        }

        long totalCost = 0;
        int rows = n + 1;
        int cols = m + 1;

        while (!minHeap.isEmpty()) {
            Road road = minHeap.poll();

            int type = road.type;
            int cost = road.cost;

            if (type == verticalRoad) {
                totalCost = (totalCost + ((long) cost * cols) % MOD) % MOD;
                rows--;
            } else {
                totalCost = (totalCost + ((long) cost * rows) % MOD) % MOD;
                cols--;
            }

        }

        return (int) totalCost;
    }
}
