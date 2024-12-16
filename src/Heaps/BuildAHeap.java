package Heaps;

import javax.swing.plaf.synth.SynthUI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/*
Problem Description:
Given an array A of N integers, convert that array into a min heap and return the array.
NOTE: A min heap is a binary tree where every node has a value less than or equal to its children.

Problem Constraints
1 ≤ N ≤ 10^5
0 ≤ A[i] ≤ 10^9

Input Format:
First and only argument of input contains a single integer array A of length N.

Output Format:
Return the reordered array A such that it forms a min heap.

Example Input:
Input: A = [5, 13, -2, 11, 27, 31, 0, 19]

Example Output:
Output: A = [-2, 5, 0, 13, 11, 19, 27, 31]

Example Explanation

One possible Heap is

                -2
               /    \
             5       0
            / \    /  \
          13  11  19   27
          /
        31

It can be seen that each parent has a value smaller than its children. Hence it is a Valid Heap.

The Heap in the Array format is [-2, 5, 0, 13, 11, 19, 27, 31].

Some more possible heaps are  [-2, 0, 5, 13, 11, 27, 19, 31], [-2, 5, 0, 11, 27, 13, 19, 31], etc.
You can return any possible Valid Heap Structure.
 */
public class BuildAHeap {
    public static void main(String[] args) {
        int[] A = {5, 13, -2, 11, 27, 31, 0, 19};

//        ArrayList<Integer> res = bruteForce(A);
//        System.out.println(res);
        // Time O(NlogN);
        // Space O(1);

        int[] ans = optimized(A, A.length);
        // Iterative
            // Time O(log N);
            // Space O(1);
        // Recursive
            // Time O(log N);
            // Space O(log N);

    }

    public static int[] bruteForce(int[] A) {
        Arrays.sort(A);
        return A;
    }

    public static int[] optimized(int[] A, int n) {
        for (int i = n / 2 - 1; i >= 0; i--) {
//            heapify(A, n, i);
            heapifyRec(A, n, i);
        }
        return A;
    }

    public static void heapify(int[] heap, int n, int i) {
        while ((2 * i) + 1 < n) {
            int left = (2 * i) + 1;
            int right = (2 * i) + 2;
            int min = i;

            if (left < n && heap[left] < heap[min]) {
                min = left;
            }

            if (right < n && heap[right] < heap[min]) {
                min = right;
            }

            if (min == i) break;

            swap(heap, i, min);
            i = min;
        }
    }

    public static void heapifyRec(int[] heap, int n, int i) {
        int left = (2 * i) + 1;
        int right = (2 * i) + 2;
        int min = i;

        if (left < n && heap[left] < heap[min]) {
            min = left;
        }

        if (right < n && heap[right] < heap[min]) {
            min = right;
        }

        if (min != i) {
            swap(heap, i, min);
            heapifyRec(heap, n, min);
        }
    }

    public static void swap(int[] A, int x, int y) {
        int temp = A[x];
        A[x] = A[y];
        A[y] = temp;
    }
}
