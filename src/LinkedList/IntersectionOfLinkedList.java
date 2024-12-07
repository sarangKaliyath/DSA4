package LinkedList;

/*
Problem Description:
Write a program to find the node at which the intersection of two singly linked lists,
A and B, begins. For example, the following two linked lists:

A:          a1 → a2
                   ↘
                     c1 → c2 → c3
                   ↗
B:     b1 → b2 → b3

NOTE:
    If the two linked lists have no intersection at all, return null.
    The linked lists must retain their original structure after the function returns.
    You may assume there are no cycles anywhere in the entire linked structure.
    Your code should preferably run in O(n) time and use only O(1) memory.
    The custom input to be given is different than the one explained in the examples. Please be careful.

Problem Constraints
0 <= |A|, |B| <= 10^6

Input Format:
The first argument of input contains a pointer to the head of the linked list A.
The second argument of input contains a pointer to the head of the linked list B.

Output Format:
Return a pointer to the node after which the linked list is intersecting.

Example Input:
Input 1:
 A = [1, 2, 3, 4, 5]
 B = [6, 3, 4, 5]

Input 2:
 A = [1, 2, 3]
 B = [4, 5]

Example Output:
Output 1: [3, 4, 5]
Output 2: []

Example Explanation

Explanation 1:
 In the first example, the nodes have the same values after 3rd node in A and 2nd node in B.
 Thus, the linked lists are intersecting after that point.

Explanation 2:
 In the second example, the nodes don't have the same values, thus we can return None/Null.
 */
public class IntersectionOfLinkedList {
    public static void main(String[] args) {
        int[] arrA = {1, 2, 3, 4, 5};
        int[] arrB = {6, 3, 4, 5};

        ListNode B = null;
        ListNode copyB = B;
        int pos = 2;

        for (int i = 0; i < pos - 1; i++) {
            if (copyB == null) {
                copyB = new ListNode(arrB[i]);
                B = copyB;
            } else {
                copyB.next = new ListNode(arrB[i]);
                copyB = copyB.next;
            }
        }

        ListNode A = new ListNode(arrA[0]);
        ListNode copyA = A;
        for (int i = 1; i < arrA.length; i++) {
            copyA.next = new ListNode(arrA[i]);
            copyA = copyA.next;
            pos--;
            if (pos == 0) {
                copyB.next = copyA;
            }
        }

        ListNode res = findCommonGrounds(A, B);
        res.printList();
        // Time O(N);
        // Space O(1);
    }

    public static ListNode findCommonGrounds(ListNode A, ListNode B) {
        int lenA = getLength(A);
        int lenB = getLength(B);


        ListNode longerList = lenA > lenB ? A : B;
        ListNode shorterList = lenA > lenB ? B : A;


        int diff = Math.abs(lenA - lenB);

        while (diff > 0) {
            longerList = longerList.next;
            diff--;
        }


        while (longerList != null && shorterList != null) {
            if (longerList == shorterList) return longerList;
            longerList = longerList.next;
            shorterList = shorterList.next;
        }

        return null;
    }


    public static int getLength(ListNode head) {
        int len = 0;
        ListNode current = head;
        while (current != null) {
            current = current.next;
            len++;
        }
        return len;
    }

}
