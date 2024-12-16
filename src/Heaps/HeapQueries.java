package Heaps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

/*
Problem Description:
You have an empty min heap.
You are given an array A consisting of N queries.
Let P denote A[i][0] and Q denote A[i][1].

There are two types of queries:
P = 1, Q = -1 : Pop the minimum element from the heap.
P = 2, 1 <= Q <= 10^9 : Insert Q into the heap.

Return an integer array containing the answer for all the extract min operation.
If the size of heap is 0, then extract min should return -1.

Problem Constraints
1 <= N <= 10^5
1 <= A[i][0] <= 2
1 <= A[i][1] <= 10^9 or A[i][1] = -1

Input Format:
The only argument A is a 2D integer array

Output Format:
Return an integer array

Example Input:
Input 1: A = [[1, -1], [2, 2], [2, 1], [1, -1]]
Input 2: A = [[2, 5], [2, 3], [2, 1], [1, -1], [1, -1]]

Example Output:
Output 1: [-1, 1]
Output 2: [1, 3]

Example Explanation

Explanation 1:
For the first extract operation the heap is empty so it gives -1.
For the second extract operation the heap contains the elements 2 and 1.
Extract min returns the element 1.

Explanation 2:
The heap contains the elements 5, 3 and 1.
The first extract min operation gets the element 1 and the second operation gets the element 3.
 */
public class HeapQueries {
    public static void main(String[] args) {
        int[][] A = {{1, -1}, {2, 2}, {2, 1}, {1, -1}};
        ArrayList<Integer> res = inbuildHeap(A, A.length);
        System.out.println(res);
        // Time O(logN);
        // Space O(logN);

        int[] ans = manualImplementation(A, A.length);
        for (int i = 0; i < ans.length; i++) System.out.print(ans[i] + " ");
        // Time O(logN);
        // Space O(logN);
    }

    public static int[] manualImplementation(int[][] arr, int n) {
        int[] heap = new int[n];
        int heapSize = 0;
        int[] res = new int[n];
        int resIdx = 0;

        for (int i = 0; i < n; i++) {
            int P = arr[i][0];
            int Q = arr[i][1];

            if (P == 2) {
                insertQ(heap, heapSize, Q);
                heapSize++;
            } else {
                if (heapSize == 0) {
                    res[resIdx++] = -1;

                } else {
                    res[resIdx++] = getMin(heap, heapSize);
                    heapSize--;
                }
            }
        }
        return Arrays.copyOf(res, resIdx);
    }

    public static void insertQ(int[] heap, int heapSize, int val) {
        heap[heapSize] = val;
        int curr = heapSize;

        while (curr > 0) {
            int parent = (curr - 1) / 2;
            if (heap[parent] > heap[curr]) {
                swap(heap, parent, curr);
                curr = parent;
            } else break;
        }
    }

    public static int getMin(int[] heap, int heapSize) {
        int min = heap[0];
        heap[0] = heap[heapSize - 1];

        int curr = 0;
        while (true) {
            int n = heapSize - 1;
            int left = (2 * curr) + 1;
            int right = (2 * curr) + 2;
            int smallest = curr;

            if (left < n && heap[left] < heap[smallest]) {
                smallest = left;
            }
            if (right < n && heap[right] < heap[smallest]) {
                smallest = right;
            }

            if (smallest == curr) break;

            swap(heap, curr, smallest);
            curr = smallest;
        }

        return min;
    }

    public static void swap(int[] arr, int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }

    public static ArrayList<Integer> inbuildHeap(int[][] arr, int n) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int P = arr[i][0];
            int Q = arr[i][1];

            if (P == 2 && Q >= 0) minHeap.add(Q);
            else {
                if (minHeap.isEmpty()) res.add(-1);

                else res.add(minHeap.poll());
            }
        }
        return res;
    }
}
