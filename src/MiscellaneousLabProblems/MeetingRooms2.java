package MiscellaneousLabProblems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/*
Problem Description
You are given an array B of meeting time intervals where each interval is represented as [start, end] (with start < end). You need to find the minimum number of conference rooms required to host all the meetings.

Problem Constraints
1 <= B.length <= 10^4
0 <= start < end <= 10^6

Input Format
The first line contains an integer n, the number of meetings.
The next n lines each contain two space-separated integers start and end representing the start and end times of the meetings.

Output Format
Output a single integer representing the minimum number of conference rooms required.

Example Input:
Input 1:
A = 3
B = [ [0, 30],
      [5, 10],
      [15, 20] ]

Input 2:
A = 1
B = [ [0, 1] ]

Example Output:
Output 1: 2
Output 2: 1

Example Explanation:
Explanation 1:
We need two rooms:
Room 1: [0, 30]
Room 2: [5, 10], [15, 20]

Explanation 2:
Since there is only one meeting, we need one room

 */
public class MeetingRooms2 {

    public static void main(String[] args) {
        int A = 3;
        ArrayList<ArrayList<Integer>> B = new ArrayList<>(Arrays.asList(
                new ArrayList<>(Arrays.asList(0, 30)),
                new ArrayList<>(Arrays.asList(5, 10)),
                new ArrayList<>(Arrays.asList(15, 20))
        ));

        int res = find(A, B);
        System.out.println(res);
        // Time O(AlogA);
        // Space O(A);
    }

    public static int find(int A, ArrayList<ArrayList<Integer>> B) {
        ArrayList<Integer> start = new ArrayList<>();
        ArrayList<Integer> end = new ArrayList<>();

        for (ArrayList<Integer> integers : B) {
            start.add(integers.get(0));
            end.add(integers.get(1));
        }

        Collections.sort(start);
        Collections.sort(end);

        int i = 0;
        int j = 0;
        int n = start.size();
        int count = 0;
        int max = 0;

        while (i < n) {

            if (start.get(i) < end.get(j)) {
                count++;
                i++;
            } else {
                count--;
                j++;
            }

            max = Math.max(max, count);
        }

        return max;
    }
}
