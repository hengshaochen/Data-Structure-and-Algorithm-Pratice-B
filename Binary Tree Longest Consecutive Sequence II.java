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
class returnType {
    int increaseLCA, decreaseLCA;
    public returnType(int increaseLCA, int decreaseLCA) {
        this.increaseLCA = increaseLCA;
        this.decreaseLCA = decreaseLCA;
    }
}
    int ans = 0;
    public int longestConsecutive(TreeNode root) {
        if (root == null) {
            return ans;
        }
        postOrder(root);
        return ans;
    }
    
    public returnType postOrder(TreeNode cur) {
        if (cur == null) {
            return new returnType(0, 0);
        }
        int increaseLCA = 0;
        int decreaseLCA = 0;
        
        // 先走左右兒子
        returnType l = postOrder(cur.left);
        returnType r = postOrder(cur.right);
        
        // 處理當前節點
        // 先處理decrease
        if (cur.left != null && cur.val == cur.left.val - 1) {
            decreaseLCA = Math.max(decreaseLCA, l.decreaseLCA + 1);
        }
        if (cur.right != null && cur.val == cur.right.val - 1) {
            decreaseLCA = Math.max(decreaseLCA, r.decreaseLCA + 1);
        }
        // 處理increase
        if (cur.left != null && cur.val == cur.left.val + 1) {
            increaseLCA = Math.max(increaseLCA, l.increaseLCA + 1);
        }
        if (cur.right != null && cur.val == cur.right.val + 1) {
            increaseLCA = Math.max(increaseLCA, r.increaseLCA + 1);
        }
        
        int curAns = increaseLCA + 1 + decreaseLCA;
        ans = Math.max(ans, curAns);
        return new returnType(increaseLCA, decreaseLCA);
    }
}