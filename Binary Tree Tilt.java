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
    public int findTilt(TreeNode root) {
        helper(root);
        return ans;
    }
    public int helper(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftSum = helper(root.left);
        int rightSum = helper(root.right);
        ans = ans + Math.abs(leftSum - rightSum);
        return leftSum + rightSum + root.val;
    }
}