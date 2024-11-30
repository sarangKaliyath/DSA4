package LinkedList;

import java.util.List;

/*
Problem Description
Given a linked list A, swap every two adjacent nodes and return its head.
NOTE: Your algorithm should use only constant space.
You may not modify the values in the list; only nodes themselves can be changed.

Problem Constraints
1 <= |A| <= 10^6

Input Format:
The first and the only argument of input contains a pointer to the head of the given linked list.

Output Format:
Return a pointer to the head of the modified linked list.

Example Input:
Input 1: A = 1 -> 2 -> 3 -> 4
Input 2: A = 7 -> 2 -> 1

Example Output:
Output 1: 2 -> 1 -> 4 -> 3
Output 2: 2 -> 7 -> 1

Example Explanation:
Explanation 1:
 In the first example (1, 2) and (3, 4) are the adjacent nodes. Swapping them will result in 2 -> 1 -> 4 -> 3
Explanation 2:
 In the second example, 3rd element i.e. 1 does not have an adjacent node, so it won't be swapped.
 */
public class SwapLIstNodeInPairs {

    public static  void main (String [] args){
        int [] arr = {1, 2, 3, 4};
        ListNode A = new ListNode(arr[0]);
        ListNode copy = A;
        for(int i = 1; i < arr.length; i++){
            copy.next = new ListNode(arr[i]);
            copy = copy.next;
        }
        ListNode res = swapPairs(A);
        res.printList();
        // Time O(N);
        // Space O(1);
    }

    public static ListNode swapPairs(ListNode head){
        if(head == null || head.next == null) return  head;

        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode prev = dummyHead;

        while(head != null && head.next != null){
            ListNode nodeA = head;
            ListNode nodeB = head.next;

            prev.next = nodeB;
            nodeA.next = nodeB.next;
            nodeB.next = nodeA;

            prev = nodeA;
            head = nodeA.next;
        }
        return  dummyHead.next;
    }
}
