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
    int ans = 0;
    public int longestConsecutive(TreeNode root) {
        if (root == null) {
            return ans;
        }
        dfs(root, 1, null);
        return ans;
    }
    
    // 定義：求當前node的LCS
    public void dfs(TreeNode cur, int curLCS, TreeNode fatherNode) {
        // base case
        if (cur == null) {
            return;
        }
        
        // preorder的方式，先做當層要做的工作再傳遞給兒子
        // 當層工作: 看當前節點.val - 1 是否等於fatherNode.val, 如果是代表LCS可以+1
        if (fatherNode != null && ((cur.val - 1) == fatherNode.val)) {
            curLCS += 1;
        } else {
            curLCS = 1;
        }
        // update ans
        ans = Math.max(ans, curLCS);
        
        // 傳給兒子
        dfs(cur.left, curLCS, cur);
        dfs(cur.right, curLCS, cur);
    }
}