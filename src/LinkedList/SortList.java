package LinkedList;

import java.util.List;

/*
Problem Description:
Sort a linked list, A in O(n log n) time.

Problem Constraints
0 <= |A| = 10^5

Input Format
The first and the only argument of input contains a pointer to the head of the linked list, A.

Output Format:
Return a pointer to the head of the sorted linked list.

Example Input:
Input 1: A = [3, 4, 2, 8]
Input 2: A = [1]

Example Output:
Output 1: [2, 3, 4, 8]
Output 2: [1]

Example Explanation:
Explanation 1:
 The sorted form of [3, 4, 2, 8] is [2, 3, 4, 8].

Explanation 2:
 The sorted form of [1] is [1].

 */
public class SortList {
    public static void main(String[] args) {
        int[] a = {3, 4, 2, 8};
        ListNode A = new ListNode(3);

        ListNode copy = A;
        for (int i = 1; i < a.length; i++) {
            copy.next = new ListNode(a[i]);
            copy = copy.next;
        }

        ListNode res = mergeSort(A);
        printList(res);
        // Time O(NlogN);
        // Space O(1);
    }

    public static ListNode mergeSort(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode mid = findMid(head);

        ListNode headA = head;
        ListNode headB = mid.next;
        mid.next = null;

        headA = mergeSort(headA);
        headB= mergeSort(headB);

        return mergeSortedList(headA, headB);
    }

    public static ListNode mergeSortedList(ListNode headA, ListNode headB) {
        if (headA == null) return headB;
        if (headB == null) return headA;

        ListNode head = null;
        if (headA.val <= headB.val) {
            head = headA;
            headA = headA.next;
        } else {
            head = headB;
            headB = headB.next;
        }

        ListNode current = head;

        while (headA != null && headB != null) {
            if (headA.val <= headB.val) {
                current.next = headA;
                headA = headA.next;
            } else {
                current.next = headB;
                headB = headB.next;
            }
            current = current.next;
        }

        if(headA != null) current.next = headA;
        if(headB != null) current.next = headB;

        return head;
    }

    public static ListNode findMid(ListNode head) {
        if (head == null) return null;
        ListNode slow = head;
        ListNode fast = head;

        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;
    }

    public static  void printList(ListNode head){
        ListNode copy = head;
        while(copy != null){
            System.out.print(copy.val + " -> ");
            copy = copy.next;
        }
    }
}
