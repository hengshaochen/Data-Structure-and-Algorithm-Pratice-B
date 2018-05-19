/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode oddEvenList(ListNode head) {
        if (head == null) {
            return null;
        }
        
        ListNode oddHead = head, evenHead = head.next, cur = head;
        
        while (cur.next != null && cur.next.next != null) {
            ListNode curNext = cur.next;
            
            // 修改結構
            cur.next = (cur.next).next;
            curNext.next = (curNext.next).next;
            
            // 移動cur pointer
            cur = cur.next;
        }
        
        // 把odd的尾端接上evenHead
        cur.next = evenHead;
        
        return head;
    }
}