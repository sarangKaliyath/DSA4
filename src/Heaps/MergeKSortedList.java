package Heaps;

/*
Problem Description
Given a list containing head pointers of N sorted linked lists.
Merge these given sorted linked lists and return them as one sorted list.

Problem Constraints:
1 <= total number of elements in given linked lists <= 100000

Input Format:
The first and only argument is a list containing N head pointers.

Output Format:
Return a pointer to the head of the sorted linked list after merging all the given linked lists.

Example Input:

Input 1:
 1 -> 10 -> 20
 4 -> 11 -> 13
 3 -> 8 -> 9

Input 2:
 10 -> 12
 13
 5 -> 6

Example Output:
Output 1:
 1 -> 3 -> 4 -> 8 -> 9 -> 10 -> 11 -> 13 -> 20

Output 2:
 5 -> 6 -> 10 -> 12 ->13

Example Explanation:
Explanation 1:
 The resulting sorted Linked List formed after merging is 1 -> 3 -> 4 -> 8 -> 9 -> 10 -> 11 -> 13 -> 20.

Explanation 2:
 The resulting sorted Linked List formed after merging is 5 -> 6 -> 10 -> 12 ->13.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class MergeKSortedList {

    public static void main(String[] args) {
        ArrayList<ListNode> A = new ArrayList<>();

        int[] arr1 = {1, 10, 20};
        ListNode list1 = new ListNode(arr1[0]);
        A.add(list1.createListNode(arr1));

        int[] arr2 = {4, 11, 13};
        ListNode list2 = new ListNode(arr2[0]);
        A.add(list2.createListNode(arr2));

        int[] arr3 = {3, 8, 9};
        ListNode list3 = new ListNode(arr3[0]);
        A.add(list3.createListNode(arr3));

        ListNode res = sort(A);
        res.printList();
    }

    public static ListNode sort(ArrayList<ListNode> A) {

        PriorityQueue<ListNode> minH = new PriorityQueue<>((x, y) -> x.val - y.val);

        for (ListNode el : A) {
            if (el != null) minH.add(el);
        }

        ListNode head = new ListNode(-1);
        ListNode current = head;

        while (!minH.isEmpty()) {
            ListNode minNode = minH.poll();
            if (minNode.next != null) minH.add(minNode.next);
            current.next = minNode;
            current = current.next;
        }

        return head.next;
    }
}
