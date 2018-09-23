// INORDER 
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
    TreeNode pre;
    int min = Integer.MAX_VALUE;
    public int getMinimumDifference(TreeNode root) {
        
        helper(root);
        return min;
    }
    
    public void helper(TreeNode root) {
        if (root == null) {
            return;
        }
        // 看到BST --> 想到走inorder
        helper(root.left);
        if (pre != null) {
            min = Math.min(min, Math.abs(root.val - pre.val));
        }
        pre = root;
        helper(root.right);
    }
}

// BUG
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
    public int getMinimumDifference(TreeNode root) {
        if (root == null) {
            return Integer.MAX_VALUE;
        }
        int left = Integer.MAX_VALUE, right = Integer.MAX_VALUE;
        int curDiff = Integer.MAX_VALUE;
        if (root.left != null) {
            left = getMinimumDifference(root.left);
            curDiff = Math.abs(root.val - root.left.val);
        }
        if (root.right != null) {
            right = getMinimumDifference(root.right);
            curDiff = Math.min(curDiff, Math.abs(root.val - root.right.val));
        }
        
        return Math.min(curDiff, Math.min(left, right));
    }
}