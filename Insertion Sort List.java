/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode insertionSortList(ListNode head) {
        ListNode dummy = new ListNode(0);
        ListNode curSorted = dummy, curOrigin = head;
        
        while (curOrigin != null) {
            
            // 找到插入點
            while (curSorted.next != null 
                    && curOrigin.val > curSorted.next.val) {
                curSorted = curSorted.next;
            }
            ListNode temp = curSorted.next;
            curSorted.next = curOrigin;
            curOrigin = curOrigin.next;
            curSorted = curSorted.next;
            curSorted.next = temp;
            
            // 把curSorted放回linked-list頭
            curSorted = dummy;
        }
        
        return dummy.next;
    }
}