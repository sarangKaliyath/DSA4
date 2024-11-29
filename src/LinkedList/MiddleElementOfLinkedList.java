package LinkedList;

import java.util.List;

/*
Problem Description
Given a linked list of integers, find and return the middle element of the linked list.
NOTE: If there are N nodes in the linked list and N is even then return the (N/2 + 1)th element.

Problem Constraints
1 <= length of the linked list <= 100000
1 <= Node value <= 10^9

Input Format:
The only argument given head pointer of linked list.

Output Format
Return the middle element of the linked list.

Example Input:
Input 1: 1 -> 2 -> 3 -> 4 -> 5
Input 2: 1 -> 5 -> 6 -> 2 -> 3 -> 4

Example Output:
Output 1: 3
Output 2: 2

Example Explanation:
Explanation 1:
 The middle element is 3.

Explanation 2:
 The middle element in even length linked list of length N is ((N/2) + 1)th element which is 2.
 */
public class MiddleElementOfLinkedList {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        ListNode A = new ListNode(1);
        ListNode copy = A;
        for (int i = 1; i < arr.length; i++) {
            copy.next = new ListNode(arr[i]);
            copy = copy.next;
        }

        ListNode res = bruteForce(A);
        System.out.println(res.val);
        // Time O(N);
        // Space O(1);

        ListNode ans = optimized(A);
        System.out.println(ans.val);
        // Time O(N);
        // Space O(1);
    }

    public static ListNode optimized(ListNode head) {
        if (head == null) return null;
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static ListNode bruteForce(ListNode head) {
        if (head == null) return null;

        ListNode current = head;
        int len = 0;

        while (current != null) {
            current = current.next;
            len++;
        }
        current = head;

        int mid = len >> 1;
        int i = 0;
        while (i != mid) {
            current = current.next;
            i++;
        }
        return current;
    }
}
