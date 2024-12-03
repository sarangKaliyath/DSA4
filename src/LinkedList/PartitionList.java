package LinkedList;

/*
Problem Description
Given a linked list A and a value B,
partition it such that all nodes less than B come before nodes greater than or equal to B.
You should preserve the original relative order of the nodes in each of the two partitions.

Problem Constraints
1 <= |A| <= 10^6
1 <= A[i], B <= 10^9

Input Format:
The first argument of input contains a pointer to the head to the given linked list.
The second argument of input contains an integer, B.

Output Format:
Return a pointer to the head of the modified linked list.

Example Input:
Input 1:
A = [1, 4, 3, 2, 5, 2]
B = 3

Input 2:
A = [1, 2, 3, 1, 3]
B = 2

Example Output:
Output 1: [1, 2, 2, 4, 3, 5]
Output 2: [1, 1, 2, 3, 3]

Example Explanation:
Explanation 1:
 [1, 2, 2] are less than B wheread [4, 3, 5] are greater than or equal to B.
Explanation 2:
 [1, 1] are less than B wheread [2, 3, 3] are greater than or equal to B.

 */
public class PartitionList {

    public static void main(String[] args) {
        int[] arr = {1, 4, 3, 2, 5, 2};
        ListNode A = new ListNode(arr[0]);
        ListNode copy = A;
        for (int i = 1; i < arr.length; i++) {
            copy.next = new ListNode(arr[i]);
            copy = copy.next;
        }
        int B = 3;
        ListNode res = find(A, B);
        res.printList();
        // Time O(N);
        // Space O(1);
    }

    public static  ListNode find(ListNode head, int B){
        ListNode copy = head;
        ListNode dummyA = new ListNode(0);
        ListNode dACopy = dummyA;
        ListNode dummyB = new ListNode(0);
        ListNode dBCopy = dummyB;

        while(copy != null){
            if(copy.val < B){
                dACopy.next = copy;
                dACopy = dACopy.next;
            }
            else {
                dBCopy.next = copy;
                dBCopy = dBCopy.next;
            }
            copy = copy.next;
        }
        dACopy.next = null;
        dBCopy.next = null;

        dACopy.next = dummyB.next;

        return dummyA.next;
    }
}
