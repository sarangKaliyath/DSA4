package LinkedList;

public class ListNode {
     ListNode next = null;
    int val;

    public ListNode(int x) {
        this.val = x;
        this.next = null;
    }

    public void printList(){
        ListNode head = this;
        while(head != null){
            System.out.print(head.val + " -> ");
            head = head.next;
        }
        System.out.print("null");
    }
}
