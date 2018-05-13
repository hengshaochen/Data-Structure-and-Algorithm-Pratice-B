// 法1: 超時
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
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        return countNodes(root.left) + countNodes(root.right) + 1;
    }
}

// 法2:
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
    public int countNodes(TreeNode root) {
        int left = leftDepth(root);
        int right = rightDepth(root);
        if (left == right) {
        	// 寫法2: (1 << left) - 1 ，把1往左移動left位
            return (int)Math.pow( (int)2, (int)left) - 1;
        } else {
            return countNodes(root.left) + countNodes(root.right) + 1;
        }
    }
    
    public int leftDepth(TreeNode root) {
        int ans = 0;
        while (root != null) {
            ans++;
            root = root.left;
        }
        return ans;
    }
    
    public int rightDepth(TreeNode root) {
        int ans = 0;
        while (root != null) {
            ans++;
            root = root.right;
        }
        return ans;
    }
}