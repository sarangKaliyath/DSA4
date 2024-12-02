package LinkedList;

import java.util.List;

/*
Problem Description
Given a singly linked list A, determine if it's a palindrome.
Return 1 or 0, denoting if it's a palindrome or not, respectively.

Problem Constraints
1 <= |A| <= 10^5

Input Format:
The first and the only argument of input contains a pointer to the head of the given linked list.

Output Format:
Return 0, if the linked list is not a palindrome.
Return 1, if the linked list is a palindrome.

Example Input:
Input 1: A = [1, 2, 2, 1]
Input 2: A = [1, 3, 2]

Example Output:
Output 1: 1
Output 2: 0

Example Explanation:
Explanation 1:
 The first linked list is a palindrome as [1, 2, 2, 1] is equal to its reversed form.
Explanation 2:
 The second linked list is not a palindrom as [1, 3, 2] is not equal to [2, 3, 1].

 */
public class PalindromeList {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        ListNode A = new ListNode(arr[0]);
        ListNode copy = A;
        for (int i = 1; i < arr.length; i++) {
            copy.next = new ListNode(arr[i]);
            copy = copy.next;
        }

        int res = bruteForce(A);
        System.out.println(res);
        // Time O(N);
        // Space O(N);

        int ans = optimized(A);
        System.out.println(ans);
        // Time O(N);
        // Space O(1);
    }

    public static  int optimized (ListNode head){
        ListNode current = head;
        ListNode middleNode = findMiddleNode(head);
        ListNode reversedHalf = reverseList(middleNode);

        ListNode curCopy = current;
        ListNode revCopy = reversedHalf;

        while(revCopy != null){
            if(curCopy.val != revCopy.val) return  0;
            curCopy = curCopy.next;
            revCopy = revCopy.next;
        }
        // Need to undo the reverse list and merge with original;
        return 1;
    }

    public static ListNode findMiddleNode(ListNode head){
        if(head == null || head.next == null) return head;
        ListNode slow = head;
        ListNode fast = head;

        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
    public static int bruteForce(ListNode head) {
        ListNode current = head;
        ListNode copyList = null, tail = null;

        while (current != null) {
            if (copyList == null) {
                copyList = new ListNode(current.val);
                tail = copyList;
            } else {
                tail.next = new ListNode(current.val);
                tail = tail.next;
            }
            current = current.next;
        }

        current = head;
        ListNode revList = reverseList(copyList);

        while (current != null) {
            if (current.val != revList.val) return 0;
            current = current.next;
            revList = revList.next;
        }
        return 1;
    }

    public static ListNode reverseList(ListNode head) {
        ListNode current = head;
        ListNode prev = null;
        while (current != null) {
            ListNode next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        return prev;
    }
}
