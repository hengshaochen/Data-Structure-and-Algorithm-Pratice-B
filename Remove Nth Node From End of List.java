/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode slow = head, fast = head, pre = dummy;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        
        while (fast != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next;
        }
        
        pre.next = slow.next;
        
        return dummy.next;
    }
}