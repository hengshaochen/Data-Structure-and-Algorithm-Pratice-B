// 我的版本
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        ListNode pre = dummy;
        
        // 很蠢的特判，我這種寫法，當鏈結串列長度為1會完全沒接上去
        if (head != null && head.next == null) {
            return head;
        }
        
        while (head != null && head.next != null) {
            ListNode temp = head.next.next;
            ListNode temp2 = head.next;
            head.next.next = head;
            head.next = temp;
            pre.next = temp2;
            
            pre = head;
            head = head.next;
        }
            
            
        
        return dummy.next;
    }
}