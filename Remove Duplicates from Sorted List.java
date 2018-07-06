/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        
        ListNode pre = head;
        ListNode cur = head.next;
        
        while (cur != null) {
            if (pre.val == cur.val) {
                pre.next = cur.next;
                cur = cur.next;
            } else {
                pre = cur;
                cur = cur.next;
            }
        }
        return head;
    }
}

// II:
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        head = dummy;
        
        while (head.next != null && head.next.next != null) {
            if (head.next.val == head.next.next.val) {
                // 設當次while的head.next為firstDuplicateVal
                int firstDuplicateVal = head.next.val;
                
                // 用while跳過所有此次數字的duplicate
                while (head.next != null && head.next.val == firstDuplicateVal) {
                    // 相當於直接切斷重複的node的ptr
                    head.next = head.next.next;
                }
            } else {
                head = head.next;
            }
        }
        return dummy.next;
    }
}