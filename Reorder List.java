// 143. Reorder Level, Time: O(n) Space: O(n)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public void reorderList(ListNode head) {
        if (head == null) {
            return;
        }
        ListNode ori = head;
        ListNode curTail = head;
        
        Map<ListNode, ListNode> map = new HashMap<>();
        ListNode cur = head, pre = null;
        int len = 0;
        while (cur != null) {
            if (pre != null) {
                map.put(cur, pre);
            }
            pre = cur;
            cur = cur.next;
            len++;
        }
        len /= 2;
        
        ListNode tail = pre;
        while (len > 0) {
            ListNode temp = head.next;
            head.next = tail;
            curTail = tail;
            head = temp;
            tail = map.get(tail);
            curTail.next = head;
            len--;
        }
        
        head.next = null;
    }
}

// Time: O(n) Space:O(1)

