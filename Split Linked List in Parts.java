/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode[] splitListToParts(ListNode root, int k) {
        ListNode[] ans = new ListNode[k];
        // 求長度
        int len = 0;
        ListNode cur = root;
        while (cur != null) {
            len += 1;
            cur = cur.next;
        }
        
        int base = len / k;
        int remain = len % k;
        int[] numEachGroup = new int[k];
        for (int i = 0; i < k; i++) {
            numEachGroup[i] = base;
            if (remain > 0) {
                numEachGroup[i]++;
                remain--;
            }
        }
        
        // 把cur歸回起點
        cur = root;
        for (int i = 0; i < numEachGroup.length; i++) {
            // 如果當前的起點已經是null, 則可以直接結束, 代表後面的linkedlist都為空了
            // 例如這個case: [1,2,3] k = 5, 答案是[[1], [2], [3], [], []]
            // 這時程式算出的numEachGroup = [1,1,1,0,0] , 後面兩個都是0, 已經沒剩下點可以放了, 直接break
            // 沒有加這行判斷後面會null pointer exception, 因為已經是null的又要指向null
            if (cur == null) {
                break;
            }
            int curNum = numEachGroup[i];
            ans[i] = cur;
            
            // 走curNum - 1會走到當前group的最後一個node
            for (int j = 0; j < curNum - 1; j++) {
                cur = cur.next;
            }
            // 走到當前group最後一個node後，
            // 要走到下個group，然後把cur.next設為null
            ListNode next = cur.next;
            cur.next = null;
            cur = next;
        }
        
        return ans;
    }
}