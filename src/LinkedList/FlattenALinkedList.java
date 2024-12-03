package LinkedList;

/*
Problem Description:
Given a linked list where every node represents a linked list and contains two pointers of its type:
    Pointer to next node in the main list (right pointer)
    Pointer to a linked list where this node is head (down pointer). All linked lists are sorted.
You are asked to flatten the linked list into a single list. Use down pointer to link nodes of the flattened list. The flattened linked list should also be sorted.

Problem Constraints:
1 <= Total nodes in the list <= 100000
1 <= Value of node <= 109

Input Format:
The only argument given is head pointer of the doubly linked list.

Output Format:
Return the head pointer of the Flattened list.

Example Input:
Input 1:
   3 -> 4 -> 20 -> 20 ->30
   |    |    |     |    |
   7    11   22    20   31
   |               |    |
   7               28   39
   |               |
   8               39

Input 2:
   2 -> 4
   |    |
   7    11
   |
   7

Example Output:
Output 1:
 3 -> 4 -> 7 -> 7 -> 8 -> 11 -> 20 -> 20 -> 20 -> 22 -> 28 -> 30 -> 31 -> 39 -> 39
Output 2:
 2 -> 4 -> 7 -> 7 -> 11

Example Explanation:
Explanation 1:
 The return linked list is the flatten sorted list
 */
public class FlattenALinkedList {
    static class Node {
        int val;
        Node next;
        Node down;

        Node(int val) {
            this.val = val;
            this.next = null;
            this.down = null;
        }
    }

    public static Node flattenList(Node head) {
        if (head == null || head.next == null) {
            return head;
        }

        head.next = flattenList(head.next);

        head = merge(head, head.next);

        return head;
    }

    private static Node merge(Node a, Node b) {
        if (a == null) {
            return b;
        }
        if (b == null) {
            return a;
        }

        Node result;
        if (a.val < b.val) {
            result = a;
            result.down = merge(a.down, b);
        } else {
            result = b;
            result.down = merge(a, b.down);
        }

        result.next = null;
        return result;
    }

    public static void main(String[] args) {
        MultiLevelListNode listCreator = new MultiLevelListNode();

        Object[] arr = {
                3, new Object[]{7, new Object[]{7, new Object[]{8}}},
                4, new Object[]{11},
                20, new Object[]{22, new Object[]{28, new Object[]{39}}},
                20, new Object[]{20},
                30, new Object[]{31, new Object[]{39}}
        };

        Node head = listCreator.createListFromArray(arr);
        System.out.println("Original Multi-Level List:");
        listCreator.printList(head);

        Node flattenedHead = flattenList(head);
        System.out.println("\nFlattened Sorted List:");
        printFlattenedList(flattenedHead);
        // Time O(N + M);
        // Space O(Max number of levels);
    }

    private static void printFlattenedList(Node head) {
        Node current = head;
        while (current != null) {
            System.out.print(current.val + " -> ");
            current = current.down;
        }
        System.out.println("null");
    }
}
