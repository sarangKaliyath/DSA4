package LinkedList;

public class ListNode {
     ListNode next = null;
    int val;

    public ListNode(int x) {
        this.val = x;
        this.next = null;
    }

    public void printList(ListNode head){
        if(head == null) System.out.println("null");
        ListNode copy = head;
        while(copy != null){
            System.out.print(copy.val + " -> ");
            copy = copy.next;
        }
        System.out.print("null");
    }
}
