/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    int maxCount = 1;
    TreeNode ans, pre;
    int curCount = 1;
    public int[] findMode(TreeNode root) {
        if (root == null) {
            return new int[0];
        }
        ans = root;
        pre = null;
        List<Integer> ans = new ArrayList<>();
        
        helper(root, ans);
        int[] ansArray = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            ansArray[i] = ans.get(i);
        }
        
        return ansArray;
    }
    
    // inorder
    public void helper(TreeNode cur, List<Integer> ans) {
        if (cur == null) {
            return;
        }
        helper(cur.left, ans);
        
        // 當前層要處理的事情
        if (pre != null && pre.val == cur.val) {
            curCount++;
        }
        if (pre == null || pre.val != cur.val) {
            curCount = 1;
        }
        
        if (curCount > maxCount) {
            maxCount = curCount;
            ans.clear();
            ans.add(cur.val);
        } else if (curCount == maxCount) {
            ans.add(cur.val);
        }
        pre = cur;
        
        helper(cur.right, ans);
    }
}