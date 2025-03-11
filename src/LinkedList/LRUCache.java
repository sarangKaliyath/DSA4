package LinkedList;

import java.util.HashMap;

public class LRUCache {

    public static  void main (String [] args){
        // Time O(1);
        // Space O(N);
    }
    class DoublyListNode {
        int val;
        int key;
        DoublyListNode prev = null;
        DoublyListNode next = null;

        DoublyListNode (int key, int val){
            this.val = val;
            this.key = key;
            this.prev = null;
            this.next = null;
        }
    }

    private DoublyListNode head = new DoublyListNode(0, 0);
    private DoublyListNode tail = new DoublyListNode(0, 0);
    private HashMap<Integer, DoublyListNode> hm = new HashMap<>();
    private int memoryLimit = 0;

    public LRUCache(int capacity) {
        head.next = tail;
        tail.prev = head;
        memoryLimit = capacity;
    }

    public int get(int key) {
        if(!hm.containsKey(key)){
            return -1;
        }
        else {
            DoublyListNode currentNode = hm.get(key);
            delete(currentNode);
            insertBack(currentNode, tail);
            return currentNode.val;
        }
    }

    public void set(int key, int value) {
        if(hm.containsKey(key)){
            DoublyListNode currentNode = hm.get(key);
            delete(currentNode);
            insertBack(currentNode, tail);
            currentNode.val = value;
        }
        else {
            if(hm.size() == memoryLimit){
                DoublyListNode currentNode = head.next;
                delete(currentNode);
                hm.remove(currentNode.key);
            }

            DoublyListNode newNode = new DoublyListNode(key, value);
            insertBack(newNode, tail);
            hm.put(key, newNode);
        }
    }

    public void delete (DoublyListNode head){
        DoublyListNode prev = head.prev;
        DoublyListNode next = head.next;
        
        prev.next = next;
        next.prev = prev;

        // Severing head from list for garbage collection;
        head.next = null;
        head.prev = null;
    }

    public void insertBack (DoublyListNode node, DoublyListNode tail){
        node.next = tail;
        node.prev = tail.prev;
        tail.prev.next = node;
        tail.prev = node;
    }
}
