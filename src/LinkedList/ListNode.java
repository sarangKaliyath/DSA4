package LinkedList;

import java.util.List;

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

    public ListNode createListNode(int [] arr){
        ListNode head = this;
        ListNode copy = head;
        for(int i = 1; i < arr.length; i++){
            copy.next = new ListNode(arr[i]);
            copy = copy.next;
        }
        return head;
    }
}
