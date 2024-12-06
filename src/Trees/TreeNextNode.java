package Trees;

public class TreeNextNode {

    int val;
    TreeNextNode left, right, next = null;

    TreeNextNode(int val) {
        this.val = val;
        this.left = this.right = this.next = null;
    }
}
