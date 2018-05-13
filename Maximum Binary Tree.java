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
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        if (nums == null) {
            return null;
        }
        
        return helper(nums, 0, nums.length - 1);
    }
    
    public TreeNode helper(int[] nums, int left, int right) {
        // base case
        if (left > right) {
            return null;
        }
        
        // 當層做的事情
        // 1. 找MAX O(n)
        int MAX = Integer.MIN_VALUE;
        int MAX_IDX = -1;
        for (int i = left; i <= right; i++) {
            if (nums[i] > MAX) {
                MAX = nums[i];
                MAX_IDX = i;
            }
        }
        
        // 2. 當前的MAX變成此次問題的Root
        TreeNode curRoot = new TreeNode(MAX);
        
        // 3. 切子問題
        curRoot.left = helper(nums, left, MAX_IDX - 1);
        curRoot.right = helper(nums, MAX_IDX + 1, right);
        
        
        // 4. 回傳
        return curRoot;
    }
}