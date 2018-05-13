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
    public int findBottomLeftValue(TreeNode root) {
        if (root == null) {
            return -1;
        }
        
        Queue<TreeNode> q = new LinkedList<>();
        int ans = -1;
        q.add(root);
        
        while (!q.isEmpty()) {
            TreeNode cur = q.remove();
            if (cur.right != null) {
                q.add(cur.right);
            }
            if (cur.left != null) {
                q.add(cur.left);
            }
            
            ans = cur.val;
        }
        
        return ans;
    }
}