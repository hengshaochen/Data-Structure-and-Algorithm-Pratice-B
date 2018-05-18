/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        
        // Step1: 找中點並把linkedlist變成兩半
        ListNode slow = head, fast = head.next;
        // slow會變成mid, slow.next是list2的開頭
        while (fast != null && fast.next != null) {
            slow = slow.next;
            //prev = slow;
            fast = fast.next.next;
        }
        
        // prev跟slow相同位置，但必須把prev.next變成null，才會讓list1有結束點，不然會連到list2。
        ListNode l2 = slow.next;
        slow.next = null;   // 雖然l2指向slow.next, 這行把slow.next變成null, 但不會影響到原本l2
        
        // Step2: 個別sort兩個list(要開一個新的ListNode pointer來接收排序好的list head)
        ListNode sortedL1 = sortList(head);
        ListNode sortedL2 = sortList(l2);
        
        // Step3: 合併兩個sorted-list
        return merge(sortedL1, sortedL2);
    }
    
    public ListNode merge(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        
        if (l1 != null) {
            cur.next = l1;
        }
        if (l2 != null) {
            cur.next = l2;
        }
        
        return dummy.next;
    }
}