/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || k == 0) {
            return head;
        }
        
        // 計算長度
        ListNode cur = head;
        int listLength = 0;
        while (cur != null) {
            listLength += 1;
            cur = cur.next;
        }
        
        int numsNeedRotate = 0;
        if (listLength > k) {
            // Case1: 長度 > k
            numsNeedRotate = k;
        } else if (listLength < k) {
            // Case2: 長度 < k
            numsNeedRotate = k % listLength;
        } else {
            // Case3: 長度 = k
            return head;
        }
        
        // 特判，如果不需要Rotate直接return, 防止這種case: [1] k = 99
        if (numsNeedRotate == 0) {
            return head;
        }
        
        // 把從尾部數過來，numsNeedRotate數量的node放到前面
        int diff = listLength - numsNeedRotate - 1;
        cur = head;
        for (int i = 0; i < diff; i++) {
            cur = cur.next;
        }
        
        ListNode newHead = cur.next;
        cur.next = null;
        
        cur = newHead;
        for (int i = 0; i < numsNeedRotate - 1; i++) {
            cur = cur.next;
        }
        cur.next = head;
        
        return newHead;
    }
}