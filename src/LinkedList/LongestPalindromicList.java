package LinkedList;

/*
Problem Description
Given a linked list of integers. Find and return the length of the longest palindrome list that exists in that linked list.
A palindrome list is a list that reads the same backward and forward.
Expected memory complexity: O(1)

Problem Constraints:
1 <= length of the linked list <= 2000
1 <= Node value <= 100

Input Format:
The only argument given is head pointer of the linked list.

Output Format:
Return the length of the longest palindrome list.

Example Input:
Input 1: 2 -> 3 -> 3 -> 3
Input 2: 2 -> 1 -> 2 -> 1 ->  2 -> 2 -> 1 -> 3 -> 2 -> 2

Example Output:
Output 1: 3
Output 2: 5

Example Explanation:
Explanation 1: 3 -> 3 -> 3 is largest palindromic sublist
Explanation 2: 2 -> 1 -> 2 -> 1 -> 2 is largest palindromic sublist.

 */
public class LongestPalindromicList {
    public static  void main (String [] args){
        int [] arr = {2 , 1 , 2 , 1 ,  2 , 2 , 1 , 3 , 2 , 2};
        ListNode A = new ListNode(arr[0]);
        ListNode copy = A;
        for(int i = 1; i < arr.length; i++){
            copy.next = new ListNode(arr[i]);
            copy = copy.next;
        }

        int res = findMaxLength(A);
        System.out.println(res);
        // Time O(N^2);
        // Space O(1);
    }

    public static int findMaxLength(ListNode head){
        if(head == null) return 0;
        ListNode prev = null;
        ListNode current = head;
        int maxLen = 0;

        while(current != null){
            ListNode next = current.next;

            int oddLen = possibleLen(prev, next, 1);
            maxLen = Math.max(maxLen, oddLen);

            int evenLen = possibleLen(prev, current, 0);
            maxLen = Math.max(evenLen, maxLen);

            current.next = prev;
            prev = current;
            current = next;
        }
        return maxLen;
    }

    public static int possibleLen(ListNode left, ListNode right, int len){
        while (left != null && right != null){
            if(left.val != right.val) return len;
            len+= 2;
            left = left.next;
            right = right.next;
        }
        return  len;
    }
}
