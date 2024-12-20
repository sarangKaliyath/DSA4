package Heaps;

public class ListNode {
    public int val;
    public ListNode next;

    ListNode(int val) {
        this.val = val;
        this.next = null;
    }

    public void printList() {
        ListNode head = this;
        while (head != null) {
            System.out.print(head.val + " -> ");
            head = head.next;
        }
        System.out.print("null");
        System.out.println();
    }

    public ListNode createListNode(int[] arr) {
        ListNode head = this;
        ListNode copy = head;
        for (int i = 1; i < arr.length; i++) {
            copy.next = new ListNode(arr[i]);
            copy = copy.next;
        }
        return head;
    }
}
