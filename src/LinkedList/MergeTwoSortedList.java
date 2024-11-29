package LinkedList;

import java.util.List;

/*
Problem Description
Merge two sorted linked lists, A and B, and return it as a new list.
The new list should be made by splicing together the nodes of the first two lists and should also be sorted.

Problem Constraints
0 <= |A|, |B| <= 10^5

Input Format
The first argument of input contains a pointer to the head of linked list A.
The second argument of input contains a pointer to the head of linked list B.

Output Format:
Return a pointer to the head of the merged linked list.

Example Input
Input 1:
 A = 5 -> 8 -> 20
 B = 4 -> 11 -> 15
Input 2:
 A = 1 -> 2 -> 3
 B = Null

Example Output
Output 1:
 4 -> 5 -> 8 -> 11 -> 15 -> 20
Output 2:
 1 -> 2 -> 3

Example Explanation:
Explanation 1:
 Merging A and B will result in 4 -> 5 -> 8 -> 11 -> 15 -> 20
Explanation 2:
 We don't need to merge as B is empty.
 */
public class MergeTwoSortedList {

    public static void main(String[] args) {
        int[] A = {5, 8, 20};
        int[] B = {4, 11, 15};

        ListNode headA = new ListNode(5);
        ListNode headARunner = headA;
        for (int i = 1; i < A.length; i++) {
            headARunner.next = new ListNode(A[i]);
            headARunner = headARunner.next;
        }

        ListNode headB = new ListNode(4);
        ListNode headBRunner = headB;
        for (int i = 1; i < B.length; i++) {
            headBRunner.next = new ListNode(B[i]);
            headBRunner = headBRunner.next;
        }

        ListNode ans = merge(headA, headB);
        ListNode copy = ans;
        while (copy != null) {
            System.out.print(copy.val + " -> ");
            copy = copy.next;
        }
        // Time O(A.length + B.length);
        // Space O(1);
    }

    public static ListNode merge(ListNode A, ListNode B) {
        if (A == null) return B;
        if (B == null) return A;

        ListNode head = null;

        if (A.val <= B.val) {
            head = A;
            A = A.next;
        } else {
            head = B;
            B = B.next;
        }

        ListNode current = head;

        while (A != null && B != null) {
            if (A.val <= B.val) {
                current.next = A;
                A = A.next;
            } else {
                current.next = B;
                B = B.next;
            }
            current = current.next;
        }

        if (A == null) current.next = B;
        else current.next = A;

        return head;
    }
}
