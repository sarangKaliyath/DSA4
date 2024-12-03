package LinkedList;

class MultiLevelListNode {

    public FlattenALinkedList.Node createListFromArray(Object[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }

        FlattenALinkedList.Node head = null;
        FlattenALinkedList.Node current = null;

        for (Object obj : arr) {
            if (obj instanceof Integer) {
                FlattenALinkedList.Node newNode = new FlattenALinkedList.Node((int) obj);
                if (head == null) {
                    head = newNode;
                    current = head;
                } else {
                    current.next = newNode;
                    current = current.next;
                }
            } else if (obj instanceof Object[]) {
                if (current != null) {
                    current.down = createListFromArray((Object[]) obj);
                }
            }
        }

        return head;
    }

    // Helper method to print the multi-level list (for debugging)
    public void printList(FlattenALinkedList.Node head) {
        FlattenALinkedList.Node row = head;
        while (row != null) {
            FlattenALinkedList.Node col = row;
            while (col != null) {
                System.out.print(col.val + " -> ");
                col = col.down;
            }
            System.out.println("null");
            row = row.next;
        }
    }
}
