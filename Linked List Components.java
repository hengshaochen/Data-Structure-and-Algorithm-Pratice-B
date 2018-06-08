/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public int numComponents(ListNode head, int[] G) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < G.length; i++) {
            set.add(G[i]);
        }
        
        int ans = 0;
        boolean continuous = false;
        ListNode cur = head;
        while (cur != null) {
            if (set.contains(cur.val)) {
                if (continuous == false) {
                    continuous = true;
                }
            } else {
                // 發現中斷點，把ans++
                if (continuous == true) {
                    ans += 1;
                    continuous = false;
                }
            }
            cur = cur.next;
        }
        
        // 要補上最後，如果最後continuous還是true，要把ans+1
        if (continuous == true) {
            ans += 1;
        }
        return ans;
    }
}