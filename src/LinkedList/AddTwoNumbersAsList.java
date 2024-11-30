package LinkedList;

import java.util.List;

/*
Problem Description:
You are given two linked lists, A and B, representing two non-negative numbers.
The digits are stored in reverse order, and each of their nodes contains a single digit.
Add the two numbers and return it as a linked list.

Problem Constraints
1 <= |A|, |B| <= 10^5

Input Format:
The first argument of input contains a pointer to the head of linked list A.
The second argument of input contains a pointer to the head of linked list B.

Output Format:
Return a pointer to the head of the required linked list.

Example Input:
Input 1:
 A = [2, 4, 3]
 B = [5, 6, 4]
Input 2:
 A = [9, 9]
 B = [1]

Example Output:
Output 1: [7, 0, 8]
Output 2: [0, 0, 1]

Example Explanation:
Explanation 1: A = 342 and B = 465. A + B = 807.
Explanation 2: A = 99 and B = 1. A + B = 100
 */
public class AddTwoNumbersAsList {
    public static void main(String[] args) {
        int[] a = {2, 4, 3};
        int[] b = {5, 6, 4};

        ListNode A = new ListNode(a[0]).createListNode(a);
        ListNode B = new ListNode(b[0]).createListNode(b);

        ListNode res = sumList(A, B);
        res.printList();
        // Time O(N);
        // Space O(1);
    }

    public static  ListNode sumList(ListNode headA, ListNode headB){
        ListNode head = new ListNode(0);
        ListNode current = head;
        int carry = 0;

        while(headA != null || headB != null || carry > 0){
            int valA = headA == null ? 0 : headA.val;
            int valB = headB == null ? 0 : headB.val;

            int sum = valA + valB + carry;
            carry = sum / 10;
            current.next = new ListNode(sum%10);

            current = current.next;
            if (headA != null) headA = headA.next;
            if(headB != null) headB = headB.next;
        }

        return head.next;
    }
}
